package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
public class MilestoneDefaultFormLayoutFactory {
    private static final DynaForm defaultForm;

    static {
        defaultForm = new DynaForm();

        DynaSection mainSection = new DynaSectionBuilder().layoutType(
                DynaSection.LayoutType.TWO_COLUMN).build();

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Milestone.Field.name).displayName(AppContext
                .getMessage(MilestoneI18nEnum.FORM_NAME_FIELD)).fieldIndex(0).mandatory(true).required(true).colSpan(true).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Milestone.Field.startdate).displayName(AppContext
                .getMessage(MilestoneI18nEnum.FORM_START_DATE_FIELD)).fieldIndex(1).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Milestone.Field.owner).displayName(AppContext
                .getMessage(GenericI18Enum.FORM_ASSIGNEE)).fieldIndex(2)
                .build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Milestone.Field.enddate).displayName(AppContext
                .getMessage(MilestoneI18nEnum.FORM_END_DATE_FIELD)).fieldIndex(3)
                .build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Milestone.Field.status).displayName(AppContext
                .getMessage(MilestoneI18nEnum.FORM_STATUS_FIELD)).fieldIndex(4)
                .build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Milestone.Field.description).displayName(AppContext
                .getMessage(GenericI18Enum.FORM_DESCRIPTION))
                .fieldIndex(5)
                .colSpan(true).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Milestone.Field.id).displayName(AppContext
                .getMessage(MilestoneI18nEnum.FORM_ASSIGNMENTS)).fieldIndex(6).colSpan(true).build());

        defaultForm.addSection(mainSection);
    }

    public static DynaForm getForm() {
        return defaultForm;
    }
}
