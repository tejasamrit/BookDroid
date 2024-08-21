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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bumptech.glide.Glide;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.geofire.util.GeoUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public static int seenStatusProduct = 1;
    static int unseen;
    final String TAG = "Query Result Activity";
    BookAdapter adapter;
    LinearLayout addNewBook;
    LinearLayout addUsedBook;
    String author;
    String bookSPsend;
    List<AllBooks> booksList;
    BottomNavigationView bottomNavigationView;
    BottomSheetDialog bottomSheetDialog;
    String category;
    Double clat;
    Double clon;
    ImageView closeBottomDialog;
    String country_code;
    String currencySymbolFetch;
    FirebaseDatabase databaseChatDotNotification;
    String desc;
    DrawerLayout drawerLayout;
    EditText editTextSearch;
    View headerView;
    String imguri;
    ArrayList<String> keyList;
    ArrayList<String> list;
    FirebaseAuth mAuth;
    CircleImageView menuIcon;
    FirebaseStorage mref;
    FirebaseFirestore mstore;
    String name;
    TextView nav_email;
    TextView nav_name;
    NavigationView navigationView;
    Double new_radius;
    ImageView noBookFoundImage;
    TextView noBookFoundMsg1;
    TextView noBookFoundMsg2;
    String pDistance;
    Double plat;
    Double plon;
    Double providerdistance;
    String publication;
    SwipeRefreshLayout pullToRefresh;
    Double radius = Double.valueOf(15.0d);
    RecyclerView recyclerView;
    Boolean result = false;
    ImageView searchClose;
    SearchView searchView;
    boolean show = false;
    String stringLat;
    String stringLon;
    String stringRadius = null;
    /* access modifiers changed from: private */
    public Double takingDoubleLat;
    /* access modifiers changed from: private */
    public String takingDoubleLatToString;
    /* access modifiers changed from: private */
    public Double takingDoubleLon;
    /* access modifiers changed from: private */
    public String takingDoubleLonToString;
    TextView toolBarTitle;
    private Toolbar toolbar;
    ImageView toolbar_logo;
    int unicode = 10004;
    String userID;
    CircleImageView viewImage;

    public void onNavigationItemReselected(MenuItem menuItem) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_dashboard);
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        this.drawerLayout = (DrawerLayout) findViewById(C0699R.C0702id.drawer);
        this.navigationView = (NavigationView) findViewById(C0699R.C0702id.navigation);
        this.menuIcon = (CircleImageView) findViewById(C0699R.C0702id.toolbar_image_icon);
        Toolbar toolbar2 = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.toolbar_logo = (ImageView) findViewById(C0699R.C0702id.toolbar_logo);
        this.searchClose = (ImageView) findViewById(C0699R.C0702id.search_close_btn);
        this.editTextSearch = (EditText) findViewById(C0699R.C0702id.search_src_text);
        this.searchView = (SearchView) findViewById(C0699R.C0702id.action_search);
        View headerView2 = this.navigationView.getHeaderView(0);
        this.headerView = headerView2;
        this.nav_name = (TextView) headerView2.findViewById(C0699R.C0702id.nav_header_name);
        this.nav_email = (TextView) this.headerView.findViewById(C0699R.C0702id.nav_header_email);
        this.viewImage = (CircleImageView) this.headerView.findViewById(C0699R.C0702id.nav_header_image);
        this.noBookFoundImage = (ImageView) findViewById(C0699R.C0702id.noBookFoundImage);
        this.noBookFoundMsg1 = (TextView) findViewById(C0699R.C0702id.noBookFoundMsg1);
        this.noBookFoundMsg2 = (TextView) findViewById(C0699R.C0702id.noBookFoundMsg2);
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) findViewById(C0699R.C0702id.bottomNavigationView);
        this.bottomNavigationView = bottomNavigationView2;
        bottomNavigationView2.setOnNavigationItemSelectedListener(this);
        this.bottomNavigationView.setSelectedItemId(C0699R.C0702id.home_bottom_nav);
        this.recyclerView = (RecyclerView) findViewById(C0699R.C0702id.recyclerView);
        this.toolbar_logo.setVisibility(0);
        this.searchClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dashboard.this.toolbar_logo.setVisibility(0);
                Dashboard.this.editTextSearch.setText("");
                Dashboard.this.searchView.setIconified(true);
            }
        });
        this.pullToRefresh = (SwipeRefreshLayout) findViewById(C0699R.C0702id.pullToRefresh);
        createBottomSheetDialog();
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        if (this.mAuth.getCurrentUser() != null) {
            this.userID = this.mAuth.getCurrentUser().getUid();
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    if (Dashboard.this.mAuth.getCurrentUser() != null) {
                        Dashboard.this.nav_name.setText(documentSnapshot.getString("displayname"));
                        Dashboard.this.nav_email.setText(documentSnapshot.getString("phone"));
                        Dashboard.this.clat = documentSnapshot.getDouble("Latitude");
                        Dashboard.this.clon = documentSnapshot.getDouble("Longitude");
                        if (Dashboard.this.stringLon != null && Dashboard.this.stringLat != null && FirebaseAuth.getInstance() != null) {
                            Dashboard dashboard = Dashboard.this;
                            dashboard.clat = Double.valueOf(Double.parseDouble(dashboard.stringLat));
                            Dashboard dashboard2 = Dashboard.this;
                            dashboard2.clon = Double.valueOf(Double.parseDouble(dashboard2.stringLon));
                            Dashboard.this.searchAround();
                        } else if ((Dashboard.this.stringLon == null || Dashboard.this.stringLat == null) && Dashboard.this.clon != null && Dashboard.this.clat != null) {
                            Dashboard dashboard3 = Dashboard.this;
                            dashboard3.stringLat = Dashboard.this.clat + "";
                            Dashboard dashboard4 = Dashboard.this;
                            dashboard4.stringLon = Dashboard.this.clon + "";
                            Dashboard.this.searchAround();
                        }
                    }
                }
            });
        }
        this.viewImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dashboard.this.startActivity(new Intent(Dashboard.this, ProfileActivity.class));
            }
        });
        FirebaseStorage instance = FirebaseStorage.getInstance();
        this.mref = instance;
        StorageReference reference = instance.getReference("ProfileImages/");
        reference.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                Glide.with((FragmentActivity) Dashboard.this).load(uri.toString()).into((ImageView) Dashboard.this.viewImage);
                Glide.with((FragmentActivity) Dashboard.this).load(uri.toString()).into((ImageView) Dashboard.this.menuIcon);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception exc) {
            }
        });
        this.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (Dashboard.this.stringLat != null && Dashboard.this.stringLon != null && FirebaseAuth.getInstance() != null) {
                    Dashboard dashboard = Dashboard.this;
                    dashboard.clat = Double.valueOf(Double.parseDouble(dashboard.stringLat));
                    Dashboard dashboard2 = Dashboard.this;
                    dashboard2.clon = Double.valueOf(Double.parseDouble(dashboard2.stringLon));
                    Dashboard.this.searchAround();
                } else if (!((Dashboard.this.stringLon != null && Dashboard.this.stringLat != null) || Dashboard.this.clon == null || Dashboard.this.clat == null)) {
                    Dashboard.this.searchAround();
                }
                Dashboard.this.pullToRefresh.setRefreshing(false);
            }
        });
        this.navigationView.setNavigationItemSelectedListener(this);
        navigationDrawer();
        this.searchView.setOnSearchClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dashboard.this.toolbar_logo.setVisibility(8);
            }
        });
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                if (Dashboard.this.adapter != null) {
                    Dashboard.this.adapter.getFilter().filter(str);
                }
                Dashboard.hideKeyboard(Dashboard.this);
                return true;
            }

            public boolean onQueryTextChange(String str) {
                if (!(str == null || Dashboard.this.adapter == null)) {
                    Dashboard.this.adapter.getFilter().filter(str);
                }
                if (str != null) {
                    return true;
                }
                Dashboard.this.toolbar_logo.setVisibility(0);
                return true;
            }
        });
        FirebaseDatabase instance2 = FirebaseDatabase.getInstance();
        this.databaseChatDotNotification = instance2;
        instance2.getReference().child("USERS").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                Dashboard.seenStatusProduct = 1;
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    User user = (User) value.getValue(User.class);
                    Dashboard.seenStatusProduct *= user.getMsgSeen() != null ? user.getMsgSeen().booleanValue() : true ? 1 : 0;
                }
                if (Dashboard.seenStatusProduct == 0) {
                    Dashboard.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(true);
                } else {
                    Dashboard.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(false);
                }
                FirebaseDatabase.getInstance().getReference().child("isChatSeen").child(FirebaseAuth.getInstance().getUid()).setValue(Integer.valueOf(Dashboard.seenStatusProduct));
            }
        });
    }

    private void navigationDrawer() {
        this.navigationView.bringToFront();
        this.menuIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Dashboard.this.drawerLayout.isDrawerVisible((int) GravityCompat.START)) {
                    Dashboard.this.drawerLayout.closeDrawer((int) GravityCompat.START);
                } else {
                    Dashboard.this.drawerLayout.openDrawer((int) GravityCompat.START);
                }
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        String str;
        this.drawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case C0699R.C0702id.aboutus /*2131296336*/:
                Intent intent = new Intent(this, ShowHTML.class);
                intent.putExtra("page", "about");
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            case C0699R.C0702id.ask_support /*2131296403*/:
                Intent intent2 = new Intent(this, ChatActivity.class);
                intent2.putExtra("name", "Support Team " + getEmojiByUnicode(this.unicode));
                intent2.putExtra("uid", getApplicationContext().getString(C0699R.string.SUPPORT_TEAM_ID));
                startActivity(intent2);
                return true;
            case C0699R.C0702id.category_bottom_nav /*2131296450*/:
                Intent intent3 = new Intent(this, CategorySelection.class);
                intent3.putExtra("g1", this.stringLat);
                intent3.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent3);
                overridePendingTransition(0, 0);
                return true;
            case C0699R.C0702id.chat_bottom_nav /*2131296457*/:
                Intent intent4 = new Intent(this, AllChatsActivity.class);
                if (this.stringLat == null || this.stringLon == null) {
                    if (this.mAuth.getCurrentUser() != null) {
                        this.userID = this.mAuth.getCurrentUser().getUid();
                        this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                                if (Dashboard.this.mAuth.getCurrentUser() != null) {
                                    Double unused = Dashboard.this.takingDoubleLat = documentSnapshot.getDouble("Latitude");
                                    Double unused2 = Dashboard.this.takingDoubleLon = documentSnapshot.getDouble("Longitude");
                                    Dashboard dashboard = Dashboard.this;
                                    String unused3 = dashboard.takingDoubleLatToString = Dashboard.this.takingDoubleLat + "";
                                    Dashboard dashboard2 = Dashboard.this;
                                    String unused4 = dashboard2.takingDoubleLonToString = Dashboard.this.takingDoubleLon + "";
                                }
                            }
                        });
                    }
                    String str2 = this.takingDoubleLatToString;
                    if (!(str2 == null || (str = this.takingDoubleLonToString) == null)) {
                        this.stringLat = str2;
                        this.stringLon = str;
                    }
                }
                intent4.putExtra("g1", this.stringLat);
                intent4.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent4);
                overridePendingTransition(0, 0);
                finish();
                return true;
            case C0699R.C0702id.home_bottom_nav /*2131296618*/:
                break;
            case C0699R.C0702id.logout_menu /*2131296665*/:
                FirebaseAuth.getInstance().signOut();
                Intent intent5 = new Intent(this, MainActivity.class);
                intent5.setFlags(268468224);
                startActivity(intent5);
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
            case C0699R.C0702id.privacy_policy /*2131296765*/:
                Intent intent6 = new Intent(this, ShowHTML.class);
                intent6.putExtra("page", "privacy");
                overridePendingTransition(0, 0);
                startActivity(intent6);
                overridePendingTransition(0, 0);
                return true;
            case C0699R.C0702id.profile_menu /*2131296781*/:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case C0699R.C0702id.rate_us_menu /*2131296827*/:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable To Open. \n" + e.getMessage(), 0).show();
                }
                return true;
            case C0699R.C0702id.report_bug /*2131296863*/:
                Intent intent7 = new Intent(this, ShowHTML.class);
                intent7.putExtra("page", "bug");
                overridePendingTransition(0, 0);
                startActivity(intent7);
                overridePendingTransition(0, 0);
                return true;
            case C0699R.C0702id.sell_bottom_nav /*2131296899*/:
                this.bottomSheetDialog.show();
                return false;
            case C0699R.C0702id.sell_used_books /*2131296900*/:
                Intent intent8 = new Intent(this, ChooseCategoryOfBook.class);
                intent8.putExtra("g1", this.stringLat);
                intent8.putExtra("g2", this.stringLon);
                startActivity(intent8);
                return true;
            case C0699R.C0702id.selling_bottom_nav /*2131296902*/:
                Intent intent9 = new Intent(this, SellingItems.class);
                intent9.putExtra("g1", this.stringLat);
                intent9.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent9);
                overridePendingTransition(0, 0);
                return true;
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
                return true;
            default:
                return false;
        }
        return true;
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

    public void onClick(View view) {
        int id = view.getId();
        if (id == C0699R.C0702id.closeBottomSheetDialog) {
            this.bottomSheetDialog.dismiss();
        } else if (id == C0699R.C0702id.newBookLayout) {
            Intent intent = new Intent(this, NewBookActivity.class);
            intent.putExtra("g1", this.stringLat);
            intent.putExtra("g2", this.stringLon);
            startActivity(intent);
            this.bottomSheetDialog.dismiss();
        } else if (id == C0699R.C0702id.usedBookLayout) {
            Intent intent2 = new Intent(this, ChooseCategoryOfBook.class);
            intent2.putExtra("g1", this.stringLat);
            intent2.putExtra("g2", this.stringLon);
            startActivity(intent2);
            this.bottomSheetDialog.dismiss();
        }
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
                Dashboard.this.list.add(str);
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
                Dashboard.this.booksList = new ArrayList();
                Dashboard.this.recyclerView.setHasFixedSize(true);
                Dashboard.this.recyclerView.setLayoutManager(new LinearLayoutManager(Dashboard.this));
                if (Dashboard.this.list.size() == 0) {
                    Dashboard.this.noBookFoundImage.setVisibility(0);
                    Dashboard.this.noBookFoundMsg1.setVisibility(0);
                    Dashboard.this.noBookFoundMsg2.setVisibility(0);
                    Dashboard.this.searchView.setVisibility(8);
                } else {
                    Dashboard.this.noBookFoundImage.setVisibility(8);
                    Dashboard.this.noBookFoundMsg1.setVisibility(8);
                    Dashboard.this.noBookFoundMsg2.setVisibility(8);
                    Dashboard.this.searchView.setVisibility(0);
                }
                for (int i = 0; i < Dashboard.this.list.size(); i++) {
                    final String str = Dashboard.this.list.get(i);
                    Dashboard.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                    Dashboard.this.mstore.collection("AllBook").document(str).addSnapshotListener((Activity) Dashboard.this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                            Dashboard.this.name = documentSnapshot.getString("BookName");
                            Dashboard.this.desc = documentSnapshot.getString("BookDescription");
                            Dashboard.this.publication = documentSnapshot.getString("Publication");
                            Dashboard.this.plat = documentSnapshot.getDouble("Latitude");
                            Dashboard.this.plon = documentSnapshot.getDouble("Longitude");
                            Dashboard.this.imguri = documentSnapshot.getString("displayURL1");
                            Dashboard.this.currencySymbolFetch = documentSnapshot.getString("Symbol");
                            Dashboard dashboard = Dashboard.this;
                            dashboard.bookSPsend = Dashboard.this.currencySymbolFetch + " " + documentSnapshot.getString("SellingPrice");
                            Dashboard.this.country_code = documentSnapshot.getString("CountryCode");
                            Dashboard.this.author = documentSnapshot.getString("Author");
                            if ((Dashboard.this.imguri == "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c" || Dashboard.this.imguri == null) && Dashboard.this.imguri == null) {
                                Dashboard.this.imguri = documentSnapshot.getString("displayURL2");
                                if (Dashboard.this.imguri == null) {
                                    Dashboard.this.imguri = "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c";
                                }
                            }
                            if (Dashboard.this.plat != null && Dashboard.this.plon != null) {
                                Dashboard.this.providerdistance = Double.valueOf(GeoUtils.distance(Dashboard.this.clat.doubleValue(), Dashboard.this.clon.doubleValue(), Dashboard.this.plat.doubleValue(), Dashboard.this.plon.doubleValue()));
                                if (Dashboard.this.country_code != null) {
                                    Dashboard.this.country_code = Dashboard.this.country_code.trim();
                                }
                                if ((Dashboard.this.country_code == null || !Dashboard.this.country_code.equalsIgnoreCase("US")) && !Dashboard.this.country_code.equalsIgnoreCase("GB") && !Dashboard.this.country_code.equalsIgnoreCase("USA") && !Dashboard.this.country_code.equalsIgnoreCase("LR") && !Dashboard.this.country_code.equalsIgnoreCase("MM")) {
                                    if (Dashboard.this.providerdistance.doubleValue() > 500.0d) {
                                        Dashboard.this.providerdistance = Double.valueOf(Dashboard.this.providerdistance.doubleValue() / 1000.0d);
                                        Dashboard.this.providerdistance = Double.valueOf(Math.floor(Dashboard.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                        Dashboard dashboard2 = Dashboard.this;
                                        dashboard2.pDistance = Dashboard.this.providerdistance + " km away";
                                    } else {
                                        Dashboard.this.providerdistance = Double.valueOf(Math.floor(Dashboard.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                        Dashboard dashboard3 = Dashboard.this;
                                        dashboard3.pDistance = Dashboard.this.providerdistance + "";
                                        if (Dashboard.this.providerdistance.doubleValue() < 2.0d) {
                                            Dashboard dashboard4 = Dashboard.this;
                                            dashboard4.pDistance = Dashboard.this.pDistance.substring(0, Dashboard.this.pDistance.length() - 3) + " meter away";
                                        } else {
                                            Dashboard dashboard5 = Dashboard.this;
                                            dashboard5.pDistance = Dashboard.this.pDistance.substring(0, Dashboard.this.pDistance.length() - 3) + " meters away";
                                        }
                                    }
                                } else if (Dashboard.this.providerdistance.doubleValue() > 1609.344d) {
                                    Dashboard.this.providerdistance = Double.valueOf(Dashboard.this.providerdistance.doubleValue() / 1609.344d);
                                    Dashboard.this.providerdistance = Double.valueOf(Math.floor(Dashboard.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                    if (Dashboard.this.providerdistance.doubleValue() > 2.0d) {
                                        Dashboard dashboard6 = Dashboard.this;
                                        dashboard6.pDistance = Dashboard.this.providerdistance + " miles away ";
                                    } else {
                                        Dashboard dashboard7 = Dashboard.this;
                                        dashboard7.pDistance = Dashboard.this.providerdistance + " mile away";
                                    }
                                } else {
                                    Dashboard.this.providerdistance = Double.valueOf(Math.floor(Dashboard.this.providerdistance.doubleValue() * 100.0d) / 100.0d);
                                    Dashboard dashboard8 = Dashboard.this;
                                    dashboard8.pDistance = Dashboard.this.providerdistance + " mile away";
                                }
                                Dashboard.this.booksList.add(new AllBooks(Dashboard.this.name, Dashboard.this.desc, str, Dashboard.this.imguri, Dashboard.this.pDistance, Dashboard.this.publication, Dashboard.this.bookSPsend, Dashboard.this.author));
                                Collections.shuffle(Dashboard.this.booksList);
                                HashSet hashSet = new HashSet(Dashboard.this.booksList);
                                Dashboard.this.booksList.clear();
                                Dashboard.this.booksList.addAll(hashSet);
                                Dashboard.this.adapter = new BookAdapter(Dashboard.this, Dashboard.this.booksList);
                                Dashboard.this.recyclerView.setAdapter(Dashboard.this.adapter);
                            }
                        }
                    });
                }
            }

            public void onGeoQueryError(DatabaseError databaseError) {
                PrintStream printStream = System.err;
                printStream.println("There was an error with this query: " + databaseError);
                progressDialog.dismiss();
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        currentFocus.clearFocus();
    }

    public void onBackPressed() {
        if (this.drawerLayout.isDrawerVisible((int) GravityCompat.START)) {
            this.drawerLayout.closeDrawer((int) GravityCompat.START);
        } else if (!this.searchView.isIconified()) {
            this.editTextSearch.setText("");
            this.searchView.setIconified(true);
            this.toolbar_logo.setVisibility(0);
            hideKeyboard(this);
        } else {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            startActivity(intent);
        }
    }

    public String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }
}
