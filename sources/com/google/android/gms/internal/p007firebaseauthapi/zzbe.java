package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzaar;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbe */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbe<PrimitiveT, KeyProtoT extends zzaar, PublicKeyProtoT extends zzaar> extends zzao<PrimitiveT, KeyProtoT> implements zzam {
    private final zzbf<KeyProtoT, PublicKeyProtoT> zza;
    private final zzas<PublicKeyProtoT> zzb;

    public zzbe(zzbf<KeyProtoT, PublicKeyProtoT> zzbf, zzas<PublicKeyProtoT> zzas, Class<PrimitiveT> cls) {
        super(zzbf, cls);
        this.zza = zzbf;
        this.zzb = zzas;
    }

    public final zzho zze(zzym zzym) throws GeneralSecurityException {
        try {
            zzgn zzd = zzgn.zzd(zzym, zzzb.zza());
            zzcx.zzj(zzd);
            zzgq zzb2 = zzd.zzb();
            this.zzb.zze(zzb2);
            zzhl zzd2 = zzho.zzd();
            zzd2.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
            zzd2.zzb(zzb2.zzn());
            zzd2.zzc(zzhn.ASYMMETRIC_PUBLIC);
            return (zzho) zzd2.zzl();
        } catch (zzzw e) {
            throw new GeneralSecurityException("expected serialized proto of type ", e);
        }
    }
}
