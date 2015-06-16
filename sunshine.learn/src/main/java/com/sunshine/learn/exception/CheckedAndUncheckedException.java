package com.sunshine.learn.exception;

/**
 * 2015��6��16��14:54:37
 * checked �� unchecked �쳣
 */
public class CheckedAndUncheckedException {

	public static String findUserName(String params){
		
		if(null == params || "".equals(params)){
			throw new UserNameIsInvalid("������:-D");
		}
		
		return "username";
	}
	
	public static String findPassword(String params) throws PasswordIsInvalid{
		
		if(null == params || "".equals(params)){
			throw new PasswordIsInvalid("������:-D");
		}
		
		return "password";
	}
}

/**
 * �̳��� java.lang.Exception 
 * �� checked���쳣
 *
 */
@SuppressWarnings("serial")
class PasswordIsInvalid extends Exception{
	public PasswordIsInvalid(String message) {
		super(message);
	}
}

/**
 * �̳��� java.lang.RuntimeException
 * �� unchecked ���쳣
 */
@SuppressWarnings("serial")
class UserNameIsInvalid extends RuntimeException{
	public UserNameIsInvalid(String message) {
		super(message);
	}
}
