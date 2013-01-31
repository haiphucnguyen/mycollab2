package com.esofthead.mycollab.module.mail.service;

import java.util.LinkedList;

public class SendResponse extends LinkedList<SentMailStatus> {

	private static final String REJECTED = "rejected";
	private static final String SENT = "sent";
	private static final String QUEUED = "queued";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2796192018165482020L;
	private int sent = 0;
	private int rejected = 0;
	private int queued = 0;
	
	public int getSents() {
		return sent;
	}
	
	public int getRejected() {
		return rejected;
	}
	
	public int getQueued() {
		return queued;
	}
	
	@Override
	public boolean add(SentMailStatus obj) {
		if (obj.getStatus().equals(REJECTED))
			rejected++;
		else if (obj.getStatus().equals(SENT))
			sent++;
		else if (obj.getStatus().equals(QUEUED))
			queued++;
		return super.add(obj);
	}
}
