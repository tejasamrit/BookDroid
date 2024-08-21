package com.google.protobuf;

import java.lang.reflect.Field;

final class OneofInfo {
    private final Field caseField;

    /* renamed from: id */
    private final int f435id;
    private final Field valueField;

    public OneofInfo(int i, Field field, Field field2) {
        this.f435id = i;
        this.caseField = field;
        this.valueField = field2;
    }

    public int getId() {
        return this.f435id;
    }

    public Field getCaseField() {
        return this.caseField;
    }

    public Field getValueField() {
        return this.valueField;
    }
}
