package com.sunshine.learn.designpattern.principle.LKP;

/**
 * 2015年7月21日16:39:38
 * 电灯
 */
public class Light {

	public void turnOn(){
		if(lifeOver()){
			System.out.println("灯泡亮了");
		}
	}
	
	public void turnOff(){
		System.out.println("灯泡灭了");
	}
	
	// 对于这个方法，是判断灯泡内部的寿命状态，所以应该对外是不可见的，
	// 即外部不需要知道这个方法，所以为了减少外部的类使用这个类的学习成本，
	// 就应该将这个类设置为 private
	// 在这里将其声明为 public 则违反了迪米特法则
	public boolean lifeOver(){
		System.out.println("寿命还在");
		return true;
	}
}
