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
	TABLE_SELECTED_ITEM_TITLE, SEARCH_MYITEMS_CHECKBOX,
	BUTTON_DELETE, BUTTON_MAIL, BUTTON_EXPORT, BUTTON_MASSUPDATE, BUTTON_SEARCH, BUTTON_CLEAR, BUTTON_ADVANCED_SEARCH, BUTTON_BASIC_SEARCH,
	WIDGET_ACTIVITY_CREATE_ACTION, WIDGET_ACTIVITY_UPDATE_ACTION, 
	DIALOG_DELETE_RELATIONSHIP_TITLE,
	TOOLBAR_ACCOUNTS_HEADER, TOOLBAR_CASES_HEADER, TOOLBAR_CONTACTS_HEADER, TOOLBAR_CAMPAIGNS_HEADER, TOOLBAR_LEADS_HEADER,
	TOOLBAR_OPPORTUNTIES_HEADER, TOOLBAR_ACTIVITIES_HEADER,
	TOOLBAR_DOCUMENT_HEADER,
	TOOLBAR_ACCOUNT_NEW_ACTION, TOOLBAR_CASE_NEW_ACTION, TOOLBAR_CONTACT_NEW_ACTION, TOOLBAR_CAMPAIGN_NEW_ACTION, TOOLBAR_LEAD_NEW_ACTION,
	TOOLBAR_OPPORTUNITY_NEW_ACTION, TOOLBAR_CALL_NEW_ACTION, TOOLBAR_MEETING_NEW_ACTION, TOOLBAR_TASK_NEW_ACTION
}
