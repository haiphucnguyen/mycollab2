package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.DateFieldWithUserTimeZone;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormLinkViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
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
class AssignmentReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleTask> {
	private static final long serialVersionUID = 1L;

	public AssignmentReadFormFieldFactory(GenericBeanForm<SimpleTask> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("assignuser")) {
			return new UserLinkViewField(attachForm.getBean().getAssignuser(),
					attachForm.getBean().getAssignUserAvatarId(), attachForm
							.getBean().getAssignUserFullName());
		} else if (propertyId.equals("startdate")) {
			if (attachForm.getBean().getStartdate() == null)
				return null;
			return new DateFieldWithUserTimeZone(attachForm.getBean()
					.getStartdate(), "DATETIME_FIELD");
		} else if (propertyId.equals("duedate")) {
			if (attachForm.getBean().getDuedate() == null)
				return null;
			return new DateFieldWithUserTimeZone(attachForm.getBean()
					.getDuedate(), "DATETIME_FIELD");
		} else if (propertyId.equals("contactid")) {
			return new FormLinkViewField(attachForm.getBean().getContactName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoRead(this, attachForm
											.getBean().getContactid()));
						}
					}, MyCollabResource.newResource("icons/16/crm/contact.png"));
		} else if (propertyId.equals("type")) {
			return new RelatedReadItemField(attachForm.getBean());

		}

		return null;
	}

}
