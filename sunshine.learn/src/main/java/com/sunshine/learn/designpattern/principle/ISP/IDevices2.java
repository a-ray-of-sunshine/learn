package com.sunshine.learn.designpattern.principle.ISP;

// 瘦接口 --- 将 Pause() 这个接口从原来的接口分离出去，形成：IPlayer接口
public interface IDevices2 {

	// 打开
	void turnOn();
	
	// 关闭
	void turnOff();
}
