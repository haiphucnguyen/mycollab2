package com.esofthead.mycollab.module.crm.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.TaskI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public final class AssignmentFieldFormatter extends FieldGroupFormatter {
    private static final AssignmentFieldFormatter _instance = new AssignmentFieldFormatter();

    private AssignmentFieldFormatter() {
        generateFieldDisplayHandler("subject", TaskI18nEnum.FORM_SUBJECT);
        generateFieldDisplayHandler("startdate", TaskI18nEnum.FORM_START_DATE);
        generateFieldDisplayHandler("duedate", TaskI18nEnum.FORM_DUE_DATE);
        generateFieldDisplayHandler("status", TaskI18nEnum.FORM_STATUS);
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE);
        generateFieldDisplayHandler("priority", TaskI18nEnum.FORM_PRIORITY);
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
    }

    public static AssignmentFieldFormatter instance() {
        return _instance;
    }
}
