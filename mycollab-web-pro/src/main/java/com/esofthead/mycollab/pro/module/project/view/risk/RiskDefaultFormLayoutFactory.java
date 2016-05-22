package com.esofthead.mycollab.pro.module.project.view.risk;

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
 * @author MyCollab Ltd.
 * @since 4.5.4
 */
public class RiskDefaultFormLayoutFactory {

    private static final DynaForm defaultForm;

    static {
        defaultForm = new DynaForm();

        DynaSection mainSection = new DynaSectionBuilder().layoutType(LayoutType.TWO_COLUMN).build();

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.riskname)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_NAME))
                .fieldIndex(0).mandatory(true).required(true).colSpan(true)
                .build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.description)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION))
                .fieldIndex(2).colSpan(true).required(true).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.raisedbyuser)
                .displayName(AppContext.getMessage(RiskI18nEnum.FORM_RAISED_BY))
                .fieldIndex(4).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.milestoneid)
                .displayName(AppContext.getMessage(RiskI18nEnum.FORM_PHASE))
                .fieldIndex(5).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.assigntouser)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE))
                .fieldIndex(6).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.status)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_STATUS))
                .fieldIndex(7).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.startdate)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .fieldIndex(8).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.consequence)
                .displayName(AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE))
                .fieldIndex(9).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.enddate)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .fieldIndex(10).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.probalitity)
                .displayName(AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY))
                .fieldIndex(11).build());

        mainSection.addFields(new DateDynaFieldBuilder().fieldName(Risk.Field.datedue)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_DUE_DATE))
                .fieldIndex(12).build());

        mainSection.addFields(new TextDynaFieldBuilder()
                .fieldName(Risk.Field.level)
                .displayName(AppContext.getMessage(RiskI18nEnum.FORM_RATING))
                .fieldIndex(13).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.response)
                .displayName(AppContext.getMessage(RiskI18nEnum.FORM_RESPONSE))
                .fieldIndex(14).colSpan(true).build());

        mainSection.addFields(new TextDynaFieldBuilder().fieldName(Risk.Field.id)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_ATTACHMENTS))
                .colSpan(true).fieldIndex(15).build());

        defaultForm.addSections(mainSection);
    }

    public static DynaForm getForm() {
        return defaultForm;
    }
}
