package com.sunshine.learn.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.junit.Assert;
import org.junit.Test;

import com.sunshine.learn.utils.Utils;

public class AtomicIntegerFieldUpdaterTest {

	@Test
	public void test(){
		
		for(int i = 0; i < 100; i++){
			this.doInc();
			System.out.println("第" + i + "测试成功.");
		}
	}

	private void doInc(){

		final AtomicIntegerFieldUpdater<VolatileObj> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(VolatileObj.class, "count");

		final VolatileObj obj = new VolatileObj();
		
		int threadSize = 100;
		Thread[] threads = new Thread[threadSize];
		for(int i = 0; i < threadSize; i++){
			threads[i] = new Thread(){
				@Override
				public void run() {
					
					// 需要同步，否则下面的 assert 将失败
					// obj.setCount(obj.getCount()+1);
					
					// 使用 AtomicIntegerFieldUpdater 可以对 类的  public volatile int 的变量进行同步修改
					fieldUpdater.incrementAndGet(obj);
				}
			};
		}
		
		Utils.start(threads);
		Utils.join(threads);
		
		Assert.assertEquals(threadSize, obj.getCount());
		
	}
}

class VolatileObj{
	
	public volatile int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}