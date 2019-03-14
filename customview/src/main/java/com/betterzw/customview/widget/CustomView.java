package com.betterzw.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhengwu on 8/20/18.
 */
public class CustomView extends View {

    public static final String TAG = CustomView.class.getSimpleName();

    private Context mContext;

    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Paint mLinePaint;

    public CustomView(Context context) {
        super(context);

        mContext = context;

        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        init();
    }

    public void init() {

//        mWidth = mContext.getResources().getDisplayMetrics().widthPixels;
//        mHeight = mContext.getResources().getDisplayMetrics().heightPixels;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d(TAG, "onSizeChanged: " + w + "," + h);

//        mWidth = w;
//        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        Log.d(TAG, "onMeasure: " + mWidth + "," + mHeight);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawLine(canvas);
        drawMutiRect(canvas);
        drawTwoCirlce(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    public void drawLine(Canvas canvas){
        canvas.translate(mWidth/2, mHeight/2);


        Path path = new Path();
        path.lineTo(100, 100);
        path.lineTo(100, 0);
        path.close();
        canvas.drawPath(path, mPaint);


    }

    public void drawMutiRect(Canvas canvas){

        canvas.save();

        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域
        for (int i=0; i<=20; i++)
        {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect,mPaint);
        }

        // getSaveCount 最小值为1,表示初始的状态
        Log.d(TAG, "drawMutiRect: "+canvas.getSaveCount());
        canvas.restore();

        Log.d(TAG, "drawMutiRect: "+canvas.getSaveCount());
    }

    public void drawTwoCirlce(Canvas canvas){

//        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0, 0, 400, mPaint);
        canvas.drawCircle(0, 0, 380, mPaint);

        for (int i=0; i<360; i+=10){
            canvas.drawLine(0, 380, 0, 400, mPaint);
            canvas.rotate(10);//旋转当前画布
        }
    }
}
