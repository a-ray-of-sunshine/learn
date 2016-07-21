package com.sunshine.learn.thread.lock;

public class MessageCountMCSLock extends AbstractCount {

	public MessageCountMCSLock() {
		super(new MCSLock());
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
