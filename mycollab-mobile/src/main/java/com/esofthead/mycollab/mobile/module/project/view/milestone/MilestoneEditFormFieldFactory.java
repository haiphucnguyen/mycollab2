package com.esofthead.mycollab.mobile.module.project.view.milestone;

import java.util.Arrays;

import com.esofthead.mycollab.mobile.module.project.view.settings.ProjectMemberSelectionField;
import com.esofthead.mycollab.mobile.ui.I18nValueComboBox;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 * @param <B>
 */
public class MilestoneEditFormFieldFactory<B extends Milestone> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {

	private static final long serialVersionUID = 1L;

	MilestoneEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("owner")) {
			final ProjectMemberSelectionField userbox = new ProjectMemberSelectionField();
			userbox.setRequired(true);
			userbox.setRequiredError("Please select an assignee");
			return userbox;
		} else if (propertyId.equals("status")) {
			if (attachForm.getBean().getStatus() == null) {
				attachForm.getBean().setStatus(
						MilestoneStatus.InProgress.toString());
			}
			return new ProgressStatusComboBox();
		} else if (propertyId.equals("name")) {
			final TextField tf = new TextField();
			if (isValidateForm) {
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("Please enter name");
			}
			return tf;
		} else if (propertyId.equals("startdate")
				|| propertyId.equals("enddate")) {
			return new DatePicker();
		} else if (propertyId.equals("description")) {
			final TextArea descArea = new TextArea();
			descArea.setNullRepresentation("");
			return descArea;
		}

		return null;
	}

	private static class ProgressStatusComboBox extends I18nValueComboBox {
		private static final long serialVersionUID = 1L;

		public ProgressStatusComboBox() {
			super();
			setCaption(null);
			this.setNullSelectionAllowed(false);
			this.loadData(Arrays.asList(MilestoneStatus.InProgress,
					MilestoneStatus.Future, MilestoneStatus.Closed));
		}
	}

}
