package com.sunshine.learn.thread.conpro;

import com.sunshine.learn.utils.Utils;

public class Consumer implements Runnable {

	private Resource resouce;

	public Consumer(Resource resouce) {
		this.resouce = resouce;
	}

	@Override
	public void run() {
		
		while(true){
			
			// Utils.sleep(567);
		synchronized (resouce) {
			// 还未生产数据，需要进行等
			while(!resouce.isProcude()){
				try {
					resouce.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			Utils.sleep(123);
			System.out.println(resouce.consumeResource());
			resouce.setProcude(false);
			resouce.notifyAll();
		}
	}
		}

}
