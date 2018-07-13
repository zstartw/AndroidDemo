package com.betterzw.androiddemo.utils;

import android.graphics.drawable.GradientDrawable;

/**
 * drawable 工具类
 * Created by zhengwu on 7/13/18.
 */
public class GBDrawableUtils {


    public static GradientDrawable getGradientDrawable(int fillColor, int roundRadius){
//        GBDrawableUtils.getGradientDrawable(Color.parseColor("#999999"), context.getResources().getDimensionPixelSize(R.dimen.dimen_2));

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(fillColor);
        drawable.setCornerRadius(roundRadius);
        return drawable;
    }
}
