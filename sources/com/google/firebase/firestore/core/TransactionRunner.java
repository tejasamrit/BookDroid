package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Function;

public class TransactionRunner<TResult> {
    private static final int RETRY_COUNT = 5;
    private AsyncQueue asyncQueue;
    private ExponentialBackoff backoff;
    private RemoteStore remoteStore;
    private int retriesLeft;
    private TaskCompletionSource<TResult> taskSource = new TaskCompletionSource<>();
    private Function<Transaction, Task<TResult>> updateFunction;

    public TransactionRunner(AsyncQueue asyncQueue2, RemoteStore remoteStore2, Function<Transaction, Task<TResult>> function) {
        this.asyncQueue = asyncQueue2;
        this.remoteStore = remoteStore2;
        this.updateFunction = function;
        this.retriesLeft = 5;
        this.backoff = new ExponentialBackoff(asyncQueue2, AsyncQueue.TimerId.RETRY_TRANSACTION);
    }

    public Task<TResult> run() {
        runWithBackoff();
        return this.taskSource.getTask();
    }

    private void runWithBackoff() {
        this.backoff.backoffAndRun(TransactionRunner$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$runWithBackoff$2(TransactionRunner transactionRunner) {
        Transaction createTransaction = transactionRunner.remoteStore.createTransaction();
        transactionRunner.updateFunction.apply(createTransaction).addOnCompleteListener(transactionRunner.asyncQueue.getExecutor(), TransactionRunner$$Lambda$2.lambdaFactory$(transactionRunner, createTransaction));
    }

    static /* synthetic */ void lambda$runWithBackoff$1(TransactionRunner transactionRunner, Transaction transaction, Task task) {
        if (!task.isSuccessful()) {
            transactionRunner.handleTransactionError(task);
        } else {
            transaction.commit().addOnCompleteListener(transactionRunner.asyncQueue.getExecutor(), TransactionRunner$$Lambda$3.lambdaFactory$(transactionRunner, task));
        }
    }

    static /* synthetic */ void lambda$runWithBackoff$0(TransactionRunner transactionRunner, Task task, Task task2) {
        if (task2.isSuccessful()) {
            transactionRunner.taskSource.setResult(task.getResult());
        } else {
            transactionRunner.handleTransactionError(task2);
        }
    }

    private void handleTransactionError(Task task) {
        if (this.retriesLeft <= 0 || !isRetryableTransactionError(task.getException())) {
            this.taskSource.setException(task.getException());
            return;
        }
        this.retriesLeft--;
        runWithBackoff();
    }

    private static boolean isRetryableTransactionError(Exception exc) {
        if (!(exc instanceof FirebaseFirestoreException)) {
            return false;
        }
        FirebaseFirestoreException firebaseFirestoreException = (FirebaseFirestoreException) exc;
        FirebaseFirestoreException.Code code = firebaseFirestoreException.getCode();
        if (code == FirebaseFirestoreException.Code.ABORTED || code == FirebaseFirestoreException.Code.FAILED_PRECONDITION || !Datastore.isPermanentError(firebaseFirestoreException.getCode())) {
            return true;
        }
        return false;
    }
}
