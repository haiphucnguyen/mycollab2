/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.ContactI18nEnum;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public interface ContactTableFieldDef {
	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id",
			UIConstants.TABLE_ACTION_CONTROL_WIDTH);

	public static TableViewField name = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_NAME),
			"contactName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField account = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_ACCOUNTS),
			"accountName", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField dicisionRole = new TableViewField(
			"Dicision Role", "decisionRole", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField title = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_TITLE), "title",
			UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField department = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_DEPARTMENT),
			"department", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField email = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_EMAIL), "email",
			UIConstants.TABLE_EMAIL_WIDTH);

	public static TableViewField assistant = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_ASSISTANT),
			"assistant", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assistantPhone = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_ASSISTANT_PHONE),
			"assistantphone", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField phoneOffice = new TableViewField(
			LocalizationHelper
					.getMessage(CrmCommonI18nEnum.FORM_PHONE_OFFICE_FIELD),
			"officephone", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField mobile = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_MOBILE),
			"mobile", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField fax = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_FAX), "fax",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField birthday = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_BIRTHDAY),
			"birthday", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField isCallable = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_IS_CALLABLE),
			"iscallable", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
			"assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

}
