package com.sunshine.learn.thread.lock;

public class MessageCountTicketLock extends AbstractCount {

	public MessageCountTicketLock() {
		super(new TicketLock());
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
