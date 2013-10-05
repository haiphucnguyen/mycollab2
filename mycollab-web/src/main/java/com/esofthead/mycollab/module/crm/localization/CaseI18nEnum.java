package com.esofthead.mycollab.module.crm.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/case")
@LocaleData({ @Locale("en_US") })
public enum CaseI18nEnum {
	FORM_PRIORITY,
	FORM_STATUS,
	FORM_ACCOUNT,
	FORM_ORIGIN,
	FORM_PHONE,
	FORM_TYPE,
	FORM_REASON,
	FORM_SUBJECT,
	FORM_EMAIL,
	FORM_CREATED_TIME,
	FORM_LAST_UPDATED_TIME
}
