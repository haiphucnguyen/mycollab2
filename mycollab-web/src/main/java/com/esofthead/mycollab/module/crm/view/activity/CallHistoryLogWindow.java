package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

public class CallHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public CallHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("subject", "Subject");
		this.generateFieldDisplayHandler("startdate", "Start Date");
		this.generateFieldDisplayHandler("assignuser", "Assign User");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("purpose", "Purpose");
	}

}
