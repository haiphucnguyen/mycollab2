package com.esofthead.mycollab.core;

public class MyCollabThread extends Thread {

	public MyCollabThread() {
		super();
		setExceptionHandler();
	}
	
	public MyCollabThread(Runnable runable) {
		super(runable);
		setExceptionHandler();
	}
	
	private void setExceptionHandler() {
		ThreadExceptionHandler threadExpceptionHandler = new ThreadExceptionHandler();
		this.setUncaughtExceptionHandler(threadExpceptionHandler);
	}
}
