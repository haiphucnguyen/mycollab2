package com.esofthead.mycollab.common.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/generic")
@LocaleData({ @Locale("en_US") })
public enum GenericI18Enum {
	APPLICATION_CAPTION, INFORMATION_GOTO_FIRST_RECORD, INFORMATION_GOTO_LAST_RECORD, INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE, INFORMATION_WINDOW_TITLE, WARNING_WINDOW_TITLE, ERROR_USER_NOTICE_INFORMATION_MESSAGE, ERROR_USER_INPUT_MESSAGE,
	EXISTING_USER_REGISTER_ERROR
}
