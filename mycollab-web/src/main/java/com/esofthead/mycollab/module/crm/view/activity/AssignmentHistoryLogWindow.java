package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

public class AssignmentHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public AssignmentHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("subject", "Subject");
		this.generateFieldDisplayHandler("startdate", "Start Date");
		this.generateFieldDisplayHandler("duedate", "Due Date");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("assignuser", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("priority", "Priority");
		this.generateFieldDisplayHandler("description", "Description");
	}

}
