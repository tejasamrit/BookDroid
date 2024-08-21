package com.booklal.booklal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class Edit_Profile_Activity extends AppCompatActivity {
    private static final int PICK_IMAGE_CODE = 1002;
    private static final int READ_PERMISSION_CODE = 1001;
    Boolean IsProvider;
    ImageButton backButton;
    ImageButton choose_Image;
    AnstronCoreHelper coreHelper;
    DocumentReference documentReference;
    EditText e_bio;
    EditText e_email;
    TextView e_pcountry;
    EditText e_pemail;
    EditText e_pname;
    TextView e_pphone;
    Boolean flag_msg = false;
    FirebaseAuth mAuth;
    FirebaseStorage mref;
    FirebaseFirestore mstore;
    String pbio;
    String pcountry;
    String pemail;
    Uri pickedImageUri;
    String pname;
    String pphone;
    DatabaseReference reference;
    Button save_profile_Button;
    StorageReference sref;
    FirebaseStorage storage;
    StorageReference storageReference;
    String updatedimageurl;
    StorageReference upr;
    String userID;
    String utilityCategory;
    Boolean verified = false;
    CircleImageView viewImage;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_edit__profile_);
        this.choose_Image = (ImageButton) findViewById(C0699R.C0702id.profile_pick);
        this.save_profile_Button = (Button) findViewById(C0699R.C0702id.edit_profile_edit_button);
        this.e_pname = (EditText) findViewById(C0699R.C0702id.edit_profile_name);
        this.e_pemail = (EditText) findViewById(C0699R.C0702id.edit_profile_email);
        this.e_pphone = (TextView) findViewById(C0699R.C0702id.edit_profile_phone);
        this.e_pcountry = (TextView) findViewById(C0699R.C0702id.edit_profile_country);
        this.e_bio = (EditText) findViewById(C0699R.C0702id.edit_profile_bio);
        this.e_email = (EditText) findViewById(C0699R.C0702id.edit_profile_email);
        this.viewImage = (CircleImageView) findViewById(C0699R.C0702id.edit_profile_image);
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        this.userID = this.mAuth.getCurrentUser().getUid();
        this.storageReference = FirebaseStorage.getInstance().getReference();
        this.coreHelper = new AnstronCoreHelper(this);
        this.backButton = (ImageButton) findViewById(C0699R.C0702id.back_button_edit_profile);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    Edit_Profile_Activity.this.pname = documentSnapshot.getString("displayname").toString();
                    Edit_Profile_Activity.this.verified = documentSnapshot.getBoolean("isVerified");
                    if (Edit_Profile_Activity.this.pname != null) {
                        Edit_Profile_Activity.this.e_pname.setText(Edit_Profile_Activity.this.pname);
                    }
                    Edit_Profile_Activity.this.pemail = documentSnapshot.getString("email");
                    if (Edit_Profile_Activity.this.pemail != null) {
                        Edit_Profile_Activity.this.e_email.setText(Edit_Profile_Activity.this.pemail);
                    }
                    Edit_Profile_Activity.this.pphone = documentSnapshot.getString("phone").toString();
                    if (Edit_Profile_Activity.this.pphone != null) {
                        Edit_Profile_Activity.this.e_pphone.setText(Edit_Profile_Activity.this.pphone);
                    }
                    Edit_Profile_Activity.this.e_pcountry.setText(documentSnapshot.getString("country"));
                    Edit_Profile_Activity.this.IsProvider = documentSnapshot.getBoolean("isSeller");
                    Edit_Profile_Activity.this.pbio = documentSnapshot.getString("pbio");
                    if (Edit_Profile_Activity.this.pbio != null) {
                        String str = "";
                        for (int i = 0; i < Edit_Profile_Activity.this.pbio.length(); i++) {
                            if (Edit_Profile_Activity.this.pbio.charAt(i) == 10) {
                                str = str + " ";
                            } else {
                                str = str + Edit_Profile_Activity.this.pbio.charAt(i);
                            }
                        }
                        if (str != null) {
                            Edit_Profile_Activity.this.e_bio.setText(str);
                        }
                    }
                }
            });
            FirebaseStorage instance = FirebaseStorage.getInstance();
            this.mref = instance;
            StorageReference reference2 = instance.getReference("ProfileImages/");
            reference2.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                public void onSuccess(Uri uri) {
                    Glide.with((FragmentActivity) Edit_Profile_Activity.this).load(uri.toString()).into((ImageView) Edit_Profile_Activity.this.viewImage);
                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(Exception exc) {
                }
            });
        }
        this.choose_Image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Edit_Profile_Activity.this.selectImage();
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Edit_Profile_Activity.this.onBackPressed();
            }
        });
    }

    public void updateProfile(View view) {
        hideKeyboard(this);
        view.setEnabled(false);
        this.documentReference = this.mstore.collection("users").document(this.userID);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (!(this.pbio != null || this.e_bio.getText().toString() == null || currentUser == null)) {
            this.documentReference.update("pbio", (Object) this.e_bio.getText().toString(), new Object[0]);
        }
        if (!(this.pbio == null || this.e_bio.getText().toString() == null || this.pbio.equals(this.e_bio.getText().toString()) || currentUser == null)) {
            this.documentReference.update("pbio", (Object) this.e_bio.getText().toString(), new Object[0]);
        }
        if (!(this.pemail != null || this.e_email.getText().toString() == null || currentUser == null)) {
            this.documentReference.update("email", (Object) this.e_email.getText().toString(), new Object[0]);
        }
        if (!(this.pemail == null || this.e_email.getText().toString() == null || this.e_email.getText().toString() == null || this.pemail.equals(this.e_email.getText().toString()) || currentUser == null)) {
            this.documentReference.update("email", (Object) this.e_email.getText().toString(), new Object[0]);
        }
        isNameChanged();
    }

    private void isNameChanged() {
        if (!this.pname.equals(this.e_pname.getText().toString())) {
            Boolean bool = this.verified;
            if (bool == null) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    this.documentReference = this.mstore.collection("users").document(this.userID);
                    String obj = this.e_pname.getText().toString();
                    this.pname = obj;
                    if (obj.length() < 1) {
                        this.e_pname.setError("Name can't be empty!");
                    }
                    this.documentReference.update("displayname", (Object) this.pname, new Object[0]);
                }
            } else if (bool == null || bool.booleanValue()) {
                Boolean bool2 = this.verified;
                if (bool2 != null && bool2.booleanValue()) {
                    this.flag_msg = true;
                    this.e_pname.setError("Verified user can't change name.");
                    Toast.makeText(this, "verified user can't change name.", 0).show();
                }
            } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                this.documentReference = this.mstore.collection("users").document(this.userID);
                String obj2 = this.e_pname.getText().toString();
                this.pname = obj2;
                if (obj2.length() < 1) {
                    this.e_pname.setError("Name can't be empty!");
                }
                this.documentReference.update("displayname", (Object) this.pname, new Object[0]);
            }
            if (!this.flag_msg.booleanValue()) {
                Toast.makeText(this, "Updated...!", 0).show();
                return;
            }
            return;
        }
        Toast.makeText(this, "Updated...!", 0).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0699R.C0704menu.main, menu);
        return true;
    }

    /* access modifiers changed from: private */
    public void selectImage() {
        final CharSequence[] charSequenceArr = {"Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() {
            /* JADX WARNING: Removed duplicated region for block: B:11:0x0055  */
            /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.content.DialogInterface r5, int r6) {
                /*
                    r4 = this;
                    java.lang.CharSequence[] r0 = r0
                    r0 = r0[r6]
                    java.lang.String r1 = "Choose from Gallery"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x006a
                    r5 = 0
                    int r6 = android.os.Build.VERSION.SDK_INT
                    r0 = 22
                    r1 = 2
                    java.lang.String r2 = "android.intent.action.PICK"
                    r3 = 1
                    if (r6 <= r0) goto L_0x003e
                    boolean r6 = r4.checkIfAlreadyHavePermission()
                    if (r6 != 0) goto L_0x0029
                    com.booklal.booklal.Edit_Profile_Activity r6 = com.booklal.booklal.Edit_Profile_Activity.this
                    java.lang.String r0 = "android.permission.READ_EXTERNAL_STORAGE"
                    java.lang.String[] r0 = new java.lang.String[]{r0}
                    androidx.core.app.ActivityCompat.requestPermissions(r6, r0, r3)
                    goto L_0x0053
                L_0x0029:
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.Edit_Profile_Activity r6 = com.booklal.booklal.Edit_Profile_Activity.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.Edit_Profile_Activity r6 = com.booklal.booklal.Edit_Profile_Activity.this
                    r6.startActivityForResult(r5, r1)
                    goto L_0x0052
                L_0x003e:
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.Edit_Profile_Activity r6 = com.booklal.booklal.Edit_Profile_Activity.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.Edit_Profile_Activity r6 = com.booklal.booklal.Edit_Profile_Activity.this
                    r6.startActivityForResult(r5, r1)
                L_0x0052:
                    r5 = 1
                L_0x0053:
                    if (r5 != 0) goto L_0x0079
                    android.content.Intent r5 = new android.content.Intent
                    android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    r5.<init>(r2, r6)
                    com.booklal.booklal.Edit_Profile_Activity r6 = com.booklal.booklal.Edit_Profile_Activity.this
                    android.net.Uri r0 = r5.getData()
                    r6.pickedImageUri = r0
                    com.booklal.booklal.Edit_Profile_Activity r6 = com.booklal.booklal.Edit_Profile_Activity.this
                    r6.startActivityForResult(r5, r1)
                    goto L_0x0079
                L_0x006a:
                    java.lang.CharSequence[] r0 = r0
                    r6 = r0[r6]
                    java.lang.String r0 = "Cancel"
                    boolean r6 = r6.equals(r0)
                    if (r6 == 0) goto L_0x0079
                    r5.dismiss()
                L_0x0079:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.booklal.booklal.Edit_Profile_Activity.C06376.onClick(android.content.DialogInterface, int):void");
            }

            private boolean checkIfAlreadyHavePermission() {
                return ContextCompat.checkSelfPermission(Edit_Profile_Activity.this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
            }
        });
        builder.show();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r9 = android.graphics.BitmapFactory.decodeFile(r8.getAbsolutePath(), new android.graphics.BitmapFactory.Options());
        r7.viewImage.setImageBitmap(r9);
        r10 = android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "Phoenix" + java.io.File.separator + "default";
        r8.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r10 = new java.io.FileOutputStream(new java.io.File(r10, java.lang.System.currentTimeMillis() + ".jpg"));
        r9.compress(android.graphics.Bitmap.CompressFormat.JPEG, 85, r10);
        r10.flush();
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0098, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r8.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x009e, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009f, code lost:
        r8.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a4, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a5, code lost:
        r8.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00aa, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ab, code lost:
        r8.printStackTrace();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r8, int r9, android.content.Intent r10) {
        /*
            r7 = this;
            super.onActivityResult(r8, r9, r10)
            r0 = -1
            if (r9 != r0) goto L_0x0156
            r9 = 1
            r0 = 0
            if (r8 != r9) goto L_0x00b0
            java.io.File r8 = new java.io.File
            java.io.File r9 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            java.io.File[] r9 = r8.listFiles()
            int r10 = r9.length
        L_0x001c:
            if (r0 >= r10) goto L_0x0031
            r1 = r9[r0]
            java.lang.String r2 = r1.getName()
            java.lang.String r3 = "temp.jpg"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x002e
            r8 = r1
            goto L_0x0031
        L_0x002e:
            int r0 = r0 + 1
            goto L_0x001c
        L_0x0031:
            android.graphics.BitmapFactory$Options r9 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x00aa }
            r9.<init>()     // Catch:{ Exception -> 0x00aa }
            java.lang.String r10 = r8.getAbsolutePath()     // Catch:{ Exception -> 0x00aa }
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeFile(r10, r9)     // Catch:{ Exception -> 0x00aa }
            de.hdodenhof.circleimageview.CircleImageView r10 = r7.viewImage     // Catch:{ Exception -> 0x00aa }
            r10.setImageBitmap(r9)     // Catch:{ Exception -> 0x00aa }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
            r10.<init>()     // Catch:{ Exception -> 0x00aa }
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x00aa }
            r10.append(r0)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r0 = java.io.File.separator     // Catch:{ Exception -> 0x00aa }
            r10.append(r0)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r0 = "Phoenix"
            r10.append(r0)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r0 = java.io.File.separator     // Catch:{ Exception -> 0x00aa }
            r10.append(r0)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r0 = "default"
            r10.append(r0)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x00aa }
            r8.delete()     // Catch:{ Exception -> 0x00aa }
            java.io.File r8 = new java.io.File     // Catch:{ Exception -> 0x00aa }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
            r0.<init>()     // Catch:{ Exception -> 0x00aa }
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00aa }
            r0.append(r1)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r1 = ".jpg"
            r0.append(r1)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00aa }
            r8.<init>(r10, r0)     // Catch:{ Exception -> 0x00aa }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x00a4, IOException -> 0x009e, Exception -> 0x0098 }
            r10.<init>(r8)     // Catch:{ FileNotFoundException -> 0x00a4, IOException -> 0x009e, Exception -> 0x0098 }
            android.graphics.Bitmap$CompressFormat r8 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ FileNotFoundException -> 0x00a4, IOException -> 0x009e, Exception -> 0x0098 }
            r0 = 85
            r9.compress(r8, r0, r10)     // Catch:{ FileNotFoundException -> 0x00a4, IOException -> 0x009e, Exception -> 0x0098 }
            r10.flush()     // Catch:{ FileNotFoundException -> 0x00a4, IOException -> 0x009e, Exception -> 0x0098 }
            r10.close()     // Catch:{ FileNotFoundException -> 0x00a4, IOException -> 0x009e, Exception -> 0x0098 }
            goto L_0x0156
        L_0x0098:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ Exception -> 0x00aa }
            goto L_0x0156
        L_0x009e:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ Exception -> 0x00aa }
            goto L_0x0156
        L_0x00a4:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ Exception -> 0x00aa }
            goto L_0x0156
        L_0x00aa:
            r8 = move-exception
            r8.printStackTrace()
            goto L_0x0156
        L_0x00b0:
            r9 = 2
            if (r8 != r9) goto L_0x0156
            android.net.Uri r8 = r10.getData()
            java.lang.String r9 = "_data"
            java.lang.String[] r9 = new java.lang.String[]{r9}
            android.content.ContentResolver r1 = r7.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r8
            r3 = r9
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6)
            r10.moveToFirst()
            r9 = r9[r0]
            int r9 = r10.getColumnIndex(r9)
            java.lang.String r9 = r10.getString(r9)
            r10.close()
            android.graphics.Bitmap r10 = android.graphics.BitmapFactory.decodeFile(r9)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            java.lang.String r9 = ""
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            java.lang.String r1 = "path of image"
            android.util.Log.w(r1, r9)
            de.hdodenhof.circleimageview.CircleImageView r9 = r7.viewImage
            r9.setImageBitmap(r10)
            if (r8 == 0) goto L_0x0156
            android.app.ProgressDialog r9 = new android.app.ProgressDialog
            r9.<init>(r7)
            java.lang.String r10 = "Updating..."
            r9.setMessage(r10)
            r9.setCancelable(r0)
            r9.show()
            java.io.File r10 = new java.io.File
            com.iceteck.silicompressorr.SiliCompressor r0 = com.iceteck.silicompressorr.SiliCompressor.with(r7)
            java.lang.String r8 = com.iceteck.silicompressorr.FileUtils.getPath(r7, r8)
            java.io.File r1 = new java.io.File
            java.io.File r2 = r7.getCacheDir()
            java.lang.String r3 = "temp"
            r1.<init>(r2, r3)
            java.lang.String r8 = r0.compress(r8, r1)
            r10.<init>(r8)
            android.net.Uri r8 = android.net.Uri.fromFile(r10)
            com.google.firebase.storage.StorageReference r0 = r7.storageReference
            java.lang.String r1 = "ProfileImages/"
            com.google.firebase.storage.StorageReference r0 = r0.child(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r7.userID
            r1.append(r2)
            java.lang.String r2 = ".jpeg"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.google.firebase.storage.StorageReference r0 = r0.child(r1)
            com.google.firebase.storage.UploadTask r8 = r0.putFile(r8)
            com.booklal.booklal.Edit_Profile_Activity$7 r0 = new com.booklal.booklal.Edit_Profile_Activity$7
            r0.<init>(r9, r10)
            r8.addOnCompleteListener((com.google.android.gms.tasks.OnCompleteListener) r0)
        L_0x0156:
            android.widget.ImageButton r8 = r7.backButton
            com.booklal.booklal.Edit_Profile_Activity$8 r9 = new com.booklal.booklal.Edit_Profile_Activity$8
            r9.<init>()
            r8.setOnClickListener(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.booklal.booklal.Edit_Profile_Activity.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(this, ProfileActivity.class));
        overridePendingTransition(0, 0);
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
}
