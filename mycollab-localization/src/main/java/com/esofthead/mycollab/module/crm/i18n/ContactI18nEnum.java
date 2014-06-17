package com.esofthead.mycollab.module.crm.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/contact")
@LocaleData({ @Locale("en_US") })
public enum ContactI18nEnum {
	NO_ITEM_VIEW_TITLE,
	NO_ITEM_VIEW_HINT,
	
	LIST_VIEW_TITLE,
	
	FORM_NEW_TITLE,
	
	SECTION_INFORMATION,
	SECTION_ADDRESS,
	SECTION_DESCRIPTION,
	
	FORM_DECISION_ROLE,
	FORM_FIRSTNAME,
	FORM_LASTNAME,
	FORM_NAME,
	FORM_ACCOUNTS,
	FORM_TITLE,
	FORM_DEPARTMENT,
	FORM_EMAIL,
	FORM_ASSISTANT,
	FORM_ASSISTANT_PHONE,
	FORM_MOBILE,
	FORM_HOME_PHONE,
	FORM_FAX,
	FORM_BIRTHDAY,
	FORM_IS_CALLABLE,
	FORM_OTHER_PHONE,
	FORM_LEAD_SOURCE, 
	FORM_PRIMARY_ADDRESS,
	FORM_PRIMARY_CITY,
	FORM_PRIMARY_STATE,
	FORM_PRIMARY_POSTAL_CODE,
	FORM_PRIMARY_COUNTRY,
	FORM_OTHER_ADDRESS,
	FORM_OTHER_CITY,
	FORM_OTHER_STATE,
	FORM_OTHER_POSTAL_CODE,
	FORM_OTHER_COUNTRY,
	FORM_DESCRIPTION,
	
	MAIL_CREATE_ITEM_SUBJECT,
	MAIL_UPDATE_ITEM_SUBJECT,
	MAIL_COMMENT_ITEM_SUBJECT
}
