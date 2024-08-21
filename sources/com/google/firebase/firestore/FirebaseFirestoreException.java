package com.google.firebase.firestore;

import android.util.SparseArray;
import com.google.firebase.FirebaseException;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;

public class FirebaseFirestoreException extends FirebaseException {
    private final Code code;

    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);
        
        private static final SparseArray<Code> STATUS_LIST = null;
        private final int value;

        static {
            STATUS_LIST = buildStatusList();
        }

        private Code(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        private static SparseArray<Code> buildStatusList() {
            SparseArray<Code> sparseArray = new SparseArray<>();
            Code[] values = values();
            int length = values.length;
            int i = 0;
            while (i < length) {
                Code code = values[i];
                Code code2 = sparseArray.get(code.value());
                if (code2 == null) {
                    sparseArray.put(code.value(), code);
                    i++;
                } else {
                    throw new IllegalStateException("Code value duplication between " + code2 + "&" + code.name());
                }
            }
            return sparseArray;
        }

        public static Code fromValue(int i) {
            return STATUS_LIST.get(i, UNKNOWN);
        }
    }

    public FirebaseFirestoreException(String str, Code code2) {
        super(str);
        Preconditions.checkNotNull(str, "Provided message must not be null.");
        Assert.hardAssert(code2 != Code.OK, "A FirebaseFirestoreException should never be thrown for OK", new Object[0]);
        this.code = (Code) Preconditions.checkNotNull(code2, "Provided code must not be null.");
    }

    public FirebaseFirestoreException(String str, Code code2, Throwable th) {
        super(str, th);
        Preconditions.checkNotNull(str, "Provided message must not be null.");
        Assert.hardAssert(code2 != Code.OK, "A FirebaseFirestoreException should never be thrown for OK", new Object[0]);
        this.code = (Code) Preconditions.checkNotNull(code2, "Provided code must not be null.");
    }

    public Code getCode() {
        return this.code;
    }
}
