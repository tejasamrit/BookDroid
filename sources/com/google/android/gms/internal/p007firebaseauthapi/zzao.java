package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzaar;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzao */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public class zzao<PrimitiveT, KeyProtoT extends zzaar> implements zzam<PrimitiveT> {
    private final zzas<KeyProtoT> zza;
    private final Class<PrimitiveT> zzb;

    public zzao(zzas<KeyProtoT> zzas, Class<PrimitiveT> cls) {
        if (zzas.zzg().contains(cls) || Void.class.equals(cls)) {
            this.zza = zzas;
            this.zzb = cls;
            return;
        }
        throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", new Object[]{zzas.toString(), cls.getName()}));
    }

    private final PrimitiveT zze(KeyProtoT keyprotot) throws GeneralSecurityException {
        if (!Void.class.equals(this.zzb)) {
            this.zza.zze(keyprotot);
            return this.zza.zzf(keyprotot, this.zzb);
        }
        throw new GeneralSecurityException("Cannot create a primitive for Void");
    }

    private final zzan<?, KeyProtoT> zzf() {
        return new zzan<>(this.zza.zzi());
    }

    public final PrimitiveT zza(zzym zzym) throws GeneralSecurityException {
        try {
            return zze(this.zza.zzd(zzym));
        } catch (zzzw e) {
            String valueOf = String.valueOf(this.zza.zza().getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "Failures parsing proto of type ".concat(valueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    public final PrimitiveT zzb(zzaar zzaar) throws GeneralSecurityException {
        String valueOf = String.valueOf(this.zza.zza().getName());
        String concat = valueOf.length() != 0 ? "Expected proto of type ".concat(valueOf) : new String("Expected proto of type ");
        if (this.zza.zza().isInstance(zzaar)) {
            return zze(zzaar);
        }
        throw new GeneralSecurityException(concat);
    }

    public final zzaar zzc(zzym zzym) throws GeneralSecurityException {
        try {
            return zzf().zza(zzym);
        } catch (zzzw e) {
            String valueOf = String.valueOf(this.zza.zzi().zza().getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "Failures parsing proto of type ".concat(valueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    public final zzho zzd(zzym zzym) throws GeneralSecurityException {
        try {
            zzaar zza2 = zzf().zza(zzym);
            zzhl zzd = zzho.zzd();
            zzd.zza(this.zza.zzb());
            zzd.zzb(zza2.zzn());
            zzd.zzc(this.zza.zzc());
            return (zzho) zzd.zzl();
        } catch (zzzw e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }
}
