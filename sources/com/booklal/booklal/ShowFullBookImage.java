package com.booklal.booklal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class ShowFullBookImage extends AppCompatActivity {
    ImageView backBtnToolbar;
    String bookID;
    String imgURL1;
    String imgURL2;
    private FirebaseFirestore mstore;
    int pos;
    SliderView sliderView;
    private TextView toolBarTitle;
    private Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_show_full_book_image);
        this.sliderView = (SliderView) findViewById(C0699R.C0702id.imageSliderFull);
        Intent intent = getIntent();
        this.pos = intent.getIntExtra("bookPos", 0);
        this.bookID = intent.getStringExtra("bookKey");
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        setSupportActionBar(this.toolbar);
        this.toolBarTitle.setText("Book Image");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final ArrayList arrayList = new ArrayList();
        this.mstore = FirebaseFirestore.getInstance();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this.mstore.collection("AllBook").document(this.bookID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    ShowFullBookImage.this.imgURL1 = documentSnapshot.getString("displayURL1");
                    ShowFullBookImage.this.imgURL2 = documentSnapshot.getString("displayURL2");
                    if (ShowFullBookImage.this.imgURL1 == null && ShowFullBookImage.this.imgURL2 == null) {
                        ShowFullBookImage.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                        ShowFullBookImage.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (ShowFullBookImage.this.imgURL1 == null) {
                        ShowFullBookImage.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (ShowFullBookImage.this.imgURL2 == null) {
                        ShowFullBookImage.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    }
                    new ArrayList();
                    if (ShowFullBookImage.this.pos == 0) {
                        arrayList.add(ShowFullBookImage.this.imgURL1);
                        arrayList.add(ShowFullBookImage.this.imgURL2);
                    } else {
                        arrayList.add(ShowFullBookImage.this.imgURL2);
                        arrayList.add(ShowFullBookImage.this.imgURL1);
                    }
                    ShowFullBookImage.this.sliderView.setSliderAdapter(new FullImageSliderAdapter(arrayList));
                }
            });
        }
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShowFullBookImage.this.onBackPressed();
                ShowFullBookImage.this.finish();
            }
        });
    }
}
