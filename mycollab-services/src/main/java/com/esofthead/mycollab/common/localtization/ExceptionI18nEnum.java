package com.esofthead.mycollab.common.localtization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/exception")
@LocaleData({ @Locale("en_US") })
public enum ExceptionI18nEnum {
	EXISTING_USER_REGISTER_ERROR, EXISTING_DOMAIN_REGISTER_ERROR, EXISTING_EMAIL_REGISTER_ERROR
}
