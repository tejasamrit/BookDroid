package com.google.firebase.auth.internal;

import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzo implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final ActionCodeInfo zzd;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzo(com.google.android.gms.internal.p007firebaseauthapi.zzwr r10) {
        /*
            r9 = this;
            r9.<init>()
            boolean r0 = r10.zzg()
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = r10.zzc()
            goto L_0x0012
        L_0x000e:
            java.lang.String r0 = r10.zzb()
        L_0x0012:
            r9.zzb = r0
            java.lang.String r0 = r10.zzb()
            r9.zzc = r0
            boolean r0 = r10.zzh()
            r1 = 0
            r2 = 3
            if (r0 != 0) goto L_0x0027
            r9.zza = r2
            r9.zzd = r1
            return
        L_0x0027:
            java.lang.String r0 = r10.zzd()
            int r3 = r0.hashCode()
            r4 = 0
            r5 = 5
            r6 = 2
            r7 = 1
            r8 = 4
            switch(r3) {
                case -1874510116: goto L_0x006a;
                case -1452371317: goto L_0x0060;
                case -1341836234: goto L_0x0056;
                case -1288726400: goto L_0x004c;
                case 870738373: goto L_0x0042;
                case 970484929: goto L_0x0038;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x0074
        L_0x0038:
            java.lang.String r3 = "RECOVER_EMAIL"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0074
            r0 = 4
            goto L_0x0075
        L_0x0042:
            java.lang.String r3 = "EMAIL_SIGNIN"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0074
            r0 = 2
            goto L_0x0075
        L_0x004c:
            java.lang.String r3 = "VERIFY_BEFORE_UPDATE_EMAIL"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0074
            r0 = 3
            goto L_0x0075
        L_0x0056:
            java.lang.String r3 = "VERIFY_EMAIL"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0074
            r0 = 1
            goto L_0x0075
        L_0x0060:
            java.lang.String r3 = "PASSWORD_RESET"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0074
            r0 = 0
            goto L_0x0075
        L_0x006a:
            java.lang.String r3 = "REVERT_SECOND_FACTOR_ADDITION"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0074
            r0 = 5
            goto L_0x0075
        L_0x0074:
            r0 = -1
        L_0x0075:
            if (r0 == 0) goto L_0x008c
            if (r0 == r7) goto L_0x008b
            if (r0 == r6) goto L_0x0089
            if (r0 == r2) goto L_0x0087
            if (r0 == r8) goto L_0x0085
            if (r0 == r5) goto L_0x0083
            r4 = 3
            goto L_0x008c
        L_0x0083:
            r4 = 6
            goto L_0x008c
        L_0x0085:
            r4 = 2
            goto L_0x008c
        L_0x0087:
            r4 = 5
            goto L_0x008c
        L_0x0089:
            r4 = 4
            goto L_0x008c
        L_0x008b:
            r4 = 1
        L_0x008c:
            r9.zza = r4
            if (r4 == r8) goto L_0x00d1
            if (r4 != r2) goto L_0x0093
            goto L_0x00d1
        L_0x0093:
            boolean r0 = r10.zzi()
            if (r0 == 0) goto L_0x00ab
            com.google.firebase.auth.internal.zzn r1 = new com.google.firebase.auth.internal.zzn
            java.lang.String r0 = r10.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwk r10 = r10.zze()
            com.google.firebase.auth.MultiFactorInfo r10 = com.google.firebase.auth.internal.zzba.zza(r10)
            r1.<init>(r0, r10)
            goto L_0x00ce
        L_0x00ab:
            boolean r0 = r10.zzg()
            if (r0 == 0) goto L_0x00bf
            com.google.firebase.auth.internal.zzl r1 = new com.google.firebase.auth.internal.zzl
            java.lang.String r0 = r10.zzc()
            java.lang.String r10 = r10.zzb()
            r1.<init>(r0, r10)
            goto L_0x00ce
        L_0x00bf:
            boolean r0 = r10.zzf()
            if (r0 == 0) goto L_0x00ce
            com.google.firebase.auth.internal.zzm r1 = new com.google.firebase.auth.internal.zzm
            java.lang.String r10 = r10.zzb()
            r1.<init>(r10)
        L_0x00ce:
            r9.zzd = r1
            return
        L_0x00d1:
            r9.zzd = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzo.<init>(com.google.android.gms.internal.firebase-auth-api.zzwr):void");
    }

    public final String getData(int i) {
        if (this.zza == 4) {
            return null;
        }
        if (i == 0) {
            return this.zzb;
        }
        if (i != 1) {
            return null;
        }
        return this.zzc;
    }

    public final ActionCodeInfo getInfo() {
        return this.zzd;
    }

    public final int getOperation() {
        return this.zza;
    }
}
