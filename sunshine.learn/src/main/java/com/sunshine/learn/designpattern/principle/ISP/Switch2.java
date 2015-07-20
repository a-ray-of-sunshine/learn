package com.sunshine.learn.designpattern.principle.ISP;

public class Switch2 implements IDevices2 {

	@Override
	public void turnOn() {
		System.out.println("打开开关");
	}

	@Override
	public void turnOff() {
		System.out.println("关闭开关");
	}

}
