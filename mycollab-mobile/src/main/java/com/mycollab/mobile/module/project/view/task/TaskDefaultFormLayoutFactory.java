package com.mycollab.mobile.module.project.view.task;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.builder.DynaSectionBuilder;
import com.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.i18n.TaskI18nEnum;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class TaskDefaultFormLayoutFactory {
    public static DynaForm getForm() {
        DynaForm defaultForm = new DynaForm();
        DynaSection taskSection = new DynaSectionBuilder().layoutType(DynaSection.LayoutType.ONE_COLUMN).orderIndex(0).build();

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.name.name())
                .displayName(GenericI18Enum.FORM_NAME)
                .customField(false).fieldIndex(0).mandatory(true)
                .required(true).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.status.name())
                .displayName(GenericI18Enum.FORM_STATUS)
                .customField(false).fieldIndex(1).mandatory(true)
                .required(true).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.startdate.name())
                .displayName(GenericI18Enum.FORM_START_DATE)
                .customField(false).fieldIndex(2).mandatory(false)
                .required(false).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.enddate.name())
                .displayName(GenericI18Enum.FORM_END_DATE)
                .customField(false).fieldIndex(3).mandatory(false)
                .required(false).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.duedate.name())
                .displayName(GenericI18Enum.FORM_DUE_DATE)
                .customField(false).fieldIndex(4).mandatory(false)
                .required(false).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.priority.name())
                .displayName(GenericI18Enum.FORM_PRIORITY)
                .customField(false).fieldIndex(5).mandatory(false)
                .required(false).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.assignuser.name())
                .displayName(GenericI18Enum.FORM_ASSIGNEE)
                .customField(false).fieldIndex(6).mandatory(false)
                .required(false).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.milestoneid.name())
                .displayName(MilestoneI18nEnum.SINGLE)
                .customField(false).fieldIndex(7).mandatory(false)
                .required(false).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.percentagecomplete.name())
                .displayName(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE)
                .customField(false).fieldIndex(8).mandatory(false)
                .required(false).build());

        taskSection.fields(new TextDynaFieldBuilder().fieldName(Task.Field.description.name())
                .displayName(GenericI18Enum.FORM_DESCRIPTION)
                .customField(false).fieldIndex(9).mandatory(false)
                .required(false).build());

        defaultForm.sections(taskSection);
        return defaultForm;
    }
}
