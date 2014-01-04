package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.DateFieldWithUserTimeZone;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class CallReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleCall> {
	private static final long serialVersionUID = 1L;

	public CallReadFormFieldFactory(GenericBeanForm<SimpleCall> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("assignuser")) {
			return new UserLinkViewField(attachForm.getBean().getAssignuser(),
					attachForm.getBean().getAssignUserAvatarId(), attachForm
							.getBean().getAssignUserFullName());
		} else if (propertyId.equals("type")) {
			return new RelatedReadItemField(attachForm.getBean());
		} else if (propertyId.equals("status")) {
			final String value = attachForm.getBean().getStatus() + " "
					+ attachForm.getBean().getCalltype();
			final FormViewField field = new FormViewField(value);
			return field;
		} else if (propertyId.equals("durationinseconds")) {
			final Integer duration = attachForm.getBean()
					.getDurationinseconds();
			if (duration != null && duration != 0) {
				final int hours = duration / 3600;
				final int minutes = (duration % 3600) / 60;
				final StringBuffer value = new StringBuffer();
				if (hours == 1) {
					value.append("1 hour ");
				} else if (hours >= 2) {
					value.append(hours + " hours ");
				}

				if (minutes > 0) {
					value.append(minutes + " minutes");
				}

				return new FormViewField(value.toString());
			} else {
				return new FormViewField("");
			}
		} else if (propertyId.equals("startdate")) {
			if (attachForm.getBean().getStartdate() == null) {
				return new FormViewField("");
			} else {
				return new DateFieldWithUserTimeZone(attachForm.getBean()
						.getStartdate(), "DATETIME_FIELD");
			}
		}

		return null;
	}

}
