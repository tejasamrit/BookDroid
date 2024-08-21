package org.aspectj.internal.lang.reflect;

import java.lang.reflect.Method;
import java.util.StringTokenizer;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.Pointcut;
import org.aspectj.lang.reflect.PointcutExpression;

public class PointcutImpl implements Pointcut {
    private final Method baseMethod;
    private final AjType declaringType;
    private final String name;
    private String[] parameterNames = new String[0];

    /* renamed from: pc */
    private final PointcutExpression f259pc;

    protected PointcutImpl(String str, String str2, Method method, AjType ajType, String str3) {
        this.name = str;
        this.f259pc = new PointcutExpressionImpl(str2);
        this.baseMethod = method;
        this.declaringType = ajType;
        this.parameterNames = splitOnComma(str3);
    }

    public PointcutExpression getPointcutExpression() {
        return this.f259pc;
    }

    public String getName() {
        return this.name;
    }

    public int getModifiers() {
        return this.baseMethod.getModifiers();
    }

    public AjType<?>[] getParameterTypes() {
        Class[] parameterTypes = this.baseMethod.getParameterTypes();
        int length = parameterTypes.length;
        AjType<?>[] ajTypeArr = new AjType[length];
        for (int i = 0; i < length; i++) {
            ajTypeArr[i] = AjTypeSystem.getAjType(parameterTypes[i]);
        }
        return ajTypeArr;
    }

    public AjType getDeclaringType() {
        return this.declaringType;
    }

    public String[] getParameterNames() {
        return this.parameterNames;
    }

    private String[] splitOnComma(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i = 0; i < countTokens; i++) {
            strArr[i] = stringTokenizer.nextToken().trim();
        }
        return strArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getName());
        stringBuffer.append("(");
        AjType[] parameterTypes = getParameterTypes();
        int i = 0;
        while (i < parameterTypes.length) {
            stringBuffer.append(parameterTypes[i].getName());
            String[] strArr = this.parameterNames;
            if (!(strArr == null || strArr[i] == null)) {
                stringBuffer.append(" ");
                stringBuffer.append(this.parameterNames[i]);
            }
            i++;
            if (i < parameterTypes.length) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(") : ");
        stringBuffer.append(getPointcutExpression().asString());
        return stringBuffer.toString();
    }
}
