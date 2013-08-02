package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

@SuppressWarnings("serial")
public class ComponentHistoryLogWindow extends HistoryLogWindow {

	public ComponentHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		
		this.generateFieldDisplayHandler("componentname", "Component Name");
		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("userlead", "Lead");
	}

}
