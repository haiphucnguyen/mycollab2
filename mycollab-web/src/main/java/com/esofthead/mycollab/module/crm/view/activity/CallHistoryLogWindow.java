package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

public class CallHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public CallHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("subject", "Subject");
		this.generateFieldDisplayHandler("startdate", "Start Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("assignuser", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("purpose", "Purpose");
	}

}
