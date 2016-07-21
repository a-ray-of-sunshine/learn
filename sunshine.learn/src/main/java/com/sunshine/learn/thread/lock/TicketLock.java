package com.sunshine.learn.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ticket Lock 是为了解决 自旋锁（SpinLock）的公平性问题，类似于现实中银行柜台的排队叫号：锁拥有一个服务号，表示正在服务的线程，还有一个排队号；每个线程尝试获取锁之前先拿一个排队号，然后不断轮询锁的当前服务号是否是自己的排队号，如果是，则表示自己拥有了锁，不是则继续轮询。
 * 当线程释放锁时，将服务号加1，这样下一个线程看到这个变化，就退出自旋。
 * 
 * Ticket Lock 虽然解决了公平性的问题，但是多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量ownerno，
 * 每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。
 *
 */
public class TicketLock implements ILock{

	// 每一个线程有自己的排队号
	private ThreadLocal<Integer> threadno = new ThreadLocal<Integer>();
	
	// 锁有自己的服务号, 初始，服务号是0
	private AtomicInteger ownerno = new AtomicInteger(0);
	
	// 号码生成器, 初始，号码是0
	private AtomicInteger no = new AtomicInteger(0);

	@Override
	public void lock() {
		
		// 给的排队的线程发放排队号
		threadno.set(no.getAndIncrement());

		// 如果服务号 不是 当前线程的排队号，则该线程一直循环
		// 直到两者相等，表示，当前获取到锁
		while(ownerno.get() != threadno.get());
	}

	@Override
	public void unlock() {
		// 服务号增加, 则表示 ， 锁被释放
		ownerno.incrementAndGet();
	}

}
