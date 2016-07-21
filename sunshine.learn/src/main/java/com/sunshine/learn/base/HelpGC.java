package com.sunshine.learn.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HelpGC {

	@Test
	public void test(){
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("abc", "000");
		map.put("def", "111");
		map.put("ghi", "222");
		
		System.out.println(map);

		Map<String, String> mapother = map;
		
		System.out.println(mapother);
		
		// help for GC
		// 不会使得 map 指向的内存，被回收
		mapother = null;
		System.out.println(mapother);

		System.out.println(map);
		System.out.println(map.get("def"));
		
	}
}
