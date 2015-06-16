package com.sunshine.learn.exception;

import org.junit.Test;

public class CheckedAndUncheckedExceptionTest {

	@Test
	public void exceptionTest(){
		
		// unchecked ���͵��쳣���Բ����񣬲����κδ���
		CheckedAndUncheckedException.findUserName("hello");
		
		// checked ���͵��쳣���벶��(try...catch)�����׳�(throws)
		try {
			CheckedAndUncheckedException.findPassword("");
		} catch (PasswordIsInvalid e) {
			e.printStackTrace();
		}
	}
}
