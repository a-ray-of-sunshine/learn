package com.sunshine.learn.thread.conpro;

/**
 * 共享资源
 */
public class Resource {

	private int resource;
	// 表示是否已经生产
	private boolean procude;
	
	/**
	 * 生产资源
	 */
	public void produceResource(){
		resource++;
	}
	
	/**
	 * 消费资源
	 */
	public int consumeResource(){
		return resource;
	}

	public boolean isProcude() {
		return procude;
	}

	public void setProcude(boolean procude) {
		this.procude = procude;
	}
	
}