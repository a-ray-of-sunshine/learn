package com.sunshine.learn.thread.atomic;

public class MessageCountSync implements ICount {

	private int messageCount;

	@Override
	public synchronized void incCount() {
		this.messageCount++;
	}

	@Override
	public synchronized void decCount() {
		this.messageCount--;
	}

	@Override
	public int getCount() {
		return this.messageCount;
	}

}
