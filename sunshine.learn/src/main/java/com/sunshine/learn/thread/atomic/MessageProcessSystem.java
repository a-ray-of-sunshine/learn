package com.sunshine.learn.thread.atomic;

import org.junit.Assert;
import org.junit.Test;

import com.sunshine.learn.utils.Utils;

public class MessageProcessSystem {

	@Test
	public void testSystem(){
		
		for(int i = 0; i < 100; i++){
			this.messageProcess();
			System.out.println("第" + i + "测试成功.");
		}
	}
	
	private void messageProcess(){

		// 0. MessageCount类没有任何同步处理，下面的 assert 有可能失败
		ICount count = new MessageCount();
		
		// 1. 使用 synchronized
		// ICount count = new MessageCountSync();
		
		// 2. 使用 Atomic 变量，不需要同步
		// ICount count = new MessageCountAtomic();

		int produceCount = Utils.randInt(100);
		int consumerCount = Utils.randInt(produceCount + 1);

		Produce produce = new Produce(count, produceCount);
		Consumer consumer = new Consumer(count, consumerCount);
		
		Thread pt = new Thread(produce);
		Thread ct = new Thread(consumer);
		
		pt.start();
		ct.start();
		
		Utils.join(pt, ct);	
		
		Assert.assertEquals(produceCount - consumerCount, count.getCount());
	}
}

class Produce implements Runnable {
	
	private ICount count;
	private int produceCount;

	public Produce(ICount count, int produceCount) {
		this.count = count;
		this.produceCount = produceCount;
	}

	@Override
	public void run() {
		
		for(int i = 0; i < this.produceCount; i++){
			this.count.incCount();
			Utils.sleep(5);
		}
	}
}

class Consumer implements Runnable{

	private ICount count;
	private int consumerCount;

	public Consumer(ICount count, int consumerCount) {
		this.count = count;
		this.consumerCount = consumerCount;
	}

	@Override
	public void run() {

		for(int i = 0; i < this.consumerCount; i++){
			this.count.decCount();
			Utils.sleep(10);
		}
	}
	
}