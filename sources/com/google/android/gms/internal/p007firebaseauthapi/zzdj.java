package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;
import javax.crypto.KeyGenerator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdj {
    /* access modifiers changed from: private */
    public zzav zza = null;
    private String zzb = null;
    /* access modifiers changed from: private */
    public zzag zzc = null;
    private zzap zzd = null;
    /* access modifiers changed from: private */
    public zzau zze;
    private zzdo zzf = null;

    private final zzag zzh() throws GeneralSecurityException {
        if (Build.VERSION.SDK_INT >= 23) {
            zzdn zzdn = new zzdn();
            boolean zzc2 = zzdn.zzc(this.zzb);
            if (!zzc2) {
                try {
                    String str = this.zzb;
                    if (!new zzdn().zzc(str)) {
                        String zzc3 = zzkr.zzc("android-keystore://", str);
                        KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                        instance.init(new KeyGenParameterSpec.Builder(zzc3, 3).setKeySize(256).setBlockModes(new String[]{CodePackage.GCM}).setEncryptionPaddings(new String[]{"NoPadding"}).build());
                        instance.generateKey();
                    } else {
                        throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", new Object[]{str}));
                    }
                } catch (GeneralSecurityException | ProviderException e) {
                    Log.w(zzdk.zzb, "cannot use Android Keystore, it'll be disabled", e);
                    return null;
                }
            }
            try {
                return zzdn.zzb(this.zzb);
            } catch (GeneralSecurityException | ProviderException e2) {
                if (!zzc2) {
                    Log.w(zzdk.zzb, "cannot use Android Keystore, it'll be disabled", e2);
                    return null;
                }
                throw new KeyStoreException(String.format("the master key %s exists but is unusable", new Object[]{this.zzb}), e2);
            }
        } else {
            Log.w(zzdk.zzb, "Android Keystore requires at least Android M");
            return null;
        }
    }

    private final zzau zzi() throws GeneralSecurityException, IOException {
        zzag zzag = this.zzc;
        if (zzag != null) {
            try {
                return zzau.zza(zzat.zzi(this.zzf, zzag));
            } catch (zzzw | GeneralSecurityException e) {
                Log.w(zzdk.zzb, "cannot decrypt keyset: ", e);
            }
        }
        return zzau.zza(zzai.zzb(this.zzf));
    }

    public final zzdj zza(Context context, String str, String str2) throws IOException {
        if (context != null) {
            this.zzf = new zzdo(context, "GenericIdpKeyset", str2);
            this.zza = new zzdp(context, "GenericIdpKeyset", str2);
            return this;
        }
        throw new IllegalArgumentException("need an Android context");
    }

    public final zzdj zzb(String str) {
        if (str.startsWith("android-keystore://")) {
            this.zzb = str;
            return this;
        }
        throw new IllegalArgumentException("key URI must start with android-keystore://");
    }

    @Deprecated
    public final zzdj zzc(zzht zzht) {
        String zza2 = zzht.zza();
        byte[] zzp = zzht.zzb().zzp();
        zziu zzc2 = zzht.zzc();
        int i = zzdk.zza;
        zziu zziu = zziu.UNKNOWN_PREFIX;
        int ordinal = zzc2.ordinal();
        int i2 = 4;
        if (ordinal == 1) {
            i2 = 1;
        } else if (ordinal == 2) {
            i2 = 2;
        } else if (ordinal == 3) {
            i2 = 3;
        } else if (ordinal != 4) {
            throw new IllegalArgumentException("Unknown output prefix type");
        }
        this.zzd = zzap.zzb(zza2, zzp, i2);
        return this;
    }

    public final synchronized zzdk zzd() throws GeneralSecurityException, IOException {
        zzau zzau;
        if (this.zzb != null) {
            this.zzc = zzh();
        }
        try {
            zzau = zzi();
        } catch (FileNotFoundException e) {
            Log.w(zzdk.zzb, "keyset not found, will generate a new one", e);
            if (this.zzd != null) {
                zzau = zzau.zzb();
                zzau.zzd(this.zzd);
                zzau.zzf(zzau.zzc().zzc().zza(0).zza());
                if (this.zzc != null) {
                    zzau.zzc().zzd(this.zza, this.zzc);
                } else {
                    zzai.zza(zzau.zzc(), this.zza);
                }
            } else {
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
        }
        this.zze = zzau;
        return new zzdk(this, (zzdi) null);
    }
}
