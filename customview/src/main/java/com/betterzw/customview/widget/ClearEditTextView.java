package com.betterzw.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.betterzw.customview.R;


public class ClearEditTextView extends LinearLayout implements View.OnTouchListener, View.OnClickListener, View.OnFocusChangeListener, TextWatcher {
    String hintText = "";
    int hintTextColor;
    int inputTextLength;
    float hintTextSize;
    public EditText clearEditText;
    private TextView tvError;
    private Context context;
    private Drawable mClearTextIcon;
    private View line;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;
    private Drawable rghtDrawableIcon;
    private ImageView ivClose;
    private String emailHint;//通过这个来判断是否为email输入框
    private boolean isRightSeleted;
    private boolean singleLine;
    private int inputType;
    public boolean iconVisible = true;//只要用来控制icon强制显示与否

    public ClearEditTextView(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public ClearEditTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initConfigs(attrs);
        init(context);

    }

    public ClearEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initConfigs(attrs);
        init(context);
    }

    private void initConfigs(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AddressInput);
        hintText = a.getString(R.styleable.AddressInput_inputHintText);
        hintTextSize = a.getDimension(R.styleable.AddressInput_inputHintTextSize, 0);
        inputTextLength = a.getInteger(R.styleable.AddressInput_inputTextLength, 250);
        singleLine = a.getBoolean(R.styleable.AddressInput_inputSingleLine, false);
        inputType = a.getInt(R.styleable.AddressInput_inputTextType, 0);
        a.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.edit_text_layout, this);
        clearEditText = findViewById(R.id.edt);
        tvError = findViewById(R.id.tv_error);
        line = findViewById(R.id.line);
        ivClose = findViewById(R.id.iv_close);
        ivClose.setOnClickListener(this);
        clearEditText.setOnFocusChangeListener(this);
        clearEditText.addTextChangedListener(this);


        initView();
    }

    private void initView() {
        if (!TextUtils.isEmpty(hintText) && hintText.contains(":") && hintText.length() >= 1)//匹配已经在使用的字段，但是又是用了"："号的字段，进行截取赋值
            hintText = hintText.indexOf(":") == 0 ? hintText.substring(1, hintText.length()) : hintText.substring(0, hintText.length() - 1);
        clearEditText.setHint(hintText);
        clearEditText.setHintTextColor(hintTextColor);
        clearEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(inputTextLength)});//限制editext的最大长度
        clearEditText.setSingleLine(singleLine);
        clearEditText.addTextChangedListener(this);
        if (inputType == 1)//email
            clearEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        if (inputType == 2)//number
            clearEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        if (inputType == 3)//password
            clearEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    /**
     * 设置最右边的图标，包括清理图标和向右选择图标
     *
     * @param iconId         图标的ID
     * @param visible        图标是否显示
     * @param isRightSeleted 是否是右击选择
     */
    public void setRightIcon(int iconId, boolean visible, boolean isRightSeleted) {
        this.isRightSeleted = isRightSeleted;
        ivClose.setImageResource(iconId);
        setClearIconVisible(visible);
        clearEditText.setFocusable(!isRightSeleted);
        clearEditText.setFocusableInTouchMode(!isRightSeleted);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                if (!isRightSeleted) {
                    clearEditText.setText("");
                    clearEditText.setError(null);
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {


    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }

    public void setClearIconVisible(final boolean visible) {
        ivClose.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    /**
     * 设置默认显示的词语
     *
     * @param text
     */
    public void setDefaultText(String text) {
        if (clearEditText != null) {
            clearEditText.setText(text);
        }
    }

    /**
     * 获取editext的输入值
     *
     * @return
     */
    public String getInputText() {
        if (clearEditText != null)
            return clearEditText.getText().toString().trim();
        else return "";
    }

    /**
     * 设置服务器Hint值
     *
     * @param content
     */
    public void setServerHintText(String content) {
        if (clearEditText != null && !TextUtils.isEmpty(content))
            clearEditText.setHint(content);

    }



    /**
     * @param selection
     */
    public void setEdtSelection(int selection) {
        if (clearEditText != null) {
            clearEditText.setSelection(selection);
        }
    }

    /**
     * 改变Editext的编辑状态
     */
    public void setEdtWritable(boolean writable) {
        if (clearEditText != null) {
            clearEditText.setFocusable(writable);
            clearEditText.setFocusableInTouchMode(writable);
        }
    }

    /**
     * 设置icon的展示与否
     *
     * @param iconVisible
     */
    public void setIconVisible(boolean iconVisible) {
        this.iconVisible = iconVisible;
        clearEditText.setPadding(0, 0, 0, 0);
    }

    /**
     * 让Edittext获取到焦点
     */
    public void edtRequstFocus() {
        if (clearEditText != null) {
            clearEditText.setFocusable(true);
            clearEditText.setFocusableInTouchMode(true);
            clearEditText.requestFocus();
        }
    }
}