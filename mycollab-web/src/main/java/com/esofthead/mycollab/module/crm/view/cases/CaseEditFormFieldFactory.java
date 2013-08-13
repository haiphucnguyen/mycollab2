package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CaseEditFormFieldFactory extends DefaultEditFormFieldFactory {
	private static final long serialVersionUID = 1L;
	private CaseWithBLOBs cases;

	public CaseEditFormFieldFactory(CaseWithBLOBs cases) {
		this.cases = cases;
	}

	@Override
	protected Field onCreateField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {
		if (propertyId.equals("priority")) {
			return new CasePriorityComboBox();
		} else if (propertyId.equals("status")) {
			return new CaseStatusComboBox();
		} else if (propertyId.equals("reason")) {
			return new CaseReasonComboBox();
		} else if (propertyId.equals("origin")) {
			return new CasesOriginComboBox();
		} else if (propertyId.equals("type")) {
			return new CaseTypeComboBox();
		} else if (propertyId.equals("description")) {
			TextArea descArea = new TextArea("", "");
			descArea.setNullRepresentation("");
			return descArea;
		} else if (propertyId.equals("resolution")) {
			TextArea reArea = new TextArea("", "");
			reArea.setNullRepresentation("");
			return reArea;
		} else if (propertyId.equals("accountid")) {
			AccountSelectionField accountField = new AccountSelectionField();
			accountField.setRequired(true);

			if (cases.getAccountid() != null) {
				AccountService accountService = AppContext
						.getSpringBean(AccountService.class);
				SimpleAccount account = accountService.findById(
						cases.getAccountid(), AppContext.getAccountId());
				if (account != null) {
					accountField.setAccount(account);
				}
			}
			return accountField;
		} else if (propertyId.equals("subject")) {
			TextField tf = new TextField();
			tf.setNullRepresentation("");
			tf.setRequired(true);
			tf.setRequiredError("Subject must not be null");
			return tf;
		} else if (propertyId.equals("assignuser")) {
			UserComboBox userBox = new UserComboBox();
			userBox.select(cases.getAssignuser());
			return userBox;
		}

		return null;
	}
}
