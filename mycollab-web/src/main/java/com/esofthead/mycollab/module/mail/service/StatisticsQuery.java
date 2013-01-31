package com.esofthead.mycollab.module.mail.service;


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
		CampaignConfig config = CampaignConfig.loadConfig();
		if (null != config) {
			key = config.getApiKey();
		}
	}
}
