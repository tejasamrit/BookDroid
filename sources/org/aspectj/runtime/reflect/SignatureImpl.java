package org.aspectj.runtime.reflect;

import java.lang.ref.SoftReference;
import java.util.StringTokenizer;
import kotlin.text.Typography;
import org.aspectj.lang.Signature;

abstract class SignatureImpl implements Signature {
    static Class[] EMPTY_CLASS_ARRAY = new Class[0];
    static String[] EMPTY_STRING_ARRAY = new String[0];
    static final String INNER_SEP = ":";
    static final char SEP = '-';
    private static boolean useCache = true;
    Class declaringType;
    String declaringTypeName;
    ClassLoader lookupClassLoader = null;
    int modifiers = -1;
    String name;
    Cache stringCache;
    private String stringRep;

    private interface Cache {
        String get(int i);

        void set(int i, String str);
    }

    /* access modifiers changed from: protected */
    public abstract String createToString(StringMaker stringMaker);

    SignatureImpl(int i, String str, Class cls) {
        this.modifiers = i;
        this.name = str;
        this.declaringType = cls;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString(org.aspectj.runtime.reflect.StringMaker r3) {
        /*
            r2 = this;
            boolean r0 = useCache
            if (r0 == 0) goto L_0x001b
            org.aspectj.runtime.reflect.SignatureImpl$Cache r0 = r2.stringCache
            if (r0 != 0) goto L_0x0014
            org.aspectj.runtime.reflect.SignatureImpl$CacheImpl r0 = new org.aspectj.runtime.reflect.SignatureImpl$CacheImpl     // Catch:{ all -> 0x0010 }
            r0.<init>()     // Catch:{ all -> 0x0010 }
            r2.stringCache = r0     // Catch:{ all -> 0x0010 }
            goto L_0x001b
        L_0x0010:
            r0 = 0
            useCache = r0
            goto L_0x001b
        L_0x0014:
            int r1 = r3.cacheOffset
            java.lang.String r0 = r0.get(r1)
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = r2.createToString(r3)
        L_0x0022:
            boolean r1 = useCache
            if (r1 == 0) goto L_0x002d
            org.aspectj.runtime.reflect.SignatureImpl$Cache r1 = r2.stringCache
            int r3 = r3.cacheOffset
            r1.set(r3, r0)
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.runtime.reflect.SignatureImpl.toString(org.aspectj.runtime.reflect.StringMaker):java.lang.String");
    }

    public final String toString() {
        return toString(StringMaker.middleStringMaker);
    }

    public final String toShortString() {
        return toString(StringMaker.shortStringMaker);
    }

    public final String toLongString() {
        return toString(StringMaker.longStringMaker);
    }

    public int getModifiers() {
        if (this.modifiers == -1) {
            this.modifiers = extractInt(0);
        }
        return this.modifiers;
    }

    public String getName() {
        if (this.name == null) {
            this.name = extractString(1);
        }
        return this.name;
    }

    public Class getDeclaringType() {
        if (this.declaringType == null) {
            this.declaringType = extractType(2);
        }
        return this.declaringType;
    }

    public String getDeclaringTypeName() {
        if (this.declaringTypeName == null) {
            this.declaringTypeName = getDeclaringType().getName();
        }
        return this.declaringTypeName;
    }

    /* access modifiers changed from: package-private */
    public String fullTypeName(Class cls) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (!cls.isArray()) {
            return cls.getName().replace(Typography.dollar, '.');
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(fullTypeName(cls.getComponentType()));
        stringBuffer.append("[]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String stripPackageName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(lastIndexOf + 1);
    }

    /* access modifiers changed from: package-private */
    public String shortTypeName(Class cls) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (!cls.isArray()) {
            return stripPackageName(cls.getName()).replace(Typography.dollar, '.');
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(shortTypeName(cls.getComponentType()));
        stringBuffer.append("[]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public void addFullTypeNames(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i = 0; i < clsArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(fullTypeName(clsArr[i]));
        }
    }

    /* access modifiers changed from: package-private */
    public void addShortTypeNames(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i = 0; i < clsArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(shortTypeName(clsArr[i]));
        }
    }

    /* access modifiers changed from: package-private */
    public void addTypeArray(StringBuffer stringBuffer, Class[] clsArr) {
        addFullTypeNames(stringBuffer, clsArr);
    }

    public void setLookupClassLoader(ClassLoader classLoader) {
        this.lookupClassLoader = classLoader;
    }

    private ClassLoader getLookupClassLoader() {
        if (this.lookupClassLoader == null) {
            this.lookupClassLoader = getClass().getClassLoader();
        }
        return this.lookupClassLoader;
    }

    public SignatureImpl(String str) {
        this.stringRep = str;
    }

    /* access modifiers changed from: package-private */
    public String extractString(int i) {
        int indexOf = this.stringRep.indexOf(45);
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                break;
            }
            i2 = indexOf + 1;
            indexOf = this.stringRep.indexOf(45, i2);
            i = i3;
        }
        if (indexOf == -1) {
            indexOf = this.stringRep.length();
        }
        return this.stringRep.substring(i2, indexOf);
    }

    /* access modifiers changed from: package-private */
    public int extractInt(int i) {
        return Integer.parseInt(extractString(i), 16);
    }

    /* access modifiers changed from: package-private */
    public Class extractType(int i) {
        return Factory.makeClass(extractString(i), getLookupClassLoader());
    }

    /* access modifiers changed from: package-private */
    public String[] extractStrings(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(extractString(i), INNER_SEP);
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            strArr[i2] = stringTokenizer.nextToken();
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public Class[] extractTypes(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(extractString(i), INNER_SEP);
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            clsArr[i2] = Factory.makeClass(stringTokenizer.nextToken(), getLookupClassLoader());
        }
        return clsArr;
    }

    static void setUseCache(boolean z) {
        useCache = z;
    }

    static boolean getUseCache() {
        return useCache;
    }

    private static final class CacheImpl implements Cache {
        private SoftReference toStringCacheRef;

        public CacheImpl() {
            makeCache();
        }

        public String get(int i) {
            String[] array = array();
            if (array == null) {
                return null;
            }
            return array[i];
        }

        public void set(int i, String str) {
            String[] array = array();
            if (array == null) {
                array = makeCache();
            }
            array[i] = str;
        }

        private String[] array() {
            return (String[]) this.toStringCacheRef.get();
        }

        private String[] makeCache() {
            String[] strArr = new String[3];
            this.toStringCacheRef = new SoftReference(strArr);
            return strArr;
        }
    }
}
