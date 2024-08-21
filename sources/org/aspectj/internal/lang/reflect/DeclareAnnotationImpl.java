package org.aspectj.internal.lang.reflect;

import java.lang.annotation.Annotation;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclareAnnotation;
import org.aspectj.lang.reflect.SignaturePattern;
import org.aspectj.lang.reflect.TypePattern;

public class DeclareAnnotationImpl implements DeclareAnnotation {
    private String annText;
    private AjType<?> declaringType;
    private DeclareAnnotation.Kind kind;
    private SignaturePattern signaturePattern;
    private Annotation theAnnotation;
    private TypePattern typePattern;

    public DeclareAnnotationImpl(AjType<?> ajType, String str, String str2, Annotation annotation, String str3) {
        this.declaringType = ajType;
        if (str.equals("at_type")) {
            this.kind = DeclareAnnotation.Kind.Type;
        } else if (str.equals("at_field")) {
            this.kind = DeclareAnnotation.Kind.Field;
        } else if (str.equals("at_method")) {
            this.kind = DeclareAnnotation.Kind.Method;
        } else if (str.equals("at_constructor")) {
            this.kind = DeclareAnnotation.Kind.Constructor;
        } else {
            throw new IllegalStateException("Unknown declare annotation kind: " + str);
        }
        if (this.kind == DeclareAnnotation.Kind.Type) {
            this.typePattern = new TypePatternImpl(str2);
        } else {
            this.signaturePattern = new SignaturePatternImpl(str2);
        }
        this.theAnnotation = annotation;
        this.annText = str3;
    }

    public AjType<?> getDeclaringType() {
        return this.declaringType;
    }

    public DeclareAnnotation.Kind getKind() {
        return this.kind;
    }

    public SignaturePattern getSignaturePattern() {
        return this.signaturePattern;
    }

    public TypePattern getTypePattern() {
        return this.typePattern;
    }

    public Annotation getAnnotation() {
        return this.theAnnotation;
    }

    public String getAnnotationAsText() {
        return this.annText;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare @");
        int i = C12621.$SwitchMap$org$aspectj$lang$reflect$DeclareAnnotation$Kind[getKind().ordinal()];
        if (i == 1) {
            stringBuffer.append("type : ");
            stringBuffer.append(getTypePattern().asString());
        } else if (i == 2) {
            stringBuffer.append("method : ");
            stringBuffer.append(getSignaturePattern().asString());
        } else if (i == 3) {
            stringBuffer.append("field : ");
            stringBuffer.append(getSignaturePattern().asString());
        } else if (i == 4) {
            stringBuffer.append("constructor : ");
            stringBuffer.append(getSignaturePattern().asString());
        }
        stringBuffer.append(" : ");
        stringBuffer.append(getAnnotationAsText());
        return stringBuffer.toString();
    }

    /* renamed from: org.aspectj.internal.lang.reflect.DeclareAnnotationImpl$1 */
    static /* synthetic */ class C12621 {
        static final /* synthetic */ int[] $SwitchMap$org$aspectj$lang$reflect$DeclareAnnotation$Kind;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.aspectj.lang.reflect.DeclareAnnotation$Kind[] r0 = org.aspectj.lang.reflect.DeclareAnnotation.Kind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$aspectj$lang$reflect$DeclareAnnotation$Kind = r0
                org.aspectj.lang.reflect.DeclareAnnotation$Kind r1 = org.aspectj.lang.reflect.DeclareAnnotation.Kind.Type     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$aspectj$lang$reflect$DeclareAnnotation$Kind     // Catch:{ NoSuchFieldError -> 0x001d }
                org.aspectj.lang.reflect.DeclareAnnotation$Kind r1 = org.aspectj.lang.reflect.DeclareAnnotation.Kind.Method     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$aspectj$lang$reflect$DeclareAnnotation$Kind     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.aspectj.lang.reflect.DeclareAnnotation$Kind r1 = org.aspectj.lang.reflect.DeclareAnnotation.Kind.Field     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$aspectj$lang$reflect$DeclareAnnotation$Kind     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.aspectj.lang.reflect.DeclareAnnotation$Kind r1 = org.aspectj.lang.reflect.DeclareAnnotation.Kind.Constructor     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.aspectj.internal.lang.reflect.DeclareAnnotationImpl.C12621.<clinit>():void");
        }
    }
}
