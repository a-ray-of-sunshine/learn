package com.sunshine.learn.thread.lock;

import com.sunshine.learn.thread.atomic.ICount;

public abstract class AbstractCount implements ICount {

	protected int messageCount;
	protected ILock lock;

	public AbstractCount() {
		this.lock = new SpinLock();
	}

	public AbstractCount(ILock lock) {
		this.lock = lock;
	}

	@Override
	public abstract void incCount();

	@Override
	public abstract void decCount();

	@Override
	public int getCount() {
		return this.messageCount;
	}

}
