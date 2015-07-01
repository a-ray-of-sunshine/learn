package com.sunshine.learn.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 2015年6月25日20:29:59
 *
 */
public class TypeTest {
	
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		// 这种写法直接报错
		// 0. Class<int> clazz = int.class;
		
		// 输出结果都一样
//		int
//		Method Size: 0
//		Field Size: 0
//		Constructor Size: 0
		// 1. Class<Integer> clazz = int.class;
		// 2. Class<Integer> clazz = Integer.TYPE;
		
		
		// 3. Class<Integer> clazz = Integer.class;
//		java.lang.Integer
//		Method Size: 39
//		Field Size: 11
//		Constructor Size: 2
		Class<Integer> clazz = (Class<Integer>) Class.forName("java.lang.Integer");
		System.out.println(clazz.getName()); // int
		
		// 方法，米有任何方法
		Method[] methods = clazz.getDeclaredMethods();
		System.out.println("Method Size: " + methods.length);
//		for(Method method: methods){
//			System.out.println(method.getName());
//		}
		
		// 字段 0
		Field[] fields = clazz.getDeclaredFields();
		System.out.println("Field Size: " + fields.length);
//		for(Field field :fields){
//			System.out.println(field.getName());
//		}
		
		// 默认构造方法 0
/*		try {
			Object oInt = clazz.newInstance();
			System.out.println(oInt);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}*/
		
		// 构造器 0
		Constructor<?>[] ctors = clazz.getConstructors();
		System.out.println("Constructor Size: " + ctors.length);
		
		System.out.println(int.class == Integer.TYPE);
		System.out.println(int.class == Integer.class);
		
		Class<Class>  cls = Class.class;
		try {
			Method method = cls.getDeclaredMethod("getPrimitiveClass", new Class[]{String.class});
			method.setAccessible(true);
			System.out.println(method.invoke(cls, "int"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		Class<TypeTest> cltype = TypeTest.class;
		Method[] mtds = cltype.getDeclaredMethods();
//		for(Method m : mtds){
//			System.out.println(m.toGenericString());
//		}
		try {
			Method imetod = cltype.getDeclaredMethod("intMethod", new Class[]{int.class});
			System.out.println(imetod);
			Object param = getParam(imetod, "1343");
			imetod.invoke(new TypeTest(), param);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}
	
	public Integer intMethod(int ione){
		System.out.println("intMethod :" + ione);
		return 0;
	}

	// 获得Method的参数类型
	public Class<?> PrimitiveClassToClass(Method method){
		return method.getParameterTypes()[0];
	}
	
	public static Object getParam(Method method, Object param) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Object obj = param;
		
		Class<?>[] clazzs = method.getParameterTypes();
		Class<?>  paramClass; 
		if(null != clazzs & clazzs.length > 0){
			paramClass = clazzs[0];
			// 判断参数是否是原生数据类
			if(null != (paramClass = getParamClass(paramClass))){
				Method valMethod = paramClass.getDeclaredMethod("valueOf", String.class);
				obj = valMethod.invoke(null, param.toString());
			}
		}
		
		System.out.println(obj);
		
		return obj;
	}
	
	public static Class<?> getParamClass(Class<?> paramClass){
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		
		map.put("int", Integer.class);
		map.put("boolean", Boolean.class);
		map.put("byte", Byte.class);
		map.put("char", Character.class);
		map.put("short", Short.class);
		map.put("float", Float.class);
		map.put("double", Double.class);
		map.put("long", Long.class);
		
		return map.get(paramClass.getName());
	}
	
}
