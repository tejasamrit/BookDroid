package com.booklal.booklal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class PublicProfileActivity extends AppCompatActivity {
    PublicProfileSellingBookAdapter adapter;
    ArrayList<PublicProfileSellingBooks> bookListZero;
    /* access modifiers changed from: private */
    public String bookSPsend;
    /* access modifiers changed from: private */
    public String book_ID;
    ArrayList<PublicProfileSellingBooks> booksList;
    private String category;
    private Double clat;
    private Double clon;
    /* access modifiers changed from: private */
    public String currencySymbolFetch;

    /* renamed from: db */
    private FirebaseFirestore f83db;
    /* access modifiers changed from: private */
    public String desc;
    private ImageView imgShow;
    /* access modifiers changed from: private */
    public String imguri;
    ArrayList<String> keyList;
    ArrayList<String> list;
    /* access modifiers changed from: private */
    public FirebaseAuth mAuth;
    private FirebaseStorage mref;
    /* access modifiers changed from: private */
    public FirebaseFirestore mstore;
    /* access modifiers changed from: private */
    public String name;
    RecyclerView recyclerViewSelling;
    /* access modifiers changed from: private */
    public String stringLat;
    /* access modifiers changed from: private */
    public String stringLon;
    /* access modifiers changed from: private */
    public Double takingDoubleLat;
    /* access modifiers changed from: private */
    public String takingDoubleLatToString;
    /* access modifiers changed from: private */
    public Double takingDoubleLon;
    /* access modifiers changed from: private */
    public String takingDoubleLonToString;
    private TextView textShow;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    private ImageView toolbarBackButton;
    int unicode = 10004;
    /* access modifiers changed from: private */
    public TextView userBio;
    /* access modifiers changed from: private */
    public String userID;
    /* access modifiers changed from: private */
    public TextView userName;
    /* access modifiers changed from: private */
    public String user_name;
    /* access modifiers changed from: private */
    public TextView username;
    /* access modifiers changed from: private */
    public CircleImageView viewImage;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_public_profile);
        this.userID = getIntent().getStringExtra("uid");
        this.viewImage = (CircleImageView) findViewById(C0699R.C0702id.public_profile_image);
        this.userName = (TextView) findViewById(C0699R.C0702id.public_profile_name);
        this.username = (TextView) findViewById(C0699R.C0702id.public_profile_username);
        this.userBio = (TextView) findViewById(C0699R.C0702id.public_profile_bio);
        this.toolbarBackButton = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        Toolbar toolbar2 = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        this.toolBarTitle.setText("User Profile");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        RecyclerView recyclerView = (RecyclerView) findViewById(C0699R.C0702id.recyclerViewPublicProfile);
        this.recyclerViewSelling = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.recyclerViewSelling.setLayoutManager(linearLayoutManager);
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        this.f83db = FirebaseFirestore.getInstance();
        if (this.mAuth.getCurrentUser() != null) {
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    String str;
                    if (PublicProfileActivity.this.mAuth.getCurrentUser() != null) {
                        String str2 = "";
                        if (PublicProfileActivity.this.stringLat == null || PublicProfileActivity.this.stringLon == null) {
                            Double unused = PublicProfileActivity.this.takingDoubleLat = documentSnapshot.getDouble("Latitude");
                            Double unused2 = PublicProfileActivity.this.takingDoubleLon = documentSnapshot.getDouble("Longitude");
                            String unused3 = PublicProfileActivity.this.takingDoubleLatToString = PublicProfileActivity.this.takingDoubleLat + str2;
                            String unused4 = PublicProfileActivity.this.takingDoubleLonToString = PublicProfileActivity.this.takingDoubleLon + str2;
                        }
                        String string = documentSnapshot.getString("displayname");
                        String string2 = documentSnapshot.getString("pbio");
                        String unused5 = PublicProfileActivity.this.user_name = documentSnapshot.getString("username");
                        if (PublicProfileActivity.this.user_name != null) {
                            PublicProfileActivity.this.username.setText(PublicProfileActivity.this.user_name);
                        }
                        Boolean bool = documentSnapshot.getBoolean("isVerified");
                        if (string != null) {
                            String str3 = str2;
                            for (int i = 0; i < string.length(); i++) {
                                if (string.charAt(i) == 10) {
                                    str3 = str3 + " ";
                                } else {
                                    str3 = str3 + string.charAt(i);
                                }
                            }
                            if (str3 != null) {
                                if (bool == null || !bool.booleanValue()) {
                                    PublicProfileActivity.this.userName.setText(str3);
                                } else {
                                    PublicProfileActivity publicProfileActivity = PublicProfileActivity.this;
                                    String emojiByUnicode = publicProfileActivity.getEmojiByUnicode(publicProfileActivity.unicode);
                                    PublicProfileActivity.this.userName.setText(str3 + " " + emojiByUnicode);
                                }
                            }
                        }
                        if (string2 != null) {
                            for (int i2 = 0; i2 < string2.length(); i2++) {
                                if (string2.charAt(i2) == 10) {
                                    str = str2 + " ";
                                } else {
                                    str = str2 + string2.charAt(i2);
                                }
                                str2 = str;
                            }
                            if (str2 != null) {
                                PublicProfileActivity.this.userBio.setText(str2);
                            }
                        }
                    }
                }
            });
        }
        FirebaseStorage instance = FirebaseStorage.getInstance();
        this.mref = instance;
        StorageReference reference = instance.getReference("ProfileImages/");
        reference.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                Glide.with(PublicProfileActivity.this.getApplicationContext()).load(uri.toString()).into((ImageView) PublicProfileActivity.this.viewImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception exc) {
            }
        });
        if (FirebaseAuth.getInstance() != null) {
            sellingBooks();
        }
        this.viewImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PublicProfileActivity.this, ProfilePhotoViewActivity.class);
                intent.putExtra("userKey", PublicProfileActivity.this.userID);
                PublicProfileActivity.this.startActivity(intent);
            }
        });
        this.toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PublicProfileActivity.this.onBackPressed();
            }
        });
    }

    public void sellingBooks() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Books...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        this.list = new ArrayList<>();
        this.booksList = new ArrayList<>();
        this.bookListZero = new ArrayList<>();
        this.f83db.collection("AllBook").whereEqualTo("user", (Object) this.userID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Iterator<QueryDocumentSnapshot> it = task.getResult().iterator();
                    while (it.hasNext()) {
                        PublicProfileActivity.this.list.add(it.next().getId());
                    }
                }
                if (PublicProfileActivity.this.list.size() == 0) {
                    PublicProfileActivity publicProfileActivity = PublicProfileActivity.this;
                    publicProfileActivity.adapter = new PublicProfileSellingBookAdapter(publicProfileActivity, publicProfileActivity.bookListZero);
                    PublicProfileActivity.this.recyclerViewSelling.setAdapter(PublicProfileActivity.this.adapter);
                    return;
                }
                for (final int i = 0; i < PublicProfileActivity.this.list.size(); i++) {
                    String unused = PublicProfileActivity.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    PublicProfileActivity.this.mstore.collection("AllBook").document(PublicProfileActivity.this.list.get(i)).addSnapshotListener((Activity) PublicProfileActivity.this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                            String unused = PublicProfileActivity.this.name = documentSnapshot.getString("BookName");
                            String unused2 = PublicProfileActivity.this.desc = documentSnapshot.getString("BookDescription");
                            String unused3 = PublicProfileActivity.this.imguri = documentSnapshot.getString("displayURL1");
                            String unused4 = PublicProfileActivity.this.currencySymbolFetch = documentSnapshot.getString("Symbol");
                            PublicProfileActivity publicProfileActivity = PublicProfileActivity.this;
                            String unused5 = publicProfileActivity.bookSPsend = PublicProfileActivity.this.currencySymbolFetch + " " + documentSnapshot.getString("SellingPrice");
                            String unused6 = PublicProfileActivity.this.book_ID = PublicProfileActivity.this.list.get(i);
                            if ((PublicProfileActivity.this.imguri == "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c" || PublicProfileActivity.this.imguri == null) && PublicProfileActivity.this.imguri == null) {
                                String unused7 = PublicProfileActivity.this.imguri = documentSnapshot.getString("displayURL2");
                                if (PublicProfileActivity.this.imguri == null) {
                                    String unused8 = PublicProfileActivity.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                                }
                            }
                            PublicProfileActivity.this.booksList.add(new PublicProfileSellingBooks(PublicProfileActivity.this.name, PublicProfileActivity.this.desc, PublicProfileActivity.this.book_ID, PublicProfileActivity.this.imguri, PublicProfileActivity.this.bookSPsend));
                            PublicProfileActivity.this.adapter = new PublicProfileSellingBookAdapter(PublicProfileActivity.this, PublicProfileActivity.this.booksList);
                            PublicProfileActivity.this.recyclerViewSelling.setAdapter(PublicProfileActivity.this.adapter);
                        }
                    });
                }
            }
        });
        progressDialog.dismiss();
    }

    public String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }
}
