package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.EmailFieldBuilder;
import com.esofthead.mycollab.form.view.builder.IntFieldBuilder;
import com.esofthead.mycollab.form.view.builder.PhoneFieldBuilder;
import com.esofthead.mycollab.form.view.builder.PickListFieldBuilder;
import com.esofthead.mycollab.form.view.builder.SectionBuilder;
import com.esofthead.mycollab.form.view.builder.StringFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.localization.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;

public class AccountDefaultDynaForm {
	public static final DynaForm form;

	static {

		form = new DynaForm();

		// Build block account information
		DynaSection accountSection = new SectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Account Information").build();
		accountSection.addField(new StringFieldBuilder()
				.fieldName("accountname")
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_ACCOUNT_NAME))
				.fieldIndex(0).required(true).build());
		accountSection
				.addField(new StringFieldBuilder()
						.fieldName("phoneoffice")
						.displayName(
								LocalizationHelper
										.getMessage(CrmCommonI18nEnum.FORM_PHONE_OFFICE_FIELD))
						.fieldIndex(1).build());

		accountSection.addField(new StringFieldBuilder()
				.fieldName("website")
				.fieldIndex(2)
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_WEBSITE))
				.build());

		accountSection
				.addField(new PhoneFieldBuilder()
						.fieldName("fax")
						.fieldIndex(3)
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_FAX))
						.build());

		accountSection.addField(new IntFieldBuilder()
				.fieldName("numemployees")
				.fieldIndex(4)
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_EMPLOYEES))
				.build());

		accountSection.addField(new PhoneFieldBuilder()
				.fieldName("alternatephone")
				.fieldIndex(5)
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_OTHER_PHONE))
				.build());

		accountSection.addField(new PickListFieldBuilder<String>()
				.fieldName("industry")
				.fieldIndex(6)
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_INDUSTRY))
				.build());

		accountSection.addField(new EmailFieldBuilder()
				.fieldName("email")
				.fieldIndex(7)
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_EMAIL))
				.build());

		accountSection
				.addField(new PickListFieldBuilder<String>()
						.fieldName("type")
						.fieldIndex(8)
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_TYPE))
						.build());

		accountSection.addField(new StringFieldBuilder()
				.fieldName("ownership")
				.fieldIndex(9)
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_OWNERSHIP))
				.build());

		accountSection
				.addField(new StringFieldBuilder()
						.fieldName("assignuser")
						.fieldIndex(10)
						.displayName(
								LocalizationHelper
										.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD))
						.build());

		accountSection
				.addField(new StringFieldBuilder()
						.fieldName("annualrevenue")
						.fieldIndex(11)
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_ANNUAL_REVENUE))
						.build());

		form.addSection(accountSection);

		// build block address
		DynaSection addressSection = new SectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN)
				.header("Address Information").build();
		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(0)
						.fieldName("billingaddress")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_ADDRESS))
						.build());
		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(1)
						.fieldName("shippingaddress")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_ADDRESS))
						.build());
		addressSection.addField(new StringFieldBuilder()
				.fieldIndex(2)
				.fieldName("city")
				.displayName(
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_BILLING_CITY))
				.build());

		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(3)
						.fieldName("shippingcity")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_CITY))
						.build());

		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(4)
						.fieldName("state")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_STATE))
						.build());

		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(5)
						.fieldName("shippingstate")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_STATE))
						.build());

		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(6)
						.fieldName("postalcode")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_POSTAL_CODE))
						.build());

		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(7)
						.fieldName("shippingpostalcode")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_POSTAL_CODE))
						.build());

		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(8)
						.fieldName("billingcountry")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_COUNTRY))
						.build());

		addressSection
				.addField(new StringFieldBuilder()
						.fieldIndex(9)
						.fieldName("shippingcountry")
						.displayName(
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_COUNTRY))
						.build());

		form.addSection(addressSection);

		// build block description
		DynaSection descSection = new SectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).header("Description")
				.orderIndex(2).build();

		descSection.addField(new StringFieldBuilder()
				.fieldIndex(0)
				.fieldName("description")
				.displayName(
						LocalizationHelper
								.getMessage(GenericI18Enum.FORM_DESCRIPTION))
				.build());
		form.addSection(descSection);

	}
}
