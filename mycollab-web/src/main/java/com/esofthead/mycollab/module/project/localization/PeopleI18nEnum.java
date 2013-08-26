package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/people")
@LocaleData({ @Locale("en_US") })
public enum PeopleI18nEnum {
	NEW_USER_ACTION, NEW_ROLE_ACTION, CAN_NOT_DELETE_ROLE_MESSAGE
}
