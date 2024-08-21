package com.booklal.booklal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
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
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.FragmentActivity;
import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class EditBookDetailsActivity extends AppCompatActivity {

    /* renamed from: CC */
    String f73CC;
    /* access modifiers changed from: private */
    public String CurrCode;
    private ImageView backBtnToolbar;
    String bookAuthor;
    String bookCategory;
    String bookDescription;
    String bookID;
    String bookName;
    String bookPrintedPriceString;
    String bookPublisher;
    String bookSellingPriceString;
    Button buttonUpdate;
    ImageView buttonUploadPicture1;
    ImageView buttonUploadPicture2;
    AnstronCoreHelper coreHelper;
    private CountryCurrencyButton currencySelectButton;

    /* renamed from: db */
    FirebaseFirestore f74db;
    DocumentReference docReference;
    DocumentReference documentReference;
    EditText editTextAuthorName;
    EditText editTextBookName;
    EditText editTextDescribeBook;
    EditText editTextPrintedPriceInput;
    EditText editTextPublicationName;
    EditText editTextSellingPriceInput;
    String imageurl;
    Boolean img1 = false;
    Boolean img1_OK = false;
    Boolean img2 = false;
    Boolean img2_OK = false;
    String imgURL1;
    String imgURL2;
    Boolean isCallOk;
    String mCurrentPhotoPath;
    FirebaseStorage mref;
    FirebaseFirestore mstore;
    Uri pickedImageUri;
    DatabaseReference reference;
    /* access modifiers changed from: private */
    public Switch simpleSwitch;
    StorageReference storageReference;
    /* access modifiers changed from: private */
    public String symbol;
    String takenBookAuthor;
    String takenBookCategory;
    String takenBookDescription;
    String takenBookID;
    String takenBookName;
    String takenBookPrintedPriceString;
    String takenBookPublisher;
    String takenBookSellingPriceString;
    String takenCC;
    String takenImgURL1;
    String takenImgURL2;
    Boolean takenIsCallOk;
    /* access modifiers changed from: private */
    public String takenSymbol;
    TextView textViewPrintedCurrTxt;
    TextView textViewSellingCurrTxt;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    Uri uri1;
    Uri uri2;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_edit_book_details);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        Toolbar toolbar2 = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.toolBarTitle.setText("Edit Book Details");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("bookKey");
        this.takenBookID = stringExtra;
        this.bookID = stringExtra;
        this.takenBookName = intent.getStringExtra("bName");
        this.takenBookDescription = intent.getStringExtra("bDesc");
        this.takenBookPublisher = intent.getStringExtra("bPub");
        this.takenBookAuthor = intent.getStringExtra("bAuthor");
        this.takenImgURL1 = intent.getStringExtra("bURL1");
        this.takenImgURL2 = intent.getStringExtra("bURL2");
        this.takenCC = intent.getStringExtra("bCC");
        this.takenBookSellingPriceString = intent.getStringExtra("bSP");
        this.takenBookPrintedPriceString = intent.getStringExtra("bPP");
        this.takenIsCallOk = Boolean.valueOf(intent.getBooleanExtra("bPhone", false));
        this.takenBookCategory = intent.getStringExtra("cat");
        this.takenSymbol = intent.getStringExtra("bSymbol");
        String str = this.takenBookAuthor;
        if (str != null) {
            this.takenBookAuthor = str.substring(3);
        }
        this.editTextBookName = (EditText) findViewById(C0699R.C0702id.edit_book_name);
        this.editTextDescribeBook = (EditText) findViewById(C0699R.C0702id.edit_book_description);
        this.editTextPublicationName = (EditText) findViewById(C0699R.C0702id.edit_book_publication);
        this.editTextAuthorName = (EditText) findViewById(C0699R.C0702id.edit_book_author);
        this.editTextPrintedPriceInput = (EditText) findViewById(C0699R.C0702id.update_book_input_printedprice);
        this.editTextSellingPriceInput = (EditText) findViewById(C0699R.C0702id.update_book_selling_price_input);
        this.buttonUploadPicture1 = (ImageView) findViewById(C0699R.C0702id.updateUploadImage1);
        this.buttonUploadPicture2 = (ImageView) findViewById(C0699R.C0702id.updateUploadImage2);
        this.buttonUpdate = (Button) findViewById(C0699R.C0702id.update_book_button);
        this.textViewPrintedCurrTxt = (TextView) findViewById(C0699R.C0702id.updatePrintedCurrency);
        this.textViewSellingCurrTxt = (TextView) findViewById(C0699R.C0702id.updateSellingCurrency);
        this.simpleSwitch = (Switch) findViewById(C0699R.C0702id.updateSimpleSwitch);
        this.currencySelectButton = (CountryCurrencyButton) findViewById(C0699R.C0702id.updateCurrencyButton);
        this.storageReference = FirebaseStorage.getInstance().getReference();
        this.currencySelectButton.setCountry(this.takenCC);
        this.currencySelectButton.setShowCurrency(true);
        Locale locale = new Locale("en", this.takenCC);
        String currencyCode = Currency.getInstance(locale).getCurrencyCode();
        this.CurrCode = currencyCode;
        String symbol2 = Currency.getInstance(currencyCode).getSymbol(locale);
        this.symbol = symbol2;
        if (symbol2 != null) {
            this.textViewPrintedCurrTxt.setText(symbol2);
            this.textViewSellingCurrTxt.setText(this.symbol);
        } else {
            String str2 = this.CurrCode;
            this.symbol = str2;
            this.textViewPrintedCurrTxt.setText(str2);
            this.textViewSellingCurrTxt.setText(this.CurrCode);
        }
        this.mstore = FirebaseFirestore.getInstance();
        this.editTextBookName.setText(this.takenBookName);
        this.editTextDescribeBook.setText(this.takenBookDescription);
        this.editTextPublicationName.setText(this.takenBookPublisher);
        this.editTextAuthorName.setText(this.takenBookAuthor);
        this.editTextSellingPriceInput.setText(this.takenBookSellingPriceString);
        this.editTextPrintedPriceInput.setText(this.takenBookPrintedPriceString);
        Glide.with((FragmentActivity) this).load(this.takenImgURL1).into(this.buttonUploadPicture1);
        Glide.with((FragmentActivity) this).load(this.takenImgURL2).into(this.buttonUploadPicture2);
        this.simpleSwitch.setChecked(this.takenIsCallOk.booleanValue());
        this.simpleSwitch.setTextOn("Yes");
        this.simpleSwitch.setTextOff("No");
        this.currencySelectButton.setOnClickListener(new CountryCurrencyPickerListener() {
            public void onSelectCurrency(com.scrounger.countrycurrencypicker.library.Currency currency) {
            }

            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(EditBookDetailsActivity.this, String.format("name: %s\ncode: %s", new Object[]{country.getName(), country.getCode()}), 0).show();
                    return;
                }
                String unused = EditBookDetailsActivity.this.symbol = country.getCurrency().getSymbol();
                String unused2 = EditBookDetailsActivity.this.CurrCode = country.getCurrency().getCode();
                if (EditBookDetailsActivity.this.symbol != null) {
                    EditBookDetailsActivity.this.textViewPrintedCurrTxt.setText(EditBookDetailsActivity.this.symbol);
                    EditBookDetailsActivity.this.textViewSellingCurrTxt.setText(EditBookDetailsActivity.this.symbol);
                    return;
                }
                EditBookDetailsActivity editBookDetailsActivity = EditBookDetailsActivity.this;
                String unused3 = editBookDetailsActivity.symbol = editBookDetailsActivity.CurrCode;
                EditBookDetailsActivity.this.textViewPrintedCurrTxt.setText(EditBookDetailsActivity.this.CurrCode);
                EditBookDetailsActivity.this.textViewSellingCurrTxt.setText(EditBookDetailsActivity.this.CurrCode);
            }
        });
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditBookDetailsActivity.this.onBackPressed();
                EditBookDetailsActivity.this.finish();
            }
        });
        this.buttonUploadPicture1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditBookDetailsActivity.this.img2 = false;
                EditBookDetailsActivity.this.img1 = true;
                EditBookDetailsActivity.this.selectImage();
            }
        });
        this.buttonUploadPicture2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditBookDetailsActivity.this.img1 = false;
                EditBookDetailsActivity.this.img2 = true;
                EditBookDetailsActivity.this.selectImage();
            }
        });
        this.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(EditBookDetailsActivity.this);
                progressDialog.setMessage("Updating...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                Boolean.valueOf(true);
                EditBookDetailsActivity editBookDetailsActivity = EditBookDetailsActivity.this;
                editBookDetailsActivity.bookName = editBookDetailsActivity.editTextBookName.getText().toString();
                EditBookDetailsActivity editBookDetailsActivity2 = EditBookDetailsActivity.this;
                editBookDetailsActivity2.bookDescription = editBookDetailsActivity2.editTextDescribeBook.getText().toString();
                EditBookDetailsActivity editBookDetailsActivity3 = EditBookDetailsActivity.this;
                editBookDetailsActivity3.bookPublisher = editBookDetailsActivity3.editTextPublicationName.getText().toString();
                EditBookDetailsActivity editBookDetailsActivity4 = EditBookDetailsActivity.this;
                editBookDetailsActivity4.bookAuthor = editBookDetailsActivity4.editTextAuthorName.getText().toString();
                EditBookDetailsActivity editBookDetailsActivity5 = EditBookDetailsActivity.this;
                editBookDetailsActivity5.bookPrintedPriceString = editBookDetailsActivity5.editTextPrintedPriceInput.getText().toString();
                EditBookDetailsActivity editBookDetailsActivity6 = EditBookDetailsActivity.this;
                editBookDetailsActivity6.bookSellingPriceString = editBookDetailsActivity6.editTextSellingPriceInput.getText().toString();
                if (EditBookDetailsActivity.this.simpleSwitch.isChecked()) {
                    EditBookDetailsActivity.this.isCallOk = true;
                } else {
                    EditBookDetailsActivity.this.isCallOk = false;
                }
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                EditBookDetailsActivity editBookDetailsActivity7 = EditBookDetailsActivity.this;
                editBookDetailsActivity7.documentReference = editBookDetailsActivity7.mstore.collection("AllBook").document(EditBookDetailsActivity.this.takenBookID);
                if (!EditBookDetailsActivity.this.takenBookName.equals(EditBookDetailsActivity.this.bookName) && currentUser != null) {
                    if (EditBookDetailsActivity.this.bookName == null || EditBookDetailsActivity.this.bookName.length() < 1) {
                        EditText editText = EditBookDetailsActivity.this.editTextBookName;
                        editText.setError("Book Name can't be empty! " + EditBookDetailsActivity.this.bookName);
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    } else if (EditBookDetailsActivity.this.bookName == null || EditBookDetailsActivity.this.bookName.length() >= 3) {
                        EditBookDetailsActivity.this.documentReference.update("BookName", (Object) EditBookDetailsActivity.this.bookName, new Object[0]);
                    } else {
                        EditBookDetailsActivity.this.editTextBookName.setError("Requires minimum 3 characters.");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    }
                }
                if (!EditBookDetailsActivity.this.takenBookDescription.equals(EditBookDetailsActivity.this.bookDescription) && currentUser != null) {
                    if (EditBookDetailsActivity.this.bookDescription == null || EditBookDetailsActivity.this.bookDescription.length() < 1) {
                        EditBookDetailsActivity.this.editTextDescribeBook.setError("This field can't be empty!");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    } else if (EditBookDetailsActivity.this.bookDescription == null || EditBookDetailsActivity.this.bookDescription.length() >= 5) {
                        EditBookDetailsActivity.this.documentReference.update("BookDescription", (Object) EditBookDetailsActivity.this.bookDescription, new Object[0]);
                    } else {
                        EditBookDetailsActivity.this.editTextDescribeBook.setError("Requires minimum 5 characters.");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    }
                }
                if (!EditBookDetailsActivity.this.takenBookPublisher.equals(EditBookDetailsActivity.this.bookPublisher) && currentUser != null) {
                    if (EditBookDetailsActivity.this.bookPublisher == null || EditBookDetailsActivity.this.bookPublisher.length() < 1) {
                        EditBookDetailsActivity.this.editTextPublicationName.setError("This field can't be empty!");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    } else if (EditBookDetailsActivity.this.bookPublisher == null || EditBookDetailsActivity.this.bookPublisher.length() >= 3) {
                        EditBookDetailsActivity.this.documentReference.update("Publication", (Object) EditBookDetailsActivity.this.bookPublisher, new Object[0]);
                    } else {
                        EditBookDetailsActivity.this.editTextPublicationName.setError("Requires minimum 3 characters.");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    }
                }
                if (EditBookDetailsActivity.this.takenBookAuthor == null || EditBookDetailsActivity.this.takenBookAuthor.equals(EditBookDetailsActivity.this.bookAuthor)) {
                    if (EditBookDetailsActivity.this.takenBookAuthor == null) {
                        if (EditBookDetailsActivity.this.bookAuthor == null || EditBookDetailsActivity.this.bookAuthor.length() < 1) {
                            EditBookDetailsActivity.this.editTextAuthorName.setError("This field can't be empty!");
                            Boolean.valueOf(false);
                            progressDialog.dismiss();
                            return;
                        } else if (EditBookDetailsActivity.this.bookAuthor == null || EditBookDetailsActivity.this.bookAuthor.length() >= 3) {
                            EditBookDetailsActivity.this.documentReference.update("Author", (Object) EditBookDetailsActivity.this.bookAuthor, new Object[0]);
                        } else {
                            EditBookDetailsActivity.this.editTextAuthorName.setError("Requires minimum 3 characters.");
                            Boolean.valueOf(false);
                            progressDialog.dismiss();
                            return;
                        }
                    }
                } else if (currentUser != null) {
                    if (EditBookDetailsActivity.this.bookAuthor == null || EditBookDetailsActivity.this.bookAuthor.length() < 1) {
                        EditBookDetailsActivity.this.editTextAuthorName.setError("This field can't be empty!");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    } else if (EditBookDetailsActivity.this.bookAuthor == null || EditBookDetailsActivity.this.bookAuthor.length() >= 3) {
                        EditBookDetailsActivity.this.documentReference.update("Author", (Object) EditBookDetailsActivity.this.bookAuthor, new Object[0]);
                    } else {
                        EditBookDetailsActivity.this.editTextAuthorName.setError("Requires minimum 3 characters.");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    }
                }
                if (!EditBookDetailsActivity.this.takenSymbol.equals(EditBookDetailsActivity.this.symbol) && currentUser != null) {
                    EditBookDetailsActivity.this.documentReference.update("Symbol", (Object) EditBookDetailsActivity.this.symbol, new Object[0]);
                }
                if (!EditBookDetailsActivity.this.takenBookPrintedPriceString.equals(EditBookDetailsActivity.this.bookPrintedPriceString) || !EditBookDetailsActivity.this.takenBookSellingPriceString.equals(EditBookDetailsActivity.this.bookSellingPriceString)) {
                    Double valueOf = Double.valueOf(Double.parseDouble(EditBookDetailsActivity.this.bookPrintedPriceString));
                    Double valueOf2 = Double.valueOf(Double.parseDouble(EditBookDetailsActivity.this.bookSellingPriceString));
                    double doubleValue = (valueOf2.doubleValue() * 100.0d) / valueOf.doubleValue();
                    if (valueOf.doubleValue() < 0.0d && valueOf2.doubleValue() < 0.0d) {
                        EditBookDetailsActivity.this.editTextPrintedPriceInput.setError("Selling Price can't be negative.");
                        EditBookDetailsActivity.this.editTextSellingPriceInput.setError("Printed Price can't be negative.");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    } else if (valueOf.doubleValue() < 0.0d) {
                        EditBookDetailsActivity.this.editTextPrintedPriceInput.setError("Selling Price can't be negative.");
                        Boolean.valueOf(false);
                        progressDialog.dismiss();
                        return;
                    } else if (valueOf.doubleValue() < 0.0d) {
                        EditBookDetailsActivity.this.editTextSellingPriceInput.setError("Printed Price can't be negative.");
                        progressDialog.dismiss();
                        return;
                    } else if (doubleValue > 60.0d) {
                        if (!EditBookDetailsActivity.this.takenBookPrintedPriceString.equals(EditBookDetailsActivity.this.bookPrintedPriceString) && !EditBookDetailsActivity.this.takenBookSellingPriceString.equals(EditBookDetailsActivity.this.bookSellingPriceString)) {
                            EditBookDetailsActivity.this.editTextPrintedPriceInput.setError("You can't sell at more than 60% of the printed price.");
                        }
                        EditBookDetailsActivity.this.editTextSellingPriceInput.setError("Max Selling Price could be 60% of the Printed Price");
                        progressDialog.dismiss();
                        return;
                    } else {
                        EditBookDetailsActivity.this.documentReference.update("PrintedPrice", (Object) EditBookDetailsActivity.this.bookPrintedPriceString, new Object[0]);
                        EditBookDetailsActivity.this.documentReference.update("SellingPrice", (Object) EditBookDetailsActivity.this.bookSellingPriceString, new Object[0]);
                    }
                }
                if (!EditBookDetailsActivity.this.takenIsCallOk.equals(EditBookDetailsActivity.this.isCallOk)) {
                    EditBookDetailsActivity.this.documentReference.update("showPhone", (Object) EditBookDetailsActivity.this.isCallOk, new Object[0]);
                }
                if (EditBookDetailsActivity.this.img1_OK.booleanValue() && EditBookDetailsActivity.this.uri1 != null) {
                    SiliCompressor with = SiliCompressor.with(EditBookDetailsActivity.this);
                    EditBookDetailsActivity editBookDetailsActivity8 = EditBookDetailsActivity.this;
                    File file = new File(with.compress(FileUtils.getPath(editBookDetailsActivity8, editBookDetailsActivity8.uri1), new File(EditBookDetailsActivity.this.getCacheDir(), "temp")));
                    Uri fromFile = Uri.fromFile(file);
                    StorageReference child = EditBookDetailsActivity.this.storageReference.child("BookImages/");
                    child.child(EditBookDetailsActivity.this.bookID + "P1.jpeg").putFile(fromFile).addOnCompleteListener((OnCompleteListener) new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        public void onComplete(Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                EditBookDetailsActivity.this.mref = FirebaseStorage.getInstance();
                                StorageReference reference = EditBookDetailsActivity.this.mref.getReference("BookImages/");
                                final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                reference.child(EditBookDetailsActivity.this.bookID + "P1.jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    public void onSuccess(Uri uri) {
                                        if (currentUser != null) {
                                            EditBookDetailsActivity.this.documentReference.update("displayURL1", (Object) uri.toString(), new Object[0]);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    public void onFailure(Exception exc) {
                                    }
                                });
                                return;
                            }
                            Toast.makeText(EditBookDetailsActivity.this, "Failed Uploading Picture...", 0).show();
                        }
                    });
                    file.delete();
                }
                if (EditBookDetailsActivity.this.img2_OK.booleanValue() && EditBookDetailsActivity.this.uri2 != null) {
                    SiliCompressor with2 = SiliCompressor.with(EditBookDetailsActivity.this);
                    EditBookDetailsActivity editBookDetailsActivity9 = EditBookDetailsActivity.this;
                    File file2 = new File(with2.compress(FileUtils.getPath(editBookDetailsActivity9, editBookDetailsActivity9.uri2), new File(EditBookDetailsActivity.this.getCacheDir(), "temp")));
                    Uri fromFile2 = Uri.fromFile(file2);
                    StorageReference child2 = EditBookDetailsActivity.this.storageReference.child("BookImages/");
                    child2.child(EditBookDetailsActivity.this.bookID + "P2.jpeg").putFile(fromFile2).addOnCompleteListener((OnCompleteListener) new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        public void onComplete(Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                EditBookDetailsActivity.this.mref = FirebaseStorage.getInstance();
                                StorageReference reference = EditBookDetailsActivity.this.mref.getReference("BookImages/");
                                final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                reference.child(EditBookDetailsActivity.this.bookID + "P2.jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    public void onSuccess(Uri uri) {
                                        if (currentUser != null) {
                                            EditBookDetailsActivity.this.documentReference.update("displayURL2", (Object) uri.toString(), new Object[0]);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    public void onFailure(Exception exc) {
                                    }
                                });
                                return;
                            }
                            Toast.makeText(EditBookDetailsActivity.this, "Failed Uploading Picture...", 0).show();
                        }
                    });
                    file2.delete();
                }
                ((Snackbar) Snackbar.make(EditBookDetailsActivity.this.findViewById(C0699R.C0702id.edit_book_details_layout), (CharSequence) "Updated Successfully...", 0).setAction((CharSequence) "Close.", (View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View view) {
                        EditBookDetailsActivity.this.startActivity(new Intent(EditBookDetailsActivity.this, SellingItems.class));
                        EditBookDetailsActivity.this.finish();
                    }
                }).setActionTextColor((int) SupportMenu.CATEGORY_MASK).setDuration(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE)).show();
                progressDialog.dismiss();
            }
        });
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
                    com.booklal.booklal.EditBookDetailsActivity r6 = com.booklal.booklal.EditBookDetailsActivity.this
                    java.lang.String r0 = "android.permission.READ_EXTERNAL_STORAGE"
                    java.lang.String[] r0 = new java.lang.String[]{r0}
                    androidx.core.app.ActivityCompat.requestPermissions(r6, r0, r3)
                    goto L_0x0064
                L_0x003a:
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.EditBookDetailsActivity r6 = com.booklal.booklal.EditBookDetailsActivity.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.EditBookDetailsActivity r6 = com.booklal.booklal.EditBookDetailsActivity.this
                    r6.startActivityForResult(r5, r1)
                    goto L_0x0063
                L_0x004f:
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.EditBookDetailsActivity r6 = com.booklal.booklal.EditBookDetailsActivity.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.EditBookDetailsActivity r6 = com.booklal.booklal.EditBookDetailsActivity.this
                    r6.startActivityForResult(r5, r1)
                L_0x0063:
                    r5 = 1
                L_0x0064:
                    if (r5 != 0) goto L_0x008a
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.EditBookDetailsActivity r6 = com.booklal.booklal.EditBookDetailsActivity.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.EditBookDetailsActivity r6 = com.booklal.booklal.EditBookDetailsActivity.this
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
                throw new UnsupportedOperationException("Method not decompiled: com.booklal.booklal.EditBookDetailsActivity.C06316.onClick(android.content.DialogInterface, int):void");
            }

            private void askCameraPermissions() {
                if (ContextCompat.checkSelfPermission(EditBookDetailsActivity.this, "android.permission.CAMERA") != 0) {
                    ActivityCompat.requestPermissions(EditBookDetailsActivity.this, new String[]{"android.permission.CAMERA"}, 101);
                } else {
                    EditBookDetailsActivity.this.openCamera();
                }
            }

            private boolean checkIfAlreadyHavePermission() {
                return ContextCompat.checkSelfPermission(EditBookDetailsActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
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
                    if (this.img1.booleanValue()) {
                        this.img1_OK = true;
                        this.buttonUploadPicture1.setImageBitmap(decodeFile);
                    }
                    if (this.img2.booleanValue()) {
                        this.img2_OK = true;
                        this.buttonUploadPicture2.setImageBitmap(decodeFile);
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

    private void setPic() {
        int width = this.buttonUploadPicture1.getWidth();
        int height = this.buttonUploadPicture2.getHeight();
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
                if (this.img1.booleanValue()) {
                    this.img1_OK = true;
                    this.buttonUploadPicture1.setImageBitmap(decodeFile);
                }
                if (this.img2.booleanValue()) {
                    this.img2_OK = true;
                    this.buttonUploadPicture2.setImageBitmap(decodeFile);
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
            Toast.makeText(this, "Error Msg:\n" + e.getMessage(), 0).show();
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
