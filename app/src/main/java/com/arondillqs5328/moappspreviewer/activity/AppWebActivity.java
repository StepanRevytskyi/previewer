package com.arondillqs5328.moappspreviewer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.arondillqs5328.moappspreviewer.web_browser.MyBrowser;
import com.arondillqs5328.moappspreviewer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppWebActivity extends AppCompatActivity {

    @BindView(R.id.webview) WebView mWebView;

    public static String URL = "url";

    public static Intent newInstance(Context context, String url) {
        Intent intent = new Intent(context, AppWebActivity.class);
        intent.putExtra(URL, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_web);
        ButterKnife.bind(this);

        mWebView.setWebViewClient(new MyBrowser());
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.loadUrl(getIntent().getStringExtra(URL));

    }
}
