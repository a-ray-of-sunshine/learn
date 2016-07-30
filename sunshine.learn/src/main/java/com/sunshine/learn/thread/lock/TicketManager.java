package com.sunshine.learn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 票务管理
 */
public class TicketManager {

	// 总票数
	private int ticketNum;
	
	private ReentrantReadWriteLock lock;
	private Lock writeLock;
	private Lock readLock;
	
	public TicketManager(int ticketNum) {
		this.ticketNum = ticketNum;
		this.lock = new ReentrantReadWriteLock();
		this.writeLock = lock.writeLock();
		this.readLock = lock.readLock();
	}

	// 售票
	public void takeTicket(){
		this.writeLock.lock();
		this.ticketNum--;
		this.writeLock.unlock();
	}
	
	// 查询余票
	public int getAvailableTicketNum(){

		this.readLock.lock();
		int avalNum = this.ticketNum;
		this.readLock.unlock();
		
		return avalNum;
	}
}
