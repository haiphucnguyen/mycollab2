/**
 * This file is part of mycollab-ui.
 *
 * mycollab-ui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-ui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-ui.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/generic")
@LocaleData({ @Locale("en_US"), @Locale("ja_JP") })
public enum GenericI18Enum {
	INFORMATION_GOTO_FIRST_RECORD,
	INFORMATION_GOTO_LAST_RECORD,
	INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE,
	ATTENTION_WINDOW_TITLE,
	BROWSER_PREVIEW_ITEM_TITLE,
	BROWSER_ADD_ITEM_TITLE,
	BROWSER_EDIT_ITEM_TITLE,
	BROWSER_LIST_ITEMS_TITLE,
	ERROR_USER_NOTICE_INFORMATION_MESSAGE,
	ERROR_USER_INPUT_MESSAGE,
	BUTTON_VIEW_LABEL,
	BUTTON_SAVE_LABEL,
	BUTTON_SAVE_NEW_LABEL,
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
	DELETE_DIALOG_TITLE,
	DELETE_SINGLE_ITEM_DIALOG_MESSAGE,
	DELETE_MULTIPLE_ITEMS_DIALOG_MESSAGE,
	CONFIRM_DELETE_RECORD_DIALOG_MESSAGE,
	BUTTON_YES_LABEL,
	BUTTON_OK_LABEL,
	BUTTON_NO_LABEL,
	FORM_DESCRIPTION,
	BUTTON_SEARCH_LABEL,
	BUTTON_CLEAR_LABEL,
	CONFIRM_DELETE_ATTACHMENT,
	MASS_UPDATE_WINDOW_TITLE,
	WARNING_NOT_VALID_EMAIL,
	BUTTON_EDIT_LABEL,
	BUTTON_ACCEPT_LABEL,
	BUTTON_HISTORY_LABEL,
	BUTTON_MAIL,
	BUTTON_EXPORT_CSV,
	BUTTON_EXPORT_EXCEL,
	BUTTON_EXPORT_PDF,
	BUTTON_UPDATE_LABEL,
	MASS_UPDATE_TOOLTIP,
	BUTTON_SEARCH,
	BUTTON_ADVANCED_SEARCH,
	BUTTON_BASIC_SEARCH,
	BUTTON_POST_LABEL,
	SEARCH_MYITEMS_CHECKBOX,
	SAVE_FILTER_VALUE,
	FORM_ASSIGNEE_FIELD,
	EXCEED_BILLING_PLAN_MSG_FOR_ADMIN,
	EXCEED_BILLING_PLAN_MSG_FOR_USER,
	CHOOSE_SUPPORT_FILE_TYPES_WARNING,

	SHOW_PREVIOUS_ITEM_TOOLTIP,
	SHOW_NEXT_ITEM_TOOLTIP,

	CRM_MODULE,
	PROJECT_MODULE,
	DOCUMENT_MODULE
}
