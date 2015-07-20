package com.sunshine.learn.designpattern.principle.ISP;

/**
 * 2015年7月20日18:24:34
 * ISP --- 接口隔离原则 （Interface Segregation Principle， ISP）
 * 使用多个专门的接口，而不使用单一的总接口，即客户端不应该依赖那些它不需要的接口。

定义：
    客户端不应该依赖它不需要的接口
    类间的依赖关系应该建立在最小的接口上
我们可以把这两个定义概括为一句话：建立单一接口，不要建立臃肿庞大的接口。再通俗一点讲：接口尽量细化，同时接口中的方法尽量少。
提供给每个模块的都应该是单一接口，提供给几个模块就应该有几个接口，而不是建立一个庞大的臃肿的接口，容纳所有的客户端访问。
接口是我们设计时对外提供的契约，通过分散定义多个接口，可以预防未来变更的扩散，提高系统的灵活性和可维护性。

含义：
    接口要尽量小
这是接口隔离原则的核心定义，不出现臃肿的接口（Fat Interface），但是“小”是有限度的，首先就是不能违反单一职责原则。
根据接口隔离原则拆分接口时，首先必须满足单一职责原则。
    
    接口要高内聚
高内聚就是要提高接口、类、模块的处理能力，减少对外的交互。
具体到接口隔离原则就是，要求在接口中尽量少公布public方法，接口是对外的承诺，承诺地越少对系统开发越有利，变更的风险也就越少，同时也有利于降低成本。

    定制服务
定制服务就是单独为一个个体提供优良的服务。

    接口设计是有限度的
接口的设计粒度越小，系统越灵活，这是不争的事实。但是，灵活的同时也带来了结构的复杂化，开发难度增加，可维护性降低，这不是一个项目或产品所期望看到的，
所以接口设计一定要注意适度，这个度只能根据经验和常识判断，没有一个固化或可测量的标准。一般来讲，我们需要注意控制接口的粒度，接口不能太小，
如果太小会导致系统中接口泛滥，不利于维护；接口也不能太大，太大的接口将违背接口隔离原则，灵活性较差，使用起来很不方便。
一般而言，接口中仅包含为某一类用户定制的方法即可，不应该强迫客户依赖于那些它们不用的方法。
 */
@SuppressWarnings(value = {"unused"})
public class ISP {

	public static void main(String[] args) {
		IDevices devices = new Switch();
		// 由于开关设备不支持暂停功能，所以下面的方法
		// 有可能因为误调用而抛出异常，这就是由于在设计接口的时候
		// 没有划分清楚接口的职责范围而导致的接口过于臃肿，导致的。
//		devices.pause();
		
		// 下面将接口的pause功能分离出去，
		IDevices2 dev2 = new Switch2();
		dev2.turnOn();
		dev2.turnOff();
		// 开灯，关灯，很明确，没有多余的操作
		
		// 使用播放器
		IPlayer player = new XXXPlayer();
		// 打开播放器，听了3分钟广播
		player.turnOn();
		// 停一会，去吃饭
		player.pause();
		// 饭吃过了，继续听广播
		player.turnOn();
		// 12点了，该睡觉了，关闭广播
		player.turnOff();
	}
}
