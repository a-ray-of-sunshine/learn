package com.sunshine.learn.thread.conpro;

import com.sunshine.learn.utils.Utils;

public class Producer implements Runnable{

	private Resource resouce;

	public Producer(Resource resouce) {
		this.resouce = resouce;
	}

	@Override
	public void run() {

		while(true){
			
		synchronized (resouce) {
			// 如果已经生产过了,则不需要再生产
			while(resouce.isProcude()){
				try {
					resouce.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			Utils.sleep(234);
			// 生产资源
			resouce.produceResource();
			resouce.setProcude(true);
			resouce.notify();
		}

	}
		}
}
