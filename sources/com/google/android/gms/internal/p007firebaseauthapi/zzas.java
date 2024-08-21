package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzaar;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzas */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzas<KeyProtoT extends zzaar> {
    private final Class<KeyProtoT> zza;
    private final Map<Class<?>, zzar<?, KeyProtoT>> zzb;
    private final Class<?> zzc;

    @SafeVarargs
    protected zzas(Class<KeyProtoT> cls, zzar<?, KeyProtoT>... zzarArr) {
        this.zza = cls;
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i <= 0) {
            zzar<?, KeyProtoT> zzar = zzarArr[i];
            if (hashMap.containsKey(zzar.zza())) {
                String valueOf = String.valueOf(zzar.zza().getCanonicalName());
                throw new IllegalArgumentException(valueOf.length() != 0 ? "KeyTypeManager constructed with duplicate factories for primitive ".concat(valueOf) : new String("KeyTypeManager constructed with duplicate factories for primitive "));
            } else {
                hashMap.put(zzar.zza(), zzar);
                i++;
            }
        }
        this.zzc = zzarArr[0].zza();
        this.zzb = Collections.unmodifiableMap(hashMap);
    }

    public final Class<KeyProtoT> zza() {
        return this.zza;
    }

    public abstract String zzb();

    public abstract zzhn zzc();

    public abstract KeyProtoT zzd(zzym zzym) throws zzzw;

    public abstract void zze(KeyProtoT keyprotot) throws GeneralSecurityException;

    public final <P> P zzf(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        zzar zzar = this.zzb.get(cls);
        if (zzar != null) {
            return zzar.zzb(keyprotot);
        }
        String canonicalName = cls.getCanonicalName();
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 41);
        sb.append("Requested primitive class ");
        sb.append(canonicalName);
        sb.append(" not supported.");
        throw new IllegalArgumentException(sb.toString());
    }

    public final Set<Class<?>> zzg() {
        return this.zzb.keySet();
    }

    /* access modifiers changed from: package-private */
    public final Class<?> zzh() {
        return this.zzc;
    }

    public zzaq<?, KeyProtoT> zzi() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }
}
