package com.zy.mycsdn.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.mycsdn.R;

public class ContentByWebActivity extends AppCompatActivity {

    private final static String URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_by_web);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_content_by_web);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_content_by_web);
                progressBar.setVisibility(View.GONE);
                TextView textView = (TextView) findViewById(R.id.tv_hint);
                textView.setVisibility(View.VISIBLE);
                Toast.makeText(ContentByWebActivity.this, "内容加载不出来啊~", Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl(getIntent().getStringExtra(URL));
    }

    public static void navToContentByWebActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, ContentByWebActivity.class);
        intent.putExtra(URL, url);
        activity.startActivity(intent);
    }

}
