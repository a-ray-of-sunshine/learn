package com.sunshine.learn.designpattern.principle.ISP;

public class XXXPlayer implements IPlayer{

	@Override
	public void pause() {
		System.out.println("播放器暂停");
	}
	
	@Override
	public void turnOff() {
		System.out.println("打开播放器");
	}
	
	@Override
	public void turnOn() {
		System.out.println("关闭播放器");
	}
}
