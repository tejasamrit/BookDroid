package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.p007firebaseauthapi.zzpl;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Future;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpo */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzpo<T extends zzpl> {
    private zzpk<T> zza;

    /* access modifiers changed from: package-private */
    public abstract Future<zzpk<T>> zza();

    public final <ResultT, A extends Api.AnyClient> Task<ResultT> zzb(zzpn<A, ResultT> zzpn) {
        return zzd().zza.doRead(zzpn.zzb());
    }

    public final <ResultT, A extends Api.AnyClient> Task<ResultT> zzc(zzpn<A, ResultT> zzpn) {
        return zzd().zza.doWrite(zzpn.zzb());
    }

    public final zzpk<T> zzd() {
        zzpk<T> zzpk;
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = (zzpk) zza().get();
                } catch (Exception e) {
                    String valueOf = String.valueOf(e.getMessage());
                    throw new RuntimeException(valueOf.length() != 0 ? "There was an error while initializing the connection to the GoogleApi: ".concat(valueOf) : new String("There was an error while initializing the connection to the GoogleApi: "));
                }
            }
            zzpk = this.zza;
        }
        return zzpk;
    }
}
