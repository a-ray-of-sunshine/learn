package com.sunshine.learn.designpattern.principle;

/**
 * 2015年7月1日21:14:38
 * 开闭原则
 * Software entities should be open for extension,but closed for modification
 */
public class OCP {
	
	public static void main(String[] args) {
		float salary = 10000.0f;
		float salaryoftax = 0.0f;
		
		// 	不使用抽象进行封装的计算方法
		// 1. 当税率计算方法发生改变时，我们就得修改 getTax 方法的实现，
		// 2. 或者，我们可以提供一个 getTax2 的方法来体现新的税率计算策略。
		// 但是这个方法，我们就得修改下面的方法调用，变成 getTax2(salary);
		// 这样我们的系统就很不稳定，就不停的要修改现有的代码
		salaryoftax = getTax(salary);
		System.out.println(salaryoftax);//300.0

		// 	使用抽象进行计算
		ITaxCalculate calc = new OldTaxCalculator();
		salaryoftax = calc.GetTax(salary);
		System.out.println(salaryoftax);//300.0
		// 上面 使用的是原有的计算方法，现在方法变化了，则
		// 新建一个 NewTaxCalculator 类，来实现税率计算接口 ITaxCalculate
		// 使用新的实现来适应变化
		calc = new NewTaxCalculator();
		salaryoftax = calc.GetTax(salary);
		System.out.println(salaryoftax);// 200.0
		// 可以看到上面的代码还有存在问题，其实使用新的类来实现ITaxCalculate的机制，就
		// 类似于使用新的税率计算方法，例如增加一个新的方法： getTax2
		// 此时 客户程序就得改变，也就是23行的代码就得改变，是 
		// new NewTaxCalculator() 而不是 new OldTaxCalculator()
		// 可以上的封装并没有解决根本问题 --- 客户代码还是随着需求的变更而发生了变化
		// 但是它至少解决了一个问题就是，需求变更时，如何实现新的需求：就是按照新的需求实现 ITaxCalculate 接口
		// 未解决的问题就是：新的计算类出现后，客户程序要使用这个类就得更改，客户程序的代码，
		// 其实产生这个问题的根本原因就是我们在客户代码中直接 new 了一个ITaxCalculate的实现
		// 就是这个 new 使得 ITaxCalculate接口的某个具体的实现（例如：OldTaxCalculator）和客户代码发生了耦合
		// 这种耦合就是---依赖，所以要解决上面的问题，就是要解决 new 一个具体的实例，带来的耦合问题。
		// 如何解决 --- 方法就是使用设计模式中的创建型模式来解决，创建型模式说白了就是解决由new带来的耦合问题
		// 这个问题解决了，就意味着当需求变更时（计算税率的策略发生变化时），我们只需要依据新的需求，提供一个已有的
		// 对改问题的抽象的接口ITaxCalculate（）的一个新实现即可。此时我们的客户程序就可以几乎不用修改任何代码的来
		// 适应需求变更了。
		// 所以说到底开闭原则的最终目的就是封装变化点，计算税率的策略有可能会发生变化，所以把计算税率的方法抽象在一个接口中
		// 在客户程序中始终面向该接口进行计算，则客户程序就不会因为需求的变化而变化了
		// 开闭原则中的封装变化点，其实就是对系统中存在变化的需求进行抽象，所以开闭原则实现的关键就是对需求的抽象过程
		// 其实这也是面向对象编程的一个基本方法--- 抽象和封装 --- 将变化点抽取出来
		// 然互使用继承的方式，进行代码的扩展（extension）而不是直接修改原有的代码，
		// 最终以多态的方法来使用该接口。
		
	}

	// 传统的计算税率的方法，直接写个函数
	// 但是这个计算方法有可能发生变化，则如何应对这个变化，就是封装变化点。
	// 把这个税率计算的方法封装到一个接口中，而不是直接提供一个函数实现。
	// 这样当计算税率的方法发生变化时，就可以使用继承的方式为系统提供一种
	// 新的计算税率的策略方法。这样就避免的修改客户端的代码，
	static float getTax(float salary){
		return salary * 0.03f;
	}
}
