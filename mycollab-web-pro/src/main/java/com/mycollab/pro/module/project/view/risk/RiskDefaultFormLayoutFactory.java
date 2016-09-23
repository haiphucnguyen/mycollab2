package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.builder.DateDynaFieldBuilder;
import com.mycollab.form.view.builder.DynaSectionBuilder;
import com.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.i18n.RiskI18nEnum;

/**
 * @author MyCollab Ltd.
 * @since 4.5.4
 */
public class RiskDefaultFormLayoutFactory {

    private static final DynaForm defaultForm;

    static {
        defaultForm = new DynaForm();

        DynaSection mainSection = new DynaSectionBuilder().layoutType(LayoutType.TWO_COLUMN).build();

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.name)
                .displayName(GenericI18Enum.FORM_NAME)
                .fieldIndex(0).mandatory(true).required(true).colSpan(true)
                .build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.description)
                .displayName(GenericI18Enum.FORM_DESCRIPTION)
                .fieldIndex(2).colSpan(true).required(true).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.raisedbyuser)
                .displayName(RiskI18nEnum.FORM_RAISED_BY)
                .fieldIndex(4).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.milestoneid)
                .displayName(RiskI18nEnum.FORM_PHASE)
                .fieldIndex(5).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.assignuser)
                .displayName(GenericI18Enum.FORM_ASSIGNEE)
                .fieldIndex(6).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.status)
                .displayName(GenericI18Enum.FORM_STATUS)
                .fieldIndex(7).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.startdate)
                .displayName(GenericI18Enum.FORM_START_DATE)
                .fieldIndex(8).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.consequence)
                .displayName(RiskI18nEnum.FORM_CONSEQUENCE)
                .fieldIndex(9).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.enddate)
                .displayName(GenericI18Enum.FORM_END_DATE)
                .fieldIndex(10).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.probalitity)
                .displayName(RiskI18nEnum.FORM_PROBABILITY)
                .fieldIndex(11).build());

        mainSection.fields(new DateDynaFieldBuilder().fieldName(Risk.Field.datedue)
                .displayName(GenericI18Enum.FORM_DUE_DATE)
                .fieldIndex(12).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.priority)
                .displayName(GenericI18Enum.FORM_PRIORITY)
                .fieldIndex(13).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.response)
                .displayName(RiskI18nEnum.FORM_RESPONSE)
                .fieldIndex(14).colSpan(true).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Risk.Field.id)
                .displayName(GenericI18Enum.FORM_ATTACHMENTS)
                .colSpan(true).fieldIndex(15).build());

        defaultForm.sections(mainSection);
    }

    public static DynaForm getForm() {
        return defaultForm;
    }
}
