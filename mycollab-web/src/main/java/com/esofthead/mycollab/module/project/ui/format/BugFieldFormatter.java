package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class BugFieldFormatter extends FieldGroupFormatter {
    private static BugFieldFormatter _instance = new BugFieldFormatter();

    private BugFieldFormatter() {
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
        generateFieldDisplayHandler("environment", BugI18nEnum.FORM_ENVIRONMENT);
        generateFieldDisplayHandler("summary", BugI18nEnum.FORM_SUMMARY);
        generateFieldDisplayHandler("status", BugI18nEnum.FORM_STATUS,
                new I18nHistoryFieldFormat(OptionI18nEnum.BugStatus.class));
        generateFieldDisplayHandler("priority", BugI18nEnum.FORM_PRIORITY,
                new I18nHistoryFieldFormat(OptionI18nEnum.BugPriority.class));
        generateFieldDisplayHandler("severity", BugI18nEnum.FORM_SEVERITY,
                new I18nHistoryFieldFormat(OptionI18nEnum.BugSeverity.class));
        generateFieldDisplayHandler("resolution", BugI18nEnum.FORM_RESOLUTION,
                new I18nHistoryFieldFormat(OptionI18nEnum.BugResolution.class));
        generateFieldDisplayHandler("estimateremaintime", BugI18nEnum.FORM_REMAIN_ESTIMATE);
        generateFieldDisplayHandler("duedate", BugI18nEnum.FORM_DUE_DATE, FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler("createdTime", BugI18nEnum.FORM_CREATED_TIME, FieldGroupFormatter.PRETTY_DATE_TIME_FIELD);
        generateFieldDisplayHandler("loguserFullName",
                BugI18nEnum.FORM_LOG_BY, new ProjectMemberHistoryFieldFormat());
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new ProjectMemberHistoryFieldFormat());
        generateFieldDisplayHandler("milestoneid", TaskI18nEnum.FORM_PHASE, new MilestoneHistoryFieldFormat());
    }

    public static BugFieldFormatter instance() {
        return _instance;
    }
}
