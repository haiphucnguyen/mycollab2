package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.form.view.builder.DateTimeDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextAreaDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class MeetingDefaultFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection meetingSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Meeting Information").build();

		meetingSection.addField(new TextDynaFieldBuilder()
				.fieldName("subject").displayName("Subject").mandatory(true)
				.fieldIndex(0).build());

		meetingSection.addField(new TextDynaFieldBuilder()
				.fieldName("status").displayName("Status").fieldIndex(1)
				.build());

		meetingSection.addField(new DateTimeDynaFieldBuilder()
				.fieldName("startdate").displayName("Start Date & Time")
				.fieldIndex(2).build());

		meetingSection.addField(new TextDynaFieldBuilder().fieldName("type")
				.displayName("Related To").fieldIndex(3).build());

		meetingSection.addField(new DateTimeDynaFieldBuilder()
				.fieldName("enddate").displayName("End Date & Time")
				.fieldIndex(4).build());

		meetingSection.addField(new TextDynaFieldBuilder()
				.fieldName("location").displayName("Location").fieldIndex(5)
				.build());

		defaultForm.addSection(meetingSection);

		DynaSection descSection = new DynaSectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).orderIndex(1)
				.header("Description").build();
		descSection.addField(new TextAreaDynaFieldBuilder()
				.fieldName("description").displayName("Description")
				.fieldIndex(0).build());

		defaultForm.addSection(descSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
