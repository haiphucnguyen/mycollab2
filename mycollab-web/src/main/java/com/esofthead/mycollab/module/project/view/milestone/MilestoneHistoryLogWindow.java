package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

@SuppressWarnings("serial")
public class MilestoneHistoryLogWindow extends HistoryLogWindow {

	public MilestoneHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("name", "Name");
		this.generateFieldDisplayHandler("owner", "Responsible User");
		this.generateFieldDisplayHandler("flag", "Flag");
		this.generateFieldDisplayHandler("startdate", "Start Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("enddate", "End Date",
				HistoryLogComponent.DATE_FIELD);
	}

}
