package com.booklal.booklal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    EditText account_name;

    /* renamed from: b1 */
    Button f78b1;
    CountryCodePicker ccp;
    FirebaseAuth mAuth;
    String name;

    /* renamed from: t1 */
    EditText f79t1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AppCompatDelegate.setDefaultNightMode(1);
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_main);
        this.f79t1 = (EditText) findViewById(C0699R.C0702id.phoneNumber);
        this.ccp = (CountryCodePicker) findViewById(C0699R.C0702id.ccp);
        this.account_name = (EditText) findViewById(C0699R.C0702id.accountname);
        this.ccp.registerCarrierNumberEditText(this.f79t1);
        this.f78b1 = (Button) findViewById(C0699R.C0702id.getOTPButton);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        if (instance.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), GetCurrentLocation.class));
            finish();
        }
        this.f78b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManageOTP.class);
                if (MainActivity.this.f79t1.getText().toString().replace(" ", "").length() < 10) {
                    MainActivity.this.f79t1.setError("Invalid Phone Number");
                } else if (MainActivity.this.account_name.getText().toString().replace(" ", "").length() < 1) {
                    MainActivity.this.account_name.setError("Name can't be empty.");
                } else {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.name = mainActivity.account_name.getText().toString();
                    intent.putExtra("mobile", MainActivity.this.ccp.getFullNumberWithPlus().replace(" ", ""));
                    intent.putExtra("account_name", MainActivity.this.name);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
    }
}
