package com.betterzw.webviewjavascript;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


public class MainActivity extends AppCompatActivity {

    private X5WebView webview;
    private Button button_loadurl;
    private Button button_evaluatejs;

    private Button showWebView;

    private boolean isPageFinished;

    private final static String test_url = "http://community.test.file.mediportal.com.cn/dd3ec722c7a94739ae7ff85494869a04";
//    private final static String test_url = "http://community.test.file.mediportal.com.cn/80404382b8344fb4951894ad8dcfb86d";
    private final static String WEBVIEW_JS_TAG = "jstag://";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isPageFinished = false;

        findView();
        initWebview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void findView() {
        webview = findViewById(R.id.mywebview);
        showWebView = findViewById(R.id.show_webview_bt);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webview.setNestedScrollingEnabled(true);
//        }

        webview.setVisibility(View.GONE);

        showWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebView.setVisibility(View.GONE);
                webview.setVisibility(View.VISIBLE);
            }
        });
//        button_loadurl = (Button) findViewById(R.id.button_loadurl);
//        button_evaluatejs = (Button) findViewById(R.id.button_evaluatejs);
    }


//    onInterceptTouchEvent
//
    private void initWebview() {

        // 初始化webview
//        WebSettings webSettings = webview.getSettings();
//        webSettings.setJavaScriptEnabled(true);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }


//        webSettings.setBlockNetworkImage(false);//不阻塞网络图片
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //允许混合（http，https）
//            //websettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
//        }

        webview.addJavascriptInterface(this, "App");
        webview.loadUrl(test_url);

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String s) {

                webview.loadUrl("javascript:App.resize(document.body.scrollHeight)");

                super.onPageFinished(webView, s);


            }
        });


//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();//接受所有网站的证书
//            }
//        });

//        webview.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });


//        webSettings.setDisplayZoomControls(false);
//        // 在android版本号低于17时可能会有安全性的问题
//        // 详情：https://developer.android.com/reference/android/webkit/WebView.html#addJavascriptInterface(java.lang.Object, java.lang.String)
//        webview.addJavascriptInterface(new JsInterface(), "androidtag");
//        webview.setWebChromeClient(new WebChromeClient() {});
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // 如果发现跳转链接是以 WEBVIEW_JS_TAG 开头的，拦截这次跳转链接并将这个链接当做字符串进行处理
//                if (url.startsWith(WEBVIEW_JS_TAG)) {
//                    handleJsNativeCall(url);
//                    return true;
//                }
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                isPageFinished = true;
//            }
//        });
//        webview.loadUrl(test_url);


/*
        String html = "<!DOCTYPE html><html>\n" +
                "<body style='margin:0px;padding:0px;'>\n" +
                "    <script type='text/javascript' src='http://www.youtube.com/iframe_api'>\n" +
                "    </script>\n" +
                "    <script type='text/javascript'>\n" +
                "        function onYouTubeIframeAPIReady(){\n" +
                "            ytplayer=new YT.Player('playerId', {\n" +
                "                events: {\n" +
                "                    onReady:onPlayerReady\n" +
                "                }\n" +
                "            })\n" +
                "        }\n" +
                "        function onPlayerReady(a){\n" +
                "            a.target.playVideo();\n" +
                "        }\n" +
                "    </script>\n" +
                "    <iframe id='playerId' type='text/html' width=100%% height=50%% src='http://www.youtube.com/embed/47yJ2XCRLZs?enablejsapi=1&rel=0&playsinline=0&autoplay=1' frameborder='0' allowfullscreen>\n" +
                "</body>\n" +
                "</html>";
        webview.loadData(html, "text/html", "utf-8");*/

    }

//    private void initButton() {
//        button_loadurl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPageFinished) {
//                    webview.loadUrl("javascript:alertMessage(\"hello from java\")");
//                } else {
//                    Toast.makeText(getApplicationContext(), "网页还在加载中", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        button_evaluatejs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPageFinished) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        webview.evaluateJavascript("getResult(\"hahaha\")", new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String value) {
//                                Toast.makeText(getApplicationContext(), "接收到网页传来的字符串：" + value, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "网页还在加载中", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }

    private void handleJsNativeCall(String url) {
        String js_string = url.replaceFirst(WEBVIEW_JS_TAG, "");
        Toast.makeText(getApplicationContext(), "接收到网页传来的字符串：" + js_string, Toast.LENGTH_SHORT).show();
    }

    public class JsInterface {
        @JavascriptInterface
        public void showMessage(String js_string) {
            Toast.makeText(getApplicationContext(), "接收到网页传来的字符串：" + js_string, Toast.LENGTH_SHORT).show();
        }
    }

    @JavascriptInterface
    public void resize(final float height) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webview.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources()
                        .getDisplayMetrics().density)));
            }
        });
    }
}
