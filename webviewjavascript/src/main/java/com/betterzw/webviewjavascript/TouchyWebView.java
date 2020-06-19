package com.betterzw.webviewjavascript;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.core.view.MotionEventCompat;

public class TouchyWebView extends WebView {
    public TouchyWebView(Context context) {
        super(context);
    }

    public TouchyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchyWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        clearView();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        Log.d("tag", "==========ontouch:"+event.getAction());
//        //Check is required to prevent crash
////        if (MotionEventCompat.findPointerIndex(event, 0) == -1) {
////            return super.onTouchEvent(event);
////        }
////
////        if (event.getPointerCount() >= 2) {
////            requestDisallowInterceptTouchEvent(true);
////        } else {
////            requestDisallowInterceptTouchEvent(false);
////        }
//
//        return super.onTouchEvent(event);
//    }

    float startx;
    float starty;
    float offsetx;
    float offsety;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                startx = event.getX();
//                starty = event.getY();
//                Log.e("MotionEvent", "webview按下");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e("MotionEvent", "webview滑动");
//                offsetx = Math.abs(event.getX() - startx);
//                offsety = Math.abs(event.getY() - starty);
////                if (offsetx < offsety) {
////                    getParent().requestDisallowInterceptTouchEvent(true);
////                    Log.e("MotionEvent", "屏蔽了父控件");
////                } else {
////                    getParent().requestDisallowInterceptTouchEvent(false);
////                    Log.e("MotionEvent", "事件传递给父控件");
////                }
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            default:
//                break;
//        }
//        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }


//    @Override
//    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
//        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
//
//        Log.d("tag", "==========onOverScrolled");
//        requestDisallowInterceptTouchEvent(true);
//    }
}
