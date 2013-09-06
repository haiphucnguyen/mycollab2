package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

@SuppressWarnings("serial")
public class CaseHistoryLogWindow extends HistoryLogWindow {

	public CaseHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		this.generateFieldDisplayHandler("priority", "Priority");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("accountid", "Account Name");
		this.generateFieldDisplayHandler("phonenumber", "Phone Number");
		this.generateFieldDisplayHandler("origin", "Origin");
		this.generateFieldDisplayHandler("type", "Type");
		this.generateFieldDisplayHandler("reason", "Reason");
		this.generateFieldDisplayHandler("subject", "Subject");
		this.generateFieldDisplayHandler("email", "Email");
		this.generateFieldDisplayHandler("assignuser", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("resolution", "Resolution");
	}

}
