package com.booklal.booklal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.List;
import javax.annotation.Nullable;

public class PublicProfileShowBookDetailsActivity extends AppCompatActivity {
    ImageView backBtnToolbar;
    /* access modifiers changed from: private */
    public String bookAuthor;
    /* access modifiers changed from: private */
    public String bookCurrSymbol;
    /* access modifiers changed from: private */
    public String bookDescription;
    /* access modifiers changed from: private */
    public String bookID;
    /* access modifiers changed from: private */
    public String bookInfo;
    /* access modifiers changed from: private */
    public String bookName;
    /* access modifiers changed from: private */
    public String bookPrintedPriceString;
    /* access modifiers changed from: private */
    public String bookPublisher;
    /* access modifiers changed from: private */
    public Double bookSavingAmount;
    /* access modifiers changed from: private */
    public String bookSellerName;
    /* access modifiers changed from: private */
    public String bookSellingPriceString;
    /* access modifiers changed from: private */
    public String bookUser;
    /* access modifiers changed from: private */
    public Button call;
    private Button chat;
    /* access modifiers changed from: private */
    public String imgURL1;
    /* access modifiers changed from: private */
    public String imgURL2;
    /* access modifiers changed from: private */
    public Boolean isCallOk = false;
    private FirebaseFirestore mstore;
    /* access modifiers changed from: private */
    public FirebaseFirestore mstoreUser;
    /* access modifiers changed from: private */
    public String phoneNumberString;
    private Double printedPriceDouble;
    private Double sellingPriceDouble;
    SliderView sliderView;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    /* access modifiers changed from: private */
    public TextView txtBookAuthor;
    /* access modifiers changed from: private */
    public TextView txtBookDescription;
    /* access modifiers changed from: private */
    public TextView txtBookName;
    /* access modifiers changed from: private */
    public TextView txtBookPublisher;
    private TextView txtBookSellerName;
    /* access modifiers changed from: private */
    public TextView txtCurrSymbol;
    /* access modifiers changed from: private */
    public TextView txtPrintedAmount;
    /* access modifiers changed from: private */
    public TextView txtSavingAmount;
    /* access modifiers changed from: private */
    public TextView txtSellingPrice;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_public_profile_show_book_details);
        this.bookID = getIntent().getStringExtra("bookKey");
        this.txtBookName = (TextView) findViewById(C0699R.C0702id.public_show_book_name);
        this.txtBookDescription = (TextView) findViewById(C0699R.C0702id.public_show_book_description);
        this.txtCurrSymbol = (TextView) findViewById(C0699R.C0702id.public_show_currency_symbol);
        this.txtSellingPrice = (TextView) findViewById(C0699R.C0702id.public_show_selling_price);
        this.txtPrintedAmount = (TextView) findViewById(C0699R.C0702id.public_show_printed_price);
        this.txtSavingAmount = (TextView) findViewById(C0699R.C0702id.public_show_saving_amount);
        this.txtBookPublisher = (TextView) findViewById(C0699R.C0702id.public_show_book_publisher);
        this.txtBookAuthor = (TextView) findViewById(C0699R.C0702id.public_show_book_author_name);
        this.call = (Button) findViewById(C0699R.C0702id.public_show_Call);
        this.chat = (Button) findViewById(C0699R.C0702id.public_show_Chat);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        setSupportActionBar(this.toolbar);
        this.toolBarTitle.setText("BookLal");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.sliderView = (SliderView) findViewById(C0699R.C0702id.public_imageSlider);
        final ArrayList arrayList = new ArrayList();
        this.mstore = FirebaseFirestore.getInstance();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this.mstore.collection("AllBook").document(this.bookID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    String str;
                    String unused = PublicProfileShowBookDetailsActivity.this.imgURL1 = documentSnapshot.getString("displayURL1");
                    String unused2 = PublicProfileShowBookDetailsActivity.this.imgURL2 = documentSnapshot.getString("displayURL2");
                    String unused3 = PublicProfileShowBookDetailsActivity.this.bookUser = documentSnapshot.getString("user");
                    String unused4 = PublicProfileShowBookDetailsActivity.this.bookDescription = documentSnapshot.getString("BookDescription");
                    String unused5 = PublicProfileShowBookDetailsActivity.this.bookPublisher = documentSnapshot.getString("Publication");
                    String unused6 = PublicProfileShowBookDetailsActivity.this.bookAuthor = documentSnapshot.getString("Author");
                    String unused7 = PublicProfileShowBookDetailsActivity.this.bookCurrSymbol = documentSnapshot.getString("Symbol");
                    String unused8 = PublicProfileShowBookDetailsActivity.this.bookSellingPriceString = documentSnapshot.getString("SellingPrice");
                    String unused9 = PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString = documentSnapshot.getString("PrintedPrice");
                    String unused10 = PublicProfileShowBookDetailsActivity.this.bookName = documentSnapshot.getString("BookName");
                    Boolean unused11 = PublicProfileShowBookDetailsActivity.this.isCallOk = documentSnapshot.getBoolean("showPhone");
                    if (PublicProfileShowBookDetailsActivity.this.isCallOk != null && !PublicProfileShowBookDetailsActivity.this.isCallOk.booleanValue()) {
                        PublicProfileShowBookDetailsActivity.this.call.setVisibility(8);
                    } else if (PublicProfileShowBookDetailsActivity.this.isCallOk != null && PublicProfileShowBookDetailsActivity.this.isCallOk.booleanValue()) {
                        PublicProfileShowBookDetailsActivity.this.call.setVisibility(0);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.imgURL1 == null && PublicProfileShowBookDetailsActivity.this.imgURL2 == null) {
                        String unused12 = PublicProfileShowBookDetailsActivity.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                        String unused13 = PublicProfileShowBookDetailsActivity.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (PublicProfileShowBookDetailsActivity.this.imgURL1 == null) {
                        String unused14 = PublicProfileShowBookDetailsActivity.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (PublicProfileShowBookDetailsActivity.this.imgURL2 == null) {
                        String unused15 = PublicProfileShowBookDetailsActivity.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    }
                    arrayList.add(PublicProfileShowBookDetailsActivity.this.imgURL1);
                    arrayList.add(PublicProfileShowBookDetailsActivity.this.imgURL2);
                    List list = arrayList;
                    PublicProfileShowBookDetailsActivity publicProfileShowBookDetailsActivity = PublicProfileShowBookDetailsActivity.this;
                    PublicProfileShowBookDetailsActivity.this.sliderView.setSliderAdapter(new ImageSliderAdapter(list, publicProfileShowBookDetailsActivity, publicProfileShowBookDetailsActivity.bookID));
                    if (PublicProfileShowBookDetailsActivity.this.bookName != null) {
                        PublicProfileShowBookDetailsActivity.this.txtBookName.setText(PublicProfileShowBookDetailsActivity.this.bookName);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.bookDescription != null) {
                        PublicProfileShowBookDetailsActivity.this.txtBookDescription.setText(PublicProfileShowBookDetailsActivity.this.bookDescription);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.bookPublisher != null) {
                        PublicProfileShowBookDetailsActivity.this.txtBookPublisher.setText(PublicProfileShowBookDetailsActivity.this.bookPublisher);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.bookAuthor != null) {
                        String unused16 = PublicProfileShowBookDetailsActivity.this.bookAuthor = "by " + PublicProfileShowBookDetailsActivity.this.bookAuthor;
                        PublicProfileShowBookDetailsActivity.this.txtBookAuthor.setText(PublicProfileShowBookDetailsActivity.this.bookAuthor);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.bookCurrSymbol != null) {
                        PublicProfileShowBookDetailsActivity.this.txtCurrSymbol.setText(PublicProfileShowBookDetailsActivity.this.bookCurrSymbol);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.bookSellingPriceString != null) {
                        PublicProfileShowBookDetailsActivity.this.txtSellingPrice.setText(PublicProfileShowBookDetailsActivity.this.bookSellingPriceString);
                    }
                    if (!(PublicProfileShowBookDetailsActivity.this.bookSellingPriceString == null || PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString == null)) {
                        PublicProfileShowBookDetailsActivity publicProfileShowBookDetailsActivity2 = PublicProfileShowBookDetailsActivity.this;
                        Double unused17 = publicProfileShowBookDetailsActivity2.bookSavingAmount = Double.valueOf(Double.parseDouble(publicProfileShowBookDetailsActivity2.bookPrintedPriceString) - Double.parseDouble(PublicProfileShowBookDetailsActivity.this.bookSellingPriceString));
                        Double valueOf = Double.valueOf(Double.parseDouble(PublicProfileShowBookDetailsActivity.this.bookSellingPriceString));
                        Double valueOf2 = Double.valueOf(Double.parseDouble(PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString));
                        int parseDouble = (int) Double.parseDouble(PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString);
                        int parseDouble2 = (int) Double.parseDouble(PublicProfileShowBookDetailsActivity.this.bookSellingPriceString);
                        if (((double) parseDouble) - valueOf2.doubleValue() == 0.0d && ((double) parseDouble2) - valueOf.doubleValue() == 0.0d) {
                            str = (parseDouble - parseDouble2) + "";
                        } else {
                            str = Double.valueOf(Math.floor(Double.valueOf(valueOf2.doubleValue() - valueOf.doubleValue()).doubleValue() * 100.0d) / 100.0d) + "";
                        }
                        if (PublicProfileShowBookDetailsActivity.this.bookCurrSymbol != null) {
                            str = PublicProfileShowBookDetailsActivity.this.bookCurrSymbol + " " + str;
                        }
                        PublicProfileShowBookDetailsActivity.this.txtSavingAmount.setText(str);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString != null) {
                        if (PublicProfileShowBookDetailsActivity.this.bookCurrSymbol != null) {
                            String unused18 = PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString = PublicProfileShowBookDetailsActivity.this.bookCurrSymbol + " " + PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString;
                        }
                        PublicProfileShowBookDetailsActivity.this.txtPrintedAmount.setText(PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString);
                        PublicProfileShowBookDetailsActivity.this.txtPrintedAmount.setPaintFlags(PublicProfileShowBookDetailsActivity.this.txtPrintedAmount.getPaintFlags() | 16);
                    }
                    if (PublicProfileShowBookDetailsActivity.this.bookUser != null) {
                        FirebaseFirestore unused19 = PublicProfileShowBookDetailsActivity.this.mstoreUser = FirebaseFirestore.getInstance();
                        PublicProfileShowBookDetailsActivity.this.mstoreUser.collection("users").document(PublicProfileShowBookDetailsActivity.this.bookUser).addSnapshotListener((Activity) PublicProfileShowBookDetailsActivity.this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                                String unused = PublicProfileShowBookDetailsActivity.this.bookSellerName = documentSnapshot.getString("displayname");
                                String unused2 = PublicProfileShowBookDetailsActivity.this.phoneNumberString = documentSnapshot.getString("phone");
                            }
                        });
                    }
                }
            });
        }
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PublicProfileShowBookDetailsActivity.this.onBackPressed();
                PublicProfileShowBookDetailsActivity.this.finish();
            }
        });
        this.chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PublicProfileShowBookDetailsActivity publicProfileShowBookDetailsActivity = PublicProfileShowBookDetailsActivity.this;
                String unused = publicProfileShowBookDetailsActivity.bookInfo = "Book Name: " + PublicProfileShowBookDetailsActivity.this.bookName + "\nPrinted Price: " + PublicProfileShowBookDetailsActivity.this.bookPrintedPriceString + "\nSelling Price: " + PublicProfileShowBookDetailsActivity.this.bookCurrSymbol + " " + PublicProfileShowBookDetailsActivity.this.bookSellingPriceString + "\n\n";
                Intent intent = new Intent(PublicProfileShowBookDetailsActivity.this, ChatActivity.class);
                intent.putExtra("name", PublicProfileShowBookDetailsActivity.this.bookSellerName);
                intent.putExtra("uid", PublicProfileShowBookDetailsActivity.this.bookUser);
                intent.putExtra("bookinfo", PublicProfileShowBookDetailsActivity.this.bookInfo);
                PublicProfileShowBookDetailsActivity.this.startActivity(intent);
            }
        });
        this.call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:" + PublicProfileShowBookDetailsActivity.this.phoneNumberString));
                PublicProfileShowBookDetailsActivity.this.startActivity(intent);
            }
        });
    }
}
