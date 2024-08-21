package org.aspectj.runtime.reflect;

import com.iceteck.silicompressorr.FileUtils;
import java.lang.reflect.Method;
import org.aspectj.lang.reflect.AdviceSignature;

class AdviceSignatureImpl extends CodeSignatureImpl implements AdviceSignature {
    private Method adviceMethod = null;
    Class returnType;

    AdviceSignatureImpl(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        super(i, str, cls, clsArr, strArr, clsArr2);
        this.returnType = cls2;
    }

    AdviceSignatureImpl(String str) {
        super(str);
    }

    public Class getReturnType() {
        if (this.returnType == null) {
            this.returnType = extractType(6);
        }
        return this.returnType;
    }

    /* access modifiers changed from: protected */
    public String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        if (stringMaker.includeArgs) {
            stringBuffer.append(stringMaker.makeTypeName(getReturnType()));
        }
        if (stringMaker.includeArgs) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(FileUtils.HIDDEN_PREFIX);
        stringBuffer.append(toAdviceName(getName()));
        stringMaker.addSignature(stringBuffer, getParameterTypes());
        stringMaker.addThrows(stringBuffer, getExceptionTypes());
        return stringBuffer.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String toAdviceName(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 36
            int r0 = r4.indexOf(r0)
            r1 = -1
            if (r0 != r1) goto L_0x000a
            return r4
        L_0x000a:
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            java.lang.String r1 = "$"
            r0.<init>(r4, r1)
        L_0x0011:
            boolean r1 = r0.hasMoreTokens()
            if (r1 == 0) goto L_0x0034
            java.lang.String r1 = r0.nextToken()
            java.lang.String r2 = "before"
            boolean r2 = r1.startsWith(r2)
            if (r2 != 0) goto L_0x0033
            java.lang.String r2 = "after"
            boolean r2 = r1.startsWith(r2)
            if (r2 != 0) goto L_0x0033
            java.lang.String r2 = "around"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x0011
        L_0x0033:
            return r1
        L_0x0034:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.runtime.reflect.AdviceSignatureImpl.toAdviceName(java.lang.String):java.lang.String");
    }

    public Method getAdvice() {
        if (this.adviceMethod == null) {
            try {
                this.adviceMethod = getDeclaringType().getDeclaredMethod(getName(), getParameterTypes());
            } catch (Exception unused) {
            }
        }
        return this.adviceMethod;
    }
}
