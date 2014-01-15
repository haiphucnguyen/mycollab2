package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class AccountReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleAccount> {
	private static final long serialVersionUID = 1L;

	public AccountReadFormFieldFactory(GenericBeanForm<SimpleAccount> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("email")) {
			return new DefaultFormViewFieldFactory.FormEmailLinkViewField(
					attachForm.getBean().getEmail());
		} else if (propertyId.equals("assignuser")) {
			return new UserLinkViewField(attachForm.getBean().getAssignuser(),
					attachForm.getBean().getAssignUserAvatarId(), attachForm
							.getBean().getAssignUserFullName());
		} else if (propertyId.equals("website")) {
			return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
					attachForm.getBean().getWebsite());
		}

		return null;
	}

}
