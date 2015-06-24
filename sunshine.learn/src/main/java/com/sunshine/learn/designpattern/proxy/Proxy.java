package com.sunshine.learn.designpattern.proxy;

public abstract class Proxy
{
	protected InvocationHandler h;
	
	protected Proxy(InvocationHandler h){
		this.h = h;
	}
	
	/**
	 * 
	 * @param h		和这个代理相关的 InvocationHandler
	 * @return		返回代理对象
	 */
	protected abstract Proxy newProxyInstance(InvocationHandler h);
}
