package com.sunshine.learn.designpattern.proxy;

public class FooInterfaceProxy extends Proxy implements FooInterface
{
	private Class<?> classType;

	public FooInterfaceProxy(InvocationHandler h)
	{
		super(h);
		this.classType = h.getClassType();
	}

	@Override
	protected Proxy newProxyInstance(InvocationHandler h)
	{
		return new FooInterfaceProxy(h);
	}
	
	@Override
	public void foo(String message)
	{
		try
		{
			this.h.invoke(this, classType.getDeclaredMethod("foo", new Class<?>[]{String.class}), new Object[]{message});
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
	}

}
