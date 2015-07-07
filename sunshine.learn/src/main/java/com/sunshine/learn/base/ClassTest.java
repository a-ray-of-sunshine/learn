package com.sunshine.learn.base;

public class ClassTest {

	public static void main(String[] args) {
		// 由于java是强类型语言，所以编译器会进行类型检查，所以下面的类型转换是
		// 不成功的，但是从内存的角度来看，Student的对象和Wood的对象，其内存
		// 分布是完全相同的，所以从内存粒度上看，这种转换是没有问题的，如果java没有强类型
		// 检查，则下面的转换是可以成功的，并且方法也可以被调用。
//		Student s = (Student)new Wood();
	}
}

class Student{
	// 学生的年龄
	int age;
	// 学生的姓名
	String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

class Wood{
	// 木材的生长年龄
	int age;
	// 木材的名称
	String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}