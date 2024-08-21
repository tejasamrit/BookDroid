package org.aspectj.runtime.reflect;

import com.iceteck.silicompressorr.FileUtils;
import java.lang.reflect.Field;
import org.aspectj.lang.reflect.FieldSignature;

public class FieldSignatureImpl extends MemberSignatureImpl implements FieldSignature {
    private Field field;
    Class fieldType;

    FieldSignatureImpl(int i, String str, Class cls, Class cls2) {
        super(i, str, cls);
        this.fieldType = cls2;
    }

    FieldSignatureImpl(String str) {
        super(str);
    }

    public Class getFieldType() {
        if (this.fieldType == null) {
            this.fieldType = extractType(3);
        }
        return this.fieldType;
    }

    /* access modifiers changed from: protected */
    public String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        if (stringMaker.includeArgs) {
            stringBuffer.append(stringMaker.makeTypeName(getFieldType()));
        }
        if (stringMaker.includeArgs) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(FileUtils.HIDDEN_PREFIX);
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }

    public Field getField() {
        if (this.field == null) {
            try {
                this.field = getDeclaringType().getDeclaredField(getName());
            } catch (Exception unused) {
            }
        }
        return this.field;
    }
}
