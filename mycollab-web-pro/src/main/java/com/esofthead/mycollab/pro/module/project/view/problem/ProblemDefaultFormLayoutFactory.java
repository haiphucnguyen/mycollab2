package com.esofthead.mycollab.pro.module.project.view.problem;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd.
 * @since 4.5.4
 */
public class ProblemDefaultFormLayoutFactory {

    private static final DynaForm defaultForm;

    static {
        defaultForm = new DynaForm();

        DynaSection mainSection = new DynaSectionBuilder().layoutType(
                LayoutType.TWO_COLUMN).build();

        mainSection.addField(new TextDynaFieldBuilder()
                .fieldName(Problem.Field.issuename)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_NAME))
                .fieldIndex(0).mandatory(true).required(true).colSpan(true)
                .build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.description)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION))
                .fieldIndex(1).mandatory(true).required(true).colSpan(true)
                .build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.raisedbyuser)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_RAISED_BY))
                .fieldIndex(2).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.type)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_RELATED))
                .fieldIndex(3).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.assigntouser)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE))
                .fieldIndex(4).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.impact)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_IMPACT)).fieldIndex(5).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.datedue)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_DATE_DUE))
                .fieldIndex(6).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.priority)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_PRIORITY))
                .fieldIndex(7).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.status)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_STATUS))
                .fieldIndex(8).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.level)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_RATING))
                .fieldIndex(9).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Problem.Field.resolution)
                .displayName(AppContext.getMessage(ProblemI18nEnum.FORM_RESOLUTION))
                .fieldIndex(10).colSpan(true).build());

        defaultForm.addSection(mainSection);
    }

    public static DynaForm getForm() {
        return defaultForm;
    }
}
