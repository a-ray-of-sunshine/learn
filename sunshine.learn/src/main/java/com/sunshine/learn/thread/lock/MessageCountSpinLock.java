package com.sunshine.learn.thread.lock;

import com.sunshine.learn.thread.atomic.ICount;

public class MessageCountSpinLock implements ICount {

	private int messageCount;
	private ILock lock = new SpinLock();

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

	@Override
	public int getCount() {
		return this.messageCount;
	}

}
