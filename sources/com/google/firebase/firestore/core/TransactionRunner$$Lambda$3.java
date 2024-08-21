package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: TransactionRunner */
final /* synthetic */ class TransactionRunner$$Lambda$3 implements OnCompleteListener {
    private final TransactionRunner arg$1;
    private final Task arg$2;

    private TransactionRunner$$Lambda$3(TransactionRunner transactionRunner, Task task) {
        this.arg$1 = transactionRunner;
        this.arg$2 = task;
    }

    public static OnCompleteListener lambdaFactory$(TransactionRunner transactionRunner, Task task) {
        return new TransactionRunner$$Lambda$3(transactionRunner, task);
    }

    public void onComplete(Task task) {
        TransactionRunner.lambda$runWithBackoff$0(this.arg$1, this.arg$2, task);
    }
}
