package com.booklal.booklal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BookAdditionSuccessful extends AppCompatActivity {
    /* access modifiers changed from: private */
    public String stringLat;
    /* access modifiers changed from: private */
    public String stringLon;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    private ImageView toolbarBackButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_book_addition_successful);
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
                Intent intent = new Intent(BookAdditionSuccessful.this, Dashboard.class);
                intent.putExtra("g1", BookAdditionSuccessful.this.stringLat);
                intent.putExtra("g2", BookAdditionSuccessful.this.stringLon);
                BookAdditionSuccessful.this.finish();
                BookAdditionSuccessful.this.startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("g1", this.stringLat);
        intent.putExtra("g2", this.stringLon);
        finish();
        startActivity(intent);
    }
}
