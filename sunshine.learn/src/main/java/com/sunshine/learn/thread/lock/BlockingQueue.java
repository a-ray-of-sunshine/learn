package com.sunshine.learn.thread.lock;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {

	private static final int capacity = 10;
	private Deque<Object> queue = new LinkedList<Object>();
	private Lock lock = new ReentrantLock();

	// 表示 當前隊列 不滿 的 條件
	private Condition notFull = lock.newCondition();

	// 表示 當前隊列 不空 的條件
	private Condition notEmpty = lock.newCondition();

	public void put(Object ele) {
		lock.lock();

		int size = queue.size();
		try {
			if (size >= capacity) { // 当前存在的元素超出容量了
				// 等待 直到當前隊列 不滿為止
				notFull.await();
			}

			queue.push(ele);
			notEmpty.signal();

		} catch (InterruptedException e) {
			throw new RuntimeException("添加元素失敗，隊列憶滿");
		} finally {
			lock.unlock();
		}

	}

	public Object get() {

		Object ret = null;

		lock.lock();

		int size = queue.size();
		try {
			if (size == 0) { // 当前隊列不存在元素
				// 等待直到 當前隊列 不空 為止
				notEmpty.await();
			}

			ret = queue.pop();
			notFull.signal();

		} catch (InterruptedException e) {
			throw new RuntimeException("获取元素失敗，隊列为空");
		} finally {
			lock.unlock();
		}

		return ret;

	}

}
