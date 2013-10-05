package com.esofthead.mycollab.module.crm.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/task")
@LocaleData({ @Locale("en_US") })
public enum TaskI18nEnum {
	TABLE_START_DATE_HEADER,
	TABLE_SUBJECT_HEADER,
	TABLE_TYPE_HEADER,
	TABLE_END_DATE_HEADER
}
