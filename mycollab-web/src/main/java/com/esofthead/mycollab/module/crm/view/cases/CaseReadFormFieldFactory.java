package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.DefaultFormViewFieldFactory.FormEmailLinkViewField;
import com.esofthead.mycollab.vaadin.resource.ui.DefaultFormViewFieldFactory.FormLinkViewField;
import com.esofthead.mycollab.vaadin.resource.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class CaseReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleCase> {
	private static final long serialVersionUID = 1L;

	public CaseReadFormFieldFactory(GenericBeanForm<SimpleCase> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		final SimpleCase cases = attachForm.getBean();
		if (propertyId.equals("accountid")) {
			return new FormLinkViewField(cases.getAccountName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, cases
											.getAccountid()));

						}
					}, MyCollabResource.newResource("icons/16/crm/account.png"));
		} else if (propertyId.equals("email")) {
			return new FormEmailLinkViewField(cases.getEmail());
		} else if (propertyId.equals("assignuser")) {
			return new UserLinkViewField(cases.getAssignuser(),
					cases.getAssignUserAvatarId(),
					cases.getAssignUserFullName());
		}

		return null;
	}

}
