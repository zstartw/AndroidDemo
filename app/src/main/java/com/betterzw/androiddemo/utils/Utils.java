package com.betterzw.androiddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Locale;

/**
 * 一些重要的工具
 * Created by zhengwu on 3/13/18.
 */

public class Utils {
    /**
     * 获取app的versionName
     */
    public static String getVersionName(Context context) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 获取app的versionCode
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将px转换成dp值
     */
    public static int px2dp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }

    /**
     * 将dp转换成px值
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 将sp转换成px值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取app目前的内存占用情况
     */
    public static String getMemoryInfo() {
        String memoryInfo = "";
        try {
            Runtime info = Runtime.getRuntime();
            long freeSize = info.freeMemory();
            long totalSize = info.totalMemory();
            long maxSize = info.maxMemory();
            double freeMemory = freeSize / 1048576.0;
            double totalMemory = totalSize / 1048576.0;
            double maxMemory = maxSize / 1048576.0;
            String freeMemoryString = String.valueOf(freeMemory).substring(0, 5);
            String totalMemoryString = String.valueOf(totalMemory).substring(0, 5);
            String maxMemoryString = String.valueOf(maxMemory).substring(0, 5);

            memoryInfo = freeMemoryString + "M/" + totalMemoryString + "M(" + maxMemoryString + "M)";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memoryInfo;
    }

    /**
     * 粗略检查字符串是不是手机号
     */
    public static boolean checkPhoneNum(String phoneNum) {
        phoneNum = phoneNum.replace("-", "");
        phoneNum = phoneNum.replace("+86", "");
        phoneNum = phoneNum.replace(" ", "");

        if (phoneNum.matches(""))
            return false;
        if (!phoneNum.substring(0, 1).equals("1"))
            return false;
        if (phoneNum.length() != 11)
            return false;
        for (int i = phoneNum.length(); --i >= 0; ) {
            if (!Character.isDigit(phoneNum.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 将view转成bitmap
     */
    public static Bitmap convertViewToBitmap(View view) {
        // Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        // Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        // Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            // has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        // draw the view on the canvas
        view.draw(canvas);
        // return the bitmap
        return returnedBitmap;
    }


    /**
     * 判断触摸点是不是在view上
     */
    public static boolean isInView(View view, float rawX, float rawY) {
        int scrcoords[] = new int[2];
        view.getLocationOnScreen(scrcoords);
        float x = rawX + view.getLeft() - scrcoords[0];
        float y = rawY + view.getTop() - scrcoords[1];
        if (x >= view.getLeft() && x <= view.getRight() && y >= view.getTop() && y <= view.getBottom())
            return true;
        return false;
    }

    /**
     * 判断是不是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


    /**
     * 显示软键盘
     */
    public static void showSoftKeyBoard(Activity mActivity, EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftKeyBoard(Activity mActivity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


}
