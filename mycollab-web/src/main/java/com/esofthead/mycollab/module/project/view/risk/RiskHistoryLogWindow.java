package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

@SuppressWarnings("serial")
public class RiskHistoryLogWindow extends HistoryLogWindow {

	public RiskHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		
		this.generateFieldDisplayHandler("riskname", "Name");
		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("raisedbyuser", "Raised by");
		
		this.generateFieldDisplayHandler("type", "Related to");
		this.generateFieldDisplayHandler("assigntouser", "Assigned to");
		this.generateFieldDisplayHandler("consequence", "Consequence");
		
		this.generateFieldDisplayHandler("datedue", "Date due",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("probalitity", "Probality");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("level", "Rating");
		this.generateFieldDisplayHandler("response", "Response");
	}

}
