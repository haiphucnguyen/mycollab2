package com.esofthead.mycollab.common.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/exception")
@LocaleData({ @Locale("en_US") })
public enum ExceptionI18nEnum {
	SUB_DOMAIN_IS_NOT_EXISTED, NOT_SUPPORT_SENDING_EMAIL_TO_ALL_USERS
}
