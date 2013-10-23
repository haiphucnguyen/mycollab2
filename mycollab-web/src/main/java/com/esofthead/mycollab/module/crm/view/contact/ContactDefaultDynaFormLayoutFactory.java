package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.BooleanDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.EmailDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.PhoneDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.StringDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;

public class ContactDefaultDynaFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection contactSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Contact Information").build();

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("firstname").displayName("First Name").fieldIndex(0)
				.build());

		contactSection
				.addField(new PhoneDynaFieldBuilder()
						.fieldName("officephone")
						.displayName(
								LocalizationHelper
										.getMessage(CrmCommonI18nEnum.FORM_PHONE_OFFICE_FIELD))
						.fieldIndex(1).build());

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("lastname").displayName("Last Name").fieldIndex(2)
				.mandatory(true).build());

		contactSection.addField(new PhoneDynaFieldBuilder().fieldName("mobile")
				.displayName("Mobile").fieldIndex(3).build());

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("accountid").displayName("Account").fieldIndex(4)
				.build());

		contactSection.addField(new PhoneDynaFieldBuilder()
				.fieldName("homephone").displayName("Home Phone").fieldIndex(5)
				.build());

		contactSection.addField(new StringDynaFieldBuilder().fieldName("title")
				.displayName("Title").fieldIndex(6).build());

		contactSection.addField(new PhoneDynaFieldBuilder()
				.fieldName("otherphone").displayName("Other Phone")
				.fieldIndex(7).build());

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("department").displayName("Department")
				.fieldIndex(8).build());

		contactSection.addField(new PhoneDynaFieldBuilder().fieldName("fax")
				.displayName("Fax").fieldIndex(9).build());

		contactSection.addField(new EmailDynaFieldBuilder().fieldName("email")
				.displayName("Email").fieldIndex(10).build());

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("birthday").displayName("Birthday").fieldIndex(11)
				.build());

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("assistant").displayName("Assistant").fieldIndex(12)
				.build());

		contactSection.addField(new BooleanDynaFieldBuilder()
				.fieldName("iscallable").displayName("Callable").fieldIndex(13)
				.build());

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("assistantphone").displayName("Assistant Phone")
				.fieldIndex(14).build());

		contactSection
				.addField(new StringDynaFieldBuilder()
						.fieldName("assignuser")
						.displayName(
								LocalizationHelper
										.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD))
						.fieldIndex(15).build());

		contactSection.addField(new StringDynaFieldBuilder()
				.fieldName("leadsource").displayName("Lead Source")
				.fieldIndex(16).build());

		defaultForm.addSection(contactSection);

		DynaSection addressSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(1)
				.header("Address Information").build();

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("primaddress").displayName("Address").fieldIndex(0)
				.build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("otheraddress").displayName("Other Address")
				.fieldIndex(1).build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("primcity").displayName("City").fieldIndex(2)
				.build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("othercity").displayName("Other City").fieldIndex(3)
				.build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("primstate").displayName("State").fieldIndex(4)
				.build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("otherstate").displayName("Other State")
				.fieldIndex(5).build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("primpostalcode").displayName("Postal Code")
				.fieldIndex(6).build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("otherpostalcode").displayName("Other Postal Code")
				.fieldIndex(7).build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("primcountry").displayName("Country").fieldIndex(8)
				.build());

		addressSection.addField(new StringDynaFieldBuilder()
				.fieldName("othercountry").displayName("Other Country")
				.fieldIndex(9).build());

		defaultForm.addSection(addressSection);

		DynaSection descSection = new DynaSectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).orderIndex(2)
				.header("Description").build();

		descSection.addField(new StringDynaFieldBuilder()
				.fieldName("description").displayName("Description")
				.fieldIndex(0).build());

		defaultForm.addSection(descSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
