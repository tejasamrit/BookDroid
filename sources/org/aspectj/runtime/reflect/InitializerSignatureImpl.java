package org.aspectj.runtime.reflect;

import com.iceteck.silicompressorr.FileUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import org.aspectj.lang.reflect.InitializerSignature;

class InitializerSignatureImpl extends CodeSignatureImpl implements InitializerSignature {
    private Constructor constructor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InitializerSignatureImpl(int i, Class cls) {
        super(i, Modifier.isStatic(i) ? "<clinit>" : "<init>", cls, SignatureImpl.EMPTY_CLASS_ARRAY, SignatureImpl.EMPTY_STRING_ARRAY, SignatureImpl.EMPTY_CLASS_ARRAY);
    }

    InitializerSignatureImpl(String str) {
        super(str);
    }

    public String getName() {
        return Modifier.isStatic(getModifiers()) ? "<clinit>" : "<init>";
    }

    /* access modifiers changed from: protected */
    public String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(FileUtils.HIDDEN_PREFIX);
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }

    public Constructor getInitializer() {
        if (this.constructor == null) {
            try {
                this.constructor = getDeclaringType().getDeclaredConstructor(getParameterTypes());
            } catch (Exception unused) {
            }
        }
        return this.constructor;
    }
}
