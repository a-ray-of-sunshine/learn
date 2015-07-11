package com.sunshine.learn.thread;

import java.util.Random;

@SuppressWarnings(value={"unused"})
public class ThreadTest
{
	private static Object syncObject = new Object();
	private static Object waitObject = new Object();
	private int globalResource = 0;
	Random random = new Random();
	boolean isRun = false;
	
	public static void main(String[] args)
	{
//		synchronized (syncObject)
//		{
//			System.out.println("hello");
//		}
		ThreadTest test = new ThreadTest();
		WriteThread write = test.new WriteThread("write thread");
		ReadThread read = test.new ReadThread("read thread");
		read.start();
		write.start();
//		write.start();
//		read.start();
	}
	
class WriteThread extends Thread{
		
		public WriteThread(String name){
			super(name);
		}
		
		@Override
		public void run()
		{
			// ʹ������ʱ�����
			try
			{
				sleep(Math.abs(random.nextInt(1000)));
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			
			// ��ʾ����Ĵ������һ���ٽ���䣬��ʵ���൱��syncObject��һ����
			// ����д�߳�����֮���������߳�˭ӵ��������� ���ͬ����Ĵ���ͻ�ִ��
			// ֱ��ӵ������̵߳�ͬ�������ִ����ϣ������ͱ��ͷ��ˣ������߳̾Ϳ��Ի�ø���
			// Ȼ��ִ�иô����
			synchronized (waitObject)
			{
				for(int i = 0; i < 100; i++){
					try
					{
						globalResource = i;
						sleep(20);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				// �����д�������֮��֪ͨ�������߳̿�ʼ��ȡ���
				// �������߳��ȵõ���syncObject�����ʱ����û��
				// �߳��� waitObject �����Ϸ��� wait��
				isRun = true;
				waitObject.notify();
			}	
		}
	}
	
	class ReadThread extends Thread{
		
		public ReadThread(String name){
			super(name);
		}
		
		@Override
		public void run()
		{
			// ʹ������ʱ�����
			try
			{
				sleep(Math.abs(random.nextInt(1000)));
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			
			
				try
				{
					// ���û��׼���ã�����������ȴ�
					// �������߳��ȵõ������ʱ�ͻᷢ������
					synchronized (waitObject)
					{
						if(!isRun){// isRun ��ʾд����ʱ���Ѿ���ɣ����û���������������߳̾�wait
							waitObject.wait();
						}
						for(int i = 0; i < 100; i++){
								sleep(10);
								System.out.println(globalResource);
						}
					}
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			
		}
	}
	
	/*class WriteThread extends Thread{
		
		public WriteThread(String name){
			super(name);
		}
		
		@Override
		public void run()
		{
			// ʹ������ʱ�����
			try
			{
				sleep(Math.abs(random.nextInt(1000)));
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			
			// ��ʾ����Ĵ������һ���ٽ���䣬��ʵ���൱��syncObject��һ����
			// ����д�߳�����֮���������߳�˭ӵ��������� ���ͬ����Ĵ���ͻ�ִ��
			// ֱ��ӵ������̵߳�ͬ�������ִ����ϣ������ͱ��ͷ��ˣ������߳̾Ϳ��Ի�ø���
			// Ȼ��ִ�иô����
			synchronized (syncObject)
			{
				for(int i = 0; i < 100; i++){
					try
					{
						globalResource = i;
						sleep(20);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					
				}
				// �����д�������֮��֪ͨ�������߳̿�ʼ��ȡ���
				// �������߳��ȵõ���syncObject�����ʱ����û��
				// �߳��� waitObject �����Ϸ��� wait��
				waitObject.notify();
			}	
		}
	}
	
	class ReadThread extends Thread{
		
		public ReadThread(String name){
			super(name);
		}
		
		@Override
		public void run()
		{
			// ʹ������ʱ�����
			try
			{
				sleep(Math.abs(random.nextInt(1000)));
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			
			synchronized (syncObject)
			{
				try
				{
					// ���û��׼���ã�����������ȴ�
					// �������߳��ȵõ������ʱ�ͻᷢ������
					waitObject.wait();
					for(int i = 0; i < 100; i++){
							sleep(10);
							System.out.println(globalResource);
					}
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}*/
}


