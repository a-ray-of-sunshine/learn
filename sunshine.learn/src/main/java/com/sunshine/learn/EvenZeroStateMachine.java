package com.sunshine.learn;

import java.util.Random;

// 偶数个0的状态机
public class EvenZeroStateMachine {
	
	
	public static void main(String[] args) {
		
		EvenZeroStateMachine ezsm = new EvenZeroStateMachine();
		Random seed = new Random();
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 10; i++){
			int num = seed.nextInt(2);
			sb.append(num);
			ezsm.handleStatus(num);
			System.out.println(String.format("binary string: %s, isEvenZero: %s", sb.toString(), ezsm.isEvenZero()));
		}
		
	}
	
	// 初始化状态
	private int status = Acceptor.ACCEPT.ordinal();
	
	public void handleStatus(int num){
	
		// 统计的是偶数个0的状态机
		// 所以外部输入为 1 时，不会对状态机产生影响
		if(num == 1){
			// 则状态不变
			// status = Acceptor.ACCEPT.ordinal();
			return;
		}
		
		// 输入为 0 时。
		if(status == Acceptor.ACCEPT.ordinal()){
			status = Acceptor.REJECT.ordinal();
		}else{
			status = Acceptor.ACCEPT.ordinal();
		}
	}

	public boolean isEvenZero(){
		return status == Acceptor.ACCEPT.ordinal();
	}
}

enum Acceptor{
	ACCEPT,
	REJECT
}