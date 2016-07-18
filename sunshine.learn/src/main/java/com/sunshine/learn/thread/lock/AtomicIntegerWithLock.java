package com.sunshine.learn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sunshine.learn.thread.atomic.ICount;

public class AtomicIntegerWithLock implements ICount{

	private int value;
	private Lock lock;

	public AtomicIntegerWithLock() {
		this.lock = new ReentrantLock();
	}

	@Override
	public void incCount() {
		lock.lock();
		try{
			this.value++;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void decCount() {
		lock.lock();
		try{
			this.value--;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public int getCount() {
		lock.lock();
		try{
			return this.value;
		}finally {
			lock.unlock();
		}
	}
	
}
