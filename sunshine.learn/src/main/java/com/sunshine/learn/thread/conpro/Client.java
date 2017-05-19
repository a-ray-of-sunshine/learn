package com.sunshine.learn.thread.conpro;

public class Client {
	
	public static void main(String[] args) {
		
		Resource resource = new Resource();
		
		Thread t1 = new Thread(new Producer(resource));
		Thread t3 = new Thread(new Producer(resource));
		Thread t2 = new Thread(new Consumer(resource));
		
		t1.start();
		t2.start();
		t3.start();
		
		boolean a;
		if(System.out.printf("a") == null){
			System.out.println("a");
			
		}else{
			System.out.println("b");
		}
	}
	
}
