package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.builder.DynaSectionBuilder;
import com.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.i18n.RiskI18nEnum;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class RiskDefaultFormLayoutFactory {
    public static DynaForm getForm() {
        DynaForm defaultForm = new DynaForm();
        DynaSection riskSection = new DynaSectionBuilder().layoutType(DynaSection.LayoutType.ONE_COLUMN).orderIndex(0)
                .header(RiskI18nEnum.SINGLE).build();

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.name.name())
                .displayName(GenericI18Enum.FORM_NAME)
                .customField(false).fieldIndex(0).mandatory(true)
                .required(true).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.description.name())
                .displayName(GenericI18Enum.FORM_DESCRIPTION)
                .customField(false).fieldIndex(1).mandatory(true)
                .required(true).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.createduser.name())
                .displayName(RiskI18nEnum.FORM_RAISED_BY)
                .customField(false).fieldIndex(2).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.assignuser.name())
                .displayName(GenericI18Enum.FORM_ASSIGNEE)
                .customField(false).fieldIndex(3).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.startdate.name())
                .displayName(GenericI18Enum.FORM_START_DATE)
                .customField(false).fieldIndex(4).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.enddate.name())
                .displayName(GenericI18Enum.FORM_END_DATE)
                .customField(false).fieldIndex(5).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.duedate.name())
                .displayName(GenericI18Enum.FORM_DUE_DATE)
                .customField(false).fieldIndex(6).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.milestoneid.name())
                .displayName(MilestoneI18nEnum.SINGLE)
                .customField(false).fieldIndex(7).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.status.name())
                .displayName(GenericI18Enum.FORM_STATUS)
                .customField(false).fieldIndex(8).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.consequence.name())
                .displayName(RiskI18nEnum.FORM_CONSEQUENCE)
                .customField(false).fieldIndex(9).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.probalitity.name())
                .displayName(RiskI18nEnum.FORM_PROBABILITY)
                .customField(false).fieldIndex(10).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.priority.name())
                .displayName(GenericI18Enum.FORM_PRIORITY)
                .customField(false).fieldIndex(11).mandatory(false)
                .required(false).build());

        riskSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.response.name())
                .displayName(RiskI18nEnum.FORM_RESPONSE)
                .customField(false).fieldIndex(12).mandatory(false)
                .required(false).build());

        defaultForm.sections(riskSection);
        return defaultForm;
    }
}
