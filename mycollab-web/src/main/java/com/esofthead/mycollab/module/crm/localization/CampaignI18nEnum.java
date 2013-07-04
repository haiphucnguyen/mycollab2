package com.esofthead.mycollab.module.crm.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/campaign")
@LocaleData({ @Locale("en_US") })
public enum CampaignI18nEnum {
	FORM_CAMPAIGN_NAME, FORM_STATUS, FORM_START_DATE, FORM_END_DATE, FORM_TYPE, FORM_ASSIGN_USER,
	FORM_EXPECTED_REVENUE
}
