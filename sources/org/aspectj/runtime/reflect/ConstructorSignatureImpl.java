package org.aspectj.runtime.reflect;

import java.lang.reflect.Constructor;
import org.aspectj.lang.reflect.ConstructorSignature;

class ConstructorSignatureImpl extends CodeSignatureImpl implements ConstructorSignature {
    private Constructor constructor;

    public String getName() {
        return "<init>";
    }

    ConstructorSignatureImpl(int i, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        super(i, "<init>", cls, clsArr, strArr, clsArr2);
    }

    ConstructorSignatureImpl(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringMaker.addSignature(stringBuffer, getParameterTypes());
        stringMaker.addThrows(stringBuffer, getExceptionTypes());
        return stringBuffer.toString();
    }

    public Constructor getConstructor() {
        if (this.constructor == null) {
            try {
                this.constructor = getDeclaringType().getDeclaredConstructor(getParameterTypes());
            } catch (Exception unused) {
            }
        }
        return this.constructor;
    }
}
