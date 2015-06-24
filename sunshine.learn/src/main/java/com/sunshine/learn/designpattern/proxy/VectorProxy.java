package com.sunshine.learn.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

@SuppressWarnings(value = {"rawtypes", "unchecked"})
public class VectorProxy implements InvocationHandler
{
	private Object obj;
	
	public VectorProxy(Object obj)
	{
		this.obj = obj;
	}
	
	public static Object factory(Object obj){
		Class<?> classType = obj.getClass();
		
		return Proxy.newProxyInstance(classType.getClassLoader(), 
				classType.getInterfaces(), new VectorProxy(obj));
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		System.out.println("before calling: " + method.getName());
		if(null != args){
			for(Object arg : args){
				System.out.println(arg);
			}
		}
		return method.invoke(this.obj, args);
	}
	
	public static void main(String[] args)
	{
		List list = (List)VectorProxy.factory(new Vector(000));
		
		list.add(123);
		System.out.println(list.size());
		
	}
}
