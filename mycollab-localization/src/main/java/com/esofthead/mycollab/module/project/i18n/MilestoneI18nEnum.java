package com.esofthead.mycollab.module.project.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/milestone")
@LocaleData({ @Locale("en_US"), @Locale("ja_JP") })
public enum MilestoneI18nEnum {
	VIEW_LIST_TITLE,
	VIEW_NO_ITEM_TITLE,
	VIEW_NO_ITEM_HINT,
	
	BUTTON_NEW_PHASE,
	
	WIDGET_CLOSED_PHASE_TITLE,
	WIDGET_INPROGRESS_PHASE_TITLE,
	WIDGET_FUTURE_PHASE_TITLE,
	
	FORM_NEW_TITLE,
	FORM_EDIT_TITLE,
	FORM_NAME_FIELD,
	FORM_START_DATE_FIELD,
	FORM_END_DATE_FIELD,
	FORM_STATUS_FIELD, 
	FORM_TASK_FIELD,
	FORM_BUG_FIELD,
	
	TAB_RELATED_TASKS,
	TAB_RELATED_BUGS,
	
	MAIL_CREATE_ITEM_SUBJECT,
	MAIL_UPDATE_ITEM_SUBJECT,
	MAIL_COMMENT_ITEM_SUBJECT
}
