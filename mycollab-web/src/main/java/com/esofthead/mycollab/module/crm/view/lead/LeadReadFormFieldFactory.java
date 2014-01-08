package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormEmailLinkViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormLinkViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class LeadReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleLead> {
	private static final long serialVersionUID = 1L;

	public LeadReadFormFieldFactory(GenericBeanForm<SimpleLead> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		SimpleLead lead = attachForm.getBean();
		if (propertyId.equals("firstname")) {
			if (lead.getPrefixname() == null) {
				return new FormViewField(lead.getFirstname());
			} else {
				return new FormViewField(lead.getPrefixname() + " "
						+ lead.getFirstname());
			}
		} else if (propertyId.equals("website")) {
			return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
					lead.getWebsite());
		} else if (propertyId.equals("email")) {
			return new FormEmailLinkViewField(lead.getEmail());
		} else if (propertyId.equals("accountid")) {
			FormLinkViewField field = new FormLinkViewField(
					lead.getAccountname(), new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
						}
					});

			return field;
		} else if (propertyId.equals("assignuser")) {
			return new UserLinkViewField(lead.getAssignuser(),
					lead.getAssignUserAvatarId(), lead.getAssignUserFullName());
		}

		return null;
	}

}
