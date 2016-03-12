package com.open.framework.base.dto;

public class InfoDTO {

	private int flag;
	private String msg;
	private Object bean;
	
	public static final int SUCCESS =1;
	public static final int FAIL =0;
	
	public InfoDTO(int success2, String msg) {
		this.flag = success2;
		this.msg = msg;
	}

	public static InfoDTO succ() {
		return new InfoDTO(InfoDTO.SUCCESS, null);
	}
	
	public static InfoDTO fail(String msg) {
		return new InfoDTO(InfoDTO.FAIL, msg);
	}
	
	public int isFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}

	public boolean isSuccess() {
		return flag == SUCCESS;
	}
	
	
}
