package com.booklal.booklal;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetCurrentLocation extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int REQUEST_CHECK_SETTINGS_GPS = 1;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 2;
    private static final String TAG = "GetCurrentLocation";
    String address;
    int ask = 0;
    String city;
    String country;
    String countryCode;

    /* renamed from: db */
    FirebaseFirestore f75db = FirebaseFirestore.getInstance();
    Double dlatitude = Double.valueOf(34.0d);
    Double dlongitude = Double.valueOf(54.9d);
    DocumentReference documentReference;
    private int flag = 0;
    /* access modifiers changed from: private */
    public GoogleApiClient googleApiClient;
    FirebaseAuth mAuth;
    /* access modifiers changed from: private */
    public Location mylocation;
    String postalCode;
    String state;

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public void onConnectionSuspended(int i) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_get_current_location);
        setUpGClient();
    }

    private synchronized void setUpGClient() {
        GoogleApiClient build = new GoogleApiClient.Builder(this).enableAutoManage(this, 0, this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        this.googleApiClient = build;
        build.connect();
    }

    public void onLocationChanged(Location location) {
        this.flag++;
        this.mylocation = location;
        if (location != null) {
            this.dlatitude = Double.valueOf(location.getLatitude());
            this.dlongitude = Double.valueOf(this.mylocation.getLongitude());
            String str = this.dlatitude + "";
            String str2 = this.dlongitude + "";
            double parseDouble = Double.parseDouble(str);
            double parseDouble2 = Double.parseDouble(str2);
            try {
                List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(parseDouble, parseDouble2, 1);
                this.address = fromLocation.get(0).getAddressLine(0);
                this.city = fromLocation.get(0).getLocality();
                this.state = fromLocation.get(0).getAdminArea();
                this.country = fromLocation.get(0).getCountryName();
                this.postalCode = fromLocation.get(0).getPostalCode();
                this.countryCode = fromLocation.get(0).getCountryCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FirebaseAuth instance = FirebaseAuth.getInstance();
            this.mAuth = instance;
            final FirebaseUser currentUser = instance.getCurrentUser();
            final double d = parseDouble2;
            final double d2 = parseDouble;
            this.f75db.collection("users").document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                public void onComplete(Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot result = task.getResult();
                        if (result.exists()) {
                            GetCurrentLocation getCurrentLocation = GetCurrentLocation.this;
                            getCurrentLocation.documentReference = getCurrentLocation.f75db.collection("users").document(currentUser.getUid());
                            if (result.getDouble("Longitude") == null || result.getDouble("Latitude") == null) {
                                GetCurrentLocation.this.documentReference.update("Longitude", (Object) Double.valueOf(d), new Object[0]);
                                GetCurrentLocation.this.documentReference.update("Latitude", (Object) Double.valueOf(d2), new Object[0]);
                            }
                            if (result.get("country") == null || result.get("city") == null) {
                                GetCurrentLocation.this.documentReference.update("country", (Object) GetCurrentLocation.this.country, new Object[0]);
                                GetCurrentLocation.this.documentReference.update("state", (Object) GetCurrentLocation.this.state, new Object[0]);
                                GetCurrentLocation.this.documentReference.update("city", (Object) GetCurrentLocation.this.city, new Object[0]);
                                GetCurrentLocation.this.documentReference.update("address", (Object) GetCurrentLocation.this.address, new Object[0]);
                                GetCurrentLocation.this.documentReference.update("zip", (Object) GetCurrentLocation.this.postalCode, new Object[0]);
                                GetCurrentLocation.this.documentReference.update("country_code", (Object) GetCurrentLocation.this.countryCode, new Object[0]);
                                return;
                            }
                            return;
                        }
                        GetCurrentLocation getCurrentLocation2 = GetCurrentLocation.this;
                        getCurrentLocation2.documentReference = getCurrentLocation2.f75db.collection("users").document(currentUser.getUid());
                        GetCurrentLocation.this.documentReference.update("Longitude", (Object) Double.valueOf(d), new Object[0]);
                        GetCurrentLocation.this.documentReference.update("Latitude", (Object) Double.valueOf(d2), new Object[0]);
                        GetCurrentLocation.this.documentReference.update("country", (Object) GetCurrentLocation.this.country, new Object[0]);
                        GetCurrentLocation.this.documentReference.update("state", (Object) GetCurrentLocation.this.state, new Object[0]);
                        GetCurrentLocation.this.documentReference.update("city", (Object) GetCurrentLocation.this.city, new Object[0]);
                        GetCurrentLocation.this.documentReference.update("address", (Object) GetCurrentLocation.this.address, new Object[0]);
                        GetCurrentLocation.this.documentReference.update("zip", (Object) GetCurrentLocation.this.postalCode, new Object[0]);
                        GetCurrentLocation.this.documentReference.update("country_code", (Object) GetCurrentLocation.this.countryCode, new Object[0]);
                    }
                }
            });
            this.ask++;
            Intent intent = new Intent(this, Dashboard.class);
            intent.putExtra("g1", str);
            intent.putExtra("g2", str2);
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    public void onConnected(Bundle bundle) {
        checkPermissions();
    }

    private void getMyLocation() {
        GoogleApiClient googleApiClient2 = this.googleApiClient;
        if (googleApiClient2 != null && googleApiClient2.isConnected() && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.mylocation = LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(2000);
            locationRequest.setPriority(100);
            LocationSettingsRequest.Builder addLocationRequest = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            addLocationRequest.setAlwaysShow(true);
            LocationServices.FusedLocationApi.requestLocationUpdates(this.googleApiClient, locationRequest, (LocationListener) this, (Looper) null);
            LocationServices.SettingsApi.checkLocationSettings(this.googleApiClient, addLocationRequest.build()).setResultCallback(new ResultCallback<LocationSettingsResult>() {
                public void onResult(LocationSettingsResult locationSettingsResult) {
                    Status status = locationSettingsResult.getStatus();
                    int statusCode = status.getStatusCode();
                    if (statusCode != 0) {
                        if (statusCode == 6) {
                            try {
                                status.startResolutionForResult(GetCurrentLocation.this, 1);
                            } catch (IntentSender.SendIntentException unused) {
                            }
                        }
                    } else if (ContextCompat.checkSelfPermission(GetCurrentLocation.this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                        Location unused2 = GetCurrentLocation.this.mylocation = LocationServices.FusedLocationApi.getLastLocation(GetCurrentLocation.this.googleApiClient);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == -1) {
                getMyLocation();
            } else if (i2 == 0) {
                finish();
            }
        }
    }

    private void checkPermissions() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
            if (!arrayList.isEmpty()) {
                ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 2);
                return;
            }
            return;
        }
        getMyLocation();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            getMyLocation();
        }
    }
}
