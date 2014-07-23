/**
 * This file is part of mycollab-localization.
 *
 * mycollab-localization is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-localization is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-localization.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/common/generic")
@LocaleData({ @Locale("en_US"), @Locale("ja_JP") })
public enum GenericI18Enum {
	NOTIFICATION_GOTO_FIRST_RECORD,
	NOTIFICATION_GOTO_LAST_RECORD,
	NOTIFICATION_RECORD_IS_NOT_EXISTED,
	NOTIFICATION_NO_PERMISSION_DO_TASK,
	
	WINDOW_ATTENTION_TITLE,
	WINDOW_INFORMATION_TITLE,
	WINDOW_WARNING_TITLE,
	WINDOW_ERROR_TITLE,
	
	BROWSER_PREVIEW_ITEM_TITLE,
	BROWSER_ADD_ITEM_TITLE,
	BROWSER_EDIT_ITEM_TITLE,
	
	ERROR_USER_NOTICE_INFORMATION_MESSAGE,
	ERROR_USER_INPUT_MESSAGE,
	
	BUTTON_MORE,
	BUTTON_LESS,
	BUTTON_VIEW,
	BUTTON_SAVE_LABEL,
	BUTTON_SAVE_NEW_LABEL,
	BUTTON_SUBMIT_LABEL,
	BUTTON_CREATE_LABEL,
	BUTTON_CANCEL_LABEL,
	BUTTON_DELETE_LABEL,
	BUTTON_CLOSE_LABEL,
	BUTTON_ASSIGN_LABEL,
	BUTTON_ADD_LABEL,
	BUTTON_CLONE_LABEL,
	BUTTON_OPTION_LABEL,
	BUTTON_REOPEN_LABEL,
	BUTTON_SELECT_LABEL,
	BUTTON_NEW_FILTER_LABEL,
	BUTTON_ADD_CRITERIA_LABEL,
	BUTTON_YES_LABEL,
	BUTTON_OK_LABEL,
	BUTTON_NO_LABEL,
	BUTTON_SEARCH_LABEL,
	BUTTON_CLEAR_LABEL,
	BUTTON_EDIT_LABEL,
	BUTTON_ACCEPT_LABEL,
	BUTTON_HISTORY_LABEL,
	BUTTON_MAIL,
	BUTTON_EXPORT_CSV,
	BUTTON_EXPORT_EXCEL,
	BUTTON_EXPORT_PDF,
	BUTTON_UPDATE_LABEL,
	BUTTON_SEARCH,
	BUTTON_ADVANCED_SEARCH,
	BUTTON_BASIC_SEARCH,
	BUTTON_POST_LABEL,
	BUTTON_SIGNOUT,
	BUTTON_NAV_NEWER,
	BUTTON_NAV_OLDER,
	
	TABLE_SELECTED_ITEM_TITLE,
	
	DIALOG_DELETE_TITLE,
	DIALOG_DELETE_SINGLE_ITEM_MESSAGE,
	DIALOG_DELETE_MULTIPLE_ITEMS_MESSAGE,
	
	CONFIRM_DELETE_ATTACHMENT,
	WINDOW_MASS_UPDATE_TITLE,
	WARNING_NOT_VALID_EMAIL,
	
	SEARCH_MYITEMS_CHECKBOX,
	SAVE_FILTER_VALUE,
	EXCEED_BILLING_PLAN_MSG_FOR_ADMIN,
	EXCEED_BILLING_PLAN_MSG_FOR_USER,

	FORM_ASSIGNEE,
	FORM_DESCRIPTION,
	FORM_CREATED_TIME,
	FORM_LAST_UPDATED_TIME,
	FORM_LAST_ACCESSED_TIME,
	FORM_EMPTY,

	MODULE_CRM,
	MODULE_PROJECT,
	MODULE_DOCUMENT,
	MODULE_PEOPLE,
	
	TOOLTIP_MASS_UPDATE,
	TOOLTIP_SHOW_PREVIOUS_ITEM,
	TOOLTIP_SHOW_NEXT_ITEM,
	TOOLTIP_NO_ITEM_EXISTED,
	
	EXT_ADDED_COMMENT,
	
	M_BUTTON_BACK,
	M_BUTTON_DONE
}
