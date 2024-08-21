package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzww */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzww implements zzty {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private final zzxe zzg = new zzxe((List<String>) null);
    private final zzxe zzh = new zzxe((List<String>) null);
    private String zzi;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza() throws org.json.JSONException {
        /*
            r11 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "returnSecureToken"
            r2 = 1
            r0.put(r1, r2)
            com.google.android.gms.internal.firebase-auth-api.zzxe r1 = r11.zzh
            java.util.List r1 = r1.zza()
            boolean r1 = r1.isEmpty()
            r3 = 0
            if (r1 != 0) goto L_0x0039
            com.google.android.gms.internal.firebase-auth-api.zzxe r1 = r11.zzh
            java.util.List r1 = r1.zza()
            org.json.JSONArray r4 = new org.json.JSONArray
            r4.<init>()
            r5 = 0
        L_0x0024:
            int r6 = r1.size()
            if (r5 >= r6) goto L_0x0034
            java.lang.Object r6 = r1.get(r5)
            r4.put(r6)
            int r5 = r5 + 1
            goto L_0x0024
        L_0x0034:
            java.lang.String r1 = "deleteProvider"
            r0.put(r1, r4)
        L_0x0039:
            com.google.android.gms.internal.firebase-auth-api.zzxe r1 = r11.zzg
            java.util.List r1 = r1.zza()
            int r4 = r1.size()
            int[] r5 = new int[r4]
            r6 = 0
        L_0x0046:
            int r7 = r1.size()
            if (r6 >= r7) goto L_0x0099
            java.lang.Object r7 = r1.get(r6)
            java.lang.String r7 = (java.lang.String) r7
            int r8 = r7.hashCode()
            r9 = 3
            r10 = 2
            switch(r8) {
                case -333046776: goto L_0x007a;
                case 66081660: goto L_0x0070;
                case 1939891618: goto L_0x0066;
                case 1999612571: goto L_0x005c;
                default: goto L_0x005b;
            }
        L_0x005b:
            goto L_0x0084
        L_0x005c:
            java.lang.String r8 = "PASSWORD"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0084
            r7 = 2
            goto L_0x0085
        L_0x0066:
            java.lang.String r8 = "PHOTO_URL"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0084
            r7 = 3
            goto L_0x0085
        L_0x0070:
            java.lang.String r8 = "EMAIL"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0084
            r7 = 0
            goto L_0x0085
        L_0x007a:
            java.lang.String r8 = "DISPLAY_NAME"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0084
            r7 = 1
            goto L_0x0085
        L_0x0084:
            r7 = -1
        L_0x0085:
            if (r7 == 0) goto L_0x0093
            if (r7 == r2) goto L_0x0094
            if (r7 == r10) goto L_0x0091
            if (r7 == r9) goto L_0x008f
            r10 = 0
            goto L_0x0094
        L_0x008f:
            r10 = 4
            goto L_0x0094
        L_0x0091:
            r10 = 5
            goto L_0x0094
        L_0x0093:
            r10 = 1
        L_0x0094:
            r5[r6] = r10
            int r6 = r6 + 1
            goto L_0x0046
        L_0x0099:
            if (r4 <= 0) goto L_0x00af
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
        L_0x00a0:
            if (r3 >= r4) goto L_0x00aa
            r2 = r5[r3]
            r1.put(r2)
            int r3 = r3 + 1
            goto L_0x00a0
        L_0x00aa:
            java.lang.String r2 = "deleteAttribute"
            r0.put(r2, r1)
        L_0x00af:
            java.lang.String r1 = r11.zza
            if (r1 == 0) goto L_0x00b8
            java.lang.String r2 = "idToken"
            r0.put(r2, r1)
        L_0x00b8:
            java.lang.String r1 = r11.zzc
            if (r1 == 0) goto L_0x00c1
            java.lang.String r2 = "email"
            r0.put(r2, r1)
        L_0x00c1:
            java.lang.String r1 = r11.zzd
            if (r1 == 0) goto L_0x00ca
            java.lang.String r2 = "password"
            r0.put(r2, r1)
        L_0x00ca:
            java.lang.String r1 = r11.zzb
            if (r1 == 0) goto L_0x00d3
            java.lang.String r2 = "displayName"
            r0.put(r2, r1)
        L_0x00d3:
            java.lang.String r1 = r11.zzf
            if (r1 == 0) goto L_0x00dc
            java.lang.String r2 = "photoUrl"
            r0.put(r2, r1)
        L_0x00dc:
            java.lang.String r1 = r11.zze
            if (r1 == 0) goto L_0x00e5
            java.lang.String r2 = "oobCode"
            r0.put(r2, r1)
        L_0x00e5:
            java.lang.String r1 = r11.zzi
            if (r1 == 0) goto L_0x00ee
            java.lang.String r2 = "tenantId"
            r0.put(r2, r1)
        L_0x00ee:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzww.zza():java.lang.String");
    }

    public final boolean zzb(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzg.zza().contains(str);
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final String zze() {
        return this.zzb;
    }

    public final String zzf() {
        return this.zzf;
    }

    public final zzww zzg(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzww zzh(String str) {
        if (str == null) {
            this.zzg.zza().add("EMAIL");
        } else {
            this.zzc = str;
        }
        return this;
    }

    public final zzww zzi(String str) {
        if (str == null) {
            this.zzg.zza().add("PASSWORD");
        } else {
            this.zzd = str;
        }
        return this;
    }

    public final zzww zzj(String str) {
        if (str == null) {
            this.zzg.zza().add("DISPLAY_NAME");
        } else {
            this.zzb = str;
        }
        return this;
    }

    public final zzww zzk(String str) {
        if (str == null) {
            this.zzg.zza().add("PHOTO_URL");
        } else {
            this.zzf = str;
        }
        return this;
    }

    public final zzww zzl(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzh.zza().add(str);
        return this;
    }

    public final zzww zzm(String str) {
        this.zze = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzww zzn(String str) {
        this.zzi = str;
        return this;
    }
}
