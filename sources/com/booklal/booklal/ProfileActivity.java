package com.booklal.booklal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import javax.annotation.Nullable;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    ImageButton backButton;
    FirebaseAuth mAuth;
    FirebaseStorage mref;
    FirebaseFirestore mstore;
    TextView pbio;
    TextView pcountry;
    TextView pemail;
    TextView pname;
    TextView pphone;
    Button profileEditButton;
    TextView pusername;
    int unicode = 10004;
    String userID;
    CircleImageView viewImage;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_profile);
        this.profileEditButton = (Button) findViewById(C0699R.C0702id.profile_edit_button);
        this.pusername = (TextView) findViewById(C0699R.C0702id.profile_name_username_tag);
        this.pname = (TextView) findViewById(C0699R.C0702id.profile_name);
        this.pemail = (TextView) findViewById(C0699R.C0702id.profile_email);
        this.pphone = (TextView) findViewById(C0699R.C0702id.profile_phone);
        this.pcountry = (TextView) findViewById(C0699R.C0702id.profile_country);
        this.pbio = (TextView) findViewById(C0699R.C0702id.profile_bio);
        this.viewImage = (CircleImageView) findViewById(C0699R.C0702id.profile_image);
        this.mAuth = FirebaseAuth.getInstance();
        this.mstore = FirebaseFirestore.getInstance();
        if (this.mAuth.getCurrentUser() != null) {
            this.userID = this.mAuth.getCurrentUser().getUid();
        }
        this.backButton = (ImageButton) findViewById(C0699R.C0702id.back_button_profile);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this.mstore.collection("users").document(this.userID).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    String string = documentSnapshot.getString("username");
                    if (string != null) {
                        ProfileActivity.this.pusername.setText(string);
                    }
                    String string2 = documentSnapshot.getString("pbio");
                    if (string2 != null) {
                        String str = "";
                        for (int i = 0; i < string2.length(); i++) {
                            if (string2.charAt(i) == 10) {
                                str = str + " ";
                            } else {
                                str = str + string2.charAt(i);
                            }
                        }
                        if (str != null) {
                            ProfileActivity.this.pbio.setText(str);
                        }
                    }
                    Boolean bool = documentSnapshot.getBoolean("isVerified");
                    String string3 = documentSnapshot.getString("displayname");
                    if (string3 != null) {
                        ProfileActivity.this.pname.setText(string3);
                        if (bool == null || !bool.booleanValue()) {
                            ProfileActivity.this.pname.setText(string3);
                        } else {
                            ProfileActivity profileActivity = ProfileActivity.this;
                            ProfileActivity.this.pname.setText(string3 + " " + profileActivity.getEmojiByUnicode(profileActivity.unicode));
                        }
                    }
                    String string4 = documentSnapshot.getString("email");
                    if (ProfileActivity.this.pemail != null) {
                        ProfileActivity.this.pemail.setText(string4);
                    }
                    ProfileActivity.this.pphone.setText(documentSnapshot.getString("phone"));
                    ProfileActivity.this.pcountry.setText(documentSnapshot.getString("country"));
                }
            });
            FirebaseStorage instance = FirebaseStorage.getInstance();
            this.mref = instance;
            StorageReference reference = instance.getReference("ProfileImages/");
            reference.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                public void onSuccess(Uri uri) {
                    Glide.with(ProfileActivity.this.getApplicationContext()).load(uri.toString()).into((ImageView) ProfileActivity.this.viewImage);
                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(Exception exc) {
                }
            });
        }
        this.profileEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this, Edit_Profile_Activity.class));
            }
        });
        this.viewImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfilePhotoViewActivity.class);
                intent.putExtra("userKey", ProfileActivity.this.userID);
                ProfileActivity.this.startActivity(intent);
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProfileActivity.this.onBackPressed();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(this, Dashboard.class));
        overridePendingTransition(0, 0);
    }

    public String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }
}
