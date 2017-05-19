package com.sunshine.learn.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPServer {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket server = new DatagramSocket(1234);
		byte[] buffer = new byte[1024];
		
		DatagramPacket p = new DatagramPacket(buffer, buffer.length);
		boolean flag = true;
		
		while(flag){
			server.receive(p);
			String line = new String(p.getData());
			System.out.println("host: " + p.getAddress().getHostAddress() + " port: " + p.getPort() + " data: " + line);

			// 重置缓冲区, 使得 DatagramPacket 可以被复用
			Arrays.fill(buffer, (byte)0);
		}
		
		server.close();
		
	}
}
