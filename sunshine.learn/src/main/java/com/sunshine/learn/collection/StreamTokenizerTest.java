package com.sunshine.learn.collection;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StreamTokenizerTest {
	
	public static void main(String[] args) throws IOException {
		String str = getStr();
		
		StringReader sr = new StringReader(str);
		StreamTokenizer st  = new StreamTokenizer(sr);
		
		// ResolveToken(st);
		ResolveFunc(st);

	}
	
	private static void ResolveFunc(StreamTokenizer st) throws IOException{
		
		Deque<String> tokenStack = new LinkedList<String>();
		Deque<String> funcStack = new LinkedList<String>();
		
		int ttype;
		while(StreamTokenizer.TT_EOF != (ttype = st.nextToken())){
			
			String tokenStr = "";
			
			switch(ttype){
			case StreamTokenizer.TT_NUMBER:
				tokenStr = Double.toString(st.nval);
				System.out.print(st.nval);break;
			case StreamTokenizer.TT_WORD:
				tokenStr = st.sval;
				System.out.print(st.sval);break;
			case '\'':
				tokenStr = st.sval;
				System.out.print(st.sval);break;
			default:
				tokenStr = new String(new char[]{(char)ttype});
					System.out.print((char)ttype);
			}
			
			tokenStack.push(tokenStr);
			System.out.println(": " + st);
		}	
		
		System.out.println(tokenStack);
	}
	
	private static void ResolveToken(StreamTokenizer st) throws IOException{
		int ttype;
		while(StreamTokenizer.TT_EOF != (ttype = st.nextToken())){
			
			switch(ttype){
			case StreamTokenizer.TT_NUMBER:
				System.out.print(st.nval);break;
			case StreamTokenizer.TT_WORD:
				System.out.print(st.sval);break;
			case '\'':
				System.out.print(st.sval);break;
			default:
					System.out.print((char)ttype);
			}
			
			System.out.println(": " + st);
		}	
	}
	
	private static String getStr(){

		StringBuilder sb = new StringBuilder();
		sb.append("int main()");
		sb.append('\n');
		sb.append("{");
		sb.append('\n');
		sb.append("printf('hello,world');");
		sb.append('\n');
		sb.append("}");
		
		return sb.toString();
	}

}
