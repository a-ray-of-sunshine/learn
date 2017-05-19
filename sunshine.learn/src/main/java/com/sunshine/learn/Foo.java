package com.sunshine.learn;

public class Foo {

	public void foo() {
		System.out.println("com.sunshine.learn");
		
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int e = 123;
		
		throw new RuntimeException("runtime");
		
	}
}
