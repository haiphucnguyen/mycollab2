package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/task")
@LocaleData({ @Locale("en_US") })
public enum TaskI18nEnum {
	NEW_PHASE_ACTION, NEW_TASKGROUP_ACTION, DISPLAY_GANTT_CHART_ACTION, REODER_TASKGROUP_ACTION, TABLE_TASK_NAME_HEADER, TABLE_START_DATE_HEADER, TABLE_DUE_DATE_HEADER, TABLE_PER_COMPLETE_HEADER, TABLE_ASSIGNEE_HEADER, TABLE_KEY_HEADER,
	FORM_TASKGROUP_RESPONSIBLE_USER,
	FILTER_ALL_TASK_GROUPS_TITLE, FILTER_ACTIVE_TASK_GROUPS_TITLE, FILTER_ARCHIEVED_TASK_GROUPS_TITLE,
	NEW_TASKGROUP_TITLE
}
