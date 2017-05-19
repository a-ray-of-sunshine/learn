package com.sunshine.learn.base.annotation;

@SuppressWarnings("")
public @MyAnnotation(value = "test", test=9527)
 class  MyClass{
	
	public void test(){
		System.out.println("test");
	}
	
	public String str()  {
		return "body";
	}
}
