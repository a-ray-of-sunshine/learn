package com.sunshine.learn.thread.atomic;

import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.sunshine.learn.thread.lock.MessageCountCLHLock;
import com.sunshine.learn.thread.lock.MessageCountTicketLock;
import com.sunshine.learn.utils.Utils;

public class MessageProcessSystem {

	@Test
	public void testSystem(){
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < 100; i++){
			this.messageProcess();
			// this.messageProcessSync();
			System.out.println("第" + i + "测试成功.");
		}
		long end = System.currentTimeMillis();
		System.out.println("总共用时：" + (end - start) / 1000 + " 秒.");
	}
	
	private void messageProcess(){

		// 0. MessageCount类没有任何同步处理，下面的 assert 有可能失败
		// ICount count = new MessageCount();
		
		// 1. 使用 synchronized
		// ICount count = new MessageCountSync();
		
		// 2. 使用 Atomic 变量，不需要同步
		// ICount count = new MessageCountAtomic();
		
		// 3. 使用 Lock 进行同步
		// ICount count = new AtomicIntegerWithLock();
		
		// 4. 使用 自旋锁
		// ICount count = new MessageCountSpinLock();
		
		// 5. 使用公平的自旋锁
		// ICount count = new MessageCountTicketLock();
		
		// 6. 使用 CLH 锁
		ICount count = new MessageCountCLHLock();

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
	
	private void messageProcessSync(){

		// 0. MessageCount类没有任何同步处理，
		// ProduceSync 和 ConsumerSync 分别进行了同步处理，所以
		// 使用 MessageCount 计数，也不会出现并发问题了。
		// ICount count = new MessageCount();
		
		// 1. 使用 synchronized
		// ICount count = new MessageCountSync();
		
		// 2. 使用 Atomic 变量，不需要同步
		ICount count = new MessageCountAtomic();

		int produceCount = Utils.randInt(100);
		int consumerCount = Utils.randInt(produceCount + 1);

		ProduceSync produce = new ProduceSync(count, produceCount);
		ConsumerSync consumer = new ConsumerSync(count, consumerCount);
		
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

class ProduceSync implements Runnable {
	
	private ICount count;
	private int produceCount;

	public ProduceSync(ICount count, int produceCount) {
		this.count = count;
		this.produceCount = produceCount;
	}

	@Override
	public void run() {
		
		for(int i = 0; i < this.produceCount; i++){
			synchronized (count) {
				this.count.incCount();
			}
			Utils.sleep(5);
		}
	}
}

class ConsumerSync implements Runnable{

	private ICount count;
	private int consumerCount;

	public ConsumerSync(ICount count, int consumerCount) {
		this.count = count;
		this.consumerCount = consumerCount;
	}

	@Override
	public void run() {

		for(int i = 0; i < this.consumerCount; i++){
			synchronized (count) {
				this.count.decCount();
			}
			Utils.sleep(10);
		}
	}
	
}