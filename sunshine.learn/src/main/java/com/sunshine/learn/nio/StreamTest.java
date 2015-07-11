package com.sunshine.learn.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings(value={"unused"})
public class StreamTest
{
	// @Test
	public static void hello()
	{
		/*System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("java.vendor"));
		System.out.println(System.getProperty("java.vendor.url"));
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("java.class.version"));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.dir"));
		
		System.out.println(System.getProperty("file.separator"));
		System.out.println(System.getProperty("path.separator"));
		System.out.println(System.getProperty("line.separator"));
		System.out.println(System.getProperty("os.name"));*/
		
		File file = new File("src/test/data.txt");
		
		System.out.println("File Name:" + file.getName());
		long size = file.length();
		System.out.println("File Length:" + size); // 总共23个字节
		
		byte[] bytes = new byte[(int) size];
		
		try
		{
			// ANSI 本地代码页，常常对应中文系统的 GBK GB2312 GB18030
			// UNICODE 对应 utf-16
			FileInputStream fis = new FileInputStream(file);
			//fis.read(bytes, 0, 2); // 读取2个字节
			//System.out.println("第一个字节：" + bytes[0]); // FF
			//System.out.println("第二个字节：" + bytes[1]); // FE 
			System.out.println("剩余字节数：" + fis.available()); // 剩余21个字节
			
			// 这里的第二个参数指明了待读取流的编码格式，
			// InputStreamReader 将把这种格式的流转换成UTF-16LE格式的字符流，
			// 也就是 java.lang.String 所支持的字符编码格式，这样 println 函数就会以默认 UTF-16LE格式来打印字符
			// 所以字符才能够正确的的被输出。
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			System.out.println("字符编码：" + isr.getEncoding());
			System.out.println(isr.read());
			System.out.println(isr.read()); // 19968 4E 00 读出的数据都被转换成 UTF-16LE的字符编码了
			System.out.println(isr.read()); // 20010 4E 2A
//			System.out.println(isr.read()); // 22909 59 7D 
			/*20010
			22909
			20154*/

			BufferedReader reader = new BufferedReader(isr);
//			reader.skip(1);
			reader.mark(0);
			reader.reset();
			String line = "";
			line = reader.readLine();
			System.out.println(line);
			
			int index = 0; 
			while(index < line.length()){
				System.out.println(line.codePointAt(index));
				index++;
			}
			
			// 下面的字符串，其格式在保存的时候应该是
			// 使用项目的配置的默认编码格式：查看文件的配置属性，
			// 工程文件右键属性：resource 有一个 text file encoding
			// 其默认是gb18030，但是这些字符串在编译的时候会被编译成UTF-16格式
			String chineseText = "我是一个中国人";
			System.out.println(chineseText);
			
//			MappedByteBuffer
//			FileChannel
			// FileInputStream
			// RandomAccessFile
			// FileOutputStream
			// FilterInputStream
			// Collections
//			HttpServletRequestWrapper
//			FacesContext
//			ExternalContext
					
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	private String[] flag = {"true"};
	
	// @Test
	public static void main(String[] args) throws InterruptedException{
		StreamTest test = new StreamTest();
		Thread thread = test.new myThread("notify thread");
		Thread waitThread0 = test.new WaitThread("waitThread0");
		Thread waitThread1 = test.new WaitThread("waitThread1");
		Thread waitThread2 = test.new WaitThread("waitThread2");
		thread.start();
		waitThread0.start();
		waitThread1.start();
		waitThread2.start();
		
//		FileChannel Impl
	}
	
	class myThread extends Thread{
		
		public myThread(String name){
			super(name);
		}
		
		@Override
		public void run()
		{
			try
			{
				Thread.sleep(3000);
				System.out.println("Thread: " + Thread.currentThread());
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			synchronized (flag)
			{
				flag[0] = "false";
				flag.notifyAll();
			}
			
		}
	}
	
	class WaitThread extends Thread{
		
		public WaitThread(String name){
			super(name);
		}
		
		@Override
		public void run()
		{
			synchronized (flag)
			{
				while(flag[0] != "false"){
					System.out.println(this.getName() + " begin waiting");
					long waitTime = System.currentTimeMillis();
					
					try
					{
						flag.wait();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
					waitTime = System.currentTimeMillis() - waitTime;
					System.out.println("wait time: " + waitTime);
				}
				System.out.println("wait end: " + this.getName());
			}
		}
	}
	
	

}

