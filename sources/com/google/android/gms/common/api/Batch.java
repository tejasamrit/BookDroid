package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class Batch extends BasePendingResult<BatchResult> {
    /* access modifiers changed from: private */
    public int zab;
    /* access modifiers changed from: private */
    public boolean zac;
    /* access modifiers changed from: private */
    public boolean zad;
    /* access modifiers changed from: private */
    public final PendingResult<?>[] zae;
    /* access modifiers changed from: private */
    public final Object zaf;

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zaf = new Object();
        int size = list.size();
        this.zab = size;
        PendingResult<?>[] pendingResultArr = new PendingResult[size];
        this.zae = pendingResultArr;
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.RESULT_SUCCESS, pendingResultArr));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult<?> pendingResult = list.get(i);
            this.zae[i] = pendingResult;
            pendingResult.addStatusListener(new zab(this));
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class Builder {
        private List<PendingResult<?>> zaa = new ArrayList();
        private GoogleApiClient zab;

        public Builder(GoogleApiClient googleApiClient) {
            this.zab = googleApiClient;
        }

        public final <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zaa.size());
            this.zaa.add(pendingResult);
            return batchResultToken;
        }

        public final Batch build() {
            return new Batch(this.zaa, this.zab, (zab) null);
        }
    }

    public final void cancel() {
        super.cancel();
        for (PendingResult<?> cancel : this.zae) {
            cancel.cancel();
        }
    }

    public final BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.zae);
    }

    /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zab zab2) {
        this(list, googleApiClient);
    }

    static /* synthetic */ int zab(Batch batch) {
        int i = batch.zab;
        batch.zab = i - 1;
        return i;
    }
}
