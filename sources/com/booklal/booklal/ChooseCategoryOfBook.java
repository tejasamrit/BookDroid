package com.booklal.booklal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class ChooseCategoryOfBook extends AppCompatActivity {
    private ImageView backBtnToolbar;
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
    private CardView cardNovels;
    private CardView cardOthers;
    private CardView cardScience;
    private String category;
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
    public String strNovel = "Novels";
    /* access modifiers changed from: private */
    public String strOthers = "Others";
    /* access modifiers changed from: private */
    public String strScience = "Science";
    /* access modifiers changed from: private */
    public String stringLat;
    /* access modifiers changed from: private */
    public String stringLon;
    private TextView toolBarTitle;
    private Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_choose_category_of_book);
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        setSupportActionBar(this.toolbar);
        this.toolBarTitle.setText("Pick Book Category");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.cardEducation = (CardView) findViewById(C0699R.C0702id.choose_categorySelectEducation);
        this.cardComputerScience = (CardView) findViewById(C0699R.C0702id.choose_categorySelectComputerScience);
        this.cardScience = (CardView) findViewById(C0699R.C0702id.choose_categorySelectScience);
        this.cardMaths = (CardView) findViewById(C0699R.C0702id.choose_categorySelectMaths);
        this.cardMedical = (CardView) findViewById(C0699R.C0702id.choose_categorySelectMedical);
        this.cardLiterature = (CardView) findViewById(C0699R.C0702id.choose_categorySelectLiterature);
        this.cardHistory = (CardView) findViewById(C0699R.C0702id.choose_categorySelectHistory);
        this.cardHealth = (CardView) findViewById(C0699R.C0702id.choose_categorySelectHealthandFitness);
        this.cardBusiness = (CardView) findViewById(C0699R.C0702id.choose_categorySelectBusinessandFinance);
        this.cardArt = (CardView) findViewById(C0699R.C0702id.choose_categorySelectArtandMusic);
        this.cardBiographics = (CardView) findViewById(C0699R.C0702id.choose_categorySelectBiographics);
        this.cardNovels = (CardView) findViewById(C0699R.C0702id.choose_categorySelectNovel);
        this.cardOthers = (CardView) findViewById(C0699R.C0702id.choose_categorySelectOthers);
        this.cardEducation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strEducation);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardComputerScience.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strComputerScience);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardScience.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strScience);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardMaths.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strMaths);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardMedical.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strMedical);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardLiterature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strLiterature);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strHistory);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardHealth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strHealth);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardBusiness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strBusiness);
                ChooseCategoryOfBook.this.startActivity(intent);
            }
        });
        this.cardArt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strArt);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardBiographics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strBiographics);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardNovels.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strNovel);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.cardOthers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoryOfBook.this, Take_Book_Information.class);
                intent.putExtra("g1", ChooseCategoryOfBook.this.stringLat);
                intent.putExtra("g2", ChooseCategoryOfBook.this.stringLon);
                intent.putExtra("cat", ChooseCategoryOfBook.this.strOthers);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
                ChooseCategoryOfBook.this.startActivity(intent);
                ChooseCategoryOfBook.this.overridePendingTransition(0, 0);
            }
        });
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseCategoryOfBook.this.onBackPressed();
                ChooseCategoryOfBook.this.finish();
            }
        });
    }
}
