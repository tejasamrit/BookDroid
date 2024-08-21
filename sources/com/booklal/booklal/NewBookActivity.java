package com.booklal.booklal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewBookActivity extends AppCompatActivity {
    private String stringLat;
    private String stringLon;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    private ImageView toolbarBackButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_new_book);
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        this.toolbarBackButton = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        Toolbar toolbar2 = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        this.toolBarTitle.setText("BookLal");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewBookActivity.this.onBackPressed();
            }
        });
    }
}
