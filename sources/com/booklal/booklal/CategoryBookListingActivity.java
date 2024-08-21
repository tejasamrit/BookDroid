package com.booklal.booklal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.geofire.util.GeoUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;

public class CategoryBookListingActivity extends AppCompatActivity {
    final String TAG = "Query Result Activity";
    BookAdapter adapter;
    LinearLayout addNewBook;
    LinearLayout addUsedBook;
    String author;
    String bookCategory;
    String bookSPsend;
    List<AllBooks> booksList;
    String category;
    Double clat;
    Double clon;
    String country_code;
    String currencySymbolFetch;
    String desc;
    View headerView;
    String imguri;
    ArrayList<String> keyList;
    ArrayList<String> list;
    FirebaseAuth mAuth;
    FirebaseStorage mref;
    FirebaseFirestore mstore;
    String name;
    TextView nav_email;
    TextView nav_name;
    Double new_radius;
    ImageView noBookFoundImage;
    TextView noBookFoundMsg1;
    TextView noBookFoundMsg2;
    String pDistance;
    Double plat;
    Double plon;
    private String printCategory = "";
    Double providerdistance;
    String publication;
    SwipeRefreshLayout pullToRefresh;
    Double radius = Double.valueOf(15.0d);
    RecyclerView recyclerView;
    private String strArt = "Art";
    private String strBiographics = "Biographic";
    private String strBusiness = "Business";
    private String strComputerScience = "ComputerScience";
    private String strEducation = "Education";
    private String strHealth = "Health";
    private String strHistory = "History";
    private String strLiterature = "Literature";
    private String strMaths = "Maths";
    private String strMedical = "Medical";
    private String strNovels = "Novels";
    private String strOthers = "Others";
    private String strScience = "Science";
    String stringLat;
    String stringLon;
    String stringRadius = null;
    private Double takingDoubleLat;
    private String takingDoubleLatToString;
    private Double takingDoubleLon;
    private String takingDoubleLonToString;
    TextView toolBarTitle;
    private Toolbar toolbar;
    private ImageView toolbarBackButton;
    String userID;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_category_book_listing);
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        this.category = intent.getStringExtra("cat");
        this.toolbarBackButton = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        if (this.category.equals("ComputerScience")) {
            this.printCategory = "Computer Science";
        } else if (this.category.equals("Medical")) {
            this.printCategory = "Medical Science";
        } else if (this.category.equals("Health")) {
            this.printCategory = "Health & Fitness";
        } else if (this.category.equals("Art")) {
            this.printCategory = "Art & Music";
        } else if (this.category.equals("Business")) {
            this.printCategory = "Business & Finance";
        } else {
            this.printCategory = this.category;
        }
        this.toolBarTitle.setText(this.printCategory);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.noBookFoundImage = (ImageView) findViewById(C0699R.C0702id.noBookFoundImageBookListing);
        this.noBookFoundMsg1 = (TextView) findViewById(C0699R.C0702id.noBookFoundMsg1BookListing);
        this.noBookFoundMsg2 = (TextView) findViewById(C0699R.C0702id.noBookFoundMsg2BookListing);
        this.recyclerView = (RecyclerView) findViewById(C0699R.C0702id.recyclerViewBookListing);
        this.pullToRefresh = (SwipeRefreshLayout) findViewById(C0699R.C0702id.pullToRefreshBookListing);
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        if (this.mAuth.getCurrentUser() != null) {
            this.userID = this.mAuth.getCurrentUser().getUid();
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    if (CategoryBookListingActivity.this.mAuth.getCurrentUser() != null) {
                        CategoryBookListingActivity.this.clat = documentSnapshot.getDouble("Latitude");
                        CategoryBookListingActivity.this.clon = documentSnapshot.getDouble("Longitude");
                        if (CategoryBookListingActivity.this.stringLon != null && CategoryBookListingActivity.this.stringLat != null && FirebaseAuth.getInstance() != null) {
                            CategoryBookListingActivity categoryBookListingActivity = CategoryBookListingActivity.this;
                            categoryBookListingActivity.clat = Double.valueOf(Double.parseDouble(categoryBookListingActivity.stringLat));
                            CategoryBookListingActivity categoryBookListingActivity2 = CategoryBookListingActivity.this;
                            categoryBookListingActivity2.clon = Double.valueOf(Double.parseDouble(categoryBookListingActivity2.stringLon));
                            CategoryBookListingActivity.this.searchAround();
                        } else if ((CategoryBookListingActivity.this.stringLon == null || CategoryBookListingActivity.this.stringLat != null) && CategoryBookListingActivity.this.clon != null && CategoryBookListingActivity.this.clat != null) {
                            CategoryBookListingActivity categoryBookListingActivity3 = CategoryBookListingActivity.this;
                            categoryBookListingActivity3.stringLat = CategoryBookListingActivity.this.clat + "";
                            CategoryBookListingActivity categoryBookListingActivity4 = CategoryBookListingActivity.this;
                            categoryBookListingActivity4.stringLon = CategoryBookListingActivity.this.clon + "";
                            CategoryBookListingActivity.this.searchAround();
                        }
                    }
                }
            });
        }
        this.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (CategoryBookListingActivity.this.stringLat != null && CategoryBookListingActivity.this.stringLon != null && FirebaseAuth.getInstance() != null) {
                    CategoryBookListingActivity categoryBookListingActivity = CategoryBookListingActivity.this;
                    categoryBookListingActivity.clat = Double.valueOf(Double.parseDouble(categoryBookListingActivity.stringLat));
                    CategoryBookListingActivity categoryBookListingActivity2 = CategoryBookListingActivity.this;
                    categoryBookListingActivity2.clon = Double.valueOf(Double.parseDouble(categoryBookListingActivity2.stringLon));
                    CategoryBookListingActivity.this.searchAround();
                } else if (!((CategoryBookListingActivity.this.stringLon != null && CategoryBookListingActivity.this.stringLat == null) || CategoryBookListingActivity.this.clon == null || CategoryBookListingActivity.this.clat == null)) {
                    CategoryBookListingActivity.this.searchAround();
                }
                CategoryBookListingActivity.this.pullToRefresh.setRefreshing(false);
            }
        });
        this.toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CategoryBookListingActivity.this.onBackPressed();
            }
        });
    }

    public void searchAround() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Searching Books...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        this.keyList = new ArrayList<>();
        GeoFire geoFire = new GeoFire(FirebaseDatabase.getInstance().getReference("AllBooks").child("geofire"));
        this.list = new ArrayList<>();
        geoFire.queryAtLocation(new GeoLocation(this.clat.doubleValue(), this.clon.doubleValue()), this.radius.doubleValue()).addGeoQueryEventListener(new GeoQueryEventListener() {
            public void onKeyEntered(String str, GeoLocation geoLocation) {
                CategoryBookListingActivity.this.list.add(str);
            }

            public void onKeyExited(String str) {
                System.out.println(String.format("Key %s is no longer in the search area", new Object[]{str}));
            }

            public void onKeyMoved(String str, GeoLocation geoLocation) {
                progressDialog.dismiss();
            }

            public void onGeoQueryReady() {
                progressDialog.dismiss();
                System.out.println("All initial data has been loaded and events have been fired!");
                CategoryBookListingActivity.this.booksList = new ArrayList();
                CategoryBookListingActivity.this.recyclerView.setHasFixedSize(true);
                CategoryBookListingActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(CategoryBookListingActivity.this));
                for (int i = 0; i < CategoryBookListingActivity.this.list.size(); i++) {
                    final String str = CategoryBookListingActivity.this.list.get(i);
                    CategoryBookListingActivity.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    CategoryBookListingActivity.this.mstore.collection("AllBook").document(str).addSnapshotListener((Activity) CategoryBookListingActivity.this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                            CategoryBookListingActivity.this.name = documentSnapshot.getString("BookName");
                            CategoryBookListingActivity.this.desc = documentSnapshot.getString("BookDescription");
                            CategoryBookListingActivity.this.publication = documentSnapshot.getString("Publication");
                            CategoryBookListingActivity.this.plat = documentSnapshot.getDouble("Latitude");
                            CategoryBookListingActivity.this.plon = documentSnapshot.getDouble("Longitude");
                            CategoryBookListingActivity.this.imguri = documentSnapshot.getString("displayURL1");
                            CategoryBookListingActivity.this.currencySymbolFetch = documentSnapshot.getString("Symbol");
                            CategoryBookListingActivity categoryBookListingActivity = CategoryBookListingActivity.this;
                            categoryBookListingActivity.bookSPsend = CategoryBookListingActivity.this.currencySymbolFetch + " " + documentSnapshot.getString("SellingPrice");
                            CategoryBookListingActivity.this.country_code = documentSnapshot.getString("CountryCode");
                            CategoryBookListingActivity.this.author = documentSnapshot.getString("Author");
                            CategoryBookListingActivity.this.bookCategory = documentSnapshot.getString("BookCategory");
                            if (CategoryBookListingActivity.this.category.equals(CategoryBookListingActivity.this.bookCategory)) {
                                CategoryBookListingActivity.this.noBookFoundImage.setVisibility(8);
                                CategoryBookListingActivity.this.noBookFoundMsg1.setVisibility(8);
                                CategoryBookListingActivity.this.noBookFoundMsg2.setVisibility(8);
                                if ((CategoryBookListingActivity.this.imguri == "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c" || CategoryBookListingActivity.this.imguri == null) && CategoryBookListingActivity.this.imguri == null) {
                                    CategoryBookListingActivity.this.imguri = documentSnapshot.getString("displayURL2");
                                    if (CategoryBookListingActivity.this.imguri == null) {
                                        CategoryBookListingActivity.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                                    }
                                }
                                CategoryBookListingActivity.this.providerdistance = Double.valueOf(GeoUtils.distance(CategoryBookListingActivity.this.clat.doubleValue(), CategoryBookListingActivity.this.clon.doubleValue(), CategoryBookListingActivity.this.plat.doubleValue(), CategoryBookListingActivity.this.plon.doubleValue()));
                                CategoryBookListingActivity.this.country_code = CategoryBookListingActivity.this.country_code.trim();
                                if (CategoryBookListingActivity.this.country_code.equalsIgnoreCase("US") || CategoryBookListingActivity.this.country_code.equalsIgnoreCase("GB") || CategoryBookListingActivity.this.country_code.equalsIgnoreCase("USA") || CategoryBookListingActivity.this.country_code.equalsIgnoreCase("LR") || CategoryBookListingActivity.this.country_code.equalsIgnoreCase("MM")) {
                                    if (CategoryBookListingActivity.this.providerdistance.doubleValue() > 1609.344d) {
                                        CategoryBookListingActivity.this.providerdistance = Double.valueOf(CategoryBookListingActivity.this.providerdistance.doubleValue() / 1609.344d);
                                        CategoryBookListingActivity.this.providerdistance = Double.valueOf(Math.floor(CategoryBookListingActivity.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                        if (CategoryBookListingActivity.this.providerdistance.doubleValue() > 2.0d) {
                                            CategoryBookListingActivity categoryBookListingActivity2 = CategoryBookListingActivity.this;
                                            categoryBookListingActivity2.pDistance = CategoryBookListingActivity.this.providerdistance + " miles away ";
                                        } else {
                                            CategoryBookListingActivity categoryBookListingActivity3 = CategoryBookListingActivity.this;
                                            categoryBookListingActivity3.pDistance = CategoryBookListingActivity.this.providerdistance + " mile away";
                                        }
                                    } else {
                                        CategoryBookListingActivity.this.providerdistance = Double.valueOf(Math.floor(CategoryBookListingActivity.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                        CategoryBookListingActivity categoryBookListingActivity4 = CategoryBookListingActivity.this;
                                        categoryBookListingActivity4.pDistance = CategoryBookListingActivity.this.providerdistance + " mile away";
                                    }
                                } else if (CategoryBookListingActivity.this.providerdistance.doubleValue() > 500.0d) {
                                    CategoryBookListingActivity.this.providerdistance = Double.valueOf(CategoryBookListingActivity.this.providerdistance.doubleValue() / 1000.0d);
                                    CategoryBookListingActivity.this.providerdistance = Double.valueOf(Math.floor(CategoryBookListingActivity.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                    CategoryBookListingActivity categoryBookListingActivity5 = CategoryBookListingActivity.this;
                                    categoryBookListingActivity5.pDistance = CategoryBookListingActivity.this.providerdistance + " km away";
                                } else {
                                    CategoryBookListingActivity.this.providerdistance = Double.valueOf(Math.floor(CategoryBookListingActivity.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                    CategoryBookListingActivity categoryBookListingActivity6 = CategoryBookListingActivity.this;
                                    categoryBookListingActivity6.pDistance = CategoryBookListingActivity.this.providerdistance + "";
                                    if (CategoryBookListingActivity.this.providerdistance.doubleValue() < 2.0d) {
                                        CategoryBookListingActivity categoryBookListingActivity7 = CategoryBookListingActivity.this;
                                        categoryBookListingActivity7.pDistance = CategoryBookListingActivity.this.pDistance.substring(0, CategoryBookListingActivity.this.pDistance.length() - 3) + " meter away";
                                    } else {
                                        CategoryBookListingActivity categoryBookListingActivity8 = CategoryBookListingActivity.this;
                                        categoryBookListingActivity8.pDistance = CategoryBookListingActivity.this.pDistance.substring(0, CategoryBookListingActivity.this.pDistance.length() - 3) + " meters away";
                                    }
                                }
                                CategoryBookListingActivity.this.booksList.add(new AllBooks(CategoryBookListingActivity.this.name, CategoryBookListingActivity.this.desc, str, CategoryBookListingActivity.this.imguri, CategoryBookListingActivity.this.pDistance, CategoryBookListingActivity.this.publication, CategoryBookListingActivity.this.bookSPsend, CategoryBookListingActivity.this.author));
                                Collections.shuffle(CategoryBookListingActivity.this.booksList);
                                HashSet hashSet = new HashSet(CategoryBookListingActivity.this.booksList);
                                CategoryBookListingActivity.this.booksList.clear();
                                CategoryBookListingActivity.this.booksList.addAll(hashSet);
                                CategoryBookListingActivity.this.adapter = new BookAdapter(CategoryBookListingActivity.this, CategoryBookListingActivity.this.booksList);
                                CategoryBookListingActivity.this.recyclerView.setAdapter(CategoryBookListingActivity.this.adapter);
                            }
                        }
                    });
                }
                progressDialog.dismiss();
            }

            public void onGeoQueryError(DatabaseError databaseError) {
                PrintStream printStream = System.err;
                printStream.println("There was an error with this query: " + databaseError);
                progressDialog.dismiss();
            }
        });
    }
}
