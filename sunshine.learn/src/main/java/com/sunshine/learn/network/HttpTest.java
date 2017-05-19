package com.sunshine.learn.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpTest {
	
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.cnblogs.com/");
			
			System.out.println(url.getPath());
			System.out.println(url.getFile());
			System.out.println(url.getDefaultPort());
			
			URLConnection connection = url.openConnection();
			System.out.println(connection.getConnectTimeout());
			System.out.println(connection.getReadTimeout());
			connection.connect();
			
			
			InputStream inputStream = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

			String line = "";
			while(null != (line = br.readLine())){
				System.out.println(line);
			}
			
		//	inputStream.close();
			
			HttpURLConnection hconn = (HttpURLConnection) connection;
			System.out.println(hconn.getHeaderField("Connection"));
			hconn.disconnect();
			
			// URLStreamHandler

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
