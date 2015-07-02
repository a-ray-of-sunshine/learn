package com.sunshine.learn.designpattern.principle.SRP;

public class Light2 {
	
	private String name;
	
	protected Light2(String name) {
		super();
		this.name = name;
	}

	public void shine(){
		System.out.println(this.name + "灯泡亮了");
    }

    public void black(){
    	System.out.println(this.name + "灯泡黑了");
    }
}

class Switch{
	
	private Light2 light;
	private String name;
	
	protected Switch(String name) {
		super();
		this.name = name;
	}

	public void setLight(Light2 light) {
		this.light = light;
	}

	public void turnOn()
    {
    	System.out.println(this.name + "打开开关");
    	light.shine();
    }

    public void turnOff()
    {
    	System.out.println(this.name + "关上开关");
    	light.black();
    }
}