package com.sunshine.learn.designpattern.principle.DIP;

/**
 * 2015年7月6日16:57:28
 * 依赖倒置 （Dependence Inversion Principle，DIP）
依赖倒置原则（Dependence Inversion Principle，DIP）的原始定义：
    高层模块不应该依赖底层模块，两者都应该依赖其抽象；
    抽象不应该依赖细节；
    细节应该依赖抽象。
依赖倒转原则的另一种表述是：要针对接口编程，不要针对实现编程。（Program to an interface， not animplementation）
 针对接口编程的意思就是说，应当使用java接口或者抽象java类进行
 	变量的类型声明，
 	参数的类型声明，
 	方法的返回类型说明，
 	以及数据类型的转换等。
 通俗点说，就是实现类只是实现  实现的接口或者继承的抽象类 中的方法，不要再声明新的变量或者新的方法
 （当然可以有protected或private的方法（用来辅助完成实现细节），但是这些方法对于客户来说是透明的，所以还是可以保证接口的纯洁性），
 这样能够保证实现依赖倒转原则，
 当然这样有时也并不是好的方式，该原则是在合适的时候使用。

依赖倒置原则在Java语言中的表现是：
    模块间的依赖通过抽象发生，实现类之间不发生直接的依赖关系，其依赖关系是通过接口或者抽象类产生的；
    接口或抽象类不依赖于实现类；
    实现类依赖接口或抽象类。

依赖倒置原则实际上就是要求“面向接口编程”。面向接口编程的好处就是，如果系统是一种分层的结构，则各个层之间就以接口的形式进行通信
则，不同的模块就可以同步的进行开发，而不会出现由于高层模块依赖于低层模块，而不能进行开发的情况。
采用依赖倒置原则可以减少类间的耦合性，提高系统的稳定性，降低并行开发引起的风险，提高代码的可读性和可维护性。

本质：
依赖倒置原则的本质就是通过抽象（接口或者抽象类）使各个类或模型（也就是具体的实现）的实现彼此独立，不互相影响，实现模块间的松耦合。

规则：
    每个类尽量都有接口或抽象类，或者抽象类和接口两者都具备；
    变量的表面类型尽量是接口或者抽象类；
    任何类都不应该从具体类派生；
    尽量不要覆写基类的方法；
    结合里氏替换原则使用。
    
--- 高层模块/底层模块，抽象/细节 ---
高层模块和低层模块容易理解，每一个逻辑的实现都是由原子逻辑组成的，不可分割的原子逻辑就是低层模块，原子逻辑的再组装就是高层模块。
那什么是抽象，什么又是细节呢？在Java语言中，抽象就是指接口或抽象类，两者都是不能直接被实例化的；
细节就是实现类，实现接口或继承抽象类而产生的类就是细节，其特点就是可以直接被实例化，也就是可以加上一个关键字new产生一个对象。
--- 依赖倒置 ---
那到底什么是“倒置”呢？我们先说“正置”是什么意思，依赖正置就是类间的依赖是实实在在的实现类间的依赖，也就是面向实现编程，
这也是正常人的思维方式，我要开奔驰车就依赖奔驰车，我要使用笔记本电脑就直接依赖笔记本电脑，
而编写程序需要的是对现实世界的事物进行抽象，抽象的结果就是有了抽象类和接口，然后我们根据系统设计的需要产生了抽象间的依赖，
代替了人们传统思维中的事物间的依赖，“倒置”就是从这里产生的。
--- 传统的依赖模型：（实现依赖） ---
                          高层模块A
	      |
	  ----------
	  |        | 
               子模块1   子模块2

--- 依赖倒转模型：（抽象依赖） ---
           高层模块A --------> 抽象类
	                 |
	            ------------
	            |          |
	                                 具体类1     	     具体类2

--- 完全的高层抽象：（抽象依赖） ---
                   抽象高层模块A --------> 抽象类B
       |                  |
   ------------       ------------
   |          |       |          |
          具体类1    具体类2     具体类1     具体类2
	                                 
 */
public class DIP {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// 妈妈讲故事
		// 1. 买本书
		Book storyBook = new Book();
		// 2. 开讲了
		Mother mother = new Mother();
		mother.sayStory(storyBook);
		// 现在宝宝拿来一本杂志要妈妈读
		Magazine magazine = new Magazine();
		// 显然下面的方法调用是不成功的，sayStory依赖的是Book类，而不是Magazine类
//		mother.sayStory(magazine);
		// 对于杂志上的故事，妈妈就读不了了，显然这是不符合现实场景的，
		// 之所以出现这种情况就是因为对于Mother类的sayStory方法依赖于具体的实现类Book，而不是依赖于抽象
		// 也就是说Mother类和Book类发生了具体耦合，这种具体耦合是一种强依赖，使得Mother类只能依赖于具体的实现Book
		// 在这里，为了解决这个问题，可以有两种方法，一种是为Mother类再添加一个readMagazine方法，这个方法就是用来读杂志的
		// 显然这个方法的实现基本和 sayStory方法的实现类似，所以这种设计就是有问题的，同时，如果孩纸（客户程序）要Mother能够读
		// 报纸，是不是还得修改Mother类，来添加一个读报纸的方法？所以这种解决方法是违反了开闭原则。对于Mother类来说，它属于高层模块
		// 在系统常常用来实现业务，如果这个类经常发生变化，则与该类相关的业务将变得非常不稳定。
		// 这些问题的根源是Mother类和Book类的耦合度太高了，所以解决这个问题的办法就是降低这两个类的耦合度
		// 这两个类目前的耦合度是具体耦合关系（具体耦合发生在两个具体类（可实例化的）之间，经由一个类对另一个类的直接引用造成。）
		// 考虑把这种耦合关系减低为抽象耦合关系，让Mother类不在直接依赖于具体的实现类Book，而是依赖于一个抽象类，这个抽象类抽象了
		// 一切可以阅读的资源（例如：书籍，报刊，杂志等等）
		
		// 依照上的思路，抽象出一个IReader接口，这个接口表示可以阅读的资源
		// 然后Father类依赖于这个抽象接口，而不是具体的实现，
		Father father = new Father();
		IReader book2 = new Book2();
		// 爸爸读书了
		father.sayStory(book2);
		// 宝宝拿来本杂志
		Magazine2 magazine2 = new Magazine2();
		// 爸爸照样可以读给宝宝听
		father.sayStory(magazine2);
		// 通过这样的修改，无论宝宝拿来什么的样的阅读资源，爸爸都可以轻松应对，因为Father只依赖于IReader接口
		// 所以只要继承IReader接口就可以了，提供具体的实现类就可以了，这样就达到了已扩展的方式来应对客户的需求变更
		// 这也就做到了开闭原则。
	}
	
}
