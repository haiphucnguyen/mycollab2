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
package com.esofthead.mycollab.module.user.accountsettings.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/accountsettings/role")
@LocaleData({ @Locale("en_US"), @Locale("ja_JP") })
public enum RoleI18nEnum {
	VIEW_LIST_TITLE,
	VIEW_NEW_TITLE,
	VIEW_EDIT_TITLE,
	
	FORM_PERMISSION_HEADER,
	SECTION_PROJECT_MANAGEMENT_TITLE,
	SECTION_CRM_TITLE,
	SECTION_DOCUMENT_TITLE,
	SECTION_ACCOUNT_MANAGEMENT_TITLE,
	
	FORM_NAME
}
