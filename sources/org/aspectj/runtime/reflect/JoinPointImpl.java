package org.aspectj.runtime.reflect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;

class JoinPointImpl implements ProceedingJoinPoint {
    Object _this;
    private AroundClosure arc;
    Object[] args;
    JoinPoint.StaticPart staticPart;
    Object target;

    static class StaticPartImpl implements JoinPoint.StaticPart {

        /* renamed from: id */
        private int f261id;
        String kind;
        Signature signature;
        SourceLocation sourceLocation;

        public StaticPartImpl(int i, String str, Signature signature2, SourceLocation sourceLocation2) {
            this.kind = str;
            this.signature = signature2;
            this.sourceLocation = sourceLocation2;
            this.f261id = i;
        }

        public int getId() {
            return this.f261id;
        }

        public String getKind() {
            return this.kind;
        }

        public Signature getSignature() {
            return this.signature;
        }

        public SourceLocation getSourceLocation() {
            return this.sourceLocation;
        }

        /* access modifiers changed from: package-private */
        public String toString(StringMaker stringMaker) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(stringMaker.makeKindName(getKind()));
            stringBuffer.append("(");
            stringBuffer.append(((SignatureImpl) getSignature()).toString(stringMaker));
            stringBuffer.append(")");
            return stringBuffer.toString();
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
    }

    static class EnclosingStaticPartImpl extends StaticPartImpl implements JoinPoint.EnclosingStaticPart {
        public EnclosingStaticPartImpl(int i, String str, Signature signature, SourceLocation sourceLocation) {
            super(i, str, signature, sourceLocation);
        }
    }

    public JoinPointImpl(JoinPoint.StaticPart staticPart2, Object obj, Object obj2, Object[] objArr) {
        this.staticPart = staticPart2;
        this._this = obj;
        this.target = obj2;
        this.args = objArr;
    }

    public Object getThis() {
        return this._this;
    }

    public Object getTarget() {
        return this.target;
    }

    public Object[] getArgs() {
        if (this.args == null) {
            this.args = new Object[0];
        }
        Object[] objArr = this.args;
        Object[] objArr2 = new Object[objArr.length];
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        return objArr2;
    }

    public JoinPoint.StaticPart getStaticPart() {
        return this.staticPart;
    }

    public String getKind() {
        return this.staticPart.getKind();
    }

    public Signature getSignature() {
        return this.staticPart.getSignature();
    }

    public SourceLocation getSourceLocation() {
        return this.staticPart.getSourceLocation();
    }

    public final String toString() {
        return this.staticPart.toString();
    }

    public final String toShortString() {
        return this.staticPart.toShortString();
    }

    public final String toLongString() {
        return this.staticPart.toLongString();
    }

    public void set$AroundClosure(AroundClosure aroundClosure) {
        this.arc = aroundClosure;
    }

    public Object proceed() throws Throwable {
        AroundClosure aroundClosure = this.arc;
        if (aroundClosure == null) {
            return null;
        }
        return aroundClosure.run(aroundClosure.getState());
    }

    public Object proceed(Object[] objArr) throws Throwable {
        AroundClosure aroundClosure = this.arc;
        if (aroundClosure == null) {
            return null;
        }
        int flags = aroundClosure.getFlags();
        int i = 1048576 & flags;
        int i2 = 1;
        boolean z = (65536 & flags) != 0;
        int i3 = (flags & 4096) != 0 ? 1 : 0;
        int i4 = (flags & 256) != 0 ? 1 : 0;
        boolean z2 = (flags & 16) != 0;
        boolean z3 = (flags & 1) != 0;
        Object[] state = this.arc.getState();
        int i5 = i3 + 0 + ((!z2 || z) ? 0 : 1);
        if (i3 == 0 || i4 == 0) {
            i2 = 0;
        } else {
            state[0] = objArr[0];
        }
        if (z2 && z3) {
            if (z) {
                i2 = i4 + 1;
                state[0] = objArr[i4];
            } else {
                i2 = i3 + 1;
                state[i3] = objArr[i3];
            }
        }
        for (int i6 = i2; i6 < objArr.length; i6++) {
            state[(i6 - i2) + i5] = objArr[i6];
        }
        return this.arc.run(state);
    }
}
