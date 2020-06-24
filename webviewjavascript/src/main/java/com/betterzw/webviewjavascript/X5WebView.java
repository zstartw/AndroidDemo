package com.betterzw.webviewjavascript;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

public class X5WebView extends WebView {

    public static final String TAG = "webview";
    public   float scrollY = 0;

    public static final int SCROLL_UP = 1;
    public static final int SCROLL_DOWN = 2;

    private int scrollDirect;

    private boolean scrollBottom = false;

    private int innerWebViewTop = -1;
    private int webViewScrollDirect = 0;
    private int webViewScrollDistance = 0;

    public X5WebView(Context context) {
        super(context);

        initWebViewSettings();
    }

    public X5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initWebViewSettings();
    }

    public X5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initWebViewSettings();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(900, View.MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int i, int i1, int i2, int i3) {
        super.onSizeChanged(i, i1, i2, i3);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    //    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WeSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    public void setScrollDirect(int direct){
        scrollDirect = direct;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        float exactContentHeight = (float) Math.floor(getContentHeight() * getScale());
////		getScrollY() >= (exactContentHeight -getHeight());
//        Log.d(TAG, "contentHeight:"+exactContentHeight+",getScrollY:"+getScrollY()+",off:"+
//        (exactContentHeight - getHeight()));
//
//        scrollY = getScaleY();
//
//
//        int[] location = new int[2];
////        WebViewActivity.bottomView.getLocationOnScreen(location);
//
//
//        int[] webViewScreenLocation = new int[2];
//        getLocationOnScreen(webViewScreenLocation);
//
//
//        //由ScrollView处理滑动事件
//        if (webViewScreenLocation[1] >= 270 || innerWebViewTop == 0 || scrollBottom){
//            getParent().requestDisallowInterceptTouchEvent(false);
//
//            innerWebViewTop = -1;
//            scrollBottom = false;
//            return true;
//        }
//
//
//
//        Log.d(TAG, "webView Y:"+webViewScreenLocation[1]);
//
//        Log.d(TAG, "====="+location[1]);
//        Log.d(TAG, "direct："+ scrollDirect);
//
////        if (webViewScrollDirect == SCROLL_DOWN){
////            getParent().requestDisallowInterceptTouchEvent(false);
////            return super.dispatchTouchEvent(ev);
////        }
//
//        boolean intercept = true;
//
//        if (scrollDirect == SCROLL_UP){
//            Log.d(TAG, "location y"+ location[1]);
//            if (location[1] < 2200){
//                intercept = true;
//            }
//        }
//
//        getParent().requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "onTouchEvent: action:down");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "onTouchEvent: action:move"+",distance="+event.getY());
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "onTouchEvent: action:up");
//
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//
//        innerWebViewTop = t;
//
//        if (webViewScrollDistance > 0){
//
//            if (webViewScrollDistance - t < 0){
//                webViewScrollDirect = SCROLL_DOWN;
//            }else {
//                webViewScrollDirect = SCROLL_UP;
//            }
//        }
//
//        webViewScrollDistance = t;
//
//        int height = (int) Math.floor(getContentHeight() * getScale());
//        int webViewHeight = getHeight();
//        int cutoff = height - webViewHeight; // Don't be too strict on the cutoff point
//        if (t >= cutoff) {
////            setDisclaimerButtonEnabled(true);
//            scrollBottom = true;
//        }
//
//        Log.d(TAG, "scroll changed: "+l+",t:"+t+",oldl:"+oldl+",oldt:"+oldt+",cutoff:"+cutoff);
//    }

//    @Override
//    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
//                                   int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//
//        final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
//                scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
//
//        // Does all of the hard work...
////			OverscrollHelper.overScrollBy(PullToRefreshWebView.this, deltaX, scrollX, deltaY, scrollY,
////					getScrollRange(), OVERSCROLL_FUZZY_THRESHOLD, OVERSCROLL_SCALE_FACTOR, isTouchEvent);
//
//        return returnValue;
//    }
}
