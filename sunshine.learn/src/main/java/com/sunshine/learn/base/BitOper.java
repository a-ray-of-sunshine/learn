package com.sunshine.learn.base;

import org.junit.Test;

public class BitOper {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);

	@Test
	public void test(){
		
		int c = SHARED_UNIT + 7682;
		System.out.println(c);

		c = sharedCount(c);
		
		c += SHARED_UNIT;
		
		System.out.println(c);
	}
	
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
}
