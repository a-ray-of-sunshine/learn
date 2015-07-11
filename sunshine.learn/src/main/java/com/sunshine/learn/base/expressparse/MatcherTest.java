package com.sunshine.learn.base.expressparse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest
{
	public static void main(String[] args)
	{
		String express = " 23 a 	45	b 54 c 90 d 94 e	3";
		test(express);
	}
	
	public static void test(String express){
		String reg = "(\\s*\\d+\\s*)([a-z]{1}\\s*)";
		Pattern pattern = Pattern.compile(reg);
		
		Matcher matcher = pattern.matcher(express);
		// 1. 第一种全部匹配
//		if(matcher.matches()){
//			System.out.println("matches: 匹配成功");
//			int start = matcher.start();
//			int end = matcher.end();
//			System.out.println(express.substring(start, end));
//		}else{
//			System.out.println("matches: 匹配失败");
//		}
		
		// 2. lookingAt
//		if(matcher.lookingAt()){
//			System.out.println("lookingAt: 匹配成功");
//			int start = matcher.start();
//			int end = matcher.end();
//			System.out.println(express.substring(start, end));
//		}else{
//			System.out.println("lookingAt: 匹配失败");
//		}
		
		// 3. find
//		if(matcher.find()){
//			System.out.println("find: 匹配成功");
//			int start = matcher.start();
//			int end = matcher.end();
//			System.out.println(express.substring(start, end));
//			System.out.println("---------");
//		}else{
//			System.out.println("find: 匹配失败");
//		}
		
		// 4. 
		while(matcher.find()){
			int start = matcher.start();
			int end = matcher.end();
			String ms = express.substring(start, end);
//			ms.trim();
			ms = ms.replaceAll("\\s", "");
			System.out.println(ms);
			
			int GroupSize = matcher.groupCount();
			for(int i = 0; i < GroupSize; i++){
				System.out.println(" " + matcher.group(i+1));
			}
		}
		
		// 找到最后的数字
		reg = ".*(\\d+)";
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(express);
		matcher.matches();
		System.out.println(matcher.group(1));
		System.out.println("-----------");
		
		// 使用字母分组来获得数字
		reg = "[a-z]{1}";
		pattern = Pattern.compile(reg);
		String[] rearray = pattern.split(express);
		int rsize = rearray.length;
		while(--rsize >= 0){
			System.out.print(rearray[rsize].trim() + " ");
		}
		System.out.println("\n-====-=--=");
		
		// 使用数字分组来获得字母
		reg = "\\d+";
		pattern = Pattern.compile(reg);
		rearray = pattern.split(express);
		rsize = rearray.length;
		while(--rsize >= 0){
			System.out.print(rearray[rsize].trim() + " ");
		}
	}
}
