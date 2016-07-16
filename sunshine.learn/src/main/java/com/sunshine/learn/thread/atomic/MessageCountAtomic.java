package com.sunshine.learn.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageCountAtomic implements ICount {

	private AtomicInteger messageCount;
	
	public MessageCountAtomic() {
		this.messageCount = new AtomicInteger();
	}

	@Override
	public void incCount() {
		this.messageCount.incrementAndGet();
	}

	@Override
	public void decCount() {
		this.messageCount.decrementAndGet();
	}

	@Override
	public int getCount() {
		return this.messageCount.get();
	}

}
