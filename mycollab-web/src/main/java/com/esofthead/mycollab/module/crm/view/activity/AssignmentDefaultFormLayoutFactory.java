package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.DateDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.StringDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class AssignmentDefaultFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection taskSection = new DynaSectionBuilder().orderIndex(0)
				.layoutType(LayoutType.TWO_COLUMN).header("Task Information")
				.build();

		taskSection.addField(new StringDynaFieldBuilder().fieldName("subject")
				.displayName("Subject").fieldIndex(0).mandatory(true).build());

		taskSection.addField(new StringDynaFieldBuilder().fieldName("status")
				.displayName("Status").fieldIndex(1).build());

		taskSection.addField(new DateDynaFieldBuilder().fieldName("startdate")
				.displayName("Start Date").fieldIndex(2).build());

		taskSection.addField(new StringDynaFieldBuilder().fieldName("type")
				.displayName("Related To").fieldIndex(3).build());

		taskSection.addField(new DateDynaFieldBuilder().fieldName("duedate")
				.displayName("Due Date").fieldIndex(4).build());

		taskSection.addField(new StringDynaFieldBuilder()
				.fieldName("contactid").displayName("Contact").fieldIndex(5)
				.build());

		taskSection.addField(new StringDynaFieldBuilder().fieldName("priority")
				.displayName("Priority").fieldIndex(6).build());

		taskSection
				.addField(new StringDynaFieldBuilder()
						.fieldName("assignuser")
						.displayName(
								LocalizationHelper
										.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD))
						.fieldIndex(7).build());

		defaultForm.addSection(taskSection);

		DynaSection descSection = new DynaSectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).orderIndex(1)
				.header("Description").build();

		descSection.addField(new TextDynaFieldBuilder()
				.fieldName("description").displayName("Description")
				.fieldIndex(0).build());

		defaultForm.addSection(descSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
