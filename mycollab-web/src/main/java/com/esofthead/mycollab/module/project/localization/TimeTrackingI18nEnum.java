package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/timetracking")
@LocaleData({ @Locale("en_US") })
public enum TimeTrackingI18nEnum {
	TIME_RECORD_HEADER, TASK_LIST_RANGE, TASK_LIST_RANGE_WITH_TOTAL_HOUR, SEARCH_TIME_TITLE
}
