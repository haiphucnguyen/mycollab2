package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

public class MeetingHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public MeetingHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("subject", "Subject");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("type", "Type");
		this.generateFieldDisplayHandler("startdate", "Start Date");
		this.generateFieldDisplayHandler("enddate", "End Date");
		this.generateFieldDisplayHandler("location", "Location");
	}

}
