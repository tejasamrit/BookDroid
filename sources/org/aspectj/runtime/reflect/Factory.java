package org.aspectj.runtime.reflect;

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.AdviceSignature;
import org.aspectj.lang.reflect.CatchClauseSignature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.InitializerSignature;
import org.aspectj.lang.reflect.LockSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.lang.reflect.UnlockSignature;
import org.aspectj.runtime.reflect.JoinPointImpl;

public final class Factory {
    private static Object[] NO_ARGS = new Object[0];
    static /* synthetic */ Class class$java$lang$ClassNotFoundException;
    static Hashtable prims;
    int count = 0;
    String filename;
    Class lexicalClass;
    ClassLoader lookupClassLoader;

    static {
        Hashtable hashtable = new Hashtable();
        prims = hashtable;
        hashtable.put("void", Void.TYPE);
        prims.put("boolean", Boolean.TYPE);
        prims.put("byte", Byte.TYPE);
        prims.put("char", Character.TYPE);
        prims.put("short", Short.TYPE);
        prims.put("int", Integer.TYPE);
        prims.put("long", Long.TYPE);
        prims.put("float", Float.TYPE);
        prims.put("double", Double.TYPE);
    }

    static Class makeClass(String str, ClassLoader classLoader) {
        if (str.equals("*")) {
            return null;
        }
        Class cls = (Class) prims.get(str);
        if (cls != null) {
            return cls;
        }
        if (classLoader != null) {
            return Class.forName(str, false, classLoader);
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            Class cls2 = class$java$lang$ClassNotFoundException;
            if (cls2 != null) {
                return cls2;
            }
            Class class$ = class$("java.lang.ClassNotFoundException");
            class$java$lang$ClassNotFoundException = class$;
            return class$;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Factory(String str, Class cls) {
        this.filename = str;
        this.lexicalClass = cls;
        this.lookupClassLoader = cls.getClassLoader();
    }

    public JoinPoint.StaticPart makeSJP(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        MethodSignature makeMethodSig = makeMethodSig(str2, str3, str4, str5, str6, str7, str8);
        int i2 = this.count;
        this.count = i2 + 1;
        String str9 = str;
        return new JoinPointImpl.StaticPartImpl(i2, str, makeMethodSig, makeSourceLoc(i, -1));
    }

    public JoinPoint.StaticPart makeSJP(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        MethodSignature makeMethodSig = makeMethodSig(str2, str3, str4, str5, str6, "", str7);
        int i2 = this.count;
        this.count = i2 + 1;
        String str8 = str;
        return new JoinPointImpl.StaticPartImpl(i2, str, makeMethodSig, makeSourceLoc(i, -1));
    }

    public JoinPoint.StaticPart makeSJP(String str, Signature signature, SourceLocation sourceLocation) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, str, signature, sourceLocation);
    }

    public JoinPoint.StaticPart makeSJP(String str, Signature signature, int i, int i2) {
        int i3 = this.count;
        this.count = i3 + 1;
        return new JoinPointImpl.StaticPartImpl(i3, str, signature, makeSourceLoc(i, i2));
    }

