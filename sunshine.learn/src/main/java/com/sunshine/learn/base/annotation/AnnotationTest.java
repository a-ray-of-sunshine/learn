package com.sunshine.learn.base.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {

	public static void main(String[] args) {
		
		Class<MyClass> clazz = MyClass.class;
		
		System.out.println(clazz);
		Class s = Class.class;
		
		Annotation[] annotations = clazz.getAnnotations();
		for(Annotation ann : annotations){
			MyAnnotation ma = (MyAnnotation) ann;
			System.out.println(ma.value());
			System.out.println(ma.test());
		}
		
		Class<MyAnnotation> annClazz = MyAnnotation.class;
	    Method[] methods = annClazz.getDeclaredMethods();
	    for(Method m : methods){
	    	System.out.println(m.getName());
	    }
	    
	    String[][] str = new String[3][5];
	    String[] stra = str[0];
	    System.out.println(str.length);
	    System.out.println(stra.length);
	    
		
	}
}