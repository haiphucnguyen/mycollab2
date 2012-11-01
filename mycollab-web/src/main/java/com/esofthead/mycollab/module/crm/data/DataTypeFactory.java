package com.esofthead.mycollab.module.crm.data;

public class DataTypeFactory {
	public static String[] getAccountIndustryList() {
		return new String[] { "Apparel", "Banking", "Biotechnology",
				"Chemicals", "Communications", "Construction", "Consulting" };
	}

	public static String[] getAccountTypeList() {
		return new String[] { "Analysts", "Competitor", "Customer",
				"Integrator", "Investor", "Partner", "Press", "Prospect",
				"Reseller", "Other" };
	}
}
