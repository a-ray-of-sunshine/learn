package com.sunshine.learn.designpattern.principle.DIP;

public class Mother {

	public void sayStory(Book book){
		System.out.println("妈妈开始讲故事：");
		System.out.println(book.getContext());
	}
}
