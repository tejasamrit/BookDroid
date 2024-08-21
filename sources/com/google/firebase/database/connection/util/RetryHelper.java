package com.google.firebase.database.connection.util;

import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.logging.Logger;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RetryHelper {
    private long currentRetryDelay;
    private final ScheduledExecutorService executorService;
    private final double jitterFactor;
    private boolean lastWasSuccess;
    private final LogWrapper logger;
    private final long maxRetryDelay;
    private final long minRetryDelayAfterFailure;
    private final Random random;
    private final double retryExponent;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> scheduledRetry;

    private RetryHelper(ScheduledExecutorService scheduledExecutorService, LogWrapper logWrapper, long j, long j2, double d, double d2) {
        this.random = new Random();
        this.lastWasSuccess = true;
        this.executorService = scheduledExecutorService;
        this.logger = logWrapper;
        this.minRetryDelayAfterFailure = j;
        this.maxRetryDelay = j2;
        this.retryExponent = d;
        this.jitterFactor = d2;
    }

    public void retry(final Runnable runnable) {
        C17661 r0 = new Runnable() {
            public void run() {
                ScheduledFuture unused = RetryHelper.this.scheduledRetry = null;
                runnable.run();
            }
        };
        if (this.scheduledRetry != null) {
            this.logger.debug("Cancelling previous scheduled retry", new Object[0]);
            this.scheduledRetry.cancel(false);
            this.scheduledRetry = null;
        }
        long j = 0;
        if (!this.lastWasSuccess) {
            long j2 = this.currentRetryDelay;
            if (j2 == 0) {
                this.currentRetryDelay = this.minRetryDelayAfterFailure;
            } else {
                this.currentRetryDelay = Math.min((long) (((double) j2) * this.retryExponent), this.maxRetryDelay);
            }
            double d = this.jitterFactor;
            long j3 = this.currentRetryDelay;
            j = (long) (((1.0d - d) * ((double) j3)) + (d * ((double) j3) * this.random.nextDouble()));
        }
        this.lastWasSuccess = false;
        this.logger.debug("Scheduling retry in %dms", Long.valueOf(j));
        this.scheduledRetry = this.executorService.schedule(r0, j, TimeUnit.MILLISECONDS);
    }

    public void signalSuccess() {
        this.lastWasSuccess = true;
        this.currentRetryDelay = 0;
    }

    public void setMaxDelay() {
        this.currentRetryDelay = this.maxRetryDelay;
    }

    public void cancel() {
        if (this.scheduledRetry != null) {
            this.logger.debug("Cancelling existing retry attempt", new Object[0]);
            this.scheduledRetry.cancel(false);
            this.scheduledRetry = null;
        } else {
            this.logger.debug("No existing retry attempt to cancel", new Object[0]);
        }
        this.currentRetryDelay = 0;
    }

    public static class Builder {
        private double jitterFactor = 0.5d;
        private final LogWrapper logger;
        private long minRetryDelayAfterFailure = 1000;
        private double retryExponent = 1.3d;
        private long retryMaxDelay = 30000;
        private final ScheduledExecutorService service;

        public Builder(ScheduledExecutorService scheduledExecutorService, Logger logger2, String str) {
            this.service = scheduledExecutorService;
            this.logger = new LogWrapper(logger2, str);
        }

        public Builder withMinDelayAfterFailure(long j) {
            this.minRetryDelayAfterFailure = j;
            return this;
        }

        public Builder withMaxDelay(long j) {
            this.retryMaxDelay = j;
            return this;
        }

        public Builder withRetryExponent(double d) {
            this.retryExponent = d;
            return this;
        }

        public Builder withJitterFactor(double d) {
            if (d < 0.0d || d > 1.0d) {
                throw new IllegalArgumentException("Argument out of range: " + d);
            }
            this.jitterFactor = d;
            return this;
        }

        public RetryHelper build() {
            return new RetryHelper(this.service, this.logger, this.minRetryDelayAfterFailure, this.retryMaxDelay, this.retryExponent, this.jitterFactor);
        }
    }
}
