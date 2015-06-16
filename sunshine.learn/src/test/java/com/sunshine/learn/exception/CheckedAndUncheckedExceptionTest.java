package com.sunshine.learn.exception;

import org.junit.Test;

public class CheckedAndUncheckedExceptionTest {

	@Test
	public void exceptionTest(){
		
		// unchecked 类型的异常可以不捕获，不做任何处理
		CheckedAndUncheckedException.findUserName("hello");
		
		// checked 类型的异常必须捕获(try...catch)或者抛出(throws)
		try {
			CheckedAndUncheckedException.findPassword("");
		} catch (PasswordIsInvalid e) {
			e.printStackTrace();
		}
	}
}
