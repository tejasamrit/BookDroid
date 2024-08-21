package com.booklal.booklal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.BaseAnimation;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.annotation.Nullable;

public class Take_Book_Information extends AppCompatActivity {

    /* renamed from: CC */
    String f109CC;
    String CurrCode;
    final String TAG = "Take_Book_Information";
    EditText authorName;
    private ImageView backBtnToolbar;

    /* renamed from: bc */
    int f110bc;
    String bookCategory;
    String bookImg1ID;
    String bookImg2ID;
    EditText bookName;
    String book_author;
    String book_name;
    String book_publication;
    String bookcount;
    CountryCurrencyButton button;
    double clat;
    double clon;
    AnstronCoreHelper coreHelper;

    /* renamed from: db */
    FirebaseFirestore f111db;
    EditText describeBook;
    String describe_book;
    DocumentReference docReference;
    String imageurl;
    Boolean img1 = false;
    Boolean img1_OK = false;
    Boolean img2 = false;
    Boolean img2_OK = false;
    private FirebaseAuth mAuth;
    String mCurrentPhotoPath;
    FirebaseStorage mref;
    FirebaseFirestore mstore;
    Uri pickedImageUri;
    TextView printedCurrTxt;
    EditText printedPriceInput;
    String printed_Price;
    EditText publicationName;
    DatabaseReference reference;
    Button registerBook;
    TextView sellingCurrTxt;
    EditText sellingPriceInput;
    String selling_Price;
    Boolean showPhoneNumber = false;
    Switch simpleSwitch;
    StorageReference storageReference;
    String stringLat;
    String stringLon;
    String symbol;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    Button uploadPicture1;
    Button uploadPicture2;
    Uri uri1;
    Uri uri2;
    String userID;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_take__book__information);
        this.bookName = (EditText) findViewById(C0699R.C0702id.register_book_name);
        this.publicationName = (EditText) findViewById(C0699R.C0702id.register_book_publication);
        this.authorName = (EditText) findViewById(C0699R.C0702id.register_book_author);
        this.describeBook = (EditText) findViewById(C0699R.C0702id.register_book_description);
        this.registerBook = (Button) findViewById(C0699R.C0702id.register_book_button);
        this.uploadPicture1 = (Button) findViewById(C0699R.C0702id.uploadImage1);
        this.uploadPicture2 = (Button) findViewById(C0699R.C0702id.uploadImage2);
        this.printedPriceInput = (EditText) findViewById(C0699R.C0702id.register_book_input_printedprice);
        this.sellingPriceInput = (EditText) findViewById(C0699R.C0702id.register_book_selling_price_input);
        this.printedCurrTxt = (TextView) findViewById(C0699R.C0702id.printedCurrency);
        this.sellingCurrTxt = (TextView) findViewById(C0699R.C0702id.sellingCurrency);
        this.button = (CountryCurrencyButton) findViewById(C0699R.C0702id.button);
        this.coreHelper = new AnstronCoreHelper(this);
        this.simpleSwitch = (Switch) findViewById(C0699R.C0702id.simpleSwitch);
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        setSupportActionBar(this.toolbar);
        this.toolBarTitle.setText("Fill Book Details");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.simpleSwitch.setTextOn("Yes");
        this.simpleSwitch.setTextOff("No");
        Intent intent = getIntent();
        this.stringLat = intent.getStringExtra("g1");
        this.stringLon = intent.getStringExtra("g2");
        this.bookCategory = intent.getStringExtra("cat");
        this.clat = Double.parseDouble(this.stringLat);
        this.clon = Double.parseDouble(this.stringLon);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.userID = instance.getCurrentUser().getUid();
        this.storageReference = FirebaseStorage.getInstance().getReference();
        this.mstore = FirebaseFirestore.getInstance();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    Take_Book_Information.this.f109CC = documentSnapshot.getString("country_code");
                    Long l = documentSnapshot.getLong("count");
                    if (l != null) {
                        Take_Book_Information.this.bookcount = l.toString();
                    }
                    Take_Book_Information.this.button.setCountry(Take_Book_Information.this.f109CC);
                    Take_Book_Information.this.button.setShowCurrency(true);
                    Locale locale = new Locale("en", Take_Book_Information.this.f109CC);
                    Take_Book_Information.this.CurrCode = Currency.getInstance(locale).getCurrencyCode();
                    Currency instance = Currency.getInstance(Take_Book_Information.this.CurrCode);
                    Take_Book_Information.this.symbol = instance.getSymbol(locale);
                    if (Take_Book_Information.this.symbol != null) {
                        Take_Book_Information.this.printedCurrTxt.setText(Take_Book_Information.this.symbol);
                        Take_Book_Information.this.sellingCurrTxt.setText(Take_Book_Information.this.symbol);
                        return;
                    }
                    Take_Book_Information.this.printedCurrTxt.setText(Take_Book_Information.this.CurrCode);
                    Take_Book_Information.this.sellingCurrTxt.setText(Take_Book_Information.this.CurrCode);
                }
            });
        }
        this.button.setOnClickListener(new CountryCurrencyPickerListener() {
            public void onSelectCurrency(com.scrounger.countrycurrencypicker.library.Currency currency) {
            }

            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(Take_Book_Information.this, String.format("name: %s\ncode: %s", new Object[]{country.getName(), country.getCode()}), 0).show();
                    return;
                }
                Take_Book_Information.this.symbol = country.getCurrency().getSymbol();
                Take_Book_Information.this.CurrCode = country.getCurrency().getCode();
                if (Take_Book_Information.this.symbol != null) {
                    Take_Book_Information.this.printedCurrTxt.setText(Take_Book_Information.this.symbol);
                    Take_Book_Information.this.sellingCurrTxt.setText(Take_Book_Information.this.symbol);
                    return;
                }
                Take_Book_Information.this.printedCurrTxt.setText(Take_Book_Information.this.CurrCode);
                Take_Book_Information.this.sellingCurrTxt.setText(Take_Book_Information.this.CurrCode);
            }
        });
        this.uploadPicture1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Take_Book_Information.this.img2 = false;
                Take_Book_Information.this.img1 = true;
                Take_Book_Information.this.selectImage();
            }
        });
        this.uploadPicture2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Take_Book_Information.this.img1 = false;
                Take_Book_Information.this.img2 = true;
                Take_Book_Information.this.selectImage();
            }
        });
        this.registerBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Take_Book_Information.hideKeyboard(Take_Book_Information.this);
                final ProgressDialog progressDialog = new ProgressDialog(Take_Book_Information.this);
                progressDialog.setMessage("Adding Book...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                if (Take_Book_Information.this.simpleSwitch.isChecked()) {
                    Take_Book_Information.this.showPhoneNumber = true;
                } else {
                    Take_Book_Information.this.showPhoneNumber = false;
                }
                Take_Book_Information take_Book_Information = Take_Book_Information.this;
                take_Book_Information.f110bc = Integer.parseInt(take_Book_Information.bookcount);
                Take_Book_Information.this.f110bc++;
                final String str = Take_Book_Information.this.userID + "Book" + Take_Book_Information.this.f110bc;
                Take_Book_Information.this.bookImg1ID = str + "P1";
                Take_Book_Information.this.bookImg2ID = str + "P2";
                Take_Book_Information take_Book_Information2 = Take_Book_Information.this;
                take_Book_Information2.book_name = take_Book_Information2.bookName.getText().toString().trim();
                String str2 = "";
                String str3 = str2;
                for (int i = 0; i < Take_Book_Information.this.book_name.length(); i++) {
                    if (Take_Book_Information.this.book_name.charAt(i) != 10) {
                        str3 = str3 + Take_Book_Information.this.book_name.charAt(i);
                    } else {
                        str3 = str3 + " ";
                    }
                }
                Take_Book_Information.this.book_name = str3;
                Take_Book_Information take_Book_Information3 = Take_Book_Information.this;
                take_Book_Information3.book_publication = take_Book_Information3.publicationName.getText().toString().trim();
                String str4 = str2;
                for (int i2 = 0; i2 < Take_Book_Information.this.book_publication.length(); i2++) {
                    if (Take_Book_Information.this.book_publication.charAt(i2) != 10) {
                        str4 = str4 + Take_Book_Information.this.book_publication.charAt(i2);
                    } else {
                        str4 = str4 + " ";
                    }
                }
                Take_Book_Information.this.book_publication = str4;
                Take_Book_Information take_Book_Information4 = Take_Book_Information.this;
                take_Book_Information4.book_author = take_Book_Information4.authorName.getText().toString().trim();
                String str5 = str2;
                for (int i3 = 0; i3 < Take_Book_Information.this.book_author.length(); i3++) {
                    if (Take_Book_Information.this.book_author.charAt(i3) != 10) {
                        str5 = str5 + Take_Book_Information.this.book_author.charAt(i3);
                    } else {
                        str5 = str5 + " ";
                    }
                }
                Take_Book_Information.this.book_author = str5;
                Take_Book_Information take_Book_Information5 = Take_Book_Information.this;
                take_Book_Information5.describe_book = take_Book_Information5.describeBook.getText().toString().trim();
                for (int i4 = 0; i4 < Take_Book_Information.this.describe_book.length(); i4++) {
                    if (Take_Book_Information.this.describe_book.charAt(i4) != 10) {
                        str2 = str2 + Take_Book_Information.this.describe_book.charAt(i4);
                    } else {
                        str2 = str2 + ' ';
                    }
                }
                Take_Book_Information.this.describe_book = str2;
                Take_Book_Information.this.f111db = FirebaseFirestore.getInstance();
                DocumentReference document = Take_Book_Information.this.f111db.collection("AllBook").document(str);
                if (Take_Book_Information.this.book_name.length() < 3) {
                    Take_Book_Information.this.bookName.setError("must be minimum 3 characters");
                    Toast.makeText(Take_Book_Information.this, "ERROR:  CHECK NAME\n", 1).show();
                    progressDialog.dismiss();
                } else if (Take_Book_Information.this.describe_book.length() < 2 || Take_Book_Information.this.describe_book.length() > 200) {
                    if (Take_Book_Information.this.describe_book.length() > 200) {
                        Take_Book_Information.this.describeBook.setError("Exceeded limit 200 characters..");
                    } else {
                        Take_Book_Information.this.describeBook.setError("Minimum 3 characters required..");
                    }
                    Toast.makeText(Take_Book_Information.this, "ERROR:\nPlease Check: Book Description", 1).show();
                    progressDialog.dismiss();
                } else if (Take_Book_Information.this.book_publication.length() < 3) {
                    Take_Book_Information.this.publicationName.setError("Minimum 3 characters required..");
                    progressDialog.dismiss();
                    Toast.makeText(Take_Book_Information.this, "ERROR: Check Publication", 1).show();
                    progressDialog.dismiss();
                } else if (Take_Book_Information.this.book_author.length() < 3) {
                    Take_Book_Information.this.authorName.setError("Minimum 3 characters required..");
                    progressDialog.dismiss();
                    Toast.makeText(Take_Book_Information.this, "ERROR: Check Author", 1).show();
                    progressDialog.dismiss();
                } else {
                    Take_Book_Information take_Book_Information6 = Take_Book_Information.this;
                    take_Book_Information6.printed_Price = take_Book_Information6.printedPriceInput.getText().toString().trim();
                    Take_Book_Information take_Book_Information7 = Take_Book_Information.this;
                    take_Book_Information7.selling_Price = take_Book_Information7.sellingPriceInput.getText().toString().trim();
                    if (Take_Book_Information.this.selling_Price.length() < 1 || Take_Book_Information.this.printed_Price.length() < 1) {
                        if (Take_Book_Information.this.selling_Price.length() < 1 && Take_Book_Information.this.printed_Price.length() < 1) {
                            Take_Book_Information.this.sellingPriceInput.setError("Can't be empty.");
                            Take_Book_Information.this.printedPriceInput.setError("Can't be empty.");
                        } else if (Take_Book_Information.this.selling_Price.length() < 1) {
                            Take_Book_Information.this.sellingPriceInput.setError("Can't be empty.");
                        } else {
                            Take_Book_Information.this.printedPriceInput.setError("Can't be empty.");
                        }
                        progressDialog.dismiss();
                        return;
                    }
                    if ((Double.parseDouble(Take_Book_Information.this.selling_Price) * 100.0d) / Double.parseDouble(Take_Book_Information.this.printed_Price) > 60.0d) {
                        Take_Book_Information.this.sellingPriceInput.setError("Max Selling Price could be 60% of the Printed Price");
                        progressDialog.dismiss();
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("user", Take_Book_Information.this.userID);
                    hashMap.put("BookName", Take_Book_Information.this.book_name);
                    hashMap.put("BookDescription", Take_Book_Information.this.describe_book);
                    hashMap.put("Publication", Take_Book_Information.this.book_publication);
                    hashMap.put("Author", Take_Book_Information.this.book_author);
                    hashMap.put("Symbol", Take_Book_Information.this.symbol);
                    hashMap.put("PrintedPrice", Take_Book_Information.this.printed_Price);
                    hashMap.put("SellingPrice", Take_Book_Information.this.selling_Price);
                    hashMap.put("BookCategory", Take_Book_Information.this.bookCategory);
                    hashMap.put("Latitude", Double.valueOf(Take_Book_Information.this.clat));
                    hashMap.put("Longitude", Double.valueOf(Take_Book_Information.this.clon));
                    hashMap.put("CountryCode", Take_Book_Information.this.f109CC);
                    hashMap.put("showPhone", Take_Book_Information.this.showPhoneNumber);
                    document.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        public void onSuccess(Void voidR) {
                            Log.d("Take_Book_Information", "onSuccess: Service provider  added for userID=" + Take_Book_Information.this.userID);
                            new GeoFire(FirebaseDatabase.getInstance().getReference("AllBooks").child("geofire")).setLocation(str, new GeoLocation(Take_Book_Information.this.clat, Take_Book_Information.this.clon), new GeoFire.CompletionListener() {
                                public void onComplete(String str, DatabaseError databaseError) {
                                    if (databaseError != null) {
                                        Take_Book_Information take_Book_Information = Take_Book_Information.this;
                                        Toast.makeText(take_Book_Information, "Error: " + databaseError.toString(), 0).show();
                                        return;
                                    }
                                    System.out.println("Location saved on server successfully!");
                                    new GeoFire(FirebaseDatabase.getInstance().getReference("AllBooks").child("geofire")).setLocation(str, new GeoLocation(Take_Book_Information.this.clat, Take_Book_Information.this.clon), new GeoFire.CompletionListener() {
                                        public void onComplete(String str, DatabaseError databaseError) {
                                            if (databaseError != null) {
                                                Take_Book_Information take_Book_Information = Take_Book_Information.this;
                                                Toast.makeText(take_Book_Information, "Error: " + databaseError.toString(), 0).show();
                                                return;
                                            }
                                            System.out.println("Location saved on server successfully!");
                                            Take_Book_Information.this.mstore = FirebaseFirestore.getInstance();
                                            Take_Book_Information.this.mstore.collection("users").document(Take_Book_Information.this.userID).update("count", (Object) Integer.valueOf(Take_Book_Information.this.f110bc), new Object[0]);
                                            if (!Take_Book_Information.this.img1_OK.booleanValue() && !Take_Book_Information.this.img2_OK.booleanValue() && !Take_Book_Information.this.img2_OK.booleanValue()) {
                                                Intent intent = new Intent(Take_Book_Information.this, BookAdditionSuccessful.class);
                                                intent.putExtra("g1", Take_Book_Information.this.stringLat);
                                                intent.putExtra("g2", Take_Book_Information.this.stringLon);
                                                Take_Book_Information.this.finish();
                                                progressDialog.dismiss();
                                                Take_Book_Information.this.startActivity(intent);
                                            }
                                            if (Take_Book_Information.this.img1_OK.booleanValue()) {
                                                File file = new File(SiliCompressor.with(Take_Book_Information.this).compress(FileUtils.getPath(Take_Book_Information.this, Take_Book_Information.this.uri1), new File(Take_Book_Information.this.getCacheDir(), "temp")));
                                                Uri fromFile = Uri.fromFile(file);
                                                StorageReference child = Take_Book_Information.this.storageReference.child("BookImages/");
                                                child.child(Take_Book_Information.this.bookImg1ID + ".jpeg").putFile(fromFile).addOnCompleteListener((OnCompleteListener) new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                    public void onComplete(Task<UploadTask.TaskSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            Take_Book_Information.this.mref = FirebaseStorage.getInstance();
                                                            StorageReference reference = Take_Book_Information.this.mref.getReference("BookImages/");
                                                            final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                                            reference.child(Take_Book_Information.this.bookImg1ID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                public void onSuccess(Uri uri) {
                                                                    if (currentUser != null) {
                                                                        Take_Book_Information.this.docReference = Take_Book_Information.this.mstore.collection("AllBook").document(str);
                                                                        Take_Book_Information.this.docReference.update("displayURL1", (Object) uri.toString(), new Object[0]);
                                                                    }
                                                                    if (!Take_Book_Information.this.img2_OK.booleanValue()) {
                                                                        Intent intent = new Intent(Take_Book_Information.this, BookAdditionSuccessful.class);
                                                                        intent.putExtra("g1", Take_Book_Information.this.stringLat);
                                                                        intent.putExtra("g2", Take_Book_Information.this.stringLon);
                                                                        Take_Book_Information.this.finish();
                                                                        progressDialog.dismiss();
                                                                        Take_Book_Information.this.startActivity(intent);
                                                                    }
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                public void onFailure(Exception exc) {
                                                                    if (currentUser != null) {
                                                                        Take_Book_Information.this.docReference = Take_Book_Information.this.mstore.collection("AllBook").document(str);
                                                                        Take_Book_Information.this.docReference.update("displayURL", (Object) "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c", new Object[0]);
                                                                        if (!Take_Book_Information.this.img2_OK.booleanValue()) {
                                                                            Intent intent = new Intent(Take_Book_Information.this, BookAdditionSuccessful.class);
                                                                            intent.putExtra("g1", Take_Book_Information.this.stringLat);
                                                                            intent.putExtra("g2", Take_Book_Information.this.stringLon);
                                                                            Take_Book_Information.this.finish();
                                                                            progressDialog.dismiss();
                                                                            Take_Book_Information.this.startActivity(intent);
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                            return;
                                                        }
                                                        Toast.makeText(Take_Book_Information.this, "Failed Uploading Picture...", 0).show();
                                                    }
                                                });
                                                file.delete();
                                            }
                                            if (Take_Book_Information.this.img2_OK.booleanValue()) {
                                                File file2 = new File(SiliCompressor.with(Take_Book_Information.this).compress(FileUtils.getPath(Take_Book_Information.this, Take_Book_Information.this.uri2), new File(Take_Book_Information.this.getCacheDir(), "temp")));
                                                Uri fromFile2 = Uri.fromFile(file2);
                                                StorageReference child2 = Take_Book_Information.this.storageReference.child("BookImages/");
                                                child2.child(Take_Book_Information.this.bookImg2ID + ".jpeg").putFile(fromFile2).addOnCompleteListener((OnCompleteListener) new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                    public void onComplete(Task<UploadTask.TaskSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            Take_Book_Information.this.mref = FirebaseStorage.getInstance();
                                                            StorageReference reference = Take_Book_Information.this.mref.getReference("BookImages/");
                                                            final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                                            reference.child(Take_Book_Information.this.bookImg2ID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                public void onSuccess(Uri uri) {
                                                                    if (currentUser != null) {
                                                                        Take_Book_Information.this.docReference = Take_Book_Information.this.mstore.collection("AllBook").document(str);
                                                                        Take_Book_Information.this.docReference.update("displayURL2", (Object) uri.toString(), new Object[0]);
                                                                    }
                                                                    Intent intent = new Intent(Take_Book_Information.this, BookAdditionSuccessful.class);
                                                                    intent.putExtra("g1", Take_Book_Information.this.stringLat);
                                                                    intent.putExtra("g2", Take_Book_Information.this.stringLon);
                                                                    Take_Book_Information.this.finish();
                                                                    progressDialog.dismiss();
                                                                    Take_Book_Information.this.startActivity(intent);
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                public void onFailure(Exception exc) {
                                                                    if (currentUser != null) {
                                                                        Take_Book_Information.this.docReference = Take_Book_Information.this.mstore.collection("AllBook").document(str);
                                                                        Take_Book_Information.this.docReference.update("displayURL2", (Object) "https://firebasestorage.googleapis.com/v0/b/booklal.appspot.com/o/DEFAULTBOOK.png?alt=media&token=5b390670-f1fe-4678-ad0f-5d65d89ed66c", new Object[0]);
                                                                    }
                                                                    Intent intent = new Intent(Take_Book_Information.this, BookAdditionSuccessful.class);
                                                                    intent.putExtra("g1", Take_Book_Information.this.stringLat);
                                                                    intent.putExtra("g2", Take_Book_Information.this.stringLon);
                                                                    Take_Book_Information.this.finish();
                                                                    progressDialog.dismiss();
                                                                    Take_Book_Information.this.startActivity(intent);
                                                                }
                                                            });
                                                            return;
                                                        }
                                                        Toast.makeText(Take_Book_Information.this, "Failed Uploading Picture...", 0).show();
                                                    }
                                                });
                                                file2.delete();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        public void onFailure(Exception exc) {
                            Log.w("Take_Book_Information", "Error adding Service Provider ", exc);
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Take_Book_Information.this.onBackPressed();
                Take_Book_Information.this.finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0699R.C0704menu.main, menu);
        return true;
    }

    /* access modifiers changed from: private */
    public void selectImage() {
        final CharSequence[] charSequenceArr = {"Take Picture", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x0066  */
            /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.content.DialogInterface r5, int r6) {
                /*
                    r4 = this;
                    java.lang.CharSequence[] r0 = r0
                    r0 = r0[r6]
                    java.lang.String r1 = "Take Picture"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x0011
                    r4.askCameraPermissions()
                    goto L_0x008a
                L_0x0011:
                    java.lang.CharSequence[] r0 = r0
                    r0 = r0[r6]
                    java.lang.String r1 = "Choose from Gallery"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x007b
                    r5 = 0
                    int r6 = android.os.Build.VERSION.SDK_INT
                    r0 = 22
                    r1 = 2
                    java.lang.String r2 = "android.intent.action.PICK"
                    r3 = 1
                    if (r6 <= r0) goto L_0x004f
                    boolean r6 = r4.checkIfAlreadyHavePermission()
                    if (r6 != 0) goto L_0x003a
                    com.booklal.booklal.Take_Book_Information r6 = com.booklal.booklal.Take_Book_Information.this
                    java.lang.String r0 = "android.permission.READ_EXTERNAL_STORAGE"
                    java.lang.String[] r0 = new java.lang.String[]{r0}
                    androidx.core.app.ActivityCompat.requestPermissions(r6, r0, r3)
                    goto L_0x0064
                L_0x003a:
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.Take_Book_Information r6 = com.booklal.booklal.Take_Book_Information.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.Take_Book_Information r6 = com.booklal.booklal.Take_Book_Information.this
                    r6.startActivityForResult(r5, r1)
                    goto L_0x0063
                L_0x004f:
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.Take_Book_Information r6 = com.booklal.booklal.Take_Book_Information.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.Take_Book_Information r6 = com.booklal.booklal.Take_Book_Information.this
                    r6.startActivityForResult(r5, r1)
                L_0x0063:
                    r5 = 1
                L_0x0064:
                    if (r5 != 0) goto L_0x008a
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.Take_Book_Information r6 = com.booklal.booklal.Take_Book_Information.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.Take_Book_Information r6 = com.booklal.booklal.Take_Book_Information.this
                    r6.startActivityForResult(r5, r1)
                    goto L_0x008a
                L_0x007b:
                    java.lang.CharSequence[] r0 = r0
                    r6 = r0[r6]
                    java.lang.String r0 = "Cancel"
                    boolean r6 = r6.equals(r0)
                    if (r6 == 0) goto L_0x008a
                    r5.dismiss()
                L_0x008a:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.booklal.booklal.Take_Book_Information.C07537.onClick(android.content.DialogInterface, int):void");
            }

            private void askCameraPermissions() {
                if (ContextCompat.checkSelfPermission(Take_Book_Information.this, "android.permission.CAMERA") != 0) {
                    ActivityCompat.requestPermissions(Take_Book_Information.this, new String[]{"android.permission.CAMERA"}, 101);
                } else {
                    Take_Book_Information.this.openCamera();
                }
            }

            private boolean checkIfAlreadyHavePermission() {
                return ContextCompat.checkSelfPermission(Take_Book_Information.this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
            }
        });
        builder.show();
    }

    /* access modifiers changed from: private */
    public void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            File file = null;
            try {
                file = createImageFile();
            } catch (IOException unused) {
            }
            if (file != null) {
                intent.putExtra("output", FileProvider.getUriForFile(this, "com.booklal.booklal.provider", file));
                startActivityForResult(intent, 1);
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101 && iArr.length > 0 && iArr[0] == 0) {
            openCamera();
        }
    }

    private void galleryAddPic() {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(this.mCurrentPhotoPath)));
        sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 1) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            setPic();
            progressDialog.dismiss();
        } else if (i == 2) {
            ProgressDialog progressDialog2 = new ProgressDialog(this);
            progressDialog2.setMessage("Uploading...");
            progressDialog2.setCancelable(false);
            progressDialog2.show();
            Uri data = intent.getData();
            String[] strArr = {"_data"};
            Cursor query = getContentResolver().query(data, strArr, (String) null, (String[]) null, (String) null);
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex(strArr[0]));
            query.close();
            try {
                Bitmap decodeFile = BitmapFactory.decodeFile(string);
                Log.w("path of image", string + "");
                if (data != null) {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodeFile, BaseAnimation.DEFAULT_ANIMATION_TIME, 500, true));
                    if (this.img1.booleanValue()) {
                        this.img1_OK = true;
                        this.uploadPicture1.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, bitmapDrawable, (Drawable) null, (Drawable) null);
                    }
                    if (this.img2.booleanValue()) {
                        this.img2_OK = true;
                        this.uploadPicture2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, bitmapDrawable, (Drawable) null, (Drawable) null);
                    }
                    if (this.img1.booleanValue()) {
                        this.img1_OK = true;
                        this.uri1 = data;
                    }
                    if (this.img2.booleanValue()) {
                        this.img2_OK = true;
                        this.uri2 = data;
                    }
                    progressDialog2.dismiss();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error Msg:\n" + e.toString(), 0).show();
            }
            progressDialog2.dismiss();
        }
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

    private void setPic() {
        int width = this.uploadPicture1.getWidth();
        int height = this.uploadPicture1.getHeight();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.mCurrentPhotoPath, options);
        int max = Math.max(1, Math.min(options.outWidth / width, options.outHeight / height));
        options.inJustDecodeBounds = false;
        options.inSampleSize = max;
        options.inPurgeable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(this.mCurrentPhotoPath, options);
        try {
            Uri fromFile = Uri.fromFile(new File(this.mCurrentPhotoPath));
            if (this.mCurrentPhotoPath != null) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodeFile, BaseAnimation.DEFAULT_ANIMATION_TIME, 500, true));
                if (this.img1.booleanValue()) {
                    this.img1_OK = true;
                    this.uploadPicture1.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, bitmapDrawable, (Drawable) null, (Drawable) null);
                }
                if (this.img2.booleanValue()) {
                    this.img2_OK = true;
                    this.uploadPicture2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, bitmapDrawable, (Drawable) null, (Drawable) null);
                }
                if (this.img1.booleanValue()) {
                    this.img1_OK = true;
                    this.uri1 = fromFile;
                }
                if (this.img2.booleanValue()) {
                    this.img2_OK = true;
                    this.uri2 = fromFile;
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error Msg:\n" + e.toString(), 0).show();
        }
    }

    private File createImageFile() throws IOException {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File createTempFile = File.createTempFile("JPEG_" + format + "_", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        this.mCurrentPhotoPath = createTempFile.getAbsolutePath();
        galleryAddPic();
        return createTempFile;
    }
}
