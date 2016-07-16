package com.sunshine.learn.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import static org.junit.Assert.*;

public class AtomicIntegerTest {

	/**
	 * 多次调用验证是否线程安全
	 * @throws InterruptedException
	 */
	@Test
	public void testInc() throws InterruptedException{
	
		// 逐步增大  testCount 的值 assert 失败越快
		int testCount = 15;
		for(int i = 0; i < testCount; i++){
			assertEquals(true, this.testint());
		}

	}
	
	/**
	 * 启动 threadSize 个线程，对 整数 value 进行 value++ 操作
	 * @return
	 * @throws InterruptedException
	 */
	public boolean testint() throws InterruptedException{
		
		final NumberHolder count = new NumberHolder(); 
		count.setValue(0);
		
		final int threadSize = 100;
		Thread[] ts = new Thread[threadSize];
		for(int i = 0; i < threadSize; i++){
			ts[i] = new Thread(){
				public void run(){
					count.incValue();
				}
			};
		}
		
		for(Thread t : ts){
			t.start();
		}

		for(Thread t : ts){
			t.join();
		}

		// 通过多次运行，出现错误： java.lang.AssertionError: expected:<100> but was:<99>
		// 说明 i++ 并不是线程安全的
		// assertEquals(threadSize, count.getValue());
		return threadSize == count.getValue();

	}

	// @Test
	public void testAll() throws InterruptedException{
		
		final AtomicInteger value = new AtomicInteger(10);

		assertEquals(value.compareAndSet(1, 2), false);
		assertEquals(value.get(), 10);
		assertEquals(value.compareAndSet(10, 2), true);
		assertEquals(value.get(), 2);
		
		value.set(0);
		
		assertEquals(value.incrementAndGet(), 1);
		assertEquals(value.getAndAdd(2), 1);
		assertEquals(value.getAndSet(5), 3);
		assertEquals(value.get(), 5);
		
		final int threadSize = 10;
		Thread[] ts = new Thread[threadSize];
		for(int i = 0; i < threadSize; i++){
			ts[i] = new Thread(){
				public void run(){
					value.incrementAndGet();
				}
			};
		}
		
		for(Thread t : ts){
			t.start();
		}

		for(Thread t : ts){
			t.join();
		}
		
		assertEquals(value.get(), 5 + threadSize);
	}
}

class NumberHolder{

	// volatile 内存可见性，和同步是完全不同的
	// volatile 可以保证可见性，但仍不能使得 value++ 成为原子操作
	private volatile int  value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void incValue(){
		this.value++;
	}
}
