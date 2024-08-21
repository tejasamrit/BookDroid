package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;

public abstract class Filter {
    public abstract String getCanonicalId();

    public abstract FieldPath getField();

    public abstract boolean matches(Document document);

    public enum Operator {
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        EQUAL("=="),
        NOT_EQUAL("!="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        ARRAY_CONTAINS("array_contains"),
        ARRAY_CONTAINS_ANY("array_contains_any"),
        IN("in"),
        NOT_IN("not_in");
        
        private final String text;

        private Operator(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }
    }
}
