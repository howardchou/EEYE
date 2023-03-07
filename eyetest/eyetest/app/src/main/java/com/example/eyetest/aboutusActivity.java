package com.example.eyetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class aboutusActivity extends AppCompatActivity {

    ImageButton homebtn;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        initViews();
        initData();
        setListener();
    }

    private void initViews() {
        homebtn=findViewById(R.id.homebutton);
        webView = findViewById(R.id.wbv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://sites.google.com/g.yzu.edu.tw/yzumaker/index");
    }

    private void initData() {

    }

    private void setListener() {
        homebtn.setOnClickListener(new MyOnTouchListeners());
    }

    private final class MyOnTouchListeners implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(),MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }
}