    public JoinPoint.StaticPart makeSJP(String str, Signature signature, int i) {
        int i2 = this.count;
        this.count = i2 + 1;
        return new JoinPointImpl.StaticPartImpl(i2, str, signature, makeSourceLoc(i, -1));
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String str, Signature signature, SourceLocation sourceLocation) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, str, signature, sourceLocation);
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String str, Signature signature, int i, int i2) {
        int i3 = this.count;
        this.count = i3 + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i3, str, signature, makeSourceLoc(i, i2));
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String str, Signature signature, int i) {
        int i2 = this.count;
        this.count = i2 + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i2, str, signature, makeSourceLoc(i, -1));
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [org.aspectj.lang.Signature] */
    /* JADX WARNING: type inference failed for: r0v10, types: [org.aspectj.runtime.reflect.ConstructorSignatureImpl] */
    /* JADX WARNING: type inference failed for: r0v11, types: [org.aspectj.runtime.reflect.MethodSignatureImpl] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.aspectj.lang.JoinPoint.StaticPart makeEncSJP(java.lang.reflect.Member r9) {
        /*
            boolean r0 = r9 instanceof java.lang.reflect.Method
            if (r0 == 0) goto L_0x002e
            java.lang.reflect.Method r9 = (java.lang.reflect.Method) r9
            org.aspectj.runtime.reflect.MethodSignatureImpl r8 = new org.aspectj.runtime.reflect.MethodSignatureImpl
            int r1 = r9.getModifiers()
            java.lang.String r2 = r9.getName()
            java.lang.Class r3 = r9.getDeclaringClass()
            java.lang.Class[] r4 = r9.getParameterTypes()
            java.lang.Class[] r0 = r9.getParameterTypes()
            int r0 = r0.length
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.Class[] r6 = r9.getExceptionTypes()
            java.lang.Class r7 = r9.getReturnType()
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            java.lang.String r9 = "method-execution"
            goto L_0x0053
        L_0x002e:
            boolean r0 = r9 instanceof java.lang.reflect.Constructor
            if (r0 == 0) goto L_0x005b
            java.lang.reflect.Constructor r9 = (java.lang.reflect.Constructor) r9
            org.aspectj.runtime.reflect.ConstructorSignatureImpl r8 = new org.aspectj.runtime.reflect.ConstructorSignatureImpl
            int r1 = r9.getModifiers()
            java.lang.Class r2 = r9.getDeclaringClass()
            java.lang.Class[] r3 = r9.getParameterTypes()
            java.lang.Class[] r0 = r9.getParameterTypes()
            int r0 = r0.length
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.Class[] r5 = r9.getExceptionTypes()
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            java.lang.String r9 = "constructor-execution"
        L_0x0053:
            org.aspectj.runtime.reflect.JoinPointImpl$EnclosingStaticPartImpl r0 = new org.aspectj.runtime.reflect.JoinPointImpl$EnclosingStaticPartImpl
            r1 = -1
            r2 = 0
            r0.<init>(r1, r9, r8, r2)
            return r0
        L_0x005b:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "member must be either a method or constructor"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.runtime.reflect.Factory.makeEncSJP(java.lang.reflect.Member):org.aspectj.lang.JoinPoint$StaticPart");
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2) {
        return new JoinPointImpl(staticPart, obj, obj2, NO_ARGS);
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object obj3) {
        return new JoinPointImpl(staticPart, obj, obj2, new Object[]{obj3});
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object obj3, Object obj4) {
        return new JoinPointImpl(staticPart, obj, obj2, new Object[]{obj3, obj4});
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object[] objArr) {
        return new JoinPointImpl(staticPart, obj, obj2, objArr);
    }

    public MethodSignature makeMethodSig(String str) {
        MethodSignatureImpl methodSignatureImpl = new MethodSignatureImpl(str);
        methodSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return methodSignatureImpl;
    }

    public MethodSignature makeMethodSig(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = str;
        int parseInt = Integer.parseInt(str, 16);
        String str9 = str3;
        Class makeClass = makeClass(str3, this.lookupClassLoader);
        String str10 = str4;
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i = 0; i < countTokens; i++) {
            clsArr[i] = makeClass(stringTokenizer.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i2 = 0; i2 < countTokens2; i2++) {
            strArr[i2] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i3 = 0; i3 < countTokens3; i3++) {
            clsArr2[i3] = makeClass(stringTokenizer3.nextToken(), this.lookupClassLoader);
        }
        return new MethodSignatureImpl(parseInt, str2, makeClass, clsArr, strArr, clsArr2, makeClass(str7, this.lookupClassLoader));
    }

    public MethodSignature makeMethodSig(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        MethodSignatureImpl methodSignatureImpl = new MethodSignatureImpl(i, str, cls, clsArr, strArr, clsArr2, cls2);
        methodSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return methodSignatureImpl;
    }

    public ConstructorSignature makeConstructorSig(String str) {
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(str);
        constructorSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return constructorSignatureImpl;
    }

    public ConstructorSignature makeConstructorSig(String str, String str2, String str3, String str4, String str5) {
        int parseInt = Integer.parseInt(str, 16);
        Class makeClass = makeClass(str2, this.lookupClassLoader);
        StringTokenizer stringTokenizer = new StringTokenizer(str3, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i = 0; i < countTokens; i++) {
            clsArr[i] = makeClass(stringTokenizer.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str4, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i2 = 0; i2 < countTokens2; i2++) {
            strArr[i2] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str5, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i3 = 0; i3 < countTokens3; i3++) {
            clsArr2[i3] = makeClass(stringTokenizer3.nextToken(), this.lookupClassLoader);
        }
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(parseInt, makeClass, clsArr, strArr, clsArr2);
        constructorSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return constructorSignatureImpl;
    }

    public ConstructorSignature makeConstructorSig(int i, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(i, cls, clsArr, strArr, clsArr2);
        constructorSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return constructorSignatureImpl;
    }

    public FieldSignature makeFieldSig(String str) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(str);
        fieldSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return fieldSignatureImpl;
    }

    public FieldSignature makeFieldSig(String str, String str2, String str3, String str4) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(Integer.parseInt(str, 16), str2, makeClass(str3, this.lookupClassLoader), makeClass(str4, this.lookupClassLoader));
        fieldSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return fieldSignatureImpl;
    }

    public FieldSignature makeFieldSig(int i, String str, Class cls, Class cls2) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(i, str, cls, cls2);
        fieldSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return fieldSignatureImpl;
    }

    public AdviceSignature makeAdviceSig(String str) {
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(str);
        adviceSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return adviceSignatureImpl;
    }

    public AdviceSignature makeAdviceSig(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = str;
        int parseInt = Integer.parseInt(str, 16);
        String str9 = str3;
        Class makeClass = makeClass(str3, this.lookupClassLoader);
        String str10 = str4;
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i = 0; i < countTokens; i++) {
            clsArr[i] = makeClass(stringTokenizer.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i2 = 0; i2 < countTokens2; i2++) {
            strArr[i2] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i3 = 0; i3 < countTokens3; i3++) {
            clsArr2[i3] = makeClass(stringTokenizer3.nextToken(), this.lookupClassLoader);
        }
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(parseInt, str2, makeClass, clsArr, strArr, clsArr2, makeClass(str7, this.lookupClassLoader));
        adviceSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return adviceSignatureImpl;
    }

    public AdviceSignature makeAdviceSig(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(i, str, cls, clsArr, strArr, clsArr2, cls2);
        adviceSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return adviceSignatureImpl;
    }

    public InitializerSignature makeInitializerSig(String str) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(str);
        initializerSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return initializerSignatureImpl;
    }

    public InitializerSignature makeInitializerSig(String str, String str2) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(Integer.parseInt(str, 16), makeClass(str2, this.lookupClassLoader));
        initializerSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return initializerSignatureImpl;
    }

    public InitializerSignature makeInitializerSig(int i, Class cls) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(i, cls);
        initializerSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return initializerSignatureImpl;
    }

    public CatchClauseSignature makeCatchClauseSig(String str) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(str);
        catchClauseSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return catchClauseSignatureImpl;
    }

    public CatchClauseSignature makeCatchClauseSig(String str, String str2, String str3) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(makeClass(str, this.lookupClassLoader), makeClass(new StringTokenizer(str2, ":").nextToken(), this.lookupClassLoader), new StringTokenizer(str3, ":").nextToken());
        catchClauseSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return catchClauseSignatureImpl;
    }

    public CatchClauseSignature makeCatchClauseSig(Class cls, Class cls2, String str) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(cls, cls2, str);
        catchClauseSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return catchClauseSignatureImpl;
    }

    public LockSignature makeLockSig(String str) {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(str);
        lockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return lockSignatureImpl;
    }

    public LockSignature makeLockSig() {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(makeClass("Ljava/lang/Object;", this.lookupClassLoader));
        lockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return lockSignatureImpl;
    }

    public LockSignature makeLockSig(Class cls) {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(cls);
        lockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return lockSignatureImpl;
    }

    public UnlockSignature makeUnlockSig(String str) {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(str);
        unlockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return unlockSignatureImpl;
    }

    public UnlockSignature makeUnlockSig() {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(makeClass("Ljava/lang/Object;", this.lookupClassLoader));
        unlockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return unlockSignatureImpl;
    }

    public UnlockSignature makeUnlockSig(Class cls) {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(cls);
        unlockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return unlockSignatureImpl;
    }

    public SourceLocation makeSourceLoc(int i, int i2) {
        return new SourceLocationImpl(this.lexicalClass, this.filename, i);
    }
}
