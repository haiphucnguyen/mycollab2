package com.esofthead.mycollab.module.crm.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/contact")
@LocaleData({ @Locale("en_US") })
public enum ContactI18nEnum {
	FORM_NAME,
	FORM_ACCOUNTS,
	FORM_TITLE,
	FORM_DEPARTMENT,
	FORM_EMAIL,
	FORM_ASSISTANT,
	FORM_ASSISTANT_PHONE,
	FORM_MOBILE,
	FORM_FAX,
	FORM_BIRTHDAY,
	FORM_IS_CALLABLE
}
