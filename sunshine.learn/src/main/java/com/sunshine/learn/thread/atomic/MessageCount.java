package com.sunshine.learn.thread.atomic;

public class MessageCount implements ICount {

	private int messageCount;
	
	public void incCount(){
		this.messageCount++;
	}
	
	public void decCount(){
		this.messageCount--;
	}
	
	public int getCount(){
		return this.messageCount;
	}
	
}
