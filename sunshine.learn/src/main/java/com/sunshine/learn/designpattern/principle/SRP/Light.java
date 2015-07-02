package com.sunshine.learn.designpattern.principle.SRP;

// 电灯类
public class Light {
	public void shine()
    {
		System.out.println("灯泡亮了");
    }

    public void black()
    {
    	System.out.println("灯泡黑了");
    }

    public void turnOn()
    {
    	System.out.println("打开电灯");
    }

    public void turnOff()
    {
    	System.out.println("关上电灯");
    }
}
