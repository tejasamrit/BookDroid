package com.google.android.gms.internal.p006authapi;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzag */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public interface zzag extends IInterface {
    void zzc(Status status, SavePasswordResult savePasswordResult) throws RemoteException;
}
