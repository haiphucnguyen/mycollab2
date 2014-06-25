package com.esofthead.mycollab.module.crm.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/case")
@LocaleData({ @Locale("en_US"), @Locale("ja_JP") })
public enum CaseI18nEnum {
	VIEW_NO_ITEM_TITLE,
	VIEW_NO_ITEM_HINT,
	VIEW_LIST_TITLE,
	VIEW_NEW_TITLE,

	SECTION_CASE_INFORMATION,
	SECTION_DESCRIPTION,

	BUTTON_NEW_CASE,

	FORM_PRIORITY,
	FORM_STATUS,
	FORM_ACCOUNT,
	FORM_ORIGIN,
	FORM_PHONE,
	FORM_TYPE,
	FORM_REASON,
	FORM_SUBJECT,
	FORM_EMAIL,
	FORM_RESOLUTION,

	MAIL_CREATE_ITEM_SUBJECT,
	MAIL_UPDATE_ITEM_SUBJECT,
	MAIL_COMMENT_ITEM_SUBJECT
}
