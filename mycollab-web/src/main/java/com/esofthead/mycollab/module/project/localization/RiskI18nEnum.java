package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/risk")
@LocaleData({ @Locale("en_US") })
public enum RiskI18nEnum {
	NEW_RISK_ACTION,
	FORM_NAME,
	FORM_DESCRIPTION,
	FORM_RAISED_BY,
	FORM_DATE_DUE,
	FORM_STATUS,
	FORM_RESPONSE,
	FORM_CONSEQUENCE,
	FORM_PROBABILITY,
	FORM_RATING
}
