package com.sunshine.learn.designpattern.principle.SRP;

/**
 * SRP 单一职责原则（SRP,Single Responsibility Principle)
 * 强调的是职责分离，在某种程度上是对职责的理解是构成了不同类之间的耦合关系的设计关键。
定义：
	应该有且仅有一个原因引起类的变更。
	There should never be more than one reason for a class to change.
	
优点：
	1、类的复杂性降低，实现什么职责都有清晰明确的定义；
	2、可读性提高，复杂性减低，可读性当然提高；
	3、可维护性提高，可读性提高，可维护性当然提高；
	4、变更引起的风险减低，变更是必不可少的，如果接口的单一职责做得好，一个接口修改只对相应的类有影响，
	       对其他接口无影响，这对系统的扩展性、维护性都有非常大的帮助。
	
注意：
	单一职责原则提出了一个编写程序的标准，用“职责”或“变化原因”来衡量接口或类设计得是否优良，
	但是“职责”和“变化原因”都是不可度量的，因项目而异，因环境而异。
	
建议：
	接口一定要做到单一职责，类的设计尽量做到只有一个原因引起变化。
 */
public class SRP {

	public static void main(String[] args) {
		// 看到电灯对象里有四个方法，点开 和关闭电灯，还有电灯的状态输出，此时发现 电灯太霸道了，
		// 大部分灯没有这个开/关功能，当然有的有，不过这应该划分到开关的功能，电灯不具备这样的功能，
		// 电灯就是有一个 发光或者不发光，电灯越权了，开关会生气，我们添加一个开关，把职能分离出去，这时，电灯亮与不亮就靠开关了。
		// 所以这样的设计违反了SRP原则
		Light light = new Light();
		light.turnOn();
		light.shine();
		System.out.println("------------");
		
		// 上面的电灯类其实有两个职责，一个是发光，另一个是控制开关，
		// 所以将上面的类进行职责重构，按职责分为  Light 类和 Switch 类
		// 买一个开关
		Switch sw = new Switch("旧开关");
		// 买一个电灯
		Light2 light2 = new Light2("旧电灯");
		// 把电灯接到开关上
		sw.setLight(light2);
		// 开关就可以使用了
		sw.turnOn(); // 打开开关
		sw.turnOff();// 关闭开关
		System.out.println("------------");
		// 用了一段时间电灯坏了，换一个电灯，注意，开关还是好的所以可以继续使用
		Light2 newLight = new Light2("新电灯");
		sw.setLight(newLight);
		sw.turnOn();
		sw.turnOff();
		System.out.println("------------");
		
		// 新电灯换了不久，开关又坏了，换个开关
		Switch newSwitch = new Switch("新开关");
		// 把以前使用的电灯，接上这个新的开关
		newSwitch.setLight(newLight);
		// 试一试这个开关好用不好用
		newSwitch.turnOn();
		newSwitch.turnOff();
		
		// 可见，将职责分离开来之后，类的复用性也大大增强了，
		// 新电灯可以接在旧开关上
		// 换个开关，电灯同样可以正常使用。
		// 这就是，职责分离的好处，假如电灯还是像最初的抽象那样Light类
		// 则开关坏，电灯就不能用了，同样，电灯坏了，开关就废了，这样很是浪费资源啊
		
	}
}
