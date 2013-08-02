package com.esofthead.mycollab.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(ThreadExceptionHandler.class);
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.error("Exception when run thread: " + t.getName()
				+ " with exception: " + e.getMessage());
	}

}
