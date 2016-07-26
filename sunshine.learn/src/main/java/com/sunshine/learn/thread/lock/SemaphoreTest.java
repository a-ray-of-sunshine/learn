package com.sunshine.learn.thread.lock;

import java.util.concurrent.Semaphore;

import com.sunshine.learn.utils.Utils;

public class SemaphoreTest {
	
	public static void main(String[] args) {
		
		int clientSize = 10;
		int poolSize = 3;
		
		PoolManager pool = new PoolManager(poolSize);
		
		for(int i = 0; i < clientSize; i++){
			Runnable task = new Client(pool);
			new Thread(task).start();
		}
		
	}

}

/**
 * 客户端，使用资源池来获取资源
 */
class Client implements Runnable {

	private PoolManager pool;
	
	public Client(PoolManager pool) {
		this.pool = pool;
	}

	@Override
	public void run() {

		Utils.sleep(Utils.randInt(1000));

		// 使用资源
		Resouce resource = pool.getResource();
		System.out.println(Thread.currentThread().getName() + " 正在使用资源：" + resource.getIndex());
		Utils.sleep(Utils.randInt(2000));

		// 释放资源
		pool.putResource(resource);
		Utils.sleep(Utils.randInt(500));
	}
}

// 资源池, 使用信号量来自管理有限的资源
// 只要内部 信号量 的 permits >= 资源的个数
// 则 findResource 方法总是可以找到，有效的可用的资源
// 而不会直接返回 null.
class PoolManager{
	
	private int poolSize;
	private Semaphore lock;
	private Resouce[] pool;

	public PoolManager(int poolSize) {

		this.poolSize = poolSize;
		this.lock = new Semaphore(poolSize);
		
		initPool();
	}
	
	private void initPool() {
		pool = new Resouce[poolSize];
		for(int i = 0; i < this.poolSize; i++){
			pool[i] = new Resouce(i);
		}
	}

	public Resouce getResource(){
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return findResource();
	}

	public void putResource(Resouce resouce){
		resouce.setLock(false);
		pool[resouce.getIndex()] = resouce;
		lock.release();
	}
	
	private Resouce findResource(){
		for(int i = 0; i < this.poolSize; i++){
			Resouce resouce = pool[i];
			if(!resouce.isLock()){
				resouce.setLock(true);
				return resouce;
			}
		}
		return null;
	}
}

class Resouce{
	private boolean isLock;
	private int index;

	public Resouce(int index) {
		this.index = index;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}