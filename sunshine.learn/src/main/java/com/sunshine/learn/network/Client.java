package com.sunshine.learn.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class Client {

	public static void main(String[] args) throws IOException {
		
		ByteBuffer bb = ByteBuffer.allocate(1024 * 20);
		FileChannel file = FileChannel.open(Paths.get("C:\\Users\\Administrator\\Downloads\\examples.zip"));
		file.read(bb);
		
		try {
			Socket socket = new Socket("127.0.0.1", 9999);
			socket.setTcpNoDelay(true);
			OutputStream outputStream = socket.getOutputStream();
			
			// outputStream.write(bb.array());
			// file.close();
			
			LineNumberReader lnr = new LineNumberReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
			String line;
			while(null != (line = lnr.readLine())){
				// System.out.println(line);

				outputStream.write(line.getBytes());
				outputStream.write('\n');
			}
			
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}