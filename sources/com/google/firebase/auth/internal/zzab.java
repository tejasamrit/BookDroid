package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactorSession;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzab implements Continuation<GetTokenResult, Task<MultiFactorSession>> {
    zzab(zzac zzac) {
    }

    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        return !task.isSuccessful() ? Tasks.forException(task.getException()) : Tasks.forResult(zzag.zza(((GetTokenResult) task.getResult()).getToken()));
    }
}
