package com.sunshine.learn.base;

import org.junit.Test;

public class BitOper {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);

	// @Test
	public void test(){
		
		int c = SHARED_UNIT + 7682;
		System.out.println(c);

		c = sharedCount(c);
		
		c += SHARED_UNIT;
		
		System.out.println(c);
	}
	
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }

    /** Node represents an unfulfilled consumer */
    static final int REQUEST    = 0;
    /** Node represents an unfulfilled producer */
    static final int DATA       = 1;
    /** Node is fulfilling another unfulfilled DATA or REQUEST */
    static final int FULFILLING = 2;

    static boolean isFulfilling(int m) { return (m & FULFILLING) != 0; }
 
    @Test
    public void test1(){
    	
    	System.out.println(isFulfilling(REQUEST)); // false
    	System.out.println(isFulfilling(DATA)); // false
    	System.out.println(isFulfilling(FULFILLING)); // true

    	System.out.println(isFulfilling(FULFILLING | REQUEST)); // true
    	System.out.println(isFulfilling(FULFILLING | DATA)); // true

    	System.out.println(FULFILLING | REQUEST); // 2
    	System.out.println(FULFILLING | DATA); // 3
    	System.out.println(FULFILLING | FULFILLING); // 2
    }
}
