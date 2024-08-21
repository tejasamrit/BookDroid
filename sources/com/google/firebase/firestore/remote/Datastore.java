package com.google.firebase.firestore.remote;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firestore.p009v1.BatchGetDocumentsRequest;
import com.google.firestore.p009v1.BatchGetDocumentsResponse;
import com.google.firestore.p009v1.CommitRequest;
import com.google.firestore.p009v1.CommitResponse;
import com.google.firestore.p009v1.FirestoreGrpc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;
import p012io.grpc.Status;

public class Datastore {
    static final String SSL_DEPENDENCY_ERROR_MESSAGE = "The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.";
    static final Set<String> WHITE_LISTED_HEADERS = new HashSet(Arrays.asList(new String[]{"date", "x-google-backends", "x-google-netmon-label", "x-google-service", "x-google-gfe-request-trace"}));
    private final FirestoreChannel channel;
    private final DatabaseInfo databaseInfo;
    private final RemoteSerializer serializer;
    private final AsyncQueue workerQueue;

    public Datastore(DatabaseInfo databaseInfo2, AsyncQueue asyncQueue, CredentialsProvider credentialsProvider, Context context, GrpcMetadataProvider grpcMetadataProvider) {
        this.databaseInfo = databaseInfo2;
        this.workerQueue = asyncQueue;
        this.serializer = new RemoteSerializer(databaseInfo2.getDatabaseId());
        this.channel = new FirestoreChannel(asyncQueue, context, credentialsProvider, databaseInfo2, grpcMetadataProvider);
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.channel.shutdown();
    }

    /* access modifiers changed from: package-private */
    public AsyncQueue getWorkerQueue() {
        return this.workerQueue;
    }

    /* access modifiers changed from: package-private */
    public DatabaseInfo getDatabaseInfo() {
        return this.databaseInfo;
    }

    /* access modifiers changed from: package-private */
    public WatchStream createWatchStream(WatchStream.Callback callback) {
        return new WatchStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    /* access modifiers changed from: package-private */
    public WriteStream createWriteStream(WriteStream.Callback callback) {
        return new WriteStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    public Task<List<MutationResult>> commit(List<Mutation> list) {
        CommitRequest.Builder newBuilder = CommitRequest.newBuilder();
        newBuilder.setDatabase(this.serializer.databaseName());
        for (Mutation encodeMutation : list) {
            newBuilder.addWrites(this.serializer.encodeMutation(encodeMutation));
        }
        return this.channel.runRpc(FirestoreGrpc.getCommitMethod(), (CommitRequest) newBuilder.build()).continueWith(this.workerQueue.getExecutor(), Datastore$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ List lambda$commit$0(Datastore datastore, Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                datastore.channel.invalidateToken();
            }
            throw task.getException();
        }
        CommitResponse commitResponse = (CommitResponse) task.getResult();
        SnapshotVersion decodeVersion = datastore.serializer.decodeVersion(commitResponse.getCommitTime());
        int writeResultsCount = commitResponse.getWriteResultsCount();
        ArrayList arrayList = new ArrayList(writeResultsCount);
        for (int i = 0; i < writeResultsCount; i++) {
            arrayList.add(datastore.serializer.decodeMutationResult(commitResponse.getWriteResults(i), decodeVersion));
        }
        return arrayList;
    }

    public Task<List<MaybeDocument>> lookup(List<DocumentKey> list) {
        BatchGetDocumentsRequest.Builder newBuilder = BatchGetDocumentsRequest.newBuilder();
        newBuilder.setDatabase(this.serializer.databaseName());
        for (DocumentKey encodeKey : list) {
            newBuilder.addDocuments(this.serializer.encodeKey(encodeKey));
        }
        return this.channel.runStreamingResponseRpc(FirestoreGrpc.getBatchGetDocumentsMethod(), (BatchGetDocumentsRequest) newBuilder.build()).continueWith(this.workerQueue.getExecutor(), Datastore$$Lambda$2.lambdaFactory$(this, list));
    }

    static /* synthetic */ List lambda$lookup$1(Datastore datastore, List list, Task task) throws Exception {
        if (!task.isSuccessful() && (task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
            datastore.channel.invalidateToken();
        }
        HashMap hashMap = new HashMap();
        for (BatchGetDocumentsResponse decodeMaybeDocument : (List) task.getResult()) {
            MaybeDocument decodeMaybeDocument2 = datastore.serializer.decodeMaybeDocument(decodeMaybeDocument);
            hashMap.put(decodeMaybeDocument2.getKey(), decodeMaybeDocument2);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((MaybeDocument) hashMap.get((DocumentKey) it.next()));
        }
        return arrayList;
    }

    public static boolean isPermanentError(Status status) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(status.getCode().value()));
    }

    /* renamed from: com.google.firebase.firestore.remote.Datastore$1 */
    static /* synthetic */ class C19071 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code */
        static final /* synthetic */ int[] f349xf2f5b1d8;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.firestore.FirebaseFirestoreException$Code[] r0 = com.google.firebase.firestore.FirebaseFirestoreException.Code.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f349xf2f5b1d8 = r0
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.OK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.CANCELLED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.DEADLINE_EXCEEDED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.INTERNAL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAVAILABLE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAUTHENTICATED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.INVALID_ARGUMENT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.NOT_FOUND     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.ALREADY_EXISTS     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.PERMISSION_DENIED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x009c }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.FAILED_PRECONDITION     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.ABORTED     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.OUT_OF_RANGE     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNIMPLEMENTED     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = f349xf2f5b1d8     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.DATA_LOSS     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.remote.Datastore.C19071.<clinit>():void");
        }
    }

    public static boolean isPermanentError(FirebaseFirestoreException.Code code) {
        switch (C19071.f349xf2f5b1d8[code.ordinal()]) {
            case 1:
                throw new IllegalArgumentException("Treated status OK as error");
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return false;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return true;
            default:
                throw new IllegalArgumentException("Unknown gRPC status code: " + code);
        }
    }

    public static boolean isMissingSslCiphers(Status status) {
        Status.Code code = status.getCode();
        Throwable cause = status.getCause();
        boolean z = (cause instanceof SSLHandshakeException) && cause.getMessage().contains("no ciphers available");
        if (Build.VERSION.SDK_INT >= 21 || !code.equals(Status.Code.UNAVAILABLE) || !z) {
            return false;
        }
        return true;
    }

    public static boolean isPermanentWriteError(Status status) {
        return isPermanentError(status) && !status.getCode().equals(Status.Code.ABORTED);
    }
}
