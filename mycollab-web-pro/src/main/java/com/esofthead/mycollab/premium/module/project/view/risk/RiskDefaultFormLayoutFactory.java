package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.DateDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.4
 *
 */
public class RiskDefaultFormLayoutFactory {

	private static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();
		
		DynaSection mainSection = new DynaSectionBuilder().layoutType(
				LayoutType.TWO_COLUMN).build();

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.riskname)
				.displayName(AppContext.getMessage(RiskI18nEnum.FORM_NAME))
				.fieldIndex(0).mandatory(true).required(true).colSpan(true)
				.build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.description)
				.displayName(
						AppContext.getMessage(RiskI18nEnum.FORM_DESCRIPTION))
				.fieldIndex(2).colSpan(true).required(true).build());

		mainSection
				.addField(new TextDynaFieldBuilder()
						.fieldName(Risk.Field.raisedbyuser)
						.displayName(
								AppContext
										.getMessage(RiskI18nEnum.FORM_RAISED_BY))
						.fieldIndex(4).build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.type)
				.displayName(AppContext.getMessage(RiskI18nEnum.FORM_RELATED))
				.fieldIndex(5).build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.assigntouser)
				.displayName(
						AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE))
				.fieldIndex(6).build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.consequence)
				.displayName(
						AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE))
				.fieldIndex(7).build());

		mainSection.addField(new DateDynaFieldBuilder()
				.fieldName(Risk.Field.datedue)
				.displayName(AppContext.getMessage(RiskI18nEnum.FORM_DATE_DUE))
				.fieldIndex(8).build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.probalitity)
				.displayName(
						AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY))
				.fieldIndex(9).build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.status)
				.displayName(AppContext.getMessage(RiskI18nEnum.FORM_STATUS))
				.fieldIndex(10).build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.level)
				.displayName(AppContext.getMessage(RiskI18nEnum.FORM_RATING))
				.fieldIndex(11).build());

		mainSection.addField(new TextDynaFieldBuilder()
				.fieldName(Risk.Field.response)
				.displayName(AppContext.getMessage(RiskI18nEnum.FORM_RESPONSE))
				.fieldIndex(12).colSpan(true).build());

		defaultForm.addSection(mainSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
