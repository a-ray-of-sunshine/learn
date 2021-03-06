package com.sunshine.learn.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();

		// final CyclicBarrier cb = new
		// CyclicBarrier(3);//创建CyclicBarrier对象并设置3个公共屏障点

		final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ":********我最先执行***********");
			}
		});

		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有"
								+ cb.getNumberWaiting() + "个已经到达，正在等候");
						cb.await();// 到此如果没有达到公共屏障点，则该线程处于等待状态，如果达到公共屏障点则所有处于等待的线程都继续往下运行
						
						// 3线程到达 common barrier point 后
						// cb的内部状态被重置, 所以下面就可以使用继续使用了

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点2，当前已有"
								+ cb.getNumberWaiting() + "个已经到达，正在等候");
						cb.await(); // 这里CyclicBarrier对象又可以重用

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点3，当前已有"
								+ cb.getNumberWaiting() + "个已经到达，正在等候");
						cb.await();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
