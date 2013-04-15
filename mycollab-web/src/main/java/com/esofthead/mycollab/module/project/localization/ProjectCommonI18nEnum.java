package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/common")
@LocaleData({ @Locale("en_US") })
public enum ProjectCommonI18nEnum {
	DASHBOARD_TITLE, FEEDS_TITLE, NEWS_TITLE, TASKS_TITLE, NEW_PROJECT_ACTION, FEED_ADD_NEWS_ACTION, FEED_IN_PROJECT_LABEL,
	FEED_CREATE_ITEM_LBL, FEED_UPDATE_ITEM_LBL
}
