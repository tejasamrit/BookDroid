package com.booklal.booklal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

public class SellingItems extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    SellingBookAdapter adapter;
    private LinearLayout addNewBook;
    private LinearLayout addUsedBook;
    ArrayList<SellingBooks> bookListZero;
    /* access modifiers changed from: private */
    public String bookSPsend;
    /* access modifiers changed from: private */
    public String book_ID;
    ArrayList<SellingBooks> booksList;
    /* access modifiers changed from: private */
    public BottomNavigationView bottomNavigationView;
    private BottomSheetDialog bottomSheetDialog;
    private String category;
    private Double clat;
    private Double clon;
    private ImageView closeBottomDialog;
    /* access modifiers changed from: private */
    public String currencySymbolFetch;
    private FirebaseDatabase databaseChatDotNotification;

    /* renamed from: db */
    private FirebaseFirestore f108db;
    /* access modifiers changed from: private */
    public String desc;
    /* access modifiers changed from: private */
    public DrawerLayout drawerLayout;
    private View headerView;
    /* access modifiers changed from: private */
    public ImageView imgShow;
    /* access modifiers changed from: private */
    public String imguri;
    ArrayList<String> keyList;
    ArrayList<String> list;
    /* access modifiers changed from: private */
    public FirebaseAuth mAuth;
    /* access modifiers changed from: private */
    public CircleImageView menuIcon;
    private FirebaseStorage mref;
    /* access modifiers changed from: private */
    public FirebaseFirestore mstore;
    /* access modifiers changed from: private */
    public String name;
    /* access modifiers changed from: private */
    public TextView nav_name;
    /* access modifiers changed from: private */
    public TextView nav_phone;
    private NavigationView navigationView;
    SwipeRefreshLayout pullToRefresh;
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
    /* access modifiers changed from: private */
    public TextView textShow;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    int unicode = 10004;
    private String userID;
    /* access modifiers changed from: private */
    public CircleImageView viewImage;

    public void onNavigationItemReselected(MenuItem menuItem) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_selling_items);
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) findViewById(C0699R.C0702id.bottomNavigationViewSelling);
        this.bottomNavigationView = bottomNavigationView2;
        bottomNavigationView2.setOnNavigationItemSelectedListener(this);
        this.bottomNavigationView.setSelectedItemId(C0699R.C0702id.selling_bottom_nav);
        this.imgShow = (ImageView) findViewById(C0699R.C0702id.bookNotSellingImage);
        this.textShow = (TextView) findViewById(C0699R.C0702id.bookNotSellingMsg);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other);
        Toolbar toolbar2 = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        this.menuIcon = (CircleImageView) findViewById(C0699R.C0702id.toolbar_image_icon_other);
        this.toolBarTitle.setText("Books You Selling");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.recyclerViewSelling = (RecyclerView) findViewById(C0699R.C0702id.recyclerViewSelling);
        NavigationView navigationView2 = (NavigationView) findViewById(C0699R.C0702id.navigationSelling);
        this.navigationView = navigationView2;
        View headerView2 = navigationView2.getHeaderView(0);
        this.headerView = headerView2;
        this.nav_name = (TextView) headerView2.findViewById(C0699R.C0702id.nav_header_name);
        this.nav_phone = (TextView) this.headerView.findViewById(C0699R.C0702id.nav_header_email);
        this.viewImage = (CircleImageView) this.headerView.findViewById(C0699R.C0702id.nav_header_image);
        this.drawerLayout = (DrawerLayout) findViewById(C0699R.C0702id.drawerSelling);
        this.recyclerViewSelling.setHasFixedSize(true);
        this.recyclerViewSelling.setLayoutManager(new LinearLayoutManager(this));
        createBottomSheetDialog();
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        this.f108db = FirebaseFirestore.getInstance();
        if (this.mAuth.getCurrentUser() != null) {
            this.userID = this.mAuth.getCurrentUser().getUid();
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    if (SellingItems.this.mAuth.getCurrentUser() != null) {
                        SellingItems.this.nav_name.setText(documentSnapshot.getString("displayname"));
                        SellingItems.this.nav_phone.setText(documentSnapshot.getString("phone"));
                        if (SellingItems.this.stringLat == null || SellingItems.this.stringLon == null) {
                            Double unused = SellingItems.this.takingDoubleLat = documentSnapshot.getDouble("Latitude");
                            Double unused2 = SellingItems.this.takingDoubleLon = documentSnapshot.getDouble("Longitude");
                            SellingItems sellingItems = SellingItems.this;
                            String unused3 = sellingItems.takingDoubleLatToString = SellingItems.this.takingDoubleLat + "";
                            SellingItems sellingItems2 = SellingItems.this;
                            String unused4 = sellingItems2.takingDoubleLonToString = SellingItems.this.takingDoubleLon + "";
                        }
                    }
                }
            });
        }
        this.viewImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SellingItems.this.startActivity(new Intent(SellingItems.this, ProfileActivity.class));
            }
        });
        FirebaseStorage instance = FirebaseStorage.getInstance();
        this.mref = instance;
        StorageReference reference = instance.getReference("ProfileImages/");
        reference.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                Glide.with((FragmentActivity) SellingItems.this).load(uri.toString()).into((ImageView) SellingItems.this.viewImage);
                Glide.with((FragmentActivity) SellingItems.this).load(uri.toString()).into((ImageView) SellingItems.this.menuIcon);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception exc) {
            }
        });
        this.navigationView.setNavigationItemSelectedListener(this);
        navigationDrawer();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(C0699R.C0702id.pullToRefreshSelling);
        this.pullToRefresh = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (FirebaseAuth.getInstance() != null) {
                    SellingItems.this.sellingBooks();
                }
                SellingItems.this.pullToRefresh.setRefreshing(false);
            }
        });
        sellingBooks();
        FirebaseDatabase instance2 = FirebaseDatabase.getInstance();
        this.databaseChatDotNotification = instance2;
        instance2.getReference().child("isChatSeen").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (((Integer) dataSnapshot.getValue(Integer.class)).intValue() == 0) {
                    SellingItems.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(true);
                } else {
                    SellingItems.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(false);
                }
            }
        });
    }

    public void sellingBooks() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Searching Books...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        this.list = new ArrayList<>();
        this.booksList = new ArrayList<>();
        this.bookListZero = new ArrayList<>();
        this.userID = this.mAuth.getCurrentUser().getUid();
        this.f108db.collection("AllBook").whereEqualTo("user", (Object) this.userID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Iterator<QueryDocumentSnapshot> it = task.getResult().iterator();
                    while (it.hasNext()) {
                        SellingItems.this.list.add(it.next().getId());
                    }
                }
                if (SellingItems.this.list.size() == 0) {
                    SellingItems.this.imgShow.setVisibility(0);
                    SellingItems.this.textShow.setVisibility(0);
                    SellingItems sellingItems = SellingItems.this;
                    sellingItems.adapter = new SellingBookAdapter(sellingItems, sellingItems.bookListZero);
                    SellingItems.this.recyclerViewSelling.setAdapter(SellingItems.this.adapter);
                    return;
                }
                SellingItems.this.imgShow.setVisibility(8);
                SellingItems.this.textShow.setVisibility(8);
                for (final int i = 0; i < SellingItems.this.list.size(); i++) {
                    String unused = SellingItems.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    SellingItems.this.mstore.collection("AllBook").document(SellingItems.this.list.get(i)).addSnapshotListener((Activity) SellingItems.this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                            String unused = SellingItems.this.name = documentSnapshot.getString("BookName");
                            String unused2 = SellingItems.this.desc = documentSnapshot.getString("BookDescription");
                            String unused3 = SellingItems.this.imguri = documentSnapshot.getString("displayURL1");
                            String unused4 = SellingItems.this.currencySymbolFetch = documentSnapshot.getString("Symbol");
                            SellingItems sellingItems = SellingItems.this;
                            String unused5 = sellingItems.bookSPsend = SellingItems.this.currencySymbolFetch + " " + documentSnapshot.getString("SellingPrice");
                            String unused6 = SellingItems.this.book_ID = SellingItems.this.list.get(i);
                            if ((SellingItems.this.imguri == "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c" || SellingItems.this.imguri == null) && SellingItems.this.imguri == null) {
                                String unused7 = SellingItems.this.imguri = documentSnapshot.getString("displayURL2");
                                if (SellingItems.this.imguri == null) {
                                    String unused8 = SellingItems.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                                }
                            }
                            SellingItems.this.booksList.add(new SellingBooks(SellingItems.this.name, SellingItems.this.desc, SellingItems.this.book_ID, SellingItems.this.imguri, SellingItems.this.bookSPsend));
                            SellingItems.this.adapter = new SellingBookAdapter(SellingItems.this, SellingItems.this.booksList);
                            SellingItems.this.recyclerViewSelling.setAdapter(SellingItems.this.adapter);
                        }
                    });
                }
            }
        });
        progressDialog.dismiss();
    }

    private void createBottomSheetDialog() {
        if (this.bottomSheetDialog == null) {
            View inflate = LayoutInflater.from(this).inflate(C0699R.layout.bottom_sheet, (ViewGroup) null);
            this.closeBottomDialog = (ImageView) inflate.findViewById(C0699R.C0702id.closeBottomSheetDialog);
            this.addUsedBook = (LinearLayout) inflate.findViewById(C0699R.C0702id.usedBookLayout);
            this.addNewBook = (LinearLayout) inflate.findViewById(C0699R.C0702id.newBookLayout);
            this.addUsedBook.setOnClickListener(this);
            this.addNewBook.setOnClickListener(this);
            this.closeBottomDialog.setOnClickListener(this);
            BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(this);
            this.bottomSheetDialog = bottomSheetDialog2;
            bottomSheetDialog2.setContentView(inflate);
        }
    }

    private void navigationDrawer() {
        this.navigationView.bringToFront();
        this.menuIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SellingItems.this.drawerLayout.isDrawerVisible((int) GravityCompat.START)) {
                    SellingItems.this.drawerLayout.closeDrawer((int) GravityCompat.START);
                } else {
                    SellingItems.this.drawerLayout.openDrawer((int) GravityCompat.START);
                }
            }
        });
    }

    public void onClick(View view) {
        String str;
        String str2;
        int id = view.getId();
        if (id == C0699R.C0702id.closeBottomSheetDialog) {
            this.bottomSheetDialog.dismiss();
            this.bottomNavigationView.setSelectedItemId(C0699R.C0702id.selling_bottom_nav);
        } else if (id == C0699R.C0702id.newBookLayout) {
            Intent intent = new Intent(this, NewBookActivity.class);
            intent.putExtra("g1", this.stringLat);
            intent.putExtra("g2", this.stringLon);
            startActivity(intent);
            this.bottomSheetDialog.dismiss();
        } else if (id == C0699R.C0702id.usedBookLayout) {
            Intent intent2 = new Intent(this, ChooseCategoryOfBook.class);
            if (!((this.stringLat != null && this.stringLon != null) || (str = this.takingDoubleLatToString) == null || (str2 = this.takingDoubleLonToString) == null)) {
                this.stringLat = str;
                this.stringLon = str2;
            }
            intent2.putExtra("g1", this.stringLat);
            intent2.putExtra("g2", this.stringLon);
            startActivity(intent2);
            this.bottomSheetDialog.dismiss();
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        String str;
        String str2;
        String str3;
        String str4;
        switch (menuItem.getItemId()) {
            case C0699R.C0702id.aboutus /*2131296336*/:
                Intent intent = new Intent(this, ShowHTML.class);
                intent.putExtra("page", "about");
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.ask_support /*2131296403*/:
                Intent intent2 = new Intent(this, ChatActivity.class);
                intent2.putExtra("name", "Support Team " + getEmojiByUnicode(this.unicode));
                intent2.putExtra("uid", getApplicationContext().getString(C0699R.string.SUPPORT_TEAM_ID));
                startActivity(intent2);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.category_bottom_nav /*2131296450*/:
                Intent intent3 = new Intent(this, CategorySelection.class);
                if (!((this.stringLat != null && this.stringLon != null) || this.takingDoubleLatToString == null || this.takingDoubleLonToString == null)) {
                    Toast.makeText(this, "ONNNNNNNNNN", 0).show();
                    this.stringLat = this.takingDoubleLatToString;
                    this.stringLon = this.takingDoubleLonToString;
                }
                intent3.putExtra("g1", this.stringLat);
                intent3.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent3);
                overridePendingTransition(0, 0);
                return true;
            case C0699R.C0702id.chat_bottom_nav /*2131296457*/:
                Intent intent4 = new Intent(this, AllChatsActivity.class);
                if (!((this.stringLat != null && this.stringLon != null) || (str = this.takingDoubleLatToString) == null || (str2 = this.takingDoubleLonToString) == null)) {
                    this.stringLat = str;
                    this.stringLon = str2;
                }
                intent4.putExtra("g1", this.stringLat);
                intent4.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent4);
                overridePendingTransition(0, 0);
                finish();
                return true;
            case C0699R.C0702id.home_bottom_nav /*2131296618*/:
                Intent intent5 = new Intent(this, Dashboard.class);
                if (!((this.stringLat != null && this.stringLon != null) || (str3 = this.takingDoubleLatToString) == null || (str4 = this.takingDoubleLonToString) == null)) {
                    this.stringLat = str3;
                    this.stringLon = str4;
                }
                intent5.putExtra("g1", this.stringLat);
                intent5.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent5);
                overridePendingTransition(0, 0);
                finish();
                return true;
            case C0699R.C0702id.logout_menu /*2131296665*/:
                FirebaseAuth.getInstance().signOut();
                Intent intent6 = new Intent(this, MainActivity.class);
                intent6.setFlags(268468224);
                startActivity(intent6);
                finish();
                FirebaseAuth.getInstance().signOut();
                return true;
            case C0699R.C0702id.privacy_policy /*2131296765*/:
                Intent intent7 = new Intent(this, ShowHTML.class);
                intent7.putExtra("page", "privacy");
                overridePendingTransition(0, 0);
                startActivity(intent7);
                overridePendingTransition(0, 0);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.profile_menu /*2131296781*/:
                this.drawerLayout.closeDrawers();
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case C0699R.C0702id.rate_us_menu /*2131296827*/:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable To Open. \n" + e.getMessage(), 0).show();
                }
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.report_bug /*2131296863*/:
                Intent intent8 = new Intent(this, ShowHTML.class);
                intent8.putExtra("page", "bug");
                overridePendingTransition(0, 0);
                startActivity(intent8);
                overridePendingTransition(0, 0);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.sell_bottom_nav /*2131296899*/:
                this.bottomSheetDialog.show();
                return false;
            case C0699R.C0702id.sell_used_books /*2131296900*/:
                Intent intent9 = new Intent(this, ChooseCategoryOfBook.class);
                intent9.putExtra("g1", this.stringLat);
                intent9.putExtra("g2", this.stringLon);
                startActivity(intent9);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.selling_bottom_nav /*2131296902*/:
                break;
            case C0699R.C0702id.share_menu /*2131296911*/:
                try {
                    Intent intent10 = new Intent("android.intent.action.SEND");
                    intent10.setType("text/plain");
                    intent10.putExtra("android.intent.extra.SUBJECT", "Share Via");
                    intent10.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName() + "\n\n");
                    startActivity(Intent.createChooser(intent10, "Share via"));
                } catch (Exception e2) {
                    Toast.makeText(this, "Error:\n" + e2.getMessage(), 0).show();
                }
                this.drawerLayout.closeDrawers();
                break;
            default:
                return false;
        }
        return true;
    }

    public void onBackPressed() {
        String str;
        String str2;
        if (this.drawerLayout.isDrawerVisible((int) GravityCompat.START)) {
            this.drawerLayout.closeDrawer((int) GravityCompat.START);
            return;
        }
        Intent intent = new Intent(this, Dashboard.class);
        if (!((this.stringLat != null && this.stringLon != null) || (str = this.takingDoubleLatToString) == null || (str2 = this.takingDoubleLonToString) == null)) {
            this.stringLat = str;
            this.stringLon = str2;
        }
        intent.putExtra("g1", this.stringLat);
        intent.putExtra("g2", this.stringLon);
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }
}
