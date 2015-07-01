package com.sunshine.learn.base.override;

/**
 * 2015年7月1日10:04:15
 */
public class SubClass extends SupClass {

	// 这里虽然没有 @Override 注解，但是还是重写（override）了父类的sayHello方法。
	public void sayHello(String message){
		System.out.println("SubClass: " + message);
	}
	
	/**
	 * 子类中添加了新的sayHello函数与原有的sayHello函数构成重载（overload）
	 */
	public void sayHello(String message, double a){
		System.out.println("SubClass: " + message + " . " + a);
	}
}
