package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

@SuppressWarnings("serial")
public class OpportunityHistoryLogWindow extends HistoryLogWindow {

	public OpportunityHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		this.generateFieldDisplayHandler("opportunityname", "Opportunity Name");
		this.generateFieldDisplayHandler("currencyid", "Currency");
		this.generateFieldDisplayHandler("amount", "Amount");
		this.generateFieldDisplayHandler("salesstage", "Sales Stage");
		this.generateFieldDisplayHandler("probability", "Probability (%)");
		this.generateFieldDisplayHandler("nextstep", "Next Step");
		this.generateFieldDisplayHandler("accountid", "Account Name");
		this.generateFieldDisplayHandler("expectedcloseddate", "Expected Close Date", HistoryLogWindow.DATE_FIELD);
		this.generateFieldDisplayHandler("opportunitytype", "Type");
		this.generateFieldDisplayHandler("source", "Lead Source");
		this.generateFieldDisplayHandler("campaignid", "Campaign");
		this.generateFieldDisplayHandler("assignuser", "Assigned User");
		this.generateFieldDisplayHandler("description", "Description");
	}

}
