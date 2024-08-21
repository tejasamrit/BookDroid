package org.aspectj.internal.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.InterTypeConstructorDeclaration;

public class InterTypeConstructorDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeConstructorDeclaration {
    private Method baseMethod;

    public InterTypeConstructorDeclarationImpl(AjType<?> ajType, String str, int i, Method method) {
        super(ajType, str, i);
        this.baseMethod = method;
    }

    public AjType<?>[] getParameterTypes() {
        Class[] parameterTypes = this.baseMethod.getParameterTypes();
        AjType<?>[] ajTypeArr = new AjType[(parameterTypes.length - 1)];
        for (int i = 1; i < parameterTypes.length; i++) {
            ajTypeArr[i - 1] = AjTypeSystem.getAjType(parameterTypes[i]);
        }
        return ajTypeArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: org.aspectj.lang.reflect.AjType[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Type[] getGenericParameterTypes() {
        /*
            r5 = this;
            java.lang.reflect.Method r0 = r5.baseMethod
            java.lang.reflect.Type[] r0 = r0.getGenericParameterTypes()
            int r1 = r0.length
            r2 = 1
            int r1 = r1 - r2
            org.aspectj.lang.reflect.AjType[] r1 = new org.aspectj.lang.reflect.AjType[r1]
        L_0x000b:
            int r3 = r0.length
            if (r2 >= r3) goto L_0x002a
            r3 = r0[r2]
            boolean r3 = r3 instanceof java.lang.Class
            if (r3 == 0) goto L_0x0021
            int r3 = r2 + -1
            r4 = r0[r2]
            java.lang.Class r4 = (java.lang.Class) r4
            org.aspectj.lang.reflect.AjType r4 = org.aspectj.lang.reflect.AjTypeSystem.getAjType(r4)
            r1[r3] = r4
            goto L_0x0027
        L_0x0021:
            int r3 = r2 + -1
            r4 = r0[r2]
            r1[r3] = r4
        L_0x0027:
            int r2 = r2 + 1
            goto L_0x000b
        L_0x002a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.internal.lang.reflect.InterTypeConstructorDeclarationImpl.getGenericParameterTypes():java.lang.reflect.Type[]");
    }

    public AjType<?>[] getExceptionTypes() {
        Class[] exceptionTypes = this.baseMethod.getExceptionTypes();
        AjType<?>[] ajTypeArr = new AjType[exceptionTypes.length];
        for (int i = 0; i < exceptionTypes.length; i++) {
            ajTypeArr[i] = AjTypeSystem.getAjType(exceptionTypes[i]);
        }
        return ajTypeArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(getModifiers()));
        stringBuffer.append(" ");
        stringBuffer.append(this.targetTypeName);
        stringBuffer.append(".new");
        stringBuffer.append("(");
        AjType[] parameterTypes = getParameterTypes();
        for (int i = 0; i < parameterTypes.length - 1; i++) {
            stringBuffer.append(parameterTypes[i].toString());
            stringBuffer.append(", ");
        }
        if (parameterTypes.length > 0) {
            stringBuffer.append(parameterTypes[parameterTypes.length - 1].toString());
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
