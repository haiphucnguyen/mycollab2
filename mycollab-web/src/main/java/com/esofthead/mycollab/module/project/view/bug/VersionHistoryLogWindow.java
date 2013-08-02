package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

@SuppressWarnings("serial")
public class VersionHistoryLogWindow extends HistoryLogWindow {

	public VersionHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		
		this.generateFieldDisplayHandler("versionname", "Version Name");
		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("duedate", "Due Date",
				HistoryLogComponent.DATE_FIELD);
	}

}
