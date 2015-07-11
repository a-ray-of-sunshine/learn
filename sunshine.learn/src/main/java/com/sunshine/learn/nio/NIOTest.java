﻿package com.sunshine.learn.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

public class NIOTest
{
	public static long checksumInputStream(String filename) throws IOException
	    {
	       InputStream in = new FileInputStream(filename);
	       CRC32 crc = new CRC32();
	
	       int c;
	       while ((c = in.read()) != -1)
	          crc.update(c);
	       return crc.getValue();
	    }
	
	    public static long checksumBufferedInputStream(String filename) throws IOException
	    {
	       InputStream in = new BufferedInputStream(new FileInputStream(filename));
	       CRC32 crc = new CRC32();
	
	       int c;
	       while ((c = in.read()) != -1)
	          crc.update(c);
	       return crc.getValue();
	    }
	
	    public static long checksumRandomAccessFile(String filename) throws IOException
	    {
	       RandomAccessFile file = new RandomAccessFile(filename, "r");
	       long length = file.length();
	       CRC32 crc = new CRC32();
	
	       for (long p = 0; p < length; p++)
	       {
	          file.seek(p);
	          int c = file.readByte();
	          crc.update(c);
	       }
	       return crc.getValue();
	    }
	
	    public static long checksumMappedFile(String filename) throws IOException
	    {
	       FileInputStream in = new FileInputStream(filename);
	       FileChannel channel = in.getChannel();
	
	       CRC32 crc = new CRC32();
	       int length = (int) channel.size();
	       MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
	
	       for (int p = 0; p < length; p++)
	       {
	          int c = buffer.get(p);
	          crc.update(c);
	       }
	       return crc.getValue();
	    }
	
	    public static void main(String[] args) throws IOException
	    {
	    	// WPSDKv71_chs1.iso
	    	// SQLFULL_CHS.iso
	    	// /vs/GRMSDK_EN_DVD.iso AnkhSvn-2.5.12582.msi
	    	String fileName = "F:/software/vs/GRMSDK_EN_DVD.iso";
	    	
//	       System.out.print("Input Stream:\t");
//	       long start = System.currentTimeMillis();
//	       long crcValue = checksumInputStream(fileName);
//	       long end = System.currentTimeMillis();
//	       System.out.print(Long.toHexString(crcValue) + "\t");
//	       System.out.println((end - start) + " milliseconds");
	
	    	long start = 0;
	    	long crcValue = 0;
	    	long end = 0;
	    	
	       System.out.print("Buffered Input Stream:\t");
	       start = System.currentTimeMillis();
	       crcValue = checksumBufferedInputStream(fileName);
	       end = System.currentTimeMillis();
	       System.out.print(Long.toHexString(crcValue) + "\t");
	       System.out.println((end - start) + " milliseconds");
	
//	       System.out.print("Random Access File:\t");
//	       start = System.currentTimeMillis();
//	       crcValue = checksumRandomAccessFile(fileName);
//	       end = System.currentTimeMillis();
//	       System.out.print(Long.toHexString(crcValue) + "\t");
//	       System.out.println((end - start) + " milliseconds");
	
	       System.out.print("Mapped File:\t");
	       start = System.currentTimeMillis();
	       crcValue = checksumMappedFile(fileName);
	       end = System.currentTimeMillis();
	       System.out.print(Long.toHexString(crcValue) + "\t");
	       System.out.println((end - start) + " milliseconds");
	    }

}
