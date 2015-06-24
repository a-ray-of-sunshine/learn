package com.sunshine.learn.designpattern.proxy;

import java.lang.reflect.Method;

public interface InvocationHandler
{
	/**
	 * 
	 * @param proxy  代理对象
	 * @param method 代理对象调用的方法
	 * @param args   上面方法的调用参数
	 * @return	                方法执行后的返回值
	 */
	Object invoke(Object proxy, Method method, Object[] args);
	
	Class<?> getClassType();
}
