package com.esofthead.mycollab.module.crm.data;

public class DataTypeFactory {
	private static String[] ACCOUNT_INDUSTRY_LIST = new String[] { "Apparel",
			"Banking", "Biotechnology", "Chemicals", "Communications",
			"Construction", "Consulting", "Education", "Electronics", "Energy",
			"Engineering", "Entertainment", "Environmental", "Finance",
			"Goverment", "Healthcare", "Hospitality", "Insurance", "Machinery",
			"Manufactory", "Media", "Not For Profit", "Recreation", "Retail",
			"Shipping", "Technology", "Telecommunications", "Utilities",
			"Other" };

	private static String[] ACCOUNT_TYPE_LIST = new String[] { "Analysts",
			"Competitor", "Customer", "Integrator", "Investor", "Partner",
			"Press", "Prospect", "Reseller", "Other" };

	private static String[] LEAD_SOURCE_LIST = new String[] { "Cold Call",
			"Existing Customer", "Self Generated", "Employee", "Partner",
			"Public Relations", "Direct Email", "Conference", "Trade Show",
			"Website", "Word of mouth", "Email", "Campaign", "Other" };

	private static String[] LEAD_STATUS_LIST = new String[] { "New",
			"Assigned", "In Process", "Converted", "Recycled", "Dead" };

	private static String[] CAMPAIGN_STATUS_LIST = new String[] { "Planning",
			"Active", "Inactive", "Complete", "In Queue", "Sending" };

	private static String[] CAMPAIGN_TYPE_LIST = new String[] { "Conference",
			"Webinar", "Trade Show", "Public Relations", "Partners",
			"Referral Program", "Advertisement", "Banner Ads", "Direct Email",
			"Mail", "Telemarketing", "Others" };

	public static String[] getAccountIndustryList() {
		return ACCOUNT_INDUSTRY_LIST;
	}

	public static String[] getAccountTypeList() {
		return ACCOUNT_TYPE_LIST;
	}

	public static String[] getLeadSourceList() {
		return LEAD_SOURCE_LIST;
	}

	public static String[] getLeadStatusList() {
		return LEAD_STATUS_LIST;
	}

	public static String[] getCampaignStatusList() {
		return CAMPAIGN_STATUS_LIST;
	}

	public static String[] getCampaignTypeList() {
		return CAMPAIGN_TYPE_LIST;
	}
}
