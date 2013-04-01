package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/bug")
@LocaleData({ @Locale("en_US") })
public enum BugI18nEnum {
	BUG_DASHBOARD_TITLE, MY_BUGS_WIDGET_TITLE, DUE_BUGS_WIDGET_TITLE, UPDATED_RECENTLY_WIDGET_TITLE, UNRESOLVED_BY_ASSIGNEE_WIDGET_TITLE, UNRESOLVED_BY_PRIORITY_WIDGET_TITLE, CHARTS_WIDGET_TITLE
}
