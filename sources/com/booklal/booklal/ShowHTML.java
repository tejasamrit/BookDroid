package com.booklal.booklal;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ShowHTML extends AppCompatActivity {
    private ImageView backBtnToolbar;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    private WebView webView;
    private String which_page;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_show_h_t_m_l);
        this.which_page = getIntent().getStringExtra("page");
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (this.which_page.equals("about")) {
            this.toolBarTitle.setText("About The App");
            str = "file:///android_asset/aboutapp.html";
        } else if (this.which_page.equals("privacy")) {
            this.toolBarTitle.setText("Privacy policy");
            str = "file:///android_asset/policy.html";
        } else if (this.which_page.equals("bug")) {
            this.toolBarTitle.setText("Report a Bug");
            str = "file:///android_asset/bugreport.html";
        } else {
            str = "";
        }
        WebView webView2 = (WebView) findViewById(C0699R.C0702id.webview_page);
        this.webView = webView2;
        webView2.setWebViewClient(new WebViewClient());
        this.webView.loadUrl(str);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShowHTML.this.onBackPressed();
                ShowHTML.this.finish();
            }
        });
    }
}
