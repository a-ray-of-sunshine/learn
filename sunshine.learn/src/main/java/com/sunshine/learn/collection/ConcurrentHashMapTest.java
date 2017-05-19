package com.sunshine.learn.collection;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sunshine.learn.utils.Utils;

public class ConcurrentHashMapTest {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) throws IOException {

		ConcurrentMap map = new ConcurrentHashMap();
		map.values();
		
		Map map1 = new HashMap();

		ArrayBlockingQueue queue = new ArrayBlockingQueue(10);
		// ArrayList

		// ArrayBlockingQueue
		// LinkedBlockingQueue
		// SynchronousQueue
		// PriorityBlockingQueue
		// ByteArrayInputStream
		// Executors
		
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("list1");
		q.offer("list2");
		q.offer("list3");
		q.offer("list4");
		q.offer("list5");
		
		System.out.println(q.size());
		
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("123");
		list.add("456");
		list.add("789");
		
		List<String> subList = list.subList(0, list.size());
		subList.add("999");
		System.out.println(list);
		subList.add("000");
		list.add("111");

		// GBK
		String str = "丂丄丅丆丏丒丗丟丠両丣並丩丮丯丱丳丵";
		byte[] bytes = str.getBytes(StandardCharsets.UTF_16);
		printBytes(bytes);
		printBytes(str.getBytes(Charset.forName("GBK")));
		
		// Console
		// RandomAccessFile
		// StreamTokenizer
		System.out.println(System.console());
		
		StringReader sr = new StringReader("你好");
		System.out.println(sr.read());
		System.out.println(Integer.toHexString(sr.read()));
		}
	
	private static void printBytes(byte[] bytes){
		for(int i = 0; i < bytes.length; i++){
			byte b = bytes[i];
			System.out.print(Integer.toHexString(0xff & b));
			System.out.print(" ");
		}
		System.out.println();

	}

}
