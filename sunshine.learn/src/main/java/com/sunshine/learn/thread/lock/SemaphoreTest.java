package com.sunshine.learn.thread.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	

}

// 资源池
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