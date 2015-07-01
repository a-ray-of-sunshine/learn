package com.sunshine.learn.base.override;

/**
 * 2015年7月1日10:04:15
 */
public class SubClass extends SupClass {

	// 这里虽然没有 @Override 注解，但是还是重写（override）了父类的sayHello方法。
	// 注意：在java强制性的将下面的方法编译为父类的override函数，
	// 在 C++ 中使用 virtual关键字来声明override函数，
	// 而如果子类中有和父类完全相同的函数，且父类没有virtual关键字修饰，则就会构成函数的Hide(隐藏)
	// 目前在下面的这个函数，如果在C++中就会构成这种情况，而java中还是正常的重写（override）
	// 所以由于编译机制的原因，在Java中不会出现方法被Hide的情况。而且如果出现Hide情况会导致问题，应该避免出现（在c++中）
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
