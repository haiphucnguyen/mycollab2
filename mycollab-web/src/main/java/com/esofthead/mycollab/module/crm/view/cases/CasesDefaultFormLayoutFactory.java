package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.EmailDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.PhoneDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.StringDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class CasesDefaultFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection infoSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Case Information").build();

		infoSection.addField(new StringDynaFieldBuilder().fieldName("priority")
				.displayName("Priority").fieldIndex(0).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("type")
				.displayName("Type").fieldIndex(1).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("status")
				.displayName("Status").fieldIndex(2).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("reason")
				.displayName("Reason").fieldIndex(3).build());

		infoSection.addField(new StringDynaFieldBuilder()
				.fieldName("accountid").displayName("Account Name")
				.fieldIndex(4).mandatory(true).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("subject")
				.displayName("Subject").fieldIndex(5).mandatory(true).build());

		infoSection.addField(new PhoneDynaFieldBuilder()
				.fieldName("phonenumber").displayName("Phone Number")
				.fieldIndex(6).build());

		infoSection.addField(new EmailDynaFieldBuilder().fieldName("email")
				.displayName("Email").fieldIndex(7).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("origin")
				.displayName("Origin").fieldIndex(8).build());

		infoSection
				.addField(new StringDynaFieldBuilder()
						.fieldName("assignuser")
						.displayName(
								LocalizationHelper
										.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD))
						.fieldIndex(9).build());

		defaultForm.addSection(infoSection);

		DynaSection descSection = new DynaSectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).orderIndex(1)
				.header("Description").build();

		descSection.addField(new TextDynaFieldBuilder()
				.fieldName("description").displayName("Description")
				.fieldIndex(0).build());
		descSection.addField(new TextDynaFieldBuilder().fieldName("resolution")
				.displayName("Resolution").fieldIndex(1).build());

		defaultForm.addSection(descSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
