package com.google.android.gms.auth.api.identity;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzm implements Parcelable.Creator<SignInCredential> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new SignInCredential[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0049
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0044;
                case 2: goto L_0x003f;
                case 3: goto L_0x003a;
                case 4: goto L_0x0035;
                case 5: goto L_0x002b;
                case 6: goto L_0x0026;
                case 7: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r11, r1)
            goto L_0x000c
        L_0x0021:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x0026:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x002b:
            android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r1, r2)
            r7 = r1
            android.net.Uri r7 = (android.net.Uri) r7
            goto L_0x000c
        L_0x0035:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x003a:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x003f:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x0044:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x0049:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r11, r0)
            com.google.android.gms.auth.api.identity.SignInCredential r11 = new com.google.android.gms.auth.api.identity.SignInCredential
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.identity.zzm.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
