package com.sunshine.learn.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class UDPClient {

	public static void main(String[] args) throws IOException {

		DatagramSocket client = new DatagramSocket();
		
		sendFile(client);

		LineNumberReader lnr = new LineNumberReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		String line;
		while(null != (line = lnr.readLine())){

			byte[] bytes = line.getBytes();
			DatagramPacket p = new DatagramPacket(bytes, bytes.length);

			SocketAddress address = new InetSocketAddress("127.0.0.1", 1234);
			p.setSocketAddress(address );

			client.send(p);
		}	
		
		client.close();
		
	}
	
	private static void sendFile(DatagramSocket client) throws IOException{

		ByteBuffer bb = ByteBuffer.allocate(1024 * 20);
		FileChannel file = FileChannel.open(Paths.get("C:\\Users\\Administrator\\Downloads\\examples.zip"));
		file.read(bb);
			
		byte[] bytes = bb.array();
		
		DatagramPacket p = new DatagramPacket(bytes, bytes.length);
		SocketAddress address = new InetSocketAddress("127.0.0.1", 1234);
		p.setSocketAddress(address);	

		client.send(p);
	}
}
