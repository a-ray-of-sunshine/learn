package com.sunshine.learn.thread.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁  --- 存在的问题
 * CAS操作需要硬件的配合；
 * 保证各个CPU的缓存（L1、L2、L3、跨CPU Socket、主存）的数据一致性，通讯开销很大，在多处理器系统上更严重；
 * 没法保证公平性，不保证等待进程/线程按照FIFO顺序获得锁。
 *
 */
public class SpinLock implements ILock {

	private AtomicReference<Thread> owner = new AtomicReference<Thread>();

	@Override
	public void lock() {
		Thread thread = Thread.currentThread();
		
		/**
		 * 这个循环是实现自旋锁的关键，当线程没有获取到锁的时候，不进行block状态，而是进行循环不断的
		 * 获取，这样可以减少线程上下文切换的开销。 但是，这里如果并发的线程数据比较多，所有线程都在自旋
		 * 非常消耗CPU
		 */
		while(!owner.compareAndSet(null, thread));
	}

	@Override
	public void unlock() {

		Thread thread = Thread.currentThread();
		
		owner.compareAndSet(thread, null);
	}

}
