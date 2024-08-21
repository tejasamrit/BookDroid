package com.google.android.gms.internal.p007firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbc */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbc<P> {
    private final ConcurrentMap<zzbb, List<zzba<P>>> zza = new ConcurrentHashMap();
    private zzba<P> zzb;
    private final Class<P> zzc;

    private zzbc(Class<P> cls) {
        this.zzc = cls;
    }

    public static <P> zzbc<P> zzb(Class<P> cls) {
        return new zzbc<>(cls);
    }

    public final List<zzba<P>> zza(byte[] bArr) {
        List<zzba<P>> list = (List) this.zza.get(new zzbb(bArr, (zzaz) null));
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    public final void zzc(zzba<P> zzba) {
        if (zzba.zzb() != zzhq.ENABLED) {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        } else if (!zza(zzba.zzd()).isEmpty()) {
            this.zzb = zzba;
        } else {
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        }
    }

    public final zzba<P> zzd(P p, zzia zzia) throws GeneralSecurityException {
        byte[] bArr;
        if (zzia.zzc() == zzhq.ENABLED) {
            zziu zziu = zziu.UNKNOWN_PREFIX;
            int ordinal = zzia.zze().ordinal();
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        bArr = zzaj.zza;
                    } else if (ordinal != 4) {
                        throw new GeneralSecurityException("unknown output prefix type");
                    }
                }
                bArr = ByteBuffer.allocate(5).put((byte) 0).putInt(zzia.zzd()).array();
            } else {
                bArr = ByteBuffer.allocate(5).put((byte) 1).putInt(zzia.zzd()).array();
            }
            zzba zzba = new zzba(p, bArr, zzia.zzc(), zzia.zze(), zzia.zzd());
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzba);
            zzbb zzbb = new zzbb(zzba.zzd(), (zzaz) null);
            List list = (List) this.zza.put(zzbb, Collections.unmodifiableList(arrayList));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list);
                arrayList2.add(zzba);
                this.zza.put(zzbb, Collections.unmodifiableList(arrayList2));
            }
            return zzba;
        }
        throw new GeneralSecurityException("only ENABLED key is allowed");
    }

    public final Class<P> zze() {
        return this.zzc;
    }
}
