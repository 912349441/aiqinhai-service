package com.tor.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;

public class MyBeanUtils {
	private static Logger log = Logger.getLogger(MyBeanUtils.class);

	/**
	 * 获得同时有get和set的field和value。
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map describe(Object bean) {
		Map des = new HashMap();
		PropertyDescriptor desor[] = PropertyUtils.getPropertyDescriptors(bean);
		String name = null;
		for (int i = 0; i < desor.length; i++) {
			if (desor[i].getReadMethod() != null
					&& desor[i].getWriteMethod() != null) {
				name = desor[i].getName();
				try {
					des.put(name, PropertyUtils.getProperty(bean, name));
				} catch (Exception e) {
					throw new RuntimeException("属性不存在：" + name);
				}
			}
		}
		return des;
	}

	public static void setSimpleProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setSimpleProperty(bean, name, value);
		} catch (Exception e) {
			throw new RuntimeException("属性不存在：" + name);
		}
	}

	public static Object setSimpleProperty(Object bean, String name) {
		try {
			return PropertyUtils.getSimpleProperty(bean, name);
		} catch (Exception e) {
			throw new RuntimeException("属性不存在：" + name);
		}
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(Object object, String fieldName)
			throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.error("不可能抛出的异常{}"+ e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(Object object, String fieldName,
			Object value) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			log.error("不可能抛出的异常:{}"+ e.getMessage());
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	public static Field getDeclaredField(Object object, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(object,"对象不能为空");
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Field getDeclaredField(Class clazz, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(clazz,"类不能为空");
		Assert.hasText(fieldName,"属性名不能为空");
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName()
				+ '.' + fieldName);
	}
	
	/**
	 * 对象转Map<String, Object>,遵循JavaBean
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> BeanToMap(Object bean) {
		if(bean == null){
			return null;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		try { 
			Class<?> c = bean.getClass();
			Method methods[] = c.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				String name = method.getName();
				String key = "";
				if (name.startsWith("get")) {
					key = name.substring(3);
				}
				if (key.length() > 0 && Character.isUpperCase(key.charAt(0))
						&& method.getParameterTypes().length == 0) {

					if (key.length() == 1) {
						key = key.toLowerCase();
					} else if (!Character.isUpperCase(key.charAt(1))) {
						key = key.substring(0, 1).toLowerCase()
								+ key.substring(1);
					}
					if(!key.toLowerCase().equals("class")){
						Object value = method.invoke(bean);
						if(value != null){
							hashMap.put(key, value);
						}
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	
	public static <T> T MapToBean(Class<T> clazz, Map map) {
		T bean = null;
		try {
			bean = clazz.newInstance();
			Map<String,Field> clsMap=new HashMap<String,Field>();
			getAllField(clsMap,clazz);

			 Set<String> set=map.keySet();
			 for(String key:set){
				 if(key.equals("hibernateLazyInitializer"))
				 continue;
				 Field f=clsMap.get(key);
				 Object o=map.get(key);
				 if(f==null||null==o)
					 continue;
				 Class<?> destClass=f.getType();
				 if(isWrapClass(o.getClass())||o.getClass().equals(destClass)){
					 BeanUtils.setProperty(bean, key, o); 	 
				 }else{
					 if(o instanceof List){
						 ArrayList ar=(ArrayList)o;
						Type type= f.getGenericType();
						if(type!=null && type instanceof ParameterizedType){
							ParameterizedType pt=(ParameterizedType) type;
							Class genericClazz = (Class)pt.getActualTypeArguments()[0];
							if(destClass.toString().equals("interface java.util.Set")){
								 Set s=new HashSet();
								 for(Object m:ar){
									 Object c= MapToBean(genericClazz,(Map)m);
									 s.add(c);
								 }
								 BeanUtils.setProperty(bean, key, s);
							 }else{
								 ArrayList a=new ArrayList();
								 for(Object m:ar){
									 Object c= MapToBean(genericClazz,(Map)m);
									 a.add(c);
								 }
								 BeanUtils.setProperty(bean, key, a);
							 }
						}else{
							 ArrayList a=new ArrayList();
							 for(Object m:ar){
								 Map s=(Map) m;
								 Set<String> hs=s.keySet();
								  for(String k:hs){
									  BeanUtils.setProperty(s, k, s.get(k));	
								  }
								 a.add(s);
							 }
							 BeanUtils.setProperty(bean, key, a);	
						}
						 
					 }else{
						 	 Object c= MapToBean(destClass,(Map)o);
							 BeanUtils.setProperty(bean, key, c);
						 }
					 }
			 }
			
		} catch (IllegalAccessException e) {
			log.error("Fill bean " + bean + " failed. Cause: " + e);
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			log.error("Fill bean " + bean + " failed. Cause: " + e);
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	  public static boolean isWrapClass(Class clz) {
	        try {
	           return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	
	  public static Map<String,Field> getAllField(Map<String,Field> clzMap,Class<?> clazz) {
	        Class<?> superClass = clazz.getSuperclass();
	        if (superClass != Object.class) {
	             getAllField(clzMap,superClass);
	        }

	        Field[] dFields = clazz.getDeclaredFields();
	        if (null != dFields && dFields.length > 0) {
	            for(Field f:dFields){
	            	clzMap.put(f.getName(), f);	
	            	}
	        }
	        return clzMap;
	    }

}
