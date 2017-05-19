package com.sunshine.learn.collection;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AIOTest {

	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("/msdia80.dll");
		
		AsynchronousFileChannel afc = AsynchronousFileChannel.open(path);
		
		// 建立一个 100KB 的缓冲区
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024); 

		// 异步read,并不会阻塞当前线程
		// 此时, 任务已经被 AsynchronousFileChannel 内部持有的线程池开始
		// 调度执行了
		Future<Integer> future = afc.read(buffer, 0L);
		
		IOTask<Integer> task = new IOTask<Integer>(future, buffer);
		
		// 这里可以直接使用线程，也可以考虑使用线程池
		Thread iothread = new Thread(task);
		iothread.setName("io-thread");
		iothread.start();

		System.out.println(Thread.currentThread() + ": =======main=======");
		
		test();
	}
	
	private static void test() throws IOException{

		Path path = Paths.get("/msdia80.dll");
		
		AsynchronousFileChannel afc = AsynchronousFileChannel.open(path);
		
		// 建立一个 100KB 的缓冲区
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024); 
		
		// 建立 handler
		CompletionHandler<Integer, Buffer> handler = new Handler();
		
		// handler 将共享 afc 内部持有的 线程池
		// 也就是说 afc 执行完毕 read 之后将调用 handler
		afc.read(buffer, 0, buffer, handler);
		
		System.out.println(Thread.currentThread() + ": =======main=======");
	}
}

class IOTask<V> implements Runnable {
	
	private Future<V> future;
	private Buffer buffer;
	
	public IOTask(Future<V> future, Buffer buffer) {
		this.future = future;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		
		try {
			System.out.println(Thread.currentThread() + " before: " + buffer.remaining());

			// 等待异步任务的结束
			future.get();
			
			// 任务结束, buffer中存储着返回的数据
			// 可以使用 buffer 了
			System.out.println(Thread.currentThread() + " after: " + buffer.remaining());

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class Handler implements CompletionHandler<Integer, Buffer>{

	@Override
	public void completed(Integer result, Buffer attachment) {
		System.out.println(Thread.currentThread() + " after: " + attachment);
	}

	@Override
	public void failed(Throwable exc, Buffer attachment) {
		System.out.println(Thread.currentThread() + " after: " + exc);
	}
}