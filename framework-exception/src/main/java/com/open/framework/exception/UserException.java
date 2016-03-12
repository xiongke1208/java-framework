package com.open.framework.exception;

/**
 * 用户行为异常
 * @author ke_xiong
 *
 */
public class UserException extends RuntimeException{

	private static final long serialVersionUID = -169486972635351339L;

	public UserException(){};
	
	private int code;
	
	public UserException(String msg){
		super(msg);
	}

	public UserException(int code, String msg){
		super(msg);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
