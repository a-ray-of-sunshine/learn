package com.sunshine.learn.thread;

import org.junit.Test;

import com.sunshine.learn.thread.lock.BlockingQueue;

public class BlockingQueueTest {

	@Test
	public void test(){
		BlockingQueue queue = new BlockingQueue();
		
		queue.put(123);
		queue.put("hello");

		// 获取到 123
		System.out.println(queue.get());
		
		// 获取到 hello
		System.out.println(queue.get());
		
		// 没有元素了，线程阻塞
		// System.out.println(queue.get());
	}
}
