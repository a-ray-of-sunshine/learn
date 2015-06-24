package com.sunshine.learn.designpattern.proxy;

public class Test
{
	public static void main(String[] args)
	{
		// 1. 真实对象
		FooObject fooObject = new FooObject();
		
		// 2. 调用处理器
		InvocationHandler fooHandler = new FooHandler(fooObject);
		// 3. 代理对象
		Proxy proxy = new FooInterfaceProxy(fooHandler);

		fooObject.foo("不使用代理直接调用");
		System.out.println("---------------------");
		((FooInterfaceProxy)proxy).foo("使用代理调用");
	}
}
