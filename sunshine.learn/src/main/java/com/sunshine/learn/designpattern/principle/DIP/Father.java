package com.sunshine.learn.designpattern.principle.DIP;

public class Father {

	public void sayStory(IReader reader){
		System.out.println("爸爸开始讲故事:");
		System.out.println(reader.getContext());
	}
}
