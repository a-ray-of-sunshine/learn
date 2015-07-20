package com.sunshine.learn.designpattern.principle.ISP;

/**
 * 2015年7月20日17:26:56
 * 开关继承自设备
 */
public class Switch implements IDevices {

	@Override
	public void turnOn(){
		System.out.println("打开开关");
	}

	@Override
	public void turnOff() {
		System.out.println("关闭开关");
	}

//	上面的接口 有3个方法，但是 电灯开关 并没有这个暂停功能，所以实现它只是抛出一个异常，
//	因为这个Pause方法，电灯开关并不需要，是多余的，
//	因此IDevices接口相对于Switch 是一个胖接口，我们应该把这个胖接口分离。
	@Override
	public void pause() {
		throw new RuntimeException("不支持的接口");
	}
	
}
