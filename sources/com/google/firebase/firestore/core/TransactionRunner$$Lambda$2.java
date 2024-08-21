package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: TransactionRunner */
final /* synthetic */ class TransactionRunner$$Lambda$2 implements OnCompleteListener {
    private final TransactionRunner arg$1;
    private final Transaction arg$2;

    private TransactionRunner$$Lambda$2(TransactionRunner transactionRunner, Transaction transaction) {
        this.arg$1 = transactionRunner;
        this.arg$2 = transaction;
    }

    public static OnCompleteListener lambdaFactory$(TransactionRunner transactionRunner, Transaction transaction) {
        return new TransactionRunner$$Lambda$2(transactionRunner, transaction);
    }

    public void onComplete(Task task) {
        TransactionRunner.lambda$runWithBackoff$1(this.arg$1, this.arg$2, task);
    }
}
