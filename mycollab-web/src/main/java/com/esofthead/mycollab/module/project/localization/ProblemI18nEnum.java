package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/problem")
@LocaleData({ @Locale("en_US") })
public enum ProblemI18nEnum {
	NEW_PROBLEM_ACTION, FORM_NAME, FORM_DESCRIPTION, FORM_RAISED_BY, FORM_ASSIGN_USER, FORM_DATE_DUE, FORM_STATUS, FORM_IMPACT, FORM_PRIORITY, FORM_RATING
}
