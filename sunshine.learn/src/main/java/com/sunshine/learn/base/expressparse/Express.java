package com.sunshine.learn.base.expressparse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public class Express
{
	public static void main(String[] args)
	{
//		String express = "23 + 42 * 10 - 4 + 65 / 5";
		String express = "24 / 3 * 10 - 4 / 2 * 34 - 5";
//		String express = "24 / 3 * 10 - 4 / 2";
//		String express = " 23 a 34	b  64  c";
//		String express = "3 + 46 / 2";
		List<String> list = new ArrayList<String>();
		explineExpressToList(express, list);
//		System.out.println(list);
		Stack<String> operNum = new Stack<String>();
		Stack<String> oper = new Stack<String>();
		int size = list.size();
		operNum.push(list.get(size - 1));
		
		for(int i = size - 2; i >= 0 ; i--){
			String str = list.get(i);
			String p1 = str.substring(0, str.length() - 2).trim();
			String p2 = str.substring(str.length() - 2, str.length() - 1).trim();
			operNum.push(p1);
			oper.push(p2);
		}
		
		System.out.println(operNum);
		System.out.println(oper);
		
		// 下面开始运算
		Oper(operNum, oper);
		System.out.println("size: " + operNum.size());
		System.out.println("res: " + operNum.pop());
	}
	
	public static void Oper(Stack<String> operNum, Stack<String> oper){
		// 递归的出口，操作符栈中没有元素
		if(oper.isEmpty()){
			return;
		}
				
		String operator1 = "";
		String operator2 = "";
		String leftOper = "";
		String rightOper = "";
		String thridOper = "";

		leftOper = operNum.pop();
		rightOper = operNum.pop();
		
		if(1 == oper.size()){
			operator1 = oper.pop();
			int res = Computer(operator1, leftOper, rightOper);
			operNum.push(String.valueOf(res));
			Oper(operNum, oper);
			return;
		}
		
		operator1 = oper.pop();

		operator2 = oper.peek();// 不移除这个对象
		thridOper = operNum.peek();
		
		Integer property = 0;
		boolean isSame = false;
		List list = isSameLevel(operator1, operator2); 
		isSame = (Boolean)list.get(0);
		property = (Integer)list.get(1);
		
		if(isSame){
			int res = Computer(operator1, leftOper, rightOper);
			operNum.push(String.valueOf(res));
			Oper(operNum, oper);
			return;
		}else{
			// 第一个运算符的优先级高于第二个
			if(1 == property){
				int res = Computer(operator1, leftOper, rightOper);
				operNum.push(String.valueOf(res));
				Oper(operNum, oper);
				return;
			}else if(2 == property){// 第一个运算符的优先级低于第二个
				int res = Computer(operator2, rightOper, thridOper);
				
				// 调整操作数
				operNum.pop(); // 将 thridOper 弹出栈顶
				operNum.push(String.valueOf(res));
				operNum.push(leftOper);
				
				
				// 调整运算符
				oper.pop();// 将 operator2 弹出栈顶
				oper.push(operator1);
				
				Oper(operNum, oper);
				return;
			}
		}
		
	}
	
	public static List isSameLevel(String operator1, String operator2){
		Integer property = 0;
		boolean isSame = false;
		
		if((operator1.equals("+") || operator1.equals("-")) 
				&& (operator2.equals("+") || operator2.equals("-"))){
			property = 1;
			isSame = true;
		}
		
		if((operator1.equals("*") || operator1.equals("/")) 
				&& (operator2.equals("*") || operator2.equals("/"))){
			property = 1;
			isSame = true;
		}
		
		if((operator1.equals("+") || operator1.equals("-")) 
				&& (operator2.equals("*") || operator2.equals("/"))){
			property = 2;
		}
		
		if((operator1.equals("*") || operator1.equals("/")) 
				&& (operator2.equals("+") || operator2.equals("-"))){
			property = 1;
		}
		
		List list = new ArrayList();
		list.add(isSame);
		list.add(property);
		return list;
	}
	
	public static int Computer(String operator, String leftOper, String rightOper){
		int left = Integer.valueOf(leftOper);
		int right = Integer.valueOf(rightOper);
		
		switch(SwitchOper(operator)){
		case 1:
			return left + right;
		case 2:
			return left - right;
		case 3:
			return left * right;
		case 4:
			return left / right;
		}
		
		return -1;
	}
	
	public static int SwitchOper(String operator){
		if("+".equals(operator)){
			return 1;
		}
		
		if("-".equals(operator)){
			return 2;
		}
		
		if("*".equals(operator)){
			return 3;
		}
		
		if("/".equals(operator)){
			return 4;
		}
		
		return -1;
	}
	
	public static void explineExpressToList(String express, List<String> res){
		String reg = "(\\s*\\d+\\s*[\\+\\-\\*\\/]{1}\\s*)(.*)";
		Pattern pattern = Pattern.compile(reg);

		Matcher matcher = pattern.matcher(express);
		
		if(!matcher.matches()){
			res.add(express);
			return;
		}
//		isOk(matcher);
//		System.out.println(matcher.groupCount());
//		System.out.println(matcher.group(1));
//		System.out.println(matcher.group(2));
		String first = matcher.group(1);
		String next = matcher.group(2);
		res.add(first);
		explineExpressToList(next, res);
		return;
	}
	
	public static String explineExpress(String express){
		String reg = "(\\s*\\d+\\s*[\\+\\-\\*\\/]{1}\\s*)(.*)";
//		String reg = "(\\d+[a-z]{1})(.*)";
//		String reg = "[\\d+[a-z]{1}]*";
//		String reg = "(\\d+[a-z]{1})*";
//		String reg = "[\\d+[a-z]{1}]*";
		Pattern pattern = Pattern.compile(reg);
		
		Matcher matcher = pattern.matcher(express);
		
		if(!matcher.matches()){
			return express;
		}
//		isOk(matcher);
//		System.out.println(matcher.groupCount());
//		System.out.println(matcher.group(1));
//		System.out.println(matcher.group(2));
		String first = matcher.group(1);
		String next = matcher.group(2);
		
		return first + " :==: " + explineExpress(next);
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
