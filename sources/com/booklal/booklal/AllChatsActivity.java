package com.booklal.booklal;

import android.app.Activity;
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
import com.bumptech.glide.Glide;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
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
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class AllChatsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    /* access modifiers changed from: private */
    public String SendName;
    /* access modifiers changed from: private */
    public String SendPhoneNumber;
    /* access modifiers changed from: private */
    public String SendProfileImage;
    private LinearLayout addNewBook;
    private LinearLayout addUsedBook;
    /* access modifiers changed from: private */
    public BottomNavigationView bottomNavigationView;
    private BottomSheetDialog bottomSheetDialog;
    private String category;
    private Double clat;
    private Double clon;
    private ImageView closeBottomDialog;
    FirebaseDatabase database;
    private FirebaseDatabase databaseChatDotNotification;

    /* renamed from: db */
    private FirebaseFirestore f72db;
    /* access modifiers changed from: private */
    public DrawerLayout drawerLayout;
    private View headerView;
    /* access modifiers changed from: private */
    public ImageView imgShow;
    long longTime;
    /* access modifiers changed from: private */
    public FirebaseAuth mAuth;
    /* access modifiers changed from: private */
    public CircleImageView menuIcon;
    private FirebaseStorage mref;
    /* access modifiers changed from: private */
    public FirebaseFirestore mstore;
    /* access modifiers changed from: private */
    public TextView nav_name;
    /* access modifiers changed from: private */
    public TextView nav_phone;
    private NavigationView navigationView;
    ShimmerRecyclerView shimmerRecyclerView;
    /* access modifiers changed from: private */
    public String stringLat;
    /* access modifiers changed from: private */
    public String stringLon;
    String stringTime;
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
    User user;
    private String userID;
    ArrayList<User> users;
    UsersAdapter usersAdapter;
    /* access modifiers changed from: private */
    public CircleImageView viewImage;

    public void onNavigationItemReselected(MenuItem menuItem) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_all_chats);
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) findViewById(C0699R.C0702id.bottomNavigationViewAllChats);
        this.bottomNavigationView = bottomNavigationView2;
        bottomNavigationView2.setOnNavigationItemSelectedListener(this);
        this.bottomNavigationView.setSelectedItemId(C0699R.C0702id.chat_bottom_nav);
        this.imgShow = (ImageView) findViewById(C0699R.C0702id.chatsNotAvailableImage);
        this.textShow = (TextView) findViewById(C0699R.C0702id.chatsNotAvailableMsg);
        this.shimmerRecyclerView = (ShimmerRecyclerView) findViewById(C0699R.C0702id.shimmer_recycler_view);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other);
        Toolbar toolbar2 = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        this.menuIcon = (CircleImageView) findViewById(C0699R.C0702id.toolbar_image_icon_other);
        this.toolBarTitle.setText("Messages");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView2 = (NavigationView) findViewById(C0699R.C0702id.navigationAllChats);
        this.navigationView = navigationView2;
        View headerView2 = navigationView2.getHeaderView(0);
        this.headerView = headerView2;
        this.nav_name = (TextView) headerView2.findViewById(C0699R.C0702id.nav_header_name);
        this.nav_phone = (TextView) this.headerView.findViewById(C0699R.C0702id.nav_header_email);
        this.viewImage = (CircleImageView) this.headerView.findViewById(C0699R.C0702id.nav_header_image);
        this.drawerLayout = (DrawerLayout) findViewById(C0699R.C0702id.drawerChats);
        createBottomSheetDialog();
        this.navigationView.setNavigationItemSelectedListener(this);
        navigationDrawer();
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        this.f72db = FirebaseFirestore.getInstance();
        ((ShimmerRecyclerView) findViewById(C0699R.C0702id.shimmer_recycler_view)).showShimmerAdapter();
        if (this.mAuth.getCurrentUser() != null) {
            this.userID = this.mAuth.getCurrentUser().getUid();
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    if (AllChatsActivity.this.mAuth.getCurrentUser() != null) {
                        AllChatsActivity.this.nav_name.setText(documentSnapshot.getString("displayname"));
                        AllChatsActivity.this.nav_phone.setText(documentSnapshot.getString("phone"));
                        if (AllChatsActivity.this.stringLat == null || AllChatsActivity.this.stringLon == null) {
                            Double unused = AllChatsActivity.this.takingDoubleLat = documentSnapshot.getDouble("Latitude");
                            Double unused2 = AllChatsActivity.this.takingDoubleLon = documentSnapshot.getDouble("Longitude");
                            AllChatsActivity allChatsActivity = AllChatsActivity.this;
                            String unused3 = allChatsActivity.takingDoubleLatToString = AllChatsActivity.this.takingDoubleLat + "";
                            AllChatsActivity allChatsActivity2 = AllChatsActivity.this;
                            String unused4 = allChatsActivity2.takingDoubleLonToString = AllChatsActivity.this.takingDoubleLon + "";
                        }
                    }
                }
            });
        }
        this.viewImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AllChatsActivity.this.startActivity(new Intent(AllChatsActivity.this, ProfileActivity.class));
            }
        });
        FirebaseStorage instance = FirebaseStorage.getInstance();
        this.mref = instance;
        StorageReference reference = instance.getReference("ProfileImages/");
        reference.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                Glide.with(AllChatsActivity.this.getApplicationContext()).load(uri.toString()).into((ImageView) AllChatsActivity.this.viewImage);
                Glide.with(AllChatsActivity.this.getApplicationContext()).load(uri.toString()).into((ImageView) AllChatsActivity.this.menuIcon);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception exc) {
            }
        });
        this.database = FirebaseDatabase.getInstance();
        ArrayList<User> arrayList = new ArrayList<>();
        this.users = arrayList;
        this.usersAdapter = new UsersAdapter(this, arrayList);
        this.database.getReference().child("USERS").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                AllChatsActivity.this.users.clear();
                AllChatsActivity.this.shimmerRecyclerView.setAdapter(AllChatsActivity.this.usersAdapter);
                AllChatsActivity.this.shimmerRecyclerView.showShimmerAdapter();
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    final User user = (User) value.getValue(User.class);
                    String uid = user.getUid();
                    if (user.getUid() != null && !user.getUid().equals(FirebaseAuth.getInstance().getUid())) {
                        AllChatsActivity.this.mstore.collection("users").document(uid).addSnapshotListener((Activity) AllChatsActivity.this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                                String unused = AllChatsActivity.this.SendName = documentSnapshot.getString("displayname");
                                String unused2 = AllChatsActivity.this.SendPhoneNumber = documentSnapshot.getString("phone");
                                String unused3 = AllChatsActivity.this.SendProfileImage = documentSnapshot.getString("profileURL");
                                if (AllChatsActivity.this.SendProfileImage == null) {
                                    String unused4 = AllChatsActivity.this.SendProfileImage = "ABC";
                                }
                                AllChatsActivity.this.users.add(new User(user.getUid(), AllChatsActivity.this.SendName, AllChatsActivity.this.SendPhoneNumber, AllChatsActivity.this.SendProfileImage, user.getStringTimeStamp()));
                                Collections.sort(AllChatsActivity.this.users, User.recentChatsComparator);
                                AllChatsActivity.this.usersAdapter.notifyDataSetChanged();
                                AllChatsActivity.this.imgShow.setVisibility(8);
                                AllChatsActivity.this.textShow.setVisibility(8);
                            }
                        });
                    }
                }
                if (AllChatsActivity.this.user == null || AllChatsActivity.this.users.size() < 1) {
                    AllChatsActivity.this.shimmerRecyclerView.hideShimmerAdapter();
                    AllChatsActivity.this.imgShow.setVisibility(0);
                    AllChatsActivity.this.textShow.setVisibility(0);
                    AllChatsActivity.this.shimmerRecyclerView.hideShimmerAdapter();
                    return;
                }
                AllChatsActivity.this.shimmerRecyclerView.hideShimmerAdapter();
                AllChatsActivity.this.usersAdapter.notifyDataSetChanged();
            }
        });
        FirebaseDatabase instance2 = FirebaseDatabase.getInstance();
        this.databaseChatDotNotification = instance2;
        instance2.getReference().child("isChatSeen").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (((Integer) dataSnapshot.getValue(Integer.class)).intValue() == 0) {
                    AllChatsActivity.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(true);
                } else {
                    AllChatsActivity.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(false);
                }
            }
        });
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
                if (AllChatsActivity.this.drawerLayout.isDrawerVisible((int) GravityCompat.START)) {
                    AllChatsActivity.this.drawerLayout.closeDrawer((int) GravityCompat.START);
                } else {
                    AllChatsActivity.this.drawerLayout.openDrawer((int) GravityCompat.START);
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
        String str5;
        String str6;
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
                if (!((this.stringLat != null && this.stringLon != null) || (str = this.takingDoubleLatToString) == null || (str2 = this.takingDoubleLonToString) == null)) {
                    this.stringLat = str;
                    this.stringLon = str2;
                }
                intent3.putExtra("g1", this.stringLat);
                intent3.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent3);
                overridePendingTransition(0, 0);
                return true;
            case C0699R.C0702id.chat_bottom_nav /*2131296457*/:
                break;
            case C0699R.C0702id.home_bottom_nav /*2131296618*/:
                Intent intent4 = new Intent(this, Dashboard.class);
                if (!((this.stringLat != null && this.stringLon != null) || (str3 = this.takingDoubleLatToString) == null || (str4 = this.takingDoubleLonToString) == null)) {
                    this.stringLat = str3;
                    this.stringLon = str4;
                }
                intent4.putExtra("g1", this.stringLat);
                intent4.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent4);
                overridePendingTransition(0, 0);
                break;
            case C0699R.C0702id.logout_menu /*2131296665*/:
                FirebaseAuth.getInstance().signOut();
                Intent intent5 = new Intent(this, MainActivity.class);
                intent5.setFlags(268468224);
                startActivity(intent5);
                finish();
                FirebaseAuth.getInstance().signOut();
                return true;
            case C0699R.C0702id.privacy_policy /*2131296765*/:
                Intent intent6 = new Intent(this, ShowHTML.class);
                intent6.putExtra("page", "privacy");
                overridePendingTransition(0, 0);
                startActivity(intent6);
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
                Intent intent7 = new Intent(this, ShowHTML.class);
                intent7.putExtra("page", "bug");
                overridePendingTransition(0, 0);
                startActivity(intent7);
                overridePendingTransition(0, 0);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.sell_bottom_nav /*2131296899*/:
                this.bottomSheetDialog.show();
                return false;
            case C0699R.C0702id.sell_used_books /*2131296900*/:
                Intent intent8 = new Intent(this, ChooseCategoryOfBook.class);
                intent8.putExtra("g1", this.stringLat);
                intent8.putExtra("g2", this.stringLon);
                startActivity(intent8);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.selling_bottom_nav /*2131296902*/:
                Intent intent9 = new Intent(this, SellingItems.class);
                if (!((this.stringLat != null && this.stringLon != null) || (str5 = this.takingDoubleLatToString) == null || (str6 = this.takingDoubleLonToString) == null)) {
                    this.stringLat = str5;
                    this.stringLon = str6;
                }
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
                this.drawerLayout.closeDrawers();
                return true;
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
    }

    public String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }
}
