package com.esofthead.mycollab.module.mail.service.mandrill;

import com.esofthead.mycollab.module.mail.domain.mandrill.Message;
import com.esofthead.mycollab.module.mail.service.MailConfig;


public class SendRequest {
	private String key;
	private Message message;
	
	@SuppressWarnings("unused")
	private boolean async = false;
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public SendRequest() {
		message = new Message();
		key = MailConfig.getProperty(MailConfig.API_KEY);
	}
}
