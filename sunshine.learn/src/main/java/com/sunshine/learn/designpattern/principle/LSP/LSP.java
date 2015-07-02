package com.sunshine.learn.designpattern.principle.LSP;

/**
 * 2015年7月2日14:36:39
 * 里氏替换原则（LSP, Liskov Substitution Principle）是关于继承机制的原则,解决继承带来的问题,
 * 是实现开放封闭原则的具体规范，违反了里氏替换原则必然违反了开放封闭原则。
 继承的优点：
    代码共享，减少创建类的工作量，每个子类都拥有父类的方法和属性；
    提高代码的重用性；
    子类可以形似父类，但又异于父类；
    提高代码的可扩展性；
    提高产品或项目的开放性。

继承的缺点：
    继承是侵入性的，只要继承就必须拥有父类的所有属性和方法；
    降低代码的灵活性，子类必须拥有父类的属性和方法，让子类增加了约束；
    增强了耦合性，当父类的常量、变量和方法被修改时，必须考虑子类的修改。

定义：
所有引用基类的地方必须能透明地使用其子类的对象。
通俗点讲，只要父类能出现的地方子类就可以出现，而且替换为子类也不会产生任何错误或异常，使用者可能根本就不需要知道是父类还是子类。
但是，反过来就不行了，有子类出现的地方，父类未必就能适应。

含义：
 1.子类必须完全实现父类的方法
在类中调用其他类时务必要使用父类或接口，如果不能使用父类或接口，则说明类的设计已经违背了里氏替换原则。
如果子类不能完整地实现父类的方法，或者父类的某些方法在子类中发生了“畸变”，则建议断开父子继承关系，采用依赖、聚集、组合等关系代替继承。

 2.子类可以有自己的个性

 3.覆盖或实现父类的方法时输入参数可以被放大
如果子类的前置条件较小，子类在没有覆写父类的方法的前提下，子类方法被执行了，这会引起业务逻辑混乱，因为在实际应用中父类一般都是抽象类，子类是实现类，你传递一个这样的实现类就会“歪曲”了父类的意图，引起一堆意想不到的业务逻辑混乱。

 4.覆盖或实现父类的方法时输出结果可以被缩小
    
目的：
采用里氏替换原则的目的就是增强程序的健壮性，版本升级是也可以保持非常好的兼容性。即使增加子类，原有的子类还可以继续运行。
在实际项目中，每个子类对应不同的业务含义，使用父类作为参数，传递不同的子类完成不同的业务逻辑。
 */
public class LSP {

	public static void main(String[] args) {
		
		// 1. 违反了里氏替换原则
		Rectangle rect = new Square();
		rect.setWidth(5);
		rect.setHeight(6);
		// 这里程序就陷入死循环了，可见对于 Rectangle 类的子类 Square来说，其并没有实现"所有引用父类的地方都可以透明的引用子类"
		// 所以，正方形继承自矩形这种继承的就是有问题的，违反了LSP
		resizeRec(rect); 
		System.out.println(rect.getWidth());
		System.out.println(rect.getHeight());
		
		
		// 2. 依据LSP进行抽象
		// 此时 矩形（Rectangle2） 和 正方形（Square2）都继承自 四边形（Quadrangle）
		// 所以此时矩形和正方形都属于Quadrangle的子类，其关系是并列的子类（可以说没有任何关系），
		// 所以下面的 resizeRec2 方法就可以正常的使用了，
		Rectangle2 rect1 = new Rectangle2();
		rect1.setWidth(5);
		rect1.setHeight(6);
		resizeRec2(rect1); // 
		System.out.println(rect1.getWidth());
		System.out.println(rect1.getHeight());
		
		Square2 rect2 = new Square2();
		rect2.setWidth(5);
		rect2.setHeight(6);
		// 下面的是不能调用的因为rect2是Square2类型的，和resizeRec2方法接受的参数Rectangle2，没有继承关系
		// 所以在就在代码编译阶段（编译不通过）解决了错误。
//		resizeRec2(rect2); 
	}
	
	/**
	 * 调整矩形的宽度使其始终大于高度
	 * 对于这个方法而言，其作用是调整矩形的宽度，使其始终大于矩形的高度，此时考虑：对于正方形来说按照第一种继承方法，正方形继承自矩形
	 * 则下面的方法也应该对正方形有效，则就是下面的方法就会调整一个正方形的宽度使其始终大于其高度，显然，这是有问题的，因为正方形的宽度和高度始终是相同的。
	 * 所以下面的方法就陷入死循环。对于这个方法来说其功能是没有任何问题的（调整矩形的宽度和高度），那么为什么，当矩形的子类（Square）传入的时候
	 * 调用会失败（死循环）呢。其实，这就是抽象的有问题。正方形继承自矩形--- 这种继承就是有问题的。
	 * @param rec
	 */
	static void resizeRec(Rectangle rec)
	{
		while(rec.getWidth()<=rec.getHeight())
		{
			rec.setWidth(rec.getWidth() + 1);
		}
	}
	
	/**
	 * 调整矩形的宽度使其始终大于高度
	 * 这个方法的作用是调整矩形的宽度，而不是正方形的，所以在第二种继承形式下，下面的代码始终是可以运行的
	 * @param rec
	 */
	static void resizeRec2(Rectangle2 rec)
	{
		while(rec.getWidth()<=rec.getHeight())
		{
			rec.setWidth(rec.getWidth() + 1);
		}
	}
}
