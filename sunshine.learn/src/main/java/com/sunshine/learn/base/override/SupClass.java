package com.sunshine.learn.base.override;

/**
 * 2015年7月1日09:58:40
 * 重载（overload） 重写(override,也即覆盖)的区别
 */
public class SupClass {

	public void sayHello(String message){
		System.out.println(message);
	}
	
	public void sayHello(String message, int num){
		System.out.println(num + " : " + message);
	}
}
