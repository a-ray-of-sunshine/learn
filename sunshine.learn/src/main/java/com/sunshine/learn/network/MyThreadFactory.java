package com.sunshine.learn.network;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

	private static int no;

	@Override
	public Thread newThread(Runnable r) {

		Thread t = new Thread(r, "AsynchronousChannelThreadPool-" + no++);
		t.setDaemon(true);
		
		System.out.println(t.getName() + ": " + r);

		return t;
	}

}
