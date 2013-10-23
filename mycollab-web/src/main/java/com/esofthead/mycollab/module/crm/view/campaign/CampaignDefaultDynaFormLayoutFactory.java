package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.CurrencyDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DateDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.NumberDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.StringDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class CampaignDefaultDynaFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection campaignSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Campaign Information").build();

		campaignSection.addField(new StringDynaFieldBuilder()
				.fieldName("campaignname").displayName("Name").mandatory(true)
				.fieldIndex(0).build());

		campaignSection.addField(new StringDynaFieldBuilder()
				.fieldName("status").displayName("Status").fieldIndex(1)
				.build());

		campaignSection.addField(new DateDynaFieldBuilder()
				.fieldName("startdate").displayName("Start Date").fieldIndex(2)
				.build());

		campaignSection.addField(new StringDynaFieldBuilder().fieldName("type")
				.displayName("Type").fieldIndex(3).build());

		campaignSection.addField(new DateDynaFieldBuilder()
				.fieldName("enddate").displayName("End Date").fieldIndex(4)
				.build());

		campaignSection
				.addField(new StringDynaFieldBuilder()
						.fieldName("assignuser")
						.displayName(
								LocalizationHelper
										.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD))
						.fieldIndex(5).build());

		defaultForm.addSection(campaignSection);

		DynaSection goalSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(1)
				.header("Campaign Goal").build();

		goalSection.addField(new CurrencyDynaFieldBuilder()
				.fieldName("currencyid").displayName("Currency").fieldIndex(0)
				.build());

		goalSection.addField(new NumberDynaFieldBuilder()
				.fieldName("expectedcost").displayName("Expected Cost")
				.fieldIndex(2).build());

		goalSection.addField(new NumberDynaFieldBuilder().fieldName("budget")
				.displayName("Budget").fieldIndex(3).build());

		goalSection.addField(new NumberDynaFieldBuilder()
				.fieldName("expectedrevenue").displayName("Expected Revenue")
				.fieldIndex(4).build());

		goalSection.addField(new NumberDynaFieldBuilder()
				.fieldName("actualcost").displayName("Actual Cost")
				.fieldIndex(5).build());

		defaultForm.addSection(goalSection);

		DynaSection descSection = new DynaSectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).orderIndex(2)
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
