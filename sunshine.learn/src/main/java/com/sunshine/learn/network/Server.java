package com.sunshine.learn.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {
			ServerSocket server = new  ServerSocket(1234);
			
			boolean flag = true;
			while(flag){
				// 返回的一个socket就代表一个tcp连接
				// 这个socket表示远程有一个client连接到本机的socket
				// 如果下面收到的 socket， 后续的请求处理，是阻塞的，那么只有
				// 当这个阻塞的 socket 完成处理之后（请求关闭），
				// server.accept() 方法才会继续得到调用 client2 的请求才能够得到处理
				// 这样就使得通信非常的慢
				Socket accept = server.accept();
				accept.setTcpNoDelay(true);
				InputStream inputStream = accept.getInputStream();
				System.out.println(server.getInetAddress() + ":" + server.getLocalPort());
				System.out.println(accept.getInetAddress() + ":" + accept.getPort() + ":" + accept.getLocalPort());

				int c = 0;
				int j = 0;
				try {
					while(-1 != (c = inputStream.read())){
						System.out.print(++j + ":" + Integer.toHexString(c) + " ");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println(accept);
			
				accept.close();
			}
			
			server.close();

	}

}