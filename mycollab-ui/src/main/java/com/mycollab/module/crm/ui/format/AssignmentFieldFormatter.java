package com.mycollab.module.crm.ui.format;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.i18n.OptionI18nEnum.TaskPriority;
import com.mycollab.module.crm.i18n.OptionI18nEnum.TaskStatus;
import com.mycollab.module.crm.i18n.TaskI18nEnum;
import com.mycollab.module.user.ui.format.UserHistoryFieldFormat;
import com.mycollab.vaadin.ui.formatter.FieldGroupFormatter;
import com.mycollab.vaadin.ui.formatter.I18nHistoryFieldFormat;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public final class AssignmentFieldFormatter extends FieldGroupFormatter {
    private static final AssignmentFieldFormatter _instance = new AssignmentFieldFormatter();

    private AssignmentFieldFormatter() {
        generateFieldDisplayHandler("subject", TaskI18nEnum.FORM_SUBJECT);
        generateFieldDisplayHandler("startdate", GenericI18Enum.FORM_START_DATE, DATETIME_FIELD);
        generateFieldDisplayHandler("duedate", GenericI18Enum.FORM_DUE_DATE, DATETIME_FIELD);
        generateFieldDisplayHandler("status", GenericI18Enum.FORM_STATUS, new I18nHistoryFieldFormat(TaskStatus.class));
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new UserHistoryFieldFormat());
        generateFieldDisplayHandler("priority", TaskI18nEnum.FORM_PRIORITY, new I18nHistoryFieldFormat(TaskPriority.class));
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
        generateFieldDisplayHandler("contactName", TaskI18nEnum.FORM_CONTACT);
    }

    public static AssignmentFieldFormatter instance() {
        return _instance;
    }
}
