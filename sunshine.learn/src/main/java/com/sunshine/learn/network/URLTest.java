package com.sunshine.learn.network;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		
		String spec = "http://www.baidu.com:8080/?q=baidu&val=test#loc=mid";
		try {
			URL url = new URL(spec);
			System.out.println(url.getProtocol());
			System.out.println(url.getHost());
			System.out.println(url.getPort());
			System.out.println(url.getPath());
			System.out.println(url.getQuery());
			System.out.println(url.getRef());
			
			System.out.println("===============");
			
			// URI uri = url.toURI();
			URI uri =  new URI(spec);
			System.out.println(uri.getScheme());
			System.out.println(uri.getHost());
			System.out.println(uri.getPort());
			System.out.println(uri.getPath());
			System.out.println(uri.getQuery());
			System.out.println(uri.getFragment());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
}
