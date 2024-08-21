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
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
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
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class CategorySelection extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    LinearLayout addNewBook;
    LinearLayout addUsedBook;
    BottomNavigationView bottomNavigationView;
    BottomSheetDialog bottomSheetDialog;
    private CardView cardArt;
    private CardView cardBiographics;
    private CardView cardBusiness;
    private CardView cardComputerScience;
    private CardView cardEducation;
    private CardView cardHealth;
    private CardView cardHistory;
    private CardView cardLiterature;
    private CardView cardMaths;
    private CardView cardMedical;
    private CardView cardNovel;
    private CardView cardOthers;
    private CardView cardScience;
    String category;
    ImageView closeBottomDialog;
    private FirebaseDatabase databaseChatDotNotification;
    DrawerLayout drawerLayout;
    View headerView;
    FirebaseAuth mAuth;
    CircleImageView menuIcon;
    FirebaseStorage mref;
    FirebaseFirestore mstore;
    TextView nav_name;
    TextView nav_phone;
    NavigationView navigationView;
    /* access modifiers changed from: private */
    public String strArt = "Art";
    /* access modifiers changed from: private */
    public String strBiographics = "Biographic";
    /* access modifiers changed from: private */
    public String strBusiness = "Business";
    /* access modifiers changed from: private */
    public String strComputerScience = "ComputerScience";
    /* access modifiers changed from: private */
    public String strEducation = "Education";
    /* access modifiers changed from: private */
    public String strHealth = "Health";
    /* access modifiers changed from: private */
    public String strHistory = "History";
    /* access modifiers changed from: private */
    public String strLiterature = "Literature";
    /* access modifiers changed from: private */
    public String strMaths = "Maths";
    /* access modifiers changed from: private */
    public String strMedical = "Medical";
    /* access modifiers changed from: private */
    public String strNovels = "Novels";
    /* access modifiers changed from: private */
    public String strOthers = "Others";
    /* access modifiers changed from: private */
    public String strScience = "Science";
    String stringLat;
    String stringLon;
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
    int unicode = 10004;
    String userID;
    CircleImageView viewImage;

    public void onNavigationItemReselected(MenuItem menuItem) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_category_selection);
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) findViewById(C0699R.C0702id.bottomNavigationViewCategory);
        this.bottomNavigationView = bottomNavigationView2;
        bottomNavigationView2.setOnNavigationItemSelectedListener(this);
        this.bottomNavigationView.setSelectedItemId(C0699R.C0702id.category_bottom_nav);
        this.drawerLayout = (DrawerLayout) findViewById(C0699R.C0702id.drawerCategory);
        this.navigationView = (NavigationView) findViewById(C0699R.C0702id.navigationCategory);
        this.cardEducation = (CardView) findViewById(C0699R.C0702id.categorySelectEducationCategoryBookListing);
        this.cardComputerScience = (CardView) findViewById(C0699R.C0702id.categorySelectComputerScienceCategoryBookListing);
        this.cardScience = (CardView) findViewById(C0699R.C0702id.categorySelectScienceCategoryBookListing);
        this.cardMaths = (CardView) findViewById(C0699R.C0702id.categorySelectMathsCategoryBookListing);
        this.cardMedical = (CardView) findViewById(C0699R.C0702id.categorySelectMedicalCategoryBookListing);
        this.cardLiterature = (CardView) findViewById(C0699R.C0702id.categorySelectLiteratureCategoryBookListing);
        this.cardHistory = (CardView) findViewById(C0699R.C0702id.categorySelectHistoryCategoryBookListing);
        this.cardHealth = (CardView) findViewById(C0699R.C0702id.categorySelectHealthandFitnessCategoryBookListing);
        this.cardBusiness = (CardView) findViewById(C0699R.C0702id.categorySelectBusinessandFinanceCategoryBookListing);
        this.cardArt = (CardView) findViewById(C0699R.C0702id.categorySelectArtandMusicCategoryBookListing);
        this.cardBiographics = (CardView) findViewById(C0699R.C0702id.categorySelectBiographicsCategoryBookListing);
        this.cardNovel = (CardView) findViewById(C0699R.C0702id.categorySelectNovelCategoryBookListing);
        this.cardOthers = (CardView) findViewById(C0699R.C0702id.categorySelectOthersCategoryBookListing);
        this.menuIcon = (CircleImageView) findViewById(C0699R.C0702id.toolbar_image_icon_other);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other);
        Toolbar toolbar2 = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        this.toolBarTitle.setText("Explore Categories");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View headerView2 = this.navigationView.getHeaderView(0);
        this.headerView = headerView2;
        this.nav_name = (TextView) headerView2.findViewById(C0699R.C0702id.nav_header_name);
        this.nav_phone = (TextView) this.headerView.findViewById(C0699R.C0702id.nav_header_email);
        this.viewImage = (CircleImageView) this.headerView.findViewById(C0699R.C0702id.nav_header_image);
        createBottomSheetDialog();
        this.navigationView.setNavigationItemSelectedListener(this);
        navigationDrawer();
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        if (this.mAuth.getCurrentUser() != null) {
            this.userID = this.mAuth.getCurrentUser().getUid();
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    if (CategorySelection.this.mAuth.getCurrentUser() != null) {
                        CategorySelection.this.nav_name.setText(documentSnapshot.getString("displayname"));
                        CategorySelection.this.nav_phone.setText(documentSnapshot.getString("phone"));
                    }
                }
            });
        }
        this.viewImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CategorySelection.this.startActivity(new Intent(CategorySelection.this, ProfileActivity.class));
            }
        });
        FirebaseStorage instance = FirebaseStorage.getInstance();
        this.mref = instance;
        StorageReference reference = instance.getReference("ProfileImages/");
        reference.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                Glide.with((FragmentActivity) CategorySelection.this).load(uri.toString()).into((ImageView) CategorySelection.this.viewImage);
                Glide.with((FragmentActivity) CategorySelection.this).load(uri.toString()).into((ImageView) CategorySelection.this.menuIcon);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception exc) {
            }
        });
        this.cardEducation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strEducation);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardComputerScience.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strComputerScience);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardScience.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strScience);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardMaths.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strMaths);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardMedical.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strMedical);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardLiterature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strLiterature);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strHistory);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardHealth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strHealth);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardBusiness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strBusiness);
                CategorySelection.this.startActivity(intent);
            }
        });
        this.cardArt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strArt);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardBiographics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strBiographics);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardNovel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strNovels);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        this.cardOthers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelection.this, CategoryBookListingActivity.class);
                intent.putExtra("g1", CategorySelection.this.stringLat);
                intent.putExtra("g2", CategorySelection.this.stringLon);
                intent.putExtra("cat", CategorySelection.this.strOthers);
                CategorySelection.this.overridePendingTransition(0, 0);
                CategorySelection.this.startActivity(intent);
                CategorySelection.this.overridePendingTransition(0, 0);
            }
        });
        FirebaseDatabase instance2 = FirebaseDatabase.getInstance();
        this.databaseChatDotNotification = instance2;
        instance2.getReference().child("isChatSeen").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (((Integer) dataSnapshot.getValue(Integer.class)).intValue() == 0) {
                    CategorySelection.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(true);
                } else {
                    CategorySelection.this.bottomNavigationView.getOrCreateBadge(C0699R.C0702id.chat_bottom_nav).setVisible(false);
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

    private void navigationDrawer() {
        this.navigationView.bringToFront();
        this.menuIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CategorySelection.this.drawerLayout.isDrawerVisible((int) GravityCompat.START)) {
                    CategorySelection.this.drawerLayout.closeDrawer((int) GravityCompat.START);
                } else {
                    CategorySelection.this.drawerLayout.openDrawer((int) GravityCompat.START);
                }
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        String str;
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
                break;
            case C0699R.C0702id.chat_bottom_nav /*2131296457*/:
                Intent intent3 = new Intent(this, AllChatsActivity.class);
                if (this.stringLat == null || this.stringLon == null) {
                    if (this.mAuth.getCurrentUser() != null) {
                        this.userID = this.mAuth.getCurrentUser().getUid();
                        this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                                if (CategorySelection.this.mAuth.getCurrentUser() != null) {
                                    Double unused = CategorySelection.this.takingDoubleLat = documentSnapshot.getDouble("Latitude");
                                    Double unused2 = CategorySelection.this.takingDoubleLon = documentSnapshot.getDouble("Longitude");
                                    CategorySelection categorySelection = CategorySelection.this;
                                    String unused3 = categorySelection.takingDoubleLatToString = CategorySelection.this.takingDoubleLat + "";
                                    CategorySelection categorySelection2 = CategorySelection.this;
                                    String unused4 = categorySelection2.takingDoubleLonToString = CategorySelection.this.takingDoubleLon + "";
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
                intent3.putExtra("g1", this.stringLat);
                intent3.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent3);
                overridePendingTransition(0, 0);
                finish();
                break;
            case C0699R.C0702id.home_bottom_nav /*2131296618*/:
                Intent intent4 = new Intent(this, Dashboard.class);
                intent4.putExtra("g1", this.stringLat);
                intent4.putExtra("g2", this.stringLon);
                overridePendingTransition(0, 0);
                startActivity(intent4);
                overridePendingTransition(0, 0);
                finish();
                return true;
            case C0699R.C0702id.logout_menu /*2131296665*/:
                FirebaseAuth.getInstance().signOut();
                Intent intent5 = new Intent(this, MainActivity.class);
                intent5.setFlags(268468224);
                startActivity(intent5);
                finish();
                FirebaseAuth.getInstance().signOut();
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.privacy_policy /*2131296765*/:
                Intent intent6 = new Intent(this, ShowHTML.class);
                intent6.putExtra("page", "policy");
                overridePendingTransition(0, 0);
                startActivity(intent6);
                overridePendingTransition(0, 0);
                this.drawerLayout.closeDrawers();
                return true;
            case C0699R.C0702id.profile_menu /*2131296781*/:
                startActivity(new Intent(this, ProfileActivity.class));
                this.drawerLayout.closeDrawers();
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
        if (this.drawerLayout.isDrawerVisible((int) GravityCompat.START)) {
            this.drawerLayout.closeDrawer((int) GravityCompat.START);
            return;
        }
        Intent intent = new Intent(this, Dashboard.class);
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
