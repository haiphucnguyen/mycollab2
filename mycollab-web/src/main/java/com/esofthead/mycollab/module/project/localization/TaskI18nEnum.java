package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/task")
@LocaleData({ @Locale("en_US") })
public enum TaskI18nEnum {
	NEW_PHASE_ACTION, NEW_TASKGROUP_ACTION, DISPLAY_GANTT_CHART_ACTION, REODER_TASKGROUP_ACTION
}
