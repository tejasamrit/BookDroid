package org.aspectj.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Aspects14 {
    private static final String ASPECTOF = "aspectOf";
    private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final String HASASPECT = "hasAspect";
    private static final Class[] PEROBJECT_CLASS_ARRAY;
    private static final Class[] PERTYPEWITHIN_CLASS_ARRAY;
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$java$lang$Object;

    static {
        Class[] clsArr = new Class[1];
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        clsArr[0] = cls;
        PEROBJECT_CLASS_ARRAY = clsArr;
        Class[] clsArr2 = new Class[1];
        Class cls2 = class$java$lang$Class;
        if (cls2 == null) {
            cls2 = class$("java.lang.Class");
            class$java$lang$Class = cls2;
        }
        clsArr2[0] = cls2;
        PERTYPEWITHIN_CLASS_ARRAY = clsArr2;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static Object aspectOf(Class cls) throws NoAspectBoundException {
        try {
            return getSingletonOrThreadAspectOf(cls).invoke((Object) null, EMPTY_OBJECT_ARRAY);
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    public static Object aspectOf(Class cls, Object obj) throws NoAspectBoundException {
        try {
            return getPerObjectAspectOf(cls).invoke((Object) null, new Object[]{obj});
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    public static Object aspectOf(Class cls, Class cls2) throws NoAspectBoundException {
        try {
            return getPerTypeWithinAspectOf(cls).invoke((Object) null, new Object[]{cls2});
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    public static boolean hasAspect(Class cls) throws NoAspectBoundException {
        try {
            return ((Boolean) getSingletonOrThreadHasAspect(cls).invoke((Object) null, EMPTY_OBJECT_ARRAY)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasAspect(Class cls, Object obj) throws NoAspectBoundException {
        try {
            return ((Boolean) getPerObjectHasAspect(cls).invoke((Object) null, new Object[]{obj})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasAspect(Class cls, Class cls2) throws NoAspectBoundException {
        try {
            return ((Boolean) getPerTypeWithinHasAspect(cls).invoke((Object) null, new Object[]{cls2})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private static Method getSingletonOrThreadAspectOf(Class cls) throws NoSuchMethodException {
        return checkAspectOf(cls.getDeclaredMethod(ASPECTOF, EMPTY_CLASS_ARRAY), cls);
    }

    private static Method getPerObjectAspectOf(Class cls) throws NoSuchMethodException {
        return checkAspectOf(cls.getDeclaredMethod(ASPECTOF, PEROBJECT_CLASS_ARRAY), cls);
    }

    private static Method getPerTypeWithinAspectOf(Class cls) throws NoSuchMethodException {
        return checkAspectOf(cls.getDeclaredMethod(ASPECTOF, PERTYPEWITHIN_CLASS_ARRAY), cls);
    }

    private static Method checkAspectOf(Method method, Class cls) throws NoSuchMethodException {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cls.getName());
        stringBuffer.append(".aspectOf(..) is not accessible public static");
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    private static Method getSingletonOrThreadHasAspect(Class cls) throws NoSuchMethodException {
        return checkHasAspect(cls.getDeclaredMethod(HASASPECT, EMPTY_CLASS_ARRAY), cls);
    }

    private static Method getPerObjectHasAspect(Class cls) throws NoSuchMethodException {
        return checkHasAspect(cls.getDeclaredMethod(HASASPECT, PEROBJECT_CLASS_ARRAY), cls);
    }

    private static Method getPerTypeWithinHasAspect(Class cls) throws NoSuchMethodException {
        return checkHasAspect(cls.getDeclaredMethod(HASASPECT, PERTYPEWITHIN_CLASS_ARRAY), cls);
    }

    private static Method checkHasAspect(Method method, Class cls) throws NoSuchMethodException {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cls.getName());
        stringBuffer.append(".hasAspect(..) is not accessible public static");
        throw new NoSuchMethodException(stringBuffer.toString());
    }
}
