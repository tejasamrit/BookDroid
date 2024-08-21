package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.ListNetworkRequest;
import org.json.JSONException;

class ListTask implements Runnable {
    private static final String TAG = "ListTask";
    private final Integer maxResults;
    private final String pageToken;
    private final TaskCompletionSource<ListResult> pendingResult;
    private final ExponentialBackoffSender sender;
    private final StorageReference storageRef;

    ListTask(StorageReference storageReference, Integer num, String str, TaskCompletionSource<ListResult> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.storageRef = storageReference;
        this.maxResults = num;
        this.pageToken = str;
        this.pendingResult = taskCompletionSource;
        FirebaseStorage storage = storageReference.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    public void run() {
        ListResult listResult;
        ListNetworkRequest listNetworkRequest = new ListNetworkRequest(this.storageRef.getStorageUri(), this.storageRef.getApp(), this.maxResults, this.pageToken);
        this.sender.sendWithExponentialBackoff(listNetworkRequest);
        if (listNetworkRequest.isResultSuccess()) {
            try {
                listResult = ListResult.fromJSON(this.storageRef.getStorage(), listNetworkRequest.getResultBody());
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse response body. " + listNetworkRequest.getRawResult(), e);
                this.pendingResult.setException(StorageException.fromException(e));
                return;
            }
        } else {
            listResult = null;
        }
        TaskCompletionSource<ListResult> taskCompletionSource = this.pendingResult;
        if (taskCompletionSource != null) {
            listNetworkRequest.completeTask(taskCompletionSource, listResult);
        }
    }
}
