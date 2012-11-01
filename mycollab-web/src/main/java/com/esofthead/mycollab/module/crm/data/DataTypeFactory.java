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

	public static String[] getAccountIndustryList() {
		return ACCOUNT_INDUSTRY_LIST;
	}

	public static String[] getAccountTypeList() {
		return ACCOUNT_TYPE_LIST;
	}

	public static String[] getLeadSourceList() {
		return LEAD_SOURCE_LIST;
	}
}
