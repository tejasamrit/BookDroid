package com.google.firebase.database.core.utilities.encoding;

import android.util.Log;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.core.utilities.Utilities;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomClassMapper {
    private static final String LOG_TAG = "ClassMapper";
    private static final ConcurrentMap<Class<?>, BeanMapper<?>> mappers = new ConcurrentHashMap();

    public static Object convertToPlainJavaTypes(Object obj) {
        return serialize(obj);
    }

    public static Map<String, Object> convertToPlainJavaTypes(Map<String, Object> map) {
        Object serialize = serialize(map);
        Utilities.hardAssert(serialize instanceof Map);
        return (Map) serialize;
    }

    public static <T> T convertToCustomClass(Object obj, Class<T> cls) {
        return deserializeToClass(obj, cls);
    }

    public static <T> T convertToCustomClass(Object obj, GenericTypeIndicator<T> genericTypeIndicator) {
        Type genericSuperclass = genericTypeIndicator.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType.getRawType().equals(GenericTypeIndicator.class)) {
                return deserializeToType(obj, parameterizedType.getActualTypeArguments()[0]);
            }
            throw new DatabaseException("Not a direct subclass of GenericTypeIndicator: " + genericSuperclass);
        }
        throw new DatabaseException("Not a direct subclass of GenericTypeIndicator: " + genericSuperclass);
    }

    /* access modifiers changed from: private */
    public static <T> Object serialize(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Number) {
            if ((t instanceof Float) || (t instanceof Double)) {
                Number number = (Number) t;
                double doubleValue = number.doubleValue();
                if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d || Math.floor(doubleValue) != doubleValue) {
                    return Double.valueOf(doubleValue);
                }
                return Long.valueOf(number.longValue());
            } else if ((t instanceof Long) || (t instanceof Integer)) {
                return t;
            } else {
                throw new DatabaseException(String.format("Numbers of type %s are not supported, please use an int, long, float or double", new Object[]{t.getClass().getSimpleName()}));
            }
        } else if ((t instanceof String) || (t instanceof Boolean)) {
            return t;
        } else {
            if (t instanceof Character) {
                throw new DatabaseException("Characters are not supported, please use Strings");
            } else if (t instanceof Map) {
                HashMap hashMap = new HashMap();
                for (Map.Entry entry : ((Map) t).entrySet()) {
                    Object key = entry.getKey();
                    if (key instanceof String) {
                        hashMap.put((String) key, serialize(entry.getValue()));
                    } else {
                        throw new DatabaseException("Maps with non-string keys are not supported");
                    }
                }
                return hashMap;
            } else if (t instanceof Collection) {
                if (t instanceof List) {
                    List<Object> list = (List) t;
                    ArrayList arrayList = new ArrayList(list.size());
                    for (Object serialize : list) {
                        arrayList.add(serialize(serialize));
                    }
                    return arrayList;
                }
                throw new DatabaseException("Serializing Collections is not supported, please use Lists instead");
            } else if (t.getClass().isArray()) {
                throw new DatabaseException("Serializing Arrays is not supported, please use Lists instead");
            } else if (t instanceof Enum) {
                return ((Enum) t).name();
            } else {
                return loadOrCreateBeanMapperForClass(t.getClass()).serialize(t);
            }
        }
    }

    /* access modifiers changed from: private */
    public static <T> T deserializeToType(Object obj, Type type) {
        if (obj == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return deserializeToParameterizedType(obj, (ParameterizedType) type);
        }
        if (type instanceof Class) {
            return deserializeToClass(obj, (Class) type);
        }
        boolean z = true;
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            if (wildcardType.getLowerBounds().length <= 0) {
                Type[] upperBounds = wildcardType.getUpperBounds();
                if (upperBounds.length <= 0) {
                    z = false;
                }
                Utilities.hardAssert(z, "Wildcard type " + type + " is not upper bounded.");
                return deserializeToType(obj, upperBounds[0]);
            }
            throw new DatabaseException("Generic lower-bounded wildcard types are not supported");
        } else if (type instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable) type).getBounds();
            if (bounds.length <= 0) {
                z = false;
            }
            Utilities.hardAssert(z, "Wildcard type " + type + " is not upper bounded.");
            return deserializeToType(obj, bounds[0]);
        } else if (type instanceof GenericArrayType) {
            throw new DatabaseException("Generic Arrays are not supported, please use Lists instead");
        } else {
            throw new IllegalStateException("Unknown type encountered: " + type);
        }
    }

    private static <T> T deserializeToClass(Object obj, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls)) {
            return deserializeToPrimitive(obj, cls);
        }
        if (String.class.isAssignableFrom(cls)) {
            return convertString(obj);
        }
        if (cls.isArray()) {
            throw new DatabaseException("Converting to Arrays is not supported, please use Listsinstead");
        } else if (cls.getTypeParameters().length > 0) {
            throw new DatabaseException("Class " + cls.getName() + " has generic type parameters, please use GenericTypeIndicator instead");
        } else if (cls.equals(Object.class)) {
            return obj;
        } else {
            if (cls.isEnum()) {
                return deserializeToEnum(obj, cls);
            }
            return convertBean(obj, cls);
        }
    }

    private static <T> T deserializeToParameterizedType(Object obj, ParameterizedType parameterizedType) {
        Class cls = (Class) parameterizedType.getRawType();
        if (List.class.isAssignableFrom(cls)) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                T arrayList = new ArrayList(list.size());
                for (Object deserializeToType : list) {
                    arrayList.add(deserializeToType(deserializeToType, type));
                }
                return arrayList;
            }
            throw new DatabaseException("Expected a List while deserializing, but got a " + obj.getClass());
        } else if (Map.class.isAssignableFrom(cls)) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (type2.equals(String.class)) {
                Map<String, Object> expectMap = expectMap(obj);
                T hashMap = new HashMap();
                for (Map.Entry next : expectMap.entrySet()) {
                    hashMap.put((String) next.getKey(), deserializeToType(next.getValue(), type3));
                }
                return hashMap;
            }
            throw new DatabaseException("Only Maps with string keys are supported, but found Map with key type " + type2);
        } else if (!Collection.class.isAssignableFrom(cls)) {
            Map<String, Object> expectMap2 = expectMap(obj);
            BeanMapper loadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
            HashMap hashMap2 = new HashMap();
            TypeVariable[] typeParameters = loadOrCreateBeanMapperForClass.clazz.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length == typeParameters.length) {
                for (int i = 0; i < typeParameters.length; i++) {
                    hashMap2.put(typeParameters[i], actualTypeArguments[i]);
                }
                return loadOrCreateBeanMapperForClass.deserialize(expectMap2, hashMap2);
            }
            throw new IllegalStateException("Mismatched lengths for type variables and actual types");
        } else {
            throw new DatabaseException("Collections are not supported, please use Lists instead");
        }
    }

    private static <T> T deserializeToPrimitive(Object obj, Class<T> cls) {
        if (Integer.class.isAssignableFrom(cls) || Integer.TYPE.isAssignableFrom(cls)) {
            return convertInteger(obj);
        }
        if (Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls)) {
            return convertBoolean(obj);
        }
        if (Double.class.isAssignableFrom(cls) || Double.TYPE.isAssignableFrom(cls)) {
            return convertDouble(obj);
        }
        if (Long.class.isAssignableFrom(cls) || Long.TYPE.isAssignableFrom(cls)) {
            return convertLong(obj);
        }
        if (Float.class.isAssignableFrom(cls) || Float.TYPE.isAssignableFrom(cls)) {
            return Float.valueOf(convertDouble(obj).floatValue());
        }
        throw new DatabaseException(String.format("Deserializing values to %s is not supported", new Object[]{cls.getSimpleName()}));
    }

    private static <T> T deserializeToEnum(Object obj, Class<T> cls) {
        if (obj instanceof String) {
            String str = (String) obj;
            try {
                return Enum.valueOf(cls, str);
            } catch (IllegalArgumentException unused) {
                throw new DatabaseException("Could not find enum value of " + cls.getName() + " for value \"" + str + "\"");
            }
        } else {
            throw new DatabaseException("Expected a String while deserializing to enum " + cls + " but got a " + obj.getClass());
        }
    }

    private static <T> BeanMapper<T> loadOrCreateBeanMapperForClass(Class<T> cls) {
        ConcurrentMap<Class<?>, BeanMapper<?>> concurrentMap = mappers;
        BeanMapper<T> beanMapper = (BeanMapper) concurrentMap.get(cls);
        if (beanMapper != null) {
            return beanMapper;
        }
        BeanMapper<T> beanMapper2 = new BeanMapper<>(cls);
        concurrentMap.put(cls, beanMapper2);
        return beanMapper2;
    }

    private static Map<String, Object> expectMap(Object obj) {
        if (obj instanceof Map) {
            return (Map) obj;
        }
        throw new DatabaseException("Expected a Map while deserializing, but got a " + obj.getClass());
    }

    private static Integer convertInteger(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            Number number = (Number) obj;
            double doubleValue = number.doubleValue();
            if (doubleValue >= -2.147483648E9d && doubleValue <= 2.147483647E9d) {
                return Integer.valueOf(number.intValue());
            }
            throw new DatabaseException("Numeric value out of 32-bit integer range: " + doubleValue + ". Did you mean to use a long or double instead of an int?");
        }
        throw new DatabaseException("Failed to convert a value of type " + obj.getClass().getName() + " to int");
    }

    private static Long convertLong(Object obj) {
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).longValue());
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.doubleValue() >= -9.223372036854776E18d && d.doubleValue() <= 9.223372036854776E18d) {
                return Long.valueOf(d.longValue());
            }
            throw new DatabaseException("Numeric value out of 64-bit long range: " + d + ". Did you mean to use a double instead of a long?");
        }
        throw new DatabaseException("Failed to convert a value of type " + obj.getClass().getName() + " to long");
    }

    private static Double convertDouble(Object obj) {
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Long) {
            Long l = (Long) obj;
            Double valueOf = Double.valueOf(l.doubleValue());
            if (valueOf.longValue() == l.longValue()) {
                return valueOf;
            }
            throw new DatabaseException("Loss of precision while converting number to double: " + obj + ". Did you mean to use a 64-bit long instead?");
        } else if (obj instanceof Double) {
            return (Double) obj;
        } else {
            throw new DatabaseException("Failed to convert a value of type " + obj.getClass().getName() + " to double");
        }
    }

    private static Boolean convertBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw new DatabaseException("Failed to convert value of type " + obj.getClass().getName() + " to boolean");
    }

    private static String convertString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new DatabaseException("Failed to convert value of type " + obj.getClass().getName() + " to String");
    }

    private static <T> T convertBean(Object obj, Class<T> cls) {
        BeanMapper<T> loadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
        if (obj instanceof Map) {
            return loadOrCreateBeanMapperForClass.deserialize(expectMap(obj));
        }
        throw new DatabaseException("Can't convert object of type " + obj.getClass().getName() + " to type " + cls.getName());
    }

    private static class BeanMapper<T> {
        /* access modifiers changed from: private */
        public final Class<T> clazz;
        private final Constructor<T> constructor;
        private final Map<String, Field> fields = new HashMap();
        private final Map<String, Method> getters = new HashMap();
        private final Map<String, String> properties = new HashMap();
        private final Map<String, Method> setters = new HashMap();
        private final boolean throwOnUnknownProperties;
        private final boolean warnOnUnknownProperties;

        /* JADX WARNING: Removed duplicated region for block: B:24:0x00aa  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x0149  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public BeanMapper(java.lang.Class<T> r11) {
            /*
                r10 = this;
                r10.<init>()
                r10.clazz = r11
                java.lang.Class<com.google.firebase.database.ThrowOnExtraProperties> r0 = com.google.firebase.database.ThrowOnExtraProperties.class
                boolean r0 = r11.isAnnotationPresent(r0)
                r10.throwOnUnknownProperties = r0
                java.lang.Class<com.google.firebase.database.IgnoreExtraProperties> r0 = com.google.firebase.database.IgnoreExtraProperties.class
                boolean r0 = r11.isAnnotationPresent(r0)
                r1 = 1
                r0 = r0 ^ r1
                r10.warnOnUnknownProperties = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r10.properties = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r10.setters = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r10.getters = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r10.fields = r0
                r0 = 0
                java.lang.Class[] r2 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x003e }
                java.lang.reflect.Constructor r2 = r11.getDeclaredConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x003e }
                r2.setAccessible(r1)     // Catch:{ NoSuchMethodException -> 0x003e }
                goto L_0x003f
            L_0x003e:
                r2 = 0
            L_0x003f:
                r10.constructor = r2
                java.lang.reflect.Method[] r2 = r11.getMethods()
                int r3 = r2.length
                r4 = 0
            L_0x0047:
                if (r4 >= r3) goto L_0x0087
                r5 = r2[r4]
                boolean r6 = shouldIncludeGetter(r5)
                if (r6 == 0) goto L_0x0084
                java.lang.String r6 = propertyName((java.lang.reflect.Method) r5)
                r10.addProperty(r6)
                r5.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r7 = r10.getters
                boolean r7 = r7.containsKey(r6)
                if (r7 != 0) goto L_0x0069
                java.util.Map<java.lang.String, java.lang.reflect.Method> r7 = r10.getters
                r7.put(r6, r5)
                goto L_0x0084
            L_0x0069:
                com.google.firebase.database.DatabaseException r11 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Found conflicting getters for name: "
                r0.append(r1)
                java.lang.String r1 = r5.getName()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r11.<init>(r0)
                throw r11
            L_0x0084:
                int r4 = r4 + 1
                goto L_0x0047
            L_0x0087:
                java.lang.reflect.Field[] r2 = r11.getFields()
                int r3 = r2.length
                r4 = 0
            L_0x008d:
                if (r4 >= r3) goto L_0x00a1
                r5 = r2[r4]
                boolean r6 = shouldIncludeField(r5)
                if (r6 == 0) goto L_0x009e
                java.lang.String r5 = propertyName((java.lang.reflect.Field) r5)
                r10.addProperty(r5)
            L_0x009e:
                int r4 = r4 + 1
                goto L_0x008d
            L_0x00a1:
                r2 = r11
            L_0x00a2:
                java.lang.reflect.Method[] r3 = r2.getDeclaredMethods()
                int r4 = r3.length
                r5 = 0
            L_0x00a8:
                if (r5 >= r4) goto L_0x0141
                r6 = r3[r5]
                boolean r7 = shouldIncludeSetter(r6)
                if (r7 == 0) goto L_0x013d
                java.lang.String r7 = propertyName((java.lang.reflect.Method) r6)
                java.util.Map<java.lang.String, java.lang.String> r8 = r10.properties
                java.util.Locale r9 = java.util.Locale.US
                java.lang.String r9 = r7.toLowerCase(r9)
                java.lang.Object r8 = r8.get(r9)
                java.lang.String r8 = (java.lang.String) r8
                if (r8 == 0) goto L_0x013d
                boolean r8 = r8.equals(r7)
                if (r8 == 0) goto L_0x0122
                java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r10.setters
                java.lang.Object r8 = r8.get(r7)
                java.lang.reflect.Method r8 = (java.lang.reflect.Method) r8
                if (r8 != 0) goto L_0x00df
                r6.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r10.setters
                r8.put(r7, r6)
                goto L_0x013d
            L_0x00df:
                boolean r7 = isSetterOverride(r6, r8)
                if (r7 == 0) goto L_0x00e6
                goto L_0x013d
            L_0x00e6:
                com.google.firebase.database.DatabaseException r11 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Found a conflicting setters with name: "
                r0.append(r1)
                java.lang.String r1 = r6.getName()
                r0.append(r1)
                java.lang.String r1 = " (conflicts with "
                r0.append(r1)
                java.lang.String r1 = r8.getName()
                r0.append(r1)
                java.lang.String r1 = " defined on "
                r0.append(r1)
                java.lang.Class r1 = r8.getDeclaringClass()
                java.lang.String r1 = r1.getName()
                r0.append(r1)
                java.lang.String r1 = ")"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r11.<init>(r0)
                throw r11
            L_0x0122:
                com.google.firebase.database.DatabaseException r11 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Found setter with invalid case-sensitive name: "
                r0.append(r1)
                java.lang.String r1 = r6.getName()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r11.<init>(r0)
                throw r11
            L_0x013d:
                int r5 = r5 + 1
                goto L_0x00a8
            L_0x0141:
                java.lang.reflect.Field[] r3 = r2.getDeclaredFields()
                int r4 = r3.length
                r5 = 0
            L_0x0147:
                if (r5 >= r4) goto L_0x0170
                r6 = r3[r5]
                java.lang.String r7 = propertyName((java.lang.reflect.Field) r6)
                java.util.Map<java.lang.String, java.lang.String> r8 = r10.properties
                java.util.Locale r9 = java.util.Locale.US
                java.lang.String r9 = r7.toLowerCase(r9)
                boolean r8 = r8.containsKey(r9)
                if (r8 == 0) goto L_0x016d
                java.util.Map<java.lang.String, java.lang.reflect.Field> r8 = r10.fields
                boolean r8 = r8.containsKey(r7)
                if (r8 != 0) goto L_0x016d
                r6.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Field> r8 = r10.fields
                r8.put(r7, r6)
            L_0x016d:
                int r5 = r5 + 1
                goto L_0x0147
            L_0x0170:
                java.lang.Class r2 = r2.getSuperclass()
                if (r2 == 0) goto L_0x017e
                java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
                boolean r3 = r2.equals(r3)
                if (r3 == 0) goto L_0x00a2
            L_0x017e:
                java.util.Map<java.lang.String, java.lang.String> r0 = r10.properties
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x0187
                return
            L_0x0187:
                com.google.firebase.database.DatabaseException r0 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "No properties to serialize found on class "
                r1.append(r2)
                java.lang.String r11 = r11.getName()
                r1.append(r11)
                java.lang.String r11 = r1.toString()
                r0.<init>(r11)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.utilities.encoding.CustomClassMapper.BeanMapper.<init>(java.lang.Class):void");
        }

        private void addProperty(String str) {
            String put = this.properties.put(str.toLowerCase(Locale.US), str);
            if (put != null && !str.equals(put)) {
                throw new DatabaseException("Found two getters or fields with conflicting case sensitivity for property: " + str.toLowerCase(Locale.US));
            }
        }

        public T deserialize(Map<String, Object> map) {
            return deserialize(map, Collections.emptyMap());
        }

        public T deserialize(Map<String, Object> map, Map<TypeVariable<Class<T>>, Type> map2) {
            Constructor<T> constructor2 = this.constructor;
            if (constructor2 != null) {
                try {
                    T newInstance = constructor2.newInstance(new Object[0]);
                    for (Map.Entry next : map.entrySet()) {
                        String str = (String) next.getKey();
                        if (this.setters.containsKey(str)) {
                            Method method = this.setters.get(str);
                            Type[] genericParameterTypes = method.getGenericParameterTypes();
                            if (genericParameterTypes.length == 1) {
                                try {
                                    method.invoke(newInstance, new Object[]{CustomClassMapper.deserializeToType(next.getValue(), resolveType(genericParameterTypes[0], map2))});
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                } catch (InvocationTargetException e2) {
                                    throw new RuntimeException(e2);
                                }
                            } else {
                                throw new IllegalStateException("Setter does not have exactly one parameter");
                            }
                        } else if (this.fields.containsKey(str)) {
                            Field field = this.fields.get(str);
                            try {
                                field.set(newInstance, CustomClassMapper.deserializeToType(next.getValue(), resolveType(field.getGenericType(), map2)));
                            } catch (IllegalAccessException e3) {
                                throw new RuntimeException(e3);
                            }
                        } else {
                            String str2 = "No setter/field for " + str + " found on class " + this.clazz.getName();
                            if (this.properties.containsKey(str.toLowerCase(Locale.US))) {
                                str2 = str2 + " (fields/setters are case sensitive!)";
                            }
                            if (this.throwOnUnknownProperties) {
                                throw new DatabaseException(str2);
                            } else if (this.warnOnUnknownProperties) {
                                Log.w(CustomClassMapper.LOG_TAG, str2);
                            }
                        }
                    }
                    return newInstance;
                } catch (InstantiationException e4) {
                    throw new RuntimeException(e4);
                } catch (IllegalAccessException e5) {
                    throw new RuntimeException(e5);
                } catch (InvocationTargetException e6) {
                    throw new RuntimeException(e6);
                }
            } else {
                throw new DatabaseException("Class " + this.clazz.getName() + " does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped.");
            }
        }

        private Type resolveType(Type type, Map<TypeVariable<Class<T>>, Type> map) {
            if (!(type instanceof TypeVariable)) {
                return type;
            }
            Type type2 = map.get(type);
            if (type2 != null) {
                return type2;
            }
            throw new IllegalStateException("Could not resolve type " + type);
        }

        public Map<String, Object> serialize(T t) {
            Object obj;
            if (this.clazz.isAssignableFrom(t.getClass())) {
                HashMap hashMap = new HashMap();
                for (String next : this.properties.values()) {
                    if (this.getters.containsKey(next)) {
                        try {
                            obj = this.getters.get(next).invoke(t, new Object[0]);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e2) {
                            throw new RuntimeException(e2);
                        }
                    } else {
                        Field field = this.fields.get(next);
                        if (field != null) {
                            try {
                                obj = field.get(t);
                            } catch (IllegalAccessException e3) {
                                throw new RuntimeException(e3);
                            }
                        } else {
                            throw new IllegalStateException("Bean property without field or getter:" + next);
                        }
                    }
                    hashMap.put(next, CustomClassMapper.serialize(obj));
                }
                return hashMap;
            }
            throw new IllegalArgumentException("Can't serialize object of class " + t.getClass() + " with BeanMapper for class " + this.clazz);
        }

        private static boolean shouldIncludeGetter(Method method) {
            if ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getDeclaringClass().equals(Object.class) && Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && !method.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean shouldIncludeSetter(Method method) {
            if (method.getName().startsWith("set") && !method.getDeclaringClass().equals(Object.class) && !Modifier.isStatic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 1 && !method.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean shouldIncludeField(Field field) {
            if (!field.getDeclaringClass().equals(Object.class) && Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !field.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean isSetterOverride(Method method, Method method2) {
            Utilities.hardAssert(method.getDeclaringClass().isAssignableFrom(method2.getDeclaringClass()), "Expected override from a base class");
            Utilities.hardAssert(method.getReturnType().equals(Void.TYPE), "Expected void return type");
            Utilities.hardAssert(method2.getReturnType().equals(Void.TYPE), "Expected void return type");
            Class[] parameterTypes = method.getParameterTypes();
            Class[] parameterTypes2 = method2.getParameterTypes();
            Utilities.hardAssert(parameterTypes.length == 1, "Expected exactly one parameter");
            Utilities.hardAssert(parameterTypes2.length == 1, "Expected exactly one parameter");
            if (!method.getName().equals(method2.getName()) || !parameterTypes[0].equals(parameterTypes2[0])) {
                return false;
            }
            return true;
        }

        private static String propertyName(Field field) {
            String annotatedName = annotatedName(field);
            return annotatedName != null ? annotatedName : field.getName();
        }

        private static String propertyName(Method method) {
            String annotatedName = annotatedName(method);
            return annotatedName != null ? annotatedName : serializedName(method.getName());
        }

        private static String annotatedName(AccessibleObject accessibleObject) {
            if (accessibleObject.isAnnotationPresent(PropertyName.class)) {
                return ((PropertyName) accessibleObject.getAnnotation(PropertyName.class)).value();
            }
            return null;
        }

        private static String serializedName(String str) {
            String[] strArr = {"get", "set", "is"};
            int i = 0;
            String str2 = null;
            for (int i2 = 0; i2 < 3; i2++) {
                String str3 = strArr[i2];
                if (str.startsWith(str3)) {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                char[] charArray = str.substring(str2.length()).toCharArray();
                while (i < charArray.length && Character.isUpperCase(charArray[i])) {
                    charArray[i] = Character.toLowerCase(charArray[i]);
                    i++;
                }
                return new String(charArray);
            }
            throw new IllegalArgumentException("Unknown Bean prefix for method: " + str);
        }
    }
}
