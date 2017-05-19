package com.sunshine.learn.base;

public class FinalTest {

	public static void main(String[] args) {
		
		int num = 10;
		
		for(int i = 0; i < num; i++){
			int stat = i;
			try{
				System.out.println("try: " + i);
			}finally{
				System.out.println("finally: " + stat);
			}
		}
		
		
		System.out.println("main after.");
	}
	
	public int test(int a, String name){
		
		int i1 = 123;
		int i2 = 456;
		int i3 = 789;
		
		fun(i1, i2, i3);
		
		{
			long l1 = 9999L;
		}
		
		// long l2 = 789788L;
		 int i4 = Integer.MAX_VALUE;
		
		return a;
	}
	
	private void fun(int i1, int i2, int i3){
		int A = i1;
	}
	
	public void spin(int i, int grain){

		Object o = new 
			/**/	Object();
		
	}
	
}
