package com.sunshine.learn.base.expressparse;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings(value={"rawtypes", "unused"})
public class Operate
{
	public static void main(String[] args)
	{
//		Oper("323*(212+134)");
		Oper1("323/(212-134)+100");
//		Oper1("(212-134)+100");
//		Oper1("323/(212-134)");
//		Oper1("(212-134)");
//		Oper1("212-134");
	}
	
	// 双目运算
		// a * ( b + c )
		public static void Oper1(String express){
			Stack operLeft = new Stack();
			Stack operRight = new Stack();
			Stack oper = new Stack();
			
			//1. 假设你对输入的表达式一无所知，则如何构造上面的3个对象 
			//由于括号的优先级高，所以首先按照括号对字符串express进行分组
			String opreg = "[\\+\\-\\*\\/]*"; // 特殊符号必须转义之后才可以使用
			String num = "\\d*";
			String leftBracket = "\\(*";
			String rightBracket = "\\)*";
			
			String reg = num + opreg + leftBracket + num + opreg + num + rightBracket + opreg + num;
			
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(express);
//			isOk(matcher);
			
//			// 对括号进行分组
			String GroupReg = "(" + num + opreg + ")" + "(" + leftBracket + num + opreg + num + rightBracket  + ")" + "(" + opreg + num + ")" ;
			
			Pattern pBracket = Pattern.compile(GroupReg);
//			Pattern pBracket = Pattern.compile(leftBracket + num + opreg + num + rightBracket);
			Matcher mBracket = pBracket.matcher(express);
			mBracket.matches();
//			isOk(mBracket);
//			System.out.println(express+": " );
			System.out.println(mBracket.group(1));
			System.out.println(mBracket.group(2));
			System.out.println(mBracket.group(3));
//			System.out.println("------------");
		}
		
	// 双目运算
	// a * ( b + c )
	public static void Oper(String express){
		Stack operLeft = new Stack();
		Stack operRight = new Stack();
		Stack oper = new Stack();
		
		//1. 假设你对输入的表达式一无所知，则如何构造上面的3个对象 
		//由于括号的优先级高，所以首先按照括号对字符串express进行分组
		String opreg = "[\\+\\-\\*\\/]"; // 特殊符号必须转义之后才可以使用
		String num = "\\d+";
		String leftBracket = "\\(";
		String rightBracket = "\\)";
		
		String reg = num + opreg + leftBracket + num + opreg + num + rightBracket;
		
		Pattern pattern = Pattern.compile(reg);
//		System.out.println(pattern);
		Matcher matcher = pattern.matcher(express);
		
//		Pattern pattern = Pattern.compile(num + opreg + num);
//		Matcher matcher = pattern.matcher("1/2");
		
//		if(matcher.matches()){
//			System.out.println("匹配成功");
//		}else{
//			System.out.println("匹配失败");
//		}
		
		// 对括号进行分组
		String GroupReg = "(" + num + opreg + "(" + leftBracket + num + opreg + num + rightBracket + ")" + ")";
		Pattern pBracket = Pattern.compile(GroupReg);
//		Pattern pBracket = Pattern.compile(leftBracket + num + opreg + num + rightBracket);
		Matcher mBracket = pBracket.matcher(express);
		mBracket.matches();
//		isOk(mBracket);
		System.out.println(express+": " );
		System.out.println(mBracket.group(1));
		System.out.println(mBracket.group(2));
		System.out.println("------------");
	}
	
	public static void isOk(Matcher matcher){
		System.out.println(matcher.pattern());
		if(matcher.matches()){
			System.out.println("匹配成功");
		}else{
			System.out.println("匹配失败");
		}
	}
}
