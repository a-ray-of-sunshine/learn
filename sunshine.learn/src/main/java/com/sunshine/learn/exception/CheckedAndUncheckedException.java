package com.sunshine.learn.exception;

/**
 * 2015年6月16日14:54:37
 * checked 和 unchecked 异常
 */
public class CheckedAndUncheckedException {

	public static String findUserName(String params){
		
		if(null == params || "".equals(params)){
			throw new UserNameIsInvalid("参数无:-D");
		}
		
		return "username";
	}
	
	public static String findPassword(String params) throws PasswordIsInvalid{
		
		if(null == params || "".equals(params)){
			throw new PasswordIsInvalid("参数无:-D");
		}
		
		return "password";
	}
}

/**
 * 继承自 java.lang.Exception 
 * 是 checked的异常
 *
 */
@SuppressWarnings("serial")
class PasswordIsInvalid extends Exception{
	public PasswordIsInvalid(String message) {
		super(message);
	}
}

/**
 * 继承自 java.lang.RuntimeException
 * 是 unchecked 的异常
 */
@SuppressWarnings("serial")
class UserNameIsInvalid extends RuntimeException{
	public UserNameIsInvalid(String message) {
		super(message);
	}
}
