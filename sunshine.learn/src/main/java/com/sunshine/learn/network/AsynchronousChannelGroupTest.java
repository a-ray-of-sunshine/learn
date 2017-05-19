package com.sunshine.learn.network;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;

public class AsynchronousChannelGroupTest {

	public static void main(String[] args) {
		
		System.setProperty("java.nio.channels.DefaultThreadPool.threadFactory", "com.sunshine.learn.network.MyThreadFactory");

		try {
			AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println("........");
		
	}
}
