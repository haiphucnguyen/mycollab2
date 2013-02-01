package com.esofthead.mycollab.module.mail.service.mandrill;

import com.esofthead.mycollab.module.mail.service.MailConfig;

class StatisticsQuery {
	private String key;
	private String address;
	
	public String getKey() {
		return key;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public StatisticsQuery() {
		key = MailConfig.getProperty(MailConfig.API_KEY);
	}
}
