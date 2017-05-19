package com.sunshine.learn.collection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class BufferTest {

	public static void main(String[] args) throws IOException {
		
		FileChannel fc = FileChannel.open(Paths.get("/msdia80.dll"));
		
		ByteBuffer bb = ByteBuffer.allocate(1024);
		int i = 0;
		int n = 0;
		while(-1 != (n = fc.read(bb))){
			System.out.println(++i + ": " + bb + " size:" + n);
			//bb.clear();
		}
		// HashMap
		// FileInputStream
		// DirectBuffer
		// NativeDispatcher
		// FileDispatcher
		
	}
}
