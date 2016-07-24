package com.TwistWallet.utils;

public enum ErrorCodes {

	EMAIL_ALREADY_EXISTS(101,"Email id already exists"),
	EMAIL_NOT_EXISTS(102,"Email id not exists"),
	INVALID_REQUEST(103,"Request parameter not completed"),
	SYSTEM_EXCEPTION(501,"System Exception Occurr"),
	CUSTOM_EXCEPTION(502,"Custom Exception Occurr");
	
	private int number;
	private String desc;
	
	ErrorCodes(int number,String desc){
		this.number=number;
		this.desc=desc;
	}
	
	public int getResultCode(){
		return this.number;
	}
	
	public String getDesc(){
		return this.desc;
	}
}
