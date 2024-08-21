package com.google.android.gms.internal.p006authapi;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.identity.CredentialSavingClient;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.auth.api.identity.zzf;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

/* renamed from: com.google.android.gms.internal.auth-api.zzak */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzak extends GoogleApi<zzf> implements CredentialSavingClient {
    private static final Api<zzf> API;
    private static final Api.ClientKey<zzab> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zzab, zzf> zzbn;

    public zzak(Context context, zzf zzf) {
        super(context, API, zzf.zzc.zzc(zzf).zze(zzba.zzw()).zzi(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public zzak(Activity activity, zzf zzf) {
        super(activity, API, zzf.zzc.zzc(zzf).zze(zzba.zzw()).zzi(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<SavePasswordResult> savePassword(SavePasswordRequest savePasswordRequest) {
        SavePasswordRequest build = SavePasswordRequest.zzc(savePasswordRequest).zzg(((zzf) getApiOptions()).zzh()).build();
        return doRead(TaskApiCall.builder().setFeatures(zzay.zzdg).run(new zzan(this, build)).setAutoResolveMissingFeatures(false).build());
    }

    static {
        Api.ClientKey<zzab> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzam zzam = new zzam();
        zzbn = zzam;
        API = new Api<>("Auth.Api.Identity.CredentialSaving.API", zzam, clientKey);
    }
}
