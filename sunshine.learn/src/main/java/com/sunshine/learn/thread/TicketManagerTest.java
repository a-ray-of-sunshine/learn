package com.sunshine.learn.thread;

import com.sunshine.learn.thread.lock.TicketManager;
import com.sunshine.learn.utils.Utils;

public class TicketManagerTest {

	public static void main(String[] args) {
		TicketManager ticket = new TicketManager(100); 
		
		Thread takeTicket = new Thread(new TakeTicket(ticket));
		takeTicket.setName("takeTicket");
		takeTicket.start();
		// CountDownLatch	
		// CyclicBarrier
		for(int i = 0; i < 10; i++){
			Thread read = new Thread(new ShowTicket(ticket));
			read.start();
		}
	}
}


class TakeTicket implements Runnable{

	private TicketManager ticketManager;

	public TakeTicket(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	@Override
	public void run() {
		while(true){
			Utils.sleep(Utils.randInt(1000));
			this.ticketManager.takeTicket();
		}
	}
}

class ShowTicket implements Runnable {

	private TicketManager ticketManager;

	public ShowTicket(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	@Override
	public void run() {
		while(true){
			Utils.sleep(Utils.randInt(1000));
			int num = this.ticketManager.getAvailableTicketNum();
			System.out.println(Thread.currentThread().getName() + " == 剩余的票数：" + num);
		}
	}
}
