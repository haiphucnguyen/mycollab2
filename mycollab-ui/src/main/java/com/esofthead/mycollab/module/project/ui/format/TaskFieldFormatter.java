package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.SimpleLogging;
import com.esofthead.mycollab.core.utils.HumanTime;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import com.esofthead.mycollab.utils.HistoryFieldFormat;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public final class TaskFieldFormatter extends FieldGroupFormatter {
    private static TaskFieldFormatter _instance = new TaskFieldFormatter();

    private TaskFieldFormatter() {
        generateFieldDisplayHandler("taskname", TaskI18nEnum.FORM_TASK_NAME);
        generateFieldDisplayHandler("startdate", TaskI18nEnum.FORM_START_DATE, FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler("enddate", TaskI18nEnum.FORM_END_DATE, FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler("actualstartdate", TaskI18nEnum.FORM_ACTUAL_START_DATE, FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler("actualenddate", TaskI18nEnum.FORM_ACTUAL_END_DATE, FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler("deadline", TaskI18nEnum.FORM_DEADLINE, FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler("priority", TaskI18nEnum.FORM_PRIORITY,
                new I18nHistoryFieldFormat(OptionI18nEnum.TaskPriority.class));
        generateFieldDisplayHandler("status", TaskI18nEnum.FORM_STATUS,
                new I18nHistoryFieldFormat(com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum.class));
        generateFieldDisplayHandler("isestimated", TaskI18nEnum.FORM_IS_ESTIMATED);
        generateFieldDisplayHandler("remainestimate", TaskI18nEnum.FORM_REMAIN_ESTIMATE);
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new ProjectMemberHistoryFieldFormat());
        generateFieldDisplayHandler("milestoneid", TaskI18nEnum.FORM_MILESTONE, new MilestoneHistoryFieldFormat());
        generateFieldDisplayHandler("percentagecomplete", TaskI18nEnum.FORM_PERCENTAGE_COMPLETE);
        generateFieldDisplayHandler("notes", TaskI18nEnum.FORM_NOTES);
        generateFieldDisplayHandler(Task.Field.duration.name(), TaskI18nEnum.FORM_DURATION, new DurationFieldFormat());
    }

    private static class DurationFieldFormat implements HistoryFieldFormat {

        @Override
        public String toString(String value) {
            if (StringUtils.isNotBlank(value)) {
                try {
                    long duration = Long.parseLong(value);
                    HumanTime humanTime = new HumanTime(duration);
                    return humanTime.getExactly();
                } catch (Exception e) {
                    SimpleLogging.error("Parse value failed " + value, e);
                    return "";
                }
            }
            return "";
        }
    }

    public static TaskFieldFormatter instance() {
        return _instance;
    }
}
