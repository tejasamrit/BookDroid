package com.booklal.booklal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.internal.view.SupportMenu;
import com.firebase.geofire.GeoFire;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class LookBeforeEditBookDetailsActivity extends AppCompatActivity {
    /* access modifiers changed from: private */

    /* renamed from: CC */
    public String f76CC;
    private ImageView backBtnToolbar;
    /* access modifiers changed from: private */
    public String bookAuthor;
    /* access modifiers changed from: private */
    public String bookCurrSymbol;
    /* access modifiers changed from: private */
    public String bookDescription;
    /* access modifiers changed from: private */
    public String bookID;
    /* access modifiers changed from: private */
    public String bookName;
    /* access modifiers changed from: private */
    public String bookPPString;
    /* access modifiers changed from: private */
    public String bookPrintedPriceString;
    /* access modifiers changed from: private */
    public String bookPublisher;
    /* access modifiers changed from: private */
    public Double bookSavingAmount;
    private String bookSellerName;
    private CircleImageView bookSellerProfileImage;
    /* access modifiers changed from: private */
    public String bookSellingPriceString;
    /* access modifiers changed from: private */
    public String bookUser;
    /* access modifiers changed from: private */

    /* renamed from: db */
    public FirebaseFirestore f77db;
    private Button delete;
    private Button edit;
    /* access modifiers changed from: private */
    public String imgURL1;
    /* access modifiers changed from: private */
    public String imgURL2;
    /* access modifiers changed from: private */
    public Boolean isCallOk = false;
    /* access modifiers changed from: private */
    public FirebaseStorage mref;
    private FirebaseFirestore mstore;
    private FirebaseFirestore mstoreUser;
    private String phoneNumberString;
    private Double printedPriceDouble;
    private Double sellingPriceDouble;
    /* access modifiers changed from: private */
    public SliderView sliderView;
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
        setContentView((int) C0699R.layout.activity_look_before_edit_book_details);
        this.bookID = getIntent().getStringExtra("bookKey");
        this.txtBookName = (TextView) findViewById(C0699R.C0702id.look_book_name);
        this.txtBookDescription = (TextView) findViewById(C0699R.C0702id.look_book_description);
        this.txtCurrSymbol = (TextView) findViewById(C0699R.C0702id.look_currency_symbol);
        this.txtSellingPrice = (TextView) findViewById(C0699R.C0702id.look_selling_price);
        this.txtPrintedAmount = (TextView) findViewById(C0699R.C0702id.look_printed_price);
        this.txtSavingAmount = (TextView) findViewById(C0699R.C0702id.look_saving_amount);
        this.txtBookPublisher = (TextView) findViewById(C0699R.C0702id.look_book_publisher);
        this.txtBookAuthor = (TextView) findViewById(C0699R.C0702id.look_book_author_name);
        this.sliderView = (SliderView) findViewById(C0699R.C0702id.look_image_slider);
        this.delete = (Button) findViewById(C0699R.C0702id.look_Delete);
        this.edit = (Button) findViewById(C0699R.C0702id.look_Edit);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        setSupportActionBar(this.toolbar);
        this.toolBarTitle.setText("BookLal");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final ArrayList arrayList = new ArrayList();
        this.mstore = FirebaseFirestore.getInstance();
        if (!(FirebaseAuth.getInstance().getCurrentUser() == null || this.bookID == null)) {
            this.mstore.collection("AllBook").document(this.bookID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    String str;
                    String unused = LookBeforeEditBookDetailsActivity.this.imgURL1 = documentSnapshot.getString("displayURL1");
                    String unused2 = LookBeforeEditBookDetailsActivity.this.imgURL2 = documentSnapshot.getString("displayURL2");
                    String unused3 = LookBeforeEditBookDetailsActivity.this.bookUser = documentSnapshot.getString("user");
                    String unused4 = LookBeforeEditBookDetailsActivity.this.bookDescription = documentSnapshot.getString("BookDescription");
                    String unused5 = LookBeforeEditBookDetailsActivity.this.bookPublisher = documentSnapshot.getString("Publication");
                    String unused6 = LookBeforeEditBookDetailsActivity.this.bookAuthor = documentSnapshot.getString("Author");
                    String unused7 = LookBeforeEditBookDetailsActivity.this.bookCurrSymbol = documentSnapshot.getString("Symbol");
                    String unused8 = LookBeforeEditBookDetailsActivity.this.bookSellingPriceString = documentSnapshot.getString("SellingPrice");
                    String unused9 = LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString = documentSnapshot.getString("PrintedPrice");
                    String unused10 = LookBeforeEditBookDetailsActivity.this.bookName = documentSnapshot.getString("BookName");
                    String unused11 = LookBeforeEditBookDetailsActivity.this.f76CC = documentSnapshot.getString("CountryCode");
                    Boolean unused12 = LookBeforeEditBookDetailsActivity.this.isCallOk = documentSnapshot.getBoolean("showPhone");
                    if (LookBeforeEditBookDetailsActivity.this.imgURL1 == null && LookBeforeEditBookDetailsActivity.this.imgURL2 == null) {
                        String unused13 = LookBeforeEditBookDetailsActivity.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                        String unused14 = LookBeforeEditBookDetailsActivity.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (LookBeforeEditBookDetailsActivity.this.imgURL1 == null) {
                        String unused15 = LookBeforeEditBookDetailsActivity.this.imgURL1 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    } else if (LookBeforeEditBookDetailsActivity.this.imgURL2 == null) {
                        String unused16 = LookBeforeEditBookDetailsActivity.this.imgURL2 = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    }
                    arrayList.add(LookBeforeEditBookDetailsActivity.this.imgURL1);
                    arrayList.add(LookBeforeEditBookDetailsActivity.this.imgURL2);
                    List list = arrayList;
                    LookBeforeEditBookDetailsActivity lookBeforeEditBookDetailsActivity = LookBeforeEditBookDetailsActivity.this;
                    LookBeforeEditBookDetailsActivity.this.sliderView.setSliderAdapter(new ImageSliderAdapter(list, lookBeforeEditBookDetailsActivity, lookBeforeEditBookDetailsActivity.bookID));
                    if (LookBeforeEditBookDetailsActivity.this.bookName != null) {
                        LookBeforeEditBookDetailsActivity.this.txtBookName.setText(LookBeforeEditBookDetailsActivity.this.bookName);
                    }
                    if (LookBeforeEditBookDetailsActivity.this.bookDescription != null) {
                        LookBeforeEditBookDetailsActivity.this.txtBookDescription.setText(LookBeforeEditBookDetailsActivity.this.bookDescription);
                    }
                    if (LookBeforeEditBookDetailsActivity.this.bookPublisher != null) {
                        LookBeforeEditBookDetailsActivity.this.txtBookPublisher.setText(LookBeforeEditBookDetailsActivity.this.bookPublisher);
                    }
                    if (LookBeforeEditBookDetailsActivity.this.bookAuthor != null) {
                        String unused17 = LookBeforeEditBookDetailsActivity.this.bookAuthor = "by " + LookBeforeEditBookDetailsActivity.this.bookAuthor;
                        LookBeforeEditBookDetailsActivity.this.txtBookAuthor.setText(LookBeforeEditBookDetailsActivity.this.bookAuthor);
                    }
                    if (LookBeforeEditBookDetailsActivity.this.bookCurrSymbol != null) {
                        LookBeforeEditBookDetailsActivity.this.txtCurrSymbol.setText(LookBeforeEditBookDetailsActivity.this.bookCurrSymbol);
                    }
                    if (LookBeforeEditBookDetailsActivity.this.bookSellingPriceString != null) {
                        LookBeforeEditBookDetailsActivity.this.txtSellingPrice.setText(LookBeforeEditBookDetailsActivity.this.bookSellingPriceString);
                    }
                    if (!(LookBeforeEditBookDetailsActivity.this.bookSellingPriceString == null || LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString == null)) {
                        LookBeforeEditBookDetailsActivity lookBeforeEditBookDetailsActivity2 = LookBeforeEditBookDetailsActivity.this;
                        Double unused18 = lookBeforeEditBookDetailsActivity2.bookSavingAmount = Double.valueOf(Double.parseDouble(lookBeforeEditBookDetailsActivity2.bookPrintedPriceString) - Double.parseDouble(LookBeforeEditBookDetailsActivity.this.bookSellingPriceString));
                        Double valueOf = Double.valueOf(Double.parseDouble(LookBeforeEditBookDetailsActivity.this.bookSellingPriceString));
                        Double valueOf2 = Double.valueOf(Double.parseDouble(LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString));
                        int parseDouble = (int) Double.parseDouble(LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString);
                        int parseDouble2 = (int) Double.parseDouble(LookBeforeEditBookDetailsActivity.this.bookSellingPriceString);
                        if (((double) parseDouble) - valueOf2.doubleValue() == 0.0d && ((double) parseDouble2) - valueOf.doubleValue() == 0.0d) {
                            str = (parseDouble - parseDouble2) + "";
                        } else {
                            str = Double.valueOf(Math.floor(Double.valueOf(valueOf2.doubleValue() - valueOf.doubleValue()).doubleValue() * 100.0d) / 100.0d) + "";
                        }
                        if (LookBeforeEditBookDetailsActivity.this.bookCurrSymbol != null) {
                            str = LookBeforeEditBookDetailsActivity.this.bookCurrSymbol + " " + str;
                        }
                        LookBeforeEditBookDetailsActivity.this.txtSavingAmount.setText(str);
                    }
                    if (LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString != null) {
                        LookBeforeEditBookDetailsActivity lookBeforeEditBookDetailsActivity3 = LookBeforeEditBookDetailsActivity.this;
                        String unused19 = lookBeforeEditBookDetailsActivity3.bookPPString = lookBeforeEditBookDetailsActivity3.bookPrintedPriceString;
                        if (LookBeforeEditBookDetailsActivity.this.bookCurrSymbol != null) {
                            String unused20 = LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString = LookBeforeEditBookDetailsActivity.this.bookCurrSymbol + " " + LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString;
                        }
                        LookBeforeEditBookDetailsActivity.this.txtPrintedAmount.setText(LookBeforeEditBookDetailsActivity.this.bookPrintedPriceString);
                        LookBeforeEditBookDetailsActivity.this.txtPrintedAmount.setPaintFlags(LookBeforeEditBookDetailsActivity.this.txtPrintedAmount.getPaintFlags() | 16);
                    }
                }
            });
        }
        this.edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LookBeforeEditBookDetailsActivity.this, EditBookDetailsActivity.class);
                intent.putExtra("bookKey", LookBeforeEditBookDetailsActivity.this.bookID);
                intent.putExtra("bName", LookBeforeEditBookDetailsActivity.this.bookName);
                intent.putExtra("bDesc", LookBeforeEditBookDetailsActivity.this.bookDescription);
                intent.putExtra("bPub", LookBeforeEditBookDetailsActivity.this.bookPublisher);
                intent.putExtra("bAuthor", LookBeforeEditBookDetailsActivity.this.bookAuthor);
                intent.putExtra("bURL1", LookBeforeEditBookDetailsActivity.this.imgURL1);
                intent.putExtra("bURL2", LookBeforeEditBookDetailsActivity.this.imgURL2);
                intent.putExtra("bCC", LookBeforeEditBookDetailsActivity.this.f76CC);
                intent.putExtra("bSP", LookBeforeEditBookDetailsActivity.this.bookSellingPriceString);
                intent.putExtra("bPP", LookBeforeEditBookDetailsActivity.this.bookPPString);
                intent.putExtra("bPhone", LookBeforeEditBookDetailsActivity.this.isCallOk);
                intent.putExtra("bSymbol", LookBeforeEditBookDetailsActivity.this.bookCurrSymbol);
                LookBeforeEditBookDetailsActivity.this.startActivity(intent);
            }
        });
        this.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LookBeforeEditBookDetailsActivity.this.AskOption().show();
            }
        });
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LookBeforeEditBookDetailsActivity.this.onBackPressed();
                LookBeforeEditBookDetailsActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public AlertDialog AskOption() {
        return new AlertDialog.Builder(this).setTitle((CharSequence) "Delete Book").setMessage((CharSequence) "Do you want to Delete?").setIcon((int) C0699R.C0701drawable.delete_32dp).setPositiveButton((CharSequence) "Delete", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ProgressDialog progressDialog = new ProgressDialog(LookBeforeEditBookDetailsActivity.this);
                progressDialog.setMessage("Deleting Book...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                FirebaseFirestore unused = LookBeforeEditBookDetailsActivity.this.f77db = FirebaseFirestore.getInstance();
                LookBeforeEditBookDetailsActivity.this.f77db.collection("AllBook").document(LookBeforeEditBookDetailsActivity.this.bookID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void voidR) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                    }
                });
                new GeoFire(FirebaseDatabase.getInstance().getReference("AllBooks").child("geofire")).removeLocation(LookBeforeEditBookDetailsActivity.this.bookID);
                FirebaseStorage unused2 = LookBeforeEditBookDetailsActivity.this.mref = FirebaseStorage.getInstance();
                StorageReference reference = LookBeforeEditBookDetailsActivity.this.mref.getReference("BookImages/");
                reference.child(LookBeforeEditBookDetailsActivity.this.bookID + "P1.jpeg").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void voidR) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                    }
                });
                StorageReference reference2 = LookBeforeEditBookDetailsActivity.this.mref.getReference("BookImages/");
                reference2.child(LookBeforeEditBookDetailsActivity.this.bookID + "P2.jpeg").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void voidR) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                    }
                });
                progressDialog.dismiss();
                dialogInterface.dismiss();
                ((Snackbar) Snackbar.make(LookBeforeEditBookDetailsActivity.this.findViewById(C0699R.C0702id.look_before_edit_layout), (CharSequence) "Deleted Successfully..", 0).setAction((CharSequence) "Close.", (View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View view) {
                        LookBeforeEditBookDetailsActivity.this.startActivity(new Intent(LookBeforeEditBookDetailsActivity.this, SellingItems.class));
                    }
                }).setActionTextColor((int) SupportMenu.CATEGORY_MASK).setDuration(1000)).show();
                LookBeforeEditBookDetailsActivity.this.startActivity(new Intent(LookBeforeEditBookDetailsActivity.this, SellingItems.class));
                LookBeforeEditBookDetailsActivity.this.finish();
            }
        }).setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
    }
}
