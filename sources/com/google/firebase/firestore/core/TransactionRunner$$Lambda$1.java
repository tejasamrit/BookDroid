package com.google.firebase.firestore.core;

/* compiled from: TransactionRunner */
final /* synthetic */ class TransactionRunner$$Lambda$1 implements Runnable {
    private final TransactionRunner arg$1;

    private TransactionRunner$$Lambda$1(TransactionRunner transactionRunner) {
        this.arg$1 = transactionRunner;
    }

    public static Runnable lambdaFactory$(TransactionRunner transactionRunner) {
        return new TransactionRunner$$Lambda$1(transactionRunner);
    }

    public void run() {
        TransactionRunner.lambda$runWithBackoff$2(this.arg$1);
    }
}
