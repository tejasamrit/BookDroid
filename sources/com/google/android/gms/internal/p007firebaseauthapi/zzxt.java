package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzxt;
import com.google.android.gms.internal.p007firebaseauthapi.zzxu;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzxt<MessageType extends zzxu<MessageType, BuilderType>, BuilderType extends zzxt<MessageType, BuilderType>> implements zzaaq {
    /* renamed from: zzf */
    public abstract BuilderType clone();

    /* access modifiers changed from: protected */
    public abstract BuilderType zzg(MessageType messagetype);

    public final /* bridge */ /* synthetic */ zzaaq zzh(zzaar zzaar) {
        if (zzo().getClass().isInstance(zzaar)) {
            return zzg((zzxu) zzaar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
