package com.sunshine.learn.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings(value={"unused"})
public class ChannelTest
{

	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("src/test/data.txt");
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		
		FileChannel fileChannel = raf.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(15);
		try
		{
			fileChannel.read(buffer);
			printByteBuffer(buffer);
			System.out.println(fileChannel.position());

			String str = "沉默的羔羊";
			ByteBuffer outBuffer = Charset.forName("utf-8").encode(str);
			fileChannel.position(raf.length());
//			fileChannel.write(outBuffer);
			System.out.println(fileChannel.position());
			
			fileChannel.position(25);
			buffer.clear();
			fileChannel.read(buffer);
			printByteBuffer(buffer);
			System.out.println(fileChannel.position());
			
			Lock lock = new ReentrantLock();
			lock.lock();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void printByteBuffer(ByteBuffer buffer){
//		CharBuffer charBuffer = buffer.asCharBuffer();
		Charset cs = Charset.forName("utf-8");
		String str = new String(buffer.array(), cs);
		
		System.out.println(str);
	}
	
}
