package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

@SuppressWarnings("serial")
public class ContactHistoryLogWindow extends HistoryLogWindow {

	public ContactHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		this.generateFieldDisplayHandler("firstname", "First Name");
		this.generateFieldDisplayHandler("lastname", "Last Name");
		this.generateFieldDisplayHandler("accountId", "Account");
		this.generateFieldDisplayHandler("title", "Title");
		this.generateFieldDisplayHandler("department", "Department");
		this.generateFieldDisplayHandler("email", "Email");
		this.generateFieldDisplayHandler("assistant", "Assistant");
		this.generateFieldDisplayHandler("assistantphone", "Assistant Phone");
		this.generateFieldDisplayHandler("leadsource", "Leade Source");
		this.generateFieldDisplayHandler("officephone", LocalizationHelper
				.getMessage(CrmCommonI18nEnum.FORM_PHONE_OFFICE_FIELD));
		this.generateFieldDisplayHandler("mobile", "Mobile");
		this.generateFieldDisplayHandler("homephone", "Home Phone");
		this.generateFieldDisplayHandler("otherphone", "Other Phone");
		this.generateFieldDisplayHandler("birthday", "Birthday",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("iscallable", "Callable");
		this.generateFieldDisplayHandler("assignuser", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("primaddress", "Address");
		this.generateFieldDisplayHandler("primcity", "City");
		this.generateFieldDisplayHandler("primstate", "State");
		this.generateFieldDisplayHandler("primpostalcode", "Postal Code");
		this.generateFieldDisplayHandler("primcountry", "Country");
		this.generateFieldDisplayHandler("otheraddress", "Other Address");
		this.generateFieldDisplayHandler("othercity", "Other City");
		this.generateFieldDisplayHandler("otherstate", "Other State");
		this.generateFieldDisplayHandler("otherpostalcode", "Other Postal Code");
		this.generateFieldDisplayHandler("othercountry", "Other Country");
		this.generateFieldDisplayHandler("description", "Description");

	}

}
