package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.EmailDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.IntDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.PhoneDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.StringDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.UrlDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class LeadDefaultDynaFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection infoSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Contact Information").build();

		infoSection.addField(new StringDynaFieldBuilder()
				.fieldName("firstname").displayName("First Name").fieldIndex(0)
				.build());

		infoSection.addField(new EmailDynaFieldBuilder().fieldName("email")
				.displayName("Email").fieldIndex(1).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("lastname")
				.displayName("Last Name").fieldIndex(2).build());

		infoSection.addField(new PhoneDynaFieldBuilder()
				.fieldName("officephone").displayName("Office Phone")
				.fieldIndex(3).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("title")
				.displayName("Title").fieldIndex(4).build());

		infoSection.addField(new PhoneDynaFieldBuilder().fieldName("mobile")
				.displayName("Mobile").fieldIndex(5).build());

		infoSection.addField(new StringDynaFieldBuilder()
				.fieldName("department").displayName("Department")
				.fieldIndex(6).build());

		infoSection.addField(new PhoneDynaFieldBuilder()
				.fieldName("otherphone").displayName("Other Phone")
				.fieldIndex(7).build());

		infoSection.addField(new StringDynaFieldBuilder()
				.fieldName("accountname").displayName("Account Name")
				.fieldIndex(8).build());

		infoSection.addField(new PhoneDynaFieldBuilder().fieldName("fax")
				.displayName("Fax").fieldIndex(9).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("source")
				.displayName("Lead Source").fieldIndex(10).build());

		infoSection.addField(new UrlDynaFieldBuilder().fieldName("website")
				.displayName("Website").fieldIndex(11).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("industry")
				.displayName("Industry").fieldIndex(12).build());

		infoSection.addField(new StringDynaFieldBuilder().fieldName("status")
				.displayName("Status").fieldIndex(13).build());

		infoSection.addField(new IntDynaFieldBuilder().fieldName("noemployees")
				.displayName("No of Employees").fieldIndex(14).build());

		infoSection
				.addField(new StringDynaFieldBuilder()
						.fieldName("assignuser")
						.displayName(
								LocalizationHelper
										.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD))
						.fieldIndex(15).build());

		defaultForm.addSection(infoSection);

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
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(2)
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
