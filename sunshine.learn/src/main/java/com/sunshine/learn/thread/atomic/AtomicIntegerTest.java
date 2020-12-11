package com.sunshine.learn.thread.atomic;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

public class AtomicIntegerTest {

	/**
	 * 多次调用验证是否线程安全
	 * @throws InterruptedException
	 */
	@Test
	public void testInc() throws InterruptedException{
	
		// 逐步增大  testCount 的值 assert 失败越快.
		// AtomicNumberHolder 的 value 改由 AtomicInteger 实现
		// 无论将 testCount 增大在多少，都不会失败 , AtomicInteger 实现了原子操作
		int testCount = 500;
		for(int i = 0; i < testCount; i++){
			// NumberHolder count = new AtomicNumberHolder();
			// NativeNumberHolder 实现中使用i++线程不安全
			NumberHolder count = new NativeNumberHolder();
			this.testint(count);
		}

	}
	
	/**
	 * 启动 threadSize 个线程，对 整数 value 进行 value++ 操作
	 * @return
	 * @throws InterruptedException
	 * @param count
	 */
	public boolean testint(NumberHolder count) throws InterruptedException{
		
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

class AtomicNumberHolder implements NumberHolder {

	private AtomicInteger value;

	public AtomicNumberHolder(){
		this.value = new AtomicInteger();
	}

	@Override
	public int getValue() {
		return value.get();
	}

	@Override
	public void setValue(int value) {
		this.value.set(value);
	}

	@Override
	public void incValue(){
		this.value.incrementAndGet();
	}
}

class NativeNumberHolder implements NumberHolder {

	private int value;

	@Override
	public int getValue() {
		return 0;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void incValue() {
		value++;
	}
}
