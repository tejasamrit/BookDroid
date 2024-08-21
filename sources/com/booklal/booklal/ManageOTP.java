package com.booklal.booklal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ManageOTP extends AppCompatActivity {
    private static final String TAG = "MainActivityTAG";

    /* renamed from: b2 */
    Button f80b2;

    /* renamed from: db */
    FirebaseFirestore f81db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    private FirebaseFirestore mstore;
    String name;
    int no_of_book = 0;
    String number;
    String otpid;
    String phonenumber;
    /* access modifiers changed from: private */
    public Boolean result = false;

    /* renamed from: t2 */
    EditText f82t2;
    TextView textMessage;
    TextView timer;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_manage_o_t_p);
        this.phonenumber = getIntent().getStringExtra("mobile");
        this.name = getIntent().getStringExtra("account_name");
        this.f82t2 = (EditText) findViewById(C0699R.C0702id.takeOTP);
        this.f80b2 = (Button) findViewById(C0699R.C0702id.verifyOTP);
        this.mAuth = FirebaseAuth.getInstance();
        this.timer = (TextView) findViewById(C0699R.C0702id.timer);
        this.textMessage = (TextView) findViewById(C0699R.C0702id.textMessage);
        initiateOTP();
        this.f80b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ManageOTP.this.f82t2.getText().toString().isEmpty()) {
                    Toast.makeText(ManageOTP.this, "Blank Field can't be processed", 1).show();
                } else if (ManageOTP.this.f82t2.getText().toString().length() != 6) {
                    Toast.makeText(ManageOTP.this, "Invalid OTP", 1).show();
                } else {
                    ManageOTP.this.signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(ManageOTP.this.otpid, ManageOTP.this.f82t2.getText().toString()));
                }
            }
        });
        new CountDownTimer(100000, 1000) {
            public void onTick(long j) {
                TextView textView = ManageOTP.this.timer;
                textView.setText("seconds left: " + (j / 1000));
            }

            public void onFinish() {
                ManageOTP.this.timer.setText("time up!");
                ManageOTP.this.textMessage.setText("Please go back and try again.");
            }
        }.start();
    }

    private void initiateOTP() {
        PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions.newBuilder(this.mAuth).setPhoneNumber(this.phonenumber).setTimeout(70L, TimeUnit.SECONDS).setActivity(this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.d(ManageOTP.TAG, "onVerificationCompleted:" + phoneAuthCredential);
                ManageOTP.this.signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            public void onVerificationFailed(FirebaseException firebaseException) {
                Log.w(ManageOTP.TAG, "onVerificationFailed", firebaseException);
                if (firebaseException instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(ManageOTP.this, firebaseException.getMessage(), 1).show();
                } else if (firebaseException instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(ManageOTP.this, "Too Many Requests!\n Try after some time.", 1).show();
                }
            }

            public void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Log.d(ManageOTP.TAG, "onCodeSent:" + str);
                ManageOTP.this.otpid = str;
            }
        }).build());
    }

    /* access modifiers changed from: private */
    public void signInWithPhoneAuthCredential(final PhoneAuthCredential phoneAuthCredential) {
        this.mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (ManageOTP.this.otpid != null) {
                        ManageOTP.this.f82t2.setText(phoneAuthCredential.getSmsCode());
                    }
                    final FirebaseUser currentUser = ManageOTP.this.mAuth.getCurrentUser();
                    ManageOTP.this.f81db.collection("users").document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        public void onComplete(Task<DocumentSnapshot> task) {
                            if (task.isSuccessful() && !task.getResult().exists()) {
                                String access$100 = ManageOTP.this.generateUsername(currentUser.getUid());
                                DocumentReference document = ManageOTP.this.f81db.collection("users").document(currentUser.getUid());
                                HashMap hashMap = new HashMap();
                                hashMap.put("displayname", ManageOTP.this.name);
                                hashMap.put("phone", ManageOTP.this.phonenumber);
                                hashMap.put("isSeller", false);
                                hashMap.put("count", Integer.valueOf(ManageOTP.this.no_of_book));
                                hashMap.put("username", access$100);
                                document.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    public void onSuccess(Void voidR) {
                                        Log.d(ManageOTP.TAG, "onSuccess: user profile ic created for user" + currentUser.getUid());
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    public void onFailure(Exception exc) {
                                        Log.w(ManageOTP.TAG, "Error adding document", exc);
                                    }
                                });
                            }
                        }
                    });
                    ManageOTP.this.startActivity(new Intent(ManageOTP.this, GetCurrentLocation.class));
                    ManageOTP.this.finish();
                    return;
                }
                Toast.makeText(ManageOTP.this, "SignIn Code Error", 1).show();
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(ManageOTP.this, "Invalid OTP", 0).show();
                }
            }
        });
    }

    private Boolean checkUsernameIsUnique(String str) {
        this.result = true;
        FirebaseFirestore instance = FirebaseFirestore.getInstance();
        this.mstore = instance;
        instance.collection("users").whereEqualTo("username", (Object) str).addSnapshotListener(new EventListener<QuerySnapshot>() {
            public void onEvent(QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
                Iterator<QueryDocumentSnapshot> it = querySnapshot.iterator();
                while (it.hasNext()) {
                    if (it.next() != null) {
                        Boolean unused = ManageOTP.this.result = false;
                        return;
                    }
                }
            }
        });
        return this.result;
    }

    /* access modifiers changed from: private */
    public String generateUsername(String str) {
        String str2;
        if (str != null) {
            str2 = str.length() == 3 ? str : str.length() > 3 ? str.substring(str.length() - 3) : "JPL";
        } else {
            str2 = "";
        }
        String str3 = "@" + str2.toUpperCase() + ThreadLocalRandom.current().nextInt(1000, 50000);
        if (!checkUsernameIsUnique(str3).booleanValue()) {
            generateUsername(str);
        }
        return str3;
    }
}
