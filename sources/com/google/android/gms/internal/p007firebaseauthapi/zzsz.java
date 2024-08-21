package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApi;
import com.google.firebase.FirebaseExceptionMapper;
import java.util.concurrent.Callable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsz implements Callable<zzpk<zztv>> {
    private final zztv zza;
    private final Context zzb;

    public zzsz(zztv zztv, Context context) {
        this.zza = zztv;
        this.zzb = context;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.zzb, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        boolean unused = zzta.zza = isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2;
        Context context = this.zzb;
        zztv zzc = this.zza.zza();
        zzc.zza = true;
        return new zzpk(new zzpm(context, zztw.zzb, zzc, new GoogleApi.Settings.Builder().setMapper(new FirebaseExceptionMapper()).build()));
    }
}
