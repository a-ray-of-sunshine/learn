package com.sunshine.learn.designpattern.proxy;

public class FooObject implements FooInterface
{

	@Override
	public void foo(String message)
	{
		System.out.println(message);
	}

}
