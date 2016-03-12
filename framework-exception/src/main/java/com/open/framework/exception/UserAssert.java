package com.open.framework.exception;

import java.text.MessageFormat;

public class UserAssert {

	
	public static void isEmpty(String s, String msg, String ...args) {
		if(!(s == null || "".equals(s))) {
			throw new UserException(MessageFormat.format(msg, args));
		}
	}
	
	
	public static void isNotEmpty(String s, String msg, String ...args) {
		if(s == null || "".equals(s)) {
			throw new UserException(MessageFormat.format(msg, args));
		}
	}
	
	public static void isNull(Object obj, String msg, String ...args) {
		if(obj != null) {
			throw new UserException(MessageFormat.format(msg, args));
		}
	}
	
	public static void isNotNull(Object obj, String msg, String ...args) {
		if(obj == null) {
			throw new UserException(MessageFormat.format(msg, args));
		}
	}
	
	public static void isTrue(boolean express, String msg, String ...args) {
		if(!express) {
			throw new UserException(MessageFormat.format(msg, args));
		}
	}
	
	public static void isEmpty(String s, int errorCode, String msg, String ...args) {
		if(!(s == null || "".equals(s))) {
			throw new UserException(errorCode, MessageFormat.format(msg, args));
		}
	}
	
	
	public static void isNotEmpty(String s, int errorCode, String msg, String ...args) {
		if(s == null || "".equals(s)) {
			throw new UserException(errorCode, MessageFormat.format(msg, args));
		}
	}
	
	public static void isNull(Object obj, int errorCode, String msg, String ...args) {
		if(obj != null) {
			throw new UserException(MessageFormat.format(msg, args));
		}
	}
	
	public static void isNotNull(Object obj, int errorCode, String msg, String ...args) {
		if(obj == null) {
			throw new UserException(errorCode, MessageFormat.format(msg, args));
		}
	}
	
	public static void isTrue(boolean express, int errorCode, String msg, String ...args) {
		if(!express) {
			throw new UserException(errorCode, MessageFormat.format(msg, args));
		}
	}
	
}
