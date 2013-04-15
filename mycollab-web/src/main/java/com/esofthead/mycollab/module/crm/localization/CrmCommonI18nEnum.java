package com.esofthead.mycollab.module.crm.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/common")
@LocaleData({ @Locale("en_US") })
public enum CrmCommonI18nEnum {
	TABLE_OFFICE_PHONE_HEADER, TABLE_CREATED_DATE_HEADER, TABLE_ASSIGNED_USER_HEADER, TABLE_NAME_HEADER,
	TABLE_CITY_HEADER, TABLE_EMAIL_ADDRESS_HEADER, TABLE_ACCOUNT_NAME_HEADER, TABLE_ACTION_HEADER,
	TABLE_END_DATE_HEADER, TABLE_STATUS_HEADER, TABLE_TYPE_HEADER, TABLE_EXPECTED_REVENUE_HEADER,
	TABLE_SUBJECT_HEADER, TABLE_PRIORITY_HEADER, TABLE_TITLE_HEADER,
	TABLE_SELECTED_ITEM_TITLE,
	BUTTON_DELETE, BUTTON_MAIL, BUTTON_EXPORT, 
}
