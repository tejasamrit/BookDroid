package com.booklal.booklal;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfilePhotoViewActivity extends AppCompatActivity {
    ImageButton backButton;
    FirebaseStorage mref;
    ImageView profileImage;
    String userID;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_profile_photo_view);
        this.backButton = (ImageButton) findViewById(C0699R.C0702id.back_button_profile_image_view);
        this.profileImage = (ImageView) findViewById(C0699R.C0702id.ful_view_profile_image);
        this.userID = getIntent().getStringExtra("userKey");
        FirebaseStorage instance = FirebaseStorage.getInstance();
        this.mref = instance;
        StorageReference reference = instance.getReference("ProfileImages/");
        reference.child(this.userID + ".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                Glide.with(ProfilePhotoViewActivity.this.getApplicationContext()).load(uri.toString()).into(ProfilePhotoViewActivity.this.profileImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception exc) {
                if (Build.VERSION.SDK_INT >= 21) {
                    ProfilePhotoViewActivity.this.profileImage.setImageDrawable(ProfilePhotoViewActivity.this.getApplicationContext().getDrawable(C0699R.C0701drawable.account_circle));
                } else {
                    ProfilePhotoViewActivity.this.profileImage.setImageDrawable(ProfilePhotoViewActivity.this.getResources().getDrawable(C0699R.C0701drawable.account_circle));
                }
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProfilePhotoViewActivity.this.onBackPressed();
            }
        });
    }
}
