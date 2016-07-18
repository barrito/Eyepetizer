package com.armxyitao.eyepetizer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;

/**
 * @author 熊亦涛
 * @time 16/7/17  12:45
 * @desc ${TODD}
 */
public class WebViewActivity extends Activity {
    private WebView mWebView;
    private ImageView mBackIv;
    private TextView mTitleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url = getIntent().getStringExtra("url");
        initView();
        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitleTv.setText(title);
            }
        });
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mBackIv = (ImageView) findViewById(R.id.iv_bacak);
        mTitleTv = (TextView) findViewById(R.id.tv_title);

    }
}
