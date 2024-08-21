package com.booklal.booklal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class ShowBookDetailsActivity extends AppCompatActivity {
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
    public CircleImageView bookSellerProfileImage;
    /* access modifiers changed from: private */
    public String bookSellingPriceString;
    /* access modifiers changed from: private */
    public String bookUser;
    /* access modifiers changed from: private */
    public Button call;
    private Button chat;
    private String distance;
    /* access modifiers changed from: private */
    public String imgURL1;
    /* access modifiers changed from: private */
    public String imgURL2;
    /* access modifiers changed from: private */
    public Boolean isCallOk = false;
    /* access modifiers changed from: private */
    public FirebaseStorage mref;
    private FirebaseFirestore mstore;
    /* access modifiers changed from: private */
    public FirebaseFirestore mstoreUser;
    /* access modifiers changed from: private */
    public String phoneNumberString;
    private Double printedPriceDouble;
    private LinearLayout public_profile_click_Button;
    private TextView sellerDistance;
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
    /* access modifiers changed from: private */
    public TextView txtBookSellerName;
    /* access modifiers changed from: private */
    public TextView txtCurrSymbol;
    /* access modifiers changed from: private */
    public TextView txtPrintedAmount;
    /* access modifiers changed from: private */
    public TextView txtSavingAmount;
    /* access modifiers changed from: private */
    public TextView txtSellingPrice;
    int unicode = 10004;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_show_book_details);
        Intent intent = getIntent();
        this.bookID = intent.getStringExtra("bookKey");
        this.distance = intent.getStringExtra("dist");
        this.txtBookName = (TextView) findViewById(C0699R.C0702id.show_book_name);
        this.txtBookDescription = (TextView) findViewById(C0699R.C0702id.show_book_description);
        this.txtCurrSymbol = (TextView) findViewById(C0699R.C0702id.show_currency_symbol);
        this.txtSellingPrice = (TextView) findViewById(C0699R.C0702id.show_selling_price);
        this.txtPrintedAmount = (TextView) findViewById(C0699R.C0702id.show_printed_price);
        this.txtSavingAmount = (TextView) findViewById(C0699R.C0702id.show_saving_amount);
        this.txtBookPublisher = (TextView) findViewById(C0699R.C0702id.show_book_publisher);
        this.txtBookAuthor = (TextView) findViewById(C0699R.C0702id.show_book_author_name);
        this.txtBookSellerName = (TextView) findViewById(C0699R.C0702id.show_book_seller_profile_name);
        this.bookSellerProfileImage = (CircleImageView) findViewById(C0699R.C0702id.show_book_seller_profile_image);
        this.sellerDistance = (TextView) findViewById(C0699R.C0702id.show_book_seller_distance);
        this.call = (Button) findViewById(C0699R.C0702id.show_Call);
        this.chat = (Button) findViewById(C0699R.C0702id.show_Chat);
        this.public_profile_click_Button = (LinearLayout) findViewById(C0699R.C0702id.user_public_profile_click_layout);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        setSupportActionBar(this.toolbar);
        this.toolBarTitle.setText("BookLal");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.sliderView = (SliderView) findViewById(C0699R.C0702id.imageSlider);
        final ArrayList arrayList = new ArrayList();
        this.mstore = FirebaseFirestore.getInstance();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this.mstore.collection("AllBook").document(this.bookID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    String str;
                    String unused = ShowBookDetailsActivity.this.imgURL1 = documentSnapshot.getString("displayURL1");
                    String unused2 = ShowBookDetailsActivity.this.imgURL2 = documentSnapshot.getString("displayURL2");
                    String unused3 = ShowBookDetailsActivity.this.bookUser = documentSnapshot.getString("user");
                    String unused4 = ShowBookDetailsActivity.this.bookDescription = documentSnapshot.getString("BookDescription");
                    String unused5 = ShowBookDetailsActivity.this.bookPublisher = documentSnapshot.getString("Publication");
                    String unused6 = ShowBookDetailsActivity.this.bookAuthor = documentSnapshot.getString("Author");
                    String unused7 = ShowBookDetailsActivity.this.bookCurrSymbol = documentSnapshot.getString("Symbol");
                    String unused8 = ShowBookDetailsActivity.this.bookSellingPriceString = documentSnapshot.getString("SellingPrice");
                    String unused9 = ShowBookDetailsActivity.this.bookPrintedPriceString = documentSnapshot.getString("PrintedPrice");
                    String unused10 = ShowBookDetailsActivity.this.bookName = documentSnapshot.getString("BookName");
                    Boolean unused11 = ShowBookDetailsActivity.this.isCallOk = documentSnapshot.getBoolean("showPhone");
                    if (ShowBookDetailsActivity.this.isCallOk != null && !ShowBookDetailsActivity.this.isCallOk.booleanValue()) {
                        ShowBookDetailsActivity.this.call.setVisibility(8);
                    } else if (ShowBookDetailsActivity.this.isCallOk != null && ShowBookDetailsActivity.this.isCallOk.booleanValue()) {
                        ShowBookDetailsActivity.this.call.setVisibility(0);
                    }
                    if (ShowBookDetailsActivity.this.imgURL1 == null && ShowBookDetailsActivity.this.imgURL2 == null) {
                        String unused12 = ShowBookDetailsActivity.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                        String unused13 = ShowBookDetailsActivity.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (ShowBookDetailsActivity.this.imgURL1 == null) {
                        String unused14 = ShowBookDetailsActivity.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (ShowBookDetailsActivity.this.imgURL2 == null) {
                        String unused15 = ShowBookDetailsActivity.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    }
                    arrayList.add(ShowBookDetailsActivity.this.imgURL1);
                    arrayList.add(ShowBookDetailsActivity.this.imgURL2);
                    List list = arrayList;
                    ShowBookDetailsActivity showBookDetailsActivity = ShowBookDetailsActivity.this;
                    ShowBookDetailsActivity.this.sliderView.setSliderAdapter(new ImageSliderAdapter(list, showBookDetailsActivity, showBookDetailsActivity.bookID));
                    if (ShowBookDetailsActivity.this.bookName != null) {
                        ShowBookDetailsActivity.this.txtBookName.setText(ShowBookDetailsActivity.this.bookName);
                    }
                    if (ShowBookDetailsActivity.this.bookDescription != null) {
                        ShowBookDetailsActivity.this.txtBookDescription.setText(ShowBookDetailsActivity.this.bookDescription);
                    }
                    if (ShowBookDetailsActivity.this.bookPublisher != null) {
                        ShowBookDetailsActivity.this.txtBookPublisher.setText(ShowBookDetailsActivity.this.bookPublisher);
                    }
                    if (ShowBookDetailsActivity.this.bookAuthor != null) {
                        String unused16 = ShowBookDetailsActivity.this.bookAuthor = "by " + ShowBookDetailsActivity.this.bookAuthor;
                        ShowBookDetailsActivity.this.txtBookAuthor.setText(ShowBookDetailsActivity.this.bookAuthor);
                    }
                    if (ShowBookDetailsActivity.this.bookCurrSymbol != null) {
                        ShowBookDetailsActivity.this.txtCurrSymbol.setText(ShowBookDetailsActivity.this.bookCurrSymbol);
                    }
                    if (ShowBookDetailsActivity.this.bookSellingPriceString != null) {
                        ShowBookDetailsActivity.this.txtSellingPrice.setText(ShowBookDetailsActivity.this.bookSellingPriceString);
                    }
                    if (!(ShowBookDetailsActivity.this.bookSellingPriceString == null || ShowBookDetailsActivity.this.bookPrintedPriceString == null)) {
                        ShowBookDetailsActivity showBookDetailsActivity2 = ShowBookDetailsActivity.this;
                        Double unused17 = showBookDetailsActivity2.bookSavingAmount = Double.valueOf(Double.parseDouble(showBookDetailsActivity2.bookPrintedPriceString) - Double.parseDouble(ShowBookDetailsActivity.this.bookSellingPriceString));
                        Double valueOf = Double.valueOf(Double.parseDouble(ShowBookDetailsActivity.this.bookSellingPriceString));
                        Double valueOf2 = Double.valueOf(Double.parseDouble(ShowBookDetailsActivity.this.bookPrintedPriceString));
                        int parseDouble = (int) Double.parseDouble(ShowBookDetailsActivity.this.bookPrintedPriceString);
                        int parseDouble2 = (int) Double.parseDouble(ShowBookDetailsActivity.this.bookSellingPriceString);
                        if (((double) parseDouble) - valueOf2.doubleValue() == 0.0d && ((double) parseDouble2) - valueOf.doubleValue() == 0.0d) {
                            str = (parseDouble - parseDouble2) + "";
                        } else {
                            str = Double.valueOf(Math.floor(Double.valueOf(valueOf2.doubleValue() - valueOf.doubleValue()).doubleValue() * 100.0d) / 100.0d) + "";
                        }
                        if (ShowBookDetailsActivity.this.bookCurrSymbol != null) {
                            str = ShowBookDetailsActivity.this.bookCurrSymbol + " " + str;
                        }
                        ShowBookDetailsActivity.this.txtSavingAmount.setText(str);
                    }
                    if (ShowBookDetailsActivity.this.bookPrintedPriceString != null) {
                        if (ShowBookDetailsActivity.this.bookCurrSymbol != null) {
                            String unused18 = ShowBookDetailsActivity.this.bookPrintedPriceString = ShowBookDetailsActivity.this.bookCurrSymbol + " " + ShowBookDetailsActivity.this.bookPrintedPriceString;
                        }
                        ShowBookDetailsActivity.this.txtPrintedAmount.setText(ShowBookDetailsActivity.this.bookPrintedPriceString);
                        ShowBookDetailsActivity.this.txtPrintedAmount.setPaintFlags(ShowBookDetailsActivity.this.txtPrintedAmount.getPaintFlags() | 16);
                    }
                    if (ShowBookDetailsActivity.this.bookUser != null) {
                        FirebaseFirestore unused19 = ShowBookDetailsActivity.this.mstoreUser = FirebaseFirestore.getInstance();
                        ShowBookDetailsActivity.this.mstoreUser.collection("users").document(ShowBookDetailsActivity.this.bookUser).addSnapshotListener((Activity) ShowBookDetailsActivity.this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                                String unused = ShowBookDetailsActivity.this.bookSellerName = documentSnapshot.getString("displayname");
                                Boolean bool = documentSnapshot.getBoolean("isVerified");
                                String unused2 = ShowBookDetailsActivity.this.phoneNumberString = documentSnapshot.getString("phone");
                                if (ShowBookDetailsActivity.this.bookSellerName == null) {
                                    return;
                                }
                                if (bool == null || !bool.booleanValue()) {
                                    ShowBookDetailsActivity.this.txtBookSellerName.setText(ShowBookDetailsActivity.this.bookSellerName);
                                    return;
                                }
                                String emojiByUnicode = ShowBookDetailsActivity.this.getEmojiByUnicode(ShowBookDetailsActivity.this.unicode);
                                ShowBookDetailsActivity showBookDetailsActivity = ShowBookDetailsActivity.this;
                                String unused3 = showBookDetailsActivity.bookSellerName = ShowBookDetailsActivity.this.bookSellerName + " " + emojiByUnicode;
                                ShowBookDetailsActivity.this.txtBookSellerName.setText(ShowBookDetailsActivity.this.bookSellerName);
                            }
                        });
                        FirebaseStorage unused20 = ShowBookDetailsActivity.this.mref = FirebaseStorage.getInstance();
                        ShowBookDetailsActivity.this.mref.getReference("ProfileImages/").child(ShowBookDetailsActivity.this.bookUser + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            public void onSuccess(Uri uri) {
                                Glide.with(ShowBookDetailsActivity.this.getApplicationContext()).load(uri.toString()).into((ImageView) ShowBookDetailsActivity.this.bookSellerProfileImage);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            public void onFailure(Exception exc) {
                            }
                        });
                    }
                }
            });
            this.sellerDistance.setText(this.distance);
        }
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShowBookDetailsActivity.this.onBackPressed();
                ShowBookDetailsActivity.this.finish();
            }
        });
        this.public_profile_click_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ShowBookDetailsActivity.this, PublicProfileActivity.class);
                intent.putExtra("uid", ShowBookDetailsActivity.this.bookUser);
                ShowBookDetailsActivity.this.startActivity(intent);
            }
        });
        this.chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShowBookDetailsActivity showBookDetailsActivity = ShowBookDetailsActivity.this;
                String unused = showBookDetailsActivity.bookInfo = "Book Name: " + ShowBookDetailsActivity.this.bookName + "\nPrinted Price: " + ShowBookDetailsActivity.this.bookPrintedPriceString + "\nSelling Price: " + ShowBookDetailsActivity.this.bookCurrSymbol + " " + ShowBookDetailsActivity.this.bookSellingPriceString + "\n\n";
                Intent intent = new Intent(ShowBookDetailsActivity.this, ChatActivity.class);
                intent.putExtra("name", ShowBookDetailsActivity.this.bookSellerName);
                intent.putExtra("uid", ShowBookDetailsActivity.this.bookUser);
                intent.putExtra("bookinfo", ShowBookDetailsActivity.this.bookInfo);
                ShowBookDetailsActivity.this.startActivity(intent);
            }
        });
        this.call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:" + ShowBookDetailsActivity.this.phoneNumberString));
                ShowBookDetailsActivity.this.startActivity(intent);
            }
        });
    }

    public String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }
}
