package com.sunshine.learn.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.sunshine.learn.utils.Utils;

public class SynchronousQueueTest {

	public static void main(String[] args) {
		BlockingQueue<Data> queue = new SynchronousQueue<Data>();
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		Thread pt = new Thread(producer);
		Thread ct = new Thread(consumer);
		
		pt.start();
		ct.start();
	}
}

class Producer implements Runnable{
	
	BlockingQueue<Data> queue;

	public Producer(BlockingQueue<Data> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		int count = 0;
		while(true){
			Utils.sleep(Utils.randInt(1000));
			Data data = new Data();
			data.setCount(count);
			try {
				this.queue.put(data);
				System.out.println("producer data: " + count);
				count++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class Consumer implements Runnable{
	
	BlockingQueue<Data> queue;
	
	public Consumer(BlockingQueue<Data> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true){
			Data data;
			try {
				data = this.queue.take();
				System.out.println("consumer data: " + data.getCount());
				Utils.sleep(Utils.randInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class Data{
	int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}