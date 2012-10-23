package com.esofthead.test;

public class TestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TestException(Throwable e) {
		super(e);
	}
}
