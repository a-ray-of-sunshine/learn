package com.sunshine.learn.base.override;

import org.junit.Test;

public class Stage {

	@Test
	public void testInheritance(){
		SubClass sub = new SubClass();
		
		sub.sayHello("hello");
		sub.sayHello("world", 45678);
		sub.sayHello("doubel message", 23.3);
	}
	
	@Test
	public void testOverride(){
		// 这里只是创建一个父类（SupClass）的引用而已，
		// 所以sub正在指向的是SubClass的类型区域
		SupClass sub = new SubClass();
		
		// 所以这里的的调用就是调用子类(SubClass)的override（重写）的版本
		sub.sayHello("testOverload");
		sub.sayHello("world", 1234);
	}
}
