package com.betterzw.webviewjavascript;


import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    public static final String TAG = "webview";

    public static TextView bottomView;
    float y0 = 0;
    float y1 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);


//        PullToRefreshWebView pullToRefreshWebView = findViewById(R.id.pull_container);
//        WebView webView = pullToRefreshWebView.getRefreshableView();

//        PullToRefreshScrollView scrollView = findViewById(R.id.pull_container);

        X5WebView webView = findViewById(R.id.webview);
//        bottomView = findViewById(R.id.bottom_text);

//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) webView.getLayoutParams();
//        params.height = getScreenHeight(this) ;

//        webView.setLayoutParams(params);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        /*解决图片不显示*/
        settings.setBlockNetworkImage(false);//不阻塞网络图片
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //允许混合（http，https）
//            //websettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//            settings.setMixedContentMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }


//        scrollView.getRefreshableView().setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        y0 = motionEvent.getY();
//                        if (y1 - y0 > 10) {
//                            webView.setScrollDirect(MyWebView.SCROLL_UP);
//                            Log.i(TAG, "++++++++++");
//                        } else if (y1 - y0 < -10) {
//                            webView.setScrollDirect(MyWebView.SCROLL_DOWN);
//                            Log.d(TAG, "------------");
//                        }
//                        y1 = motionEvent.getY();
//
//                        break;
//                }
//                return false;
//            }
//        });
//        scrollView.getRefreshableView().setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//            }
//        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String s) {
                Log.d(TAG, "page finished:"+s);
                super.onPageFinished(webView, s);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                Log.d(TAG, "shouldoverride url:"+s);
                return super.shouldOverrideUrlLoading(webView, s);
            }

            //            @Override
//            public void onPageFinished(WebView view, String url) {
//                int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//                int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//                //重新测量
//
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) webView.getLayoutParams();
//                params.height = 2000;
//
//                webView.setLayoutParams(params);
////                webView.measure(w, h);
////                super.onPageFinished(view, url);
//            }
        });


        webView.loadUrl("http://www.medcircle.cn/osteoprosisonline/detail/254?type=2");
//        webView.loadUrl("https://m.baidu.com");
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
