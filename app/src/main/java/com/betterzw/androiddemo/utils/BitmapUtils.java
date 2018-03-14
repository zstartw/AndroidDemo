package com.betterzw.androiddemo.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

/**
 *
 *
 *
 *  Bitmap bitmap = Bitmap.createBitmap(700, 450,Bitmap.Config.ARGB_8888);

 A：透明度
 R：红色
 G：绿
 B：蓝

 Bitmap.Config ARGB_4444：每个像素占四位，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位
 Bitmap.Config ARGB_8888：每个像素占四位，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位
 Bitmap.Config RGB_565：每个像素占四位，即R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位
 Bitmap.Config ALPHA_8：每个像素占四位，只有透明度，没有颜色。

 一般情况下我们都是使用的ARGB_8888，由此可知它是最占内存的，因为一个像素占32位，8位=1字节，所以一个像素占4字节的内存。假设有一张480x800的图片，如果格式为ARGB_8888，那么将会占用1500KB的内存。


 * Created by zhengwu on 3/14/18.
 */

public class BitmapUtils {

    /**
     * Get the size in bytes of a bitmap in a BitmapDrawable. Note that from Android 4.4 (KitKat)
     * onward this returns the allocated memory size of the bitmap which can be larger than the
     * actual bitmap data byte count (in the case it was re-used).
     *
     * @param value
     * @return size in bytes
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static int getBitmapSize(BitmapDrawable value) {
        Bitmap bitmap = value.getBitmap();

        // From KitKat onward use getAllocationByteCount() as allocated bytes can potentially be
        // larger than bitmap byte count.
       /* if (DeviceUtil.hasKitKat()) {
            return bitmap.getAllocationByteCount();
        }

        if (DeviceUtil.hasHoneycombMR1()) {
            return bitmap.getByteCount();
        }*/

        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * drawable id to bitmap
     * @param context
     * @param drawableId
     * @return
     */
    public Bitmap toBitmap(Context context, int drawableId){
        return BitmapFactory.decodeResource(context.getResources(), drawableId);
    }
}
