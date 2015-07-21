package com.sunshine.learn.designpattern.principle.LKP;

/**
 * 2015年7月21日16:42:49
 * 电灯开关
 */
public class Switch {

	public void turnOn(){
		Light light = new Light();
		System.out.println("打开开关");
		light.turnOn();
	}

	// 可以看到上面的 turnOn方法和这个方法都依赖于一个Light类，然而对于
	// Switch类却对此浑然不知，因为他们都是在两个方法中的局部变量，这就出现了
	// Switch类依赖于Light类，却不知道自己的行为(turnOn, turnOff)与其他的类发生了依赖
	public void turnOff(){
		Light light = new Light();
		System.out.println("关上开关");
		light.turnOff();
	}
}
