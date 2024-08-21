package org.aspectj.runtime;

public class CFlow {
    private Object _aspect;

    public Object get(int i) {
        return null;
    }

    public CFlow() {
        this((Object) null);
    }

    public CFlow(Object obj) {
        this._aspect = obj;
    }

    public Object getAspect() {
        return this._aspect;
    }

    public void setAspect(Object obj) {
        this._aspect = obj;
    }
}
