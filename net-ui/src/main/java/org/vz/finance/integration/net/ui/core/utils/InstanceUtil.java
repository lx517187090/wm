package org.vz.finance.integration.net.ui.core.utils;

import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public final class InstanceUtil {

    public static final <T> T to(Object orig, Class<T> clazz) {
        T bean = null;
        try {
            bean = (T) clazz.newInstance();
            PropertyUtils.copyProperties(bean, orig);
        } catch (Exception exception) {
        }

        return bean;
    }


    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);

                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, new Object[]{value});
                }
            }
        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }
    }


    public static Map<String, Object> transBean2Map(Object obj) {
        Map<String, Object> map = newHashMap(new Object[0]);
        if (obj == null) {
            return map;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (!key.equals("class")) {

                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj, new Object[0]);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }


    public static <T> T getDiff(T oldBean, T newBean) {
        if (oldBean == null && newBean != null)
            return newBean;
        if (newBean == null) {
            return null;
        }
        Class<?> cls1 = oldBean.getClass();

        try {
            T object = (T) cls1.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(cls1);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (!key.equals("class")) {

                    Method getter = property.getReadMethod();

                    Method setter = property.getWriteMethod();
                    Object oldValue = getter.invoke(oldBean, new Object[0]);
                    Object newValue = getter.invoke(newBean, new Object[0]);
                    if (newValue != null) {
                        if (oldValue == null) {
                            setter.invoke(object, new Object[]{newValue});
                        } else if (oldValue != null && !newValue.equals(oldValue)) {
                            setter.invoke(object, new Object[]{newValue});
                        }
                    }
                }
            }
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final Class<?> getClass(String clazz) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            if (loader != null) {
                return Class.forName(clazz, true, loader);
            }
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static final <E> List<E> getInstanceList(Class<E> cls, List<?> list) {
        List<E> resultList = newArrayList();
        E object = null;
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext(); ) {
            Map<?, ?> map = (Map) iterator.next();
            object = (E) newInstance(cls, new Object[]{map});
            resultList.add(object);
        }
        return resultList;
    }

    public static final <E> List<E> getInstanceList(Class<E> cls, ResultSet rs) {
        List<E> resultList = newArrayList();
        try {
            E object = (E) cls.newInstance();
            Field[] fields = cls.getDeclaredFields();
            while (rs.next()) {
                object = (E) cls.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    String fieldName = fields[i].getName();
                    PropertyUtils.setProperty(object, fieldName, rs.getObject(fieldName));
                }
                resultList.add(object);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public static final <E> E newInstance(Class<E> cls, Map<String, ?> map) {
        E object = null;
        try {
            object = (E) cls.newInstance();
            BeanUtils.populate(object, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public static final Object newInstance(String clazz) {
        try {
            return getClass(clazz).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final <K> K newInstance(Class<K> cls, Object... args) {
        try {
            Class[] argsClass = null;
            if (args != null) {
                argsClass = new Class[args.length];
                for (int i = 0, j = args.length; i < j; i++) {
                    argsClass[i] = args[i].getClass();
                }
            }
            Constructor<K> cons = cls.getConstructor(argsClass);
            return (K) cons.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final Object newInstance(String className, Object... args) {
        try {
            Class<?> newoneClass = Class.forName(className);
            return newInstance(newoneClass, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static Object jsonObjectToBeanByClass(Object object) {
        JSONObject jsonObj = (JSONObject) object;
        String _class = jsonObj.getString("_class");
        if (null == _class || "".equals(_class)) {
            throw new RuntimeException("_class ������������");
        }
        return jsonObj.toJavaObject(getClass(_class));
    }


    public static final <E> ArrayList<E> newArrayList() {
        return new ArrayList();
    }


    public static final <E> ArrayList<E> newArrayList(E... e) {
        ArrayList<E> list = new ArrayList<E>();
        Collections.addAll(list, e);
        return list;
    }

    public static <k, v> Map<k, v> newMap(Object... args) {
        return newHashMap(args);
    }


    public static final <k, v> HashMap<k, v> newHashMap(Object... args) {
        HashMap<k, v> map = new HashMap<k, v>();
        return map;
    }


    public static final <E> HashSet<E> newHashSet() {
        return new HashSet();
    }


    public static final <k, v> Hashtable<k, v> newHashtable() {
        return new Hashtable();
    }


    public static final <k, v> LinkedHashMap<k, v> newLinkedHashMap() {
        return new LinkedHashMap();
    }


    public static final <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet();
    }


    public static final <E> LinkedList<E> newLinkedList() {
        return new LinkedList();
    }


    public static final <k, v> TreeMap<k, v> newTreeMap() {
        return new TreeMap();
    }


    public static final <E> TreeSet<E> newTreeSet() {
        return new TreeSet();
    }


    public static final <E> Vector<E> newVector() {
        return new Vector();
    }


    public static final <k, v> WeakHashMap<k, v> newWeakHashMap() {
        return new WeakHashMap();
    }


    public static final <k, v> Map<k, v> newHashMap(k key, v value) {
        Map<k, v> map = newHashMap(new Object[0]);
        map.put(key, value);
        return map;
    }


    public static final <k, v> ConcurrentHashMap<k, v> newConcurrentHashMap() {
        return new ConcurrentHashMap();
    }


    public static RuntimeException unchecked(Throwable e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException(e);
    }


    public static RuntimeException unchecked(Throwable e, String message, Object... args) {
        return new RuntimeException(String.format(message, args), e);
    }
}
