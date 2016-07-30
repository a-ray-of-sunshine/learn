package com.sunshine.learn.thread;

import java.util.concurrent.CountDownLatch;

import com.sunshine.learn.utils.Utils;

public class CountDownLatchTest {

	public static void main(String[] args) {
		int workerNum = 10;
		CountDownLatch lock = new CountDownLatch(1);
		
		for(int i = 0; i < workerNum; i++){
			Thread worker = new Thread(new Worker(lock));
			worker.start();
		}
		
		Thread driver = new Thread(new Driver(lock));
		driver.start();
	}
}

class Driver implements Runnable{

	private CountDownLatch lock;

	public Driver(CountDownLatch lock) {
		this.lock = lock;
	}

	@Override
	public void run() {

		System.out.println("the driver is start...");
		Utils.sleep(5000);
		System.out.println("the driver start done...");
		
		// 驱动启动完成之后，通知所有worker线程
		lock.countDown();

	}
}

class Worker implements Runnable{

	private CountDownLatch lock;
	
	public Worker(CountDownLatch lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		
		try {
			lock.await();
			String workerName = Thread.currentThread().getName();
			System.out.println(workerName + " is working...");
			Utils.sleep(Utils.randInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}