package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public class ActivityRecognitionClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public ActivityRecognitionClient(Context context) {
        super(context, LocationServices.API, null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    /* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
    private static class zza implements BaseImplementation.ResultHolder<Status> {
        private final TaskCompletionSource<Void> zza;

        public zza(TaskCompletionSource<Void> taskCompletionSource) {
            this.zza = taskCompletionSource;
        }

        public final void setFailedResult(Status status) {
            this.zza.setException(new ApiException(status));
        }

        public final /* synthetic */ void setResult(Object obj) {
            TaskUtil.setResultOrApiException((Status) obj, null, this.zza);
        }
    }

    public ActivityRecognitionClient(Activity activity) {
        super(activity, LocationServices.API, null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> requestActivityUpdates(long j, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzb(j, pendingIntent)).build());
    }

    public Task<Void> removeActivityUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzd(pendingIntent)).build());
    }

    public Task<Void> requestActivityTransitionUpdates(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzc(activityTransitionRequest, pendingIntent)).build());
    }

    public Task<Void> removeActivityTransitionUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zze(pendingIntent)).build());
    }
}
