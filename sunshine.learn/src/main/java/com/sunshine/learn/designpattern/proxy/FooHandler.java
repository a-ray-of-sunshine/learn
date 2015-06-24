package com.sunshine.learn.designpattern.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FooHandler implements InvocationHandler
{

	private Object realObj;
	
	public FooHandler(Object realObj)
	{
		this.realObj = realObj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
	{
		Object res = null;
		try
		{
			System.out.println("before calling: " + method.getName());
			if(null != args){
				System.out.print("参数:\t");
				for(Object obj : args){
					System.out.print(obj + " ");
				}
				System.out.println();
			}
			res = method.invoke(realObj, args);
			
			System.out.println("after calling: " + method.getName());
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public Class<?> getClassType()
	{
		return this.realObj.getClass();
	}

}
