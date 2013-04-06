package com.esofthead.mycollab.rest;

public class RestletErrorItem {
	public static final int BAD_REQUEST = 400;
	public static final int INTERNAL_ERROR = 500;
	
	private int code;

	private String message;

	public RestletErrorItem(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
