package com.sunshine.learn.thread.lock;

public class MessageCountCLHLock extends AbstractCount {

	
	public MessageCountCLHLock() {
		super(new CLHLock());
	}

	@Override
	public void incCount() {
		this.lock.lock();
		this.messageCount++;
		this.lock.unlock();
	}

	@Override
	public void decCount() {
		this.lock.lock();
		this.messageCount--;
		this.lock.unlock();
	}

}
