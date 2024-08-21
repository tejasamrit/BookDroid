package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzxt;
import com.google.android.gms.internal.p007firebaseauthapi.zzxu;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxu */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzxu<MessageType extends zzxu<MessageType, BuilderType>, BuilderType extends zzxt<MessageType, BuilderType>> implements zzaar {
    protected int zza = 0;

    public final byte[] zzI() {
        try {
            byte[] bArr = new byte[zzw()];
            zzyw zzx = zzyw.zzx(bArr);
            zzv(zzx);
            zzx.zzI();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final zzym zzn() {
        try {
            zzyi zzr = zzym.zzr(zzw());
            zzv(zzr.zzb());
            return zzr.zza();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final void zzp(OutputStream outputStream) throws IOException {
        zzyw zzw = zzyw.zzw(outputStream, zzyw.zzv(zzw()));
        zzv(zzw);
        zzw.zzu();
    }

    /* access modifiers changed from: package-private */
    public int zzq() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public void zzr(int i) {
        throw null;
    }
}
