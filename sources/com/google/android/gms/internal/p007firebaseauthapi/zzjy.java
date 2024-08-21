package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzkg;
import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzjy<T_WRAPPER extends zzkg<T_ENGINE>, T_ENGINE> {
    public static final zzjy<zzjz, Cipher> zza = new zzjy<>(new zzjz());
    public static final zzjy<zzkd, Mac> zzb = new zzjy<>(new zzkd());
    public static final zzjy<zzkf, Signature> zzc = new zzjy<>(new zzkf());
    public static final zzjy<zzke, MessageDigest> zzd = new zzjy<>(new zzke());
    public static final zzjy<zzka, KeyAgreement> zze = new zzjy<>(new zzka());
    public static final zzjy<zzkc, KeyPairGenerator> zzf = new zzjy<>(new zzkc());
    public static final zzjy<zzkb, KeyFactory> zzg = new zzjy<>(new zzkb());
    private static final Logger zzh = Logger.getLogger(zzjy.class.getName());
    private static final List<Provider> zzi;
    private final T_WRAPPER zzj;
    private final List<Provider> zzk = zzi;

    static {
        if (zzkq.zza()) {
            String[] strArr = {ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                String str = strArr[i];
                Provider provider = Security.getProvider(str);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    zzh.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{str}));
                }
            }
            zzi = arrayList;
        } else {
            zzi = new ArrayList();
        }
    }

    public zzjy(T_WRAPPER t_wrapper) {
        this.zzj = t_wrapper;
    }

    public final T_ENGINE zza(String str) throws GeneralSecurityException {
        for (Provider zza2 : this.zzk) {
            try {
                return this.zzj.zza(str, zza2);
            } catch (Exception unused) {
            }
        }
        return this.zzj.zza(str, (Provider) null);
    }
}
