package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.DefaultFormViewFieldFactory.DateFieldWithUserTimeZone;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class MeetingReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleMeeting> {
	private static final long serialVersionUID = 1L;

	public MeetingReadFormFieldFactory(GenericBeanForm<SimpleMeeting> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("type")) {
			return new RelatedReadItemField(attachForm.getBean());
		} else if (propertyId.equals("startdate")) {
			if (attachForm.getBean().getStartdate() == null)
				return null;
			return new DateFieldWithUserTimeZone(attachForm.getBean()
					.getStartdate(), "DATETIME_FIELD");
		} else if (propertyId.equals("enddate")) {
			if (attachForm.getBean().getEnddate() == null)
				return null;
			return new DateFieldWithUserTimeZone(attachForm.getBean()
					.getEnddate(), "DATETIME_FIELD");
		} else if (propertyId.equals("isrecurrence")) {
			return null;
		}
		return null;
	}

}
