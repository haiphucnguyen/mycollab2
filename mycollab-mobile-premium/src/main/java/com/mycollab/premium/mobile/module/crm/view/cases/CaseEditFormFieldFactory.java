package com.mycollab.premium.mobile.module.crm.view.cases;

import com.mycollab.premium.mobile.module.crm.view.account.AccountSelectionField;
import com.mycollab.mobile.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.module.crm.domain.CaseWithBLOBs;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 * @param <B>
 */
class CaseEditFormFieldFactory<B extends CaseWithBLOBs> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	CaseEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	@Override
	protected AbstractField<?> onCreateField(Object propertyId) {
		if (propertyId.equals("priority")) {
			return new CasePriorityListSelect();
		} else if (propertyId.equals("status")) {
			return new CaseStatusListSelect();
		} else if (propertyId.equals("reason")) {
			return new CaseReasonListSelect();
		} else if (propertyId.equals("origin")) {
			return new CasesOriginListSelect();
		} else if (propertyId.equals("type")) {
			return new CaseTypeListSelect();
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
			return accountField;
		} else if (propertyId.equals("subject")) {
			TextField tf = new TextField();
			if (isValidateForm) {
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("Subject must not be null");
			}

			return tf;
		} else if (propertyId.equals("assignuser")) {
			ActiveUserComboBox userBox = new ActiveUserComboBox();
			userBox.select(attachForm.getBean().getAssignuser());
			return userBox;
		}

		return null;
	}
}
