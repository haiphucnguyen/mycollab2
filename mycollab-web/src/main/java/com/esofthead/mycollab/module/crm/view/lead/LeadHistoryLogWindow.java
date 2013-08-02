package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

@SuppressWarnings("serial")
public class LeadHistoryLogWindow extends HistoryLogWindow {

	public LeadHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		this.generateFieldDisplayHandler("prefixname", "Prefix name");
		this.generateFieldDisplayHandler("firstname", "First name");
		this.generateFieldDisplayHandler("lastname", "Last Name");
		this.generateFieldDisplayHandler("title", "Title");
		this.generateFieldDisplayHandler("department", "Department");
		this.generateFieldDisplayHandler("accountname", "Account Name");
		this.generateFieldDisplayHandler("source", "Lead Source");
		this.generateFieldDisplayHandler("industry", "Industry");
		this.generateFieldDisplayHandler("noemployees", "No of Employees");
		this.generateFieldDisplayHandler("email", "Email");
		this.generateFieldDisplayHandler("officephone", "Office Phone");
		this.generateFieldDisplayHandler("mobile", "Mobile");
		this.generateFieldDisplayHandler("otherphone", "Other Phone");
		this.generateFieldDisplayHandler("fax", "Fax");
		this.generateFieldDisplayHandler("website", "Web Site");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("assignuser", "Assigned User");
		this.generateFieldDisplayHandler("primaddress", "Address");
		this.generateFieldDisplayHandler("primcity", "City");
		this.generateFieldDisplayHandler("primstate", "State");
		this.generateFieldDisplayHandler("primpostalcode", "Postal Code");
		this.generateFieldDisplayHandler("primcountry", "Country");
		this.generateFieldDisplayHandler("otheraddress", "Other Address");
		this.generateFieldDisplayHandler("othercity", "Other City");
		this.generateFieldDisplayHandler("otherstate", "Other State");
		this.generateFieldDisplayHandler("otherpostalcode", "Other Postal Code");
		this.generateFieldDisplayHandler("othercountry", "Other Country");
		this.generateFieldDisplayHandler("description", "Description");
	}

}
