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
package com.esofthead.mycollab.module.crm.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/crm/account")
@LocaleData({ @Locale("en_US"), @Locale("ja_JP") })
public enum AccountI18nEnum {
	VIEW_NO_ITEM_TITLE,
	VIEW_NO_ITEM_HINT,
	VIEW_LIST_TITLE,
	VIEW_NEW_TITLE,
	
	BUTTON_NEW_ACCOUNT,
	
	SECTION_ACCOUNT_INFORMATION,
	SECTION_ADDRESS_INFORMATION,
	SECTION_DESCRIPTION,
	
	FORM_ACCOUNT_NAME,
	FORM_WEBSITE,
	FORM_FAX,
	FORM_EMPLOYEES,
	FORM_OFFICE_PHONE,
	FORM_OTHER_PHONE,
	FORM_INDUSTRY,
	FORM_EMAIL,
	FORM_TYPE,
	FORM_OWNERSHIP,
	FORM_ANNUAL_REVENUE,
	FORM_BILLING_ADDRESS,
	FORM_SHIPPING_ADDRESS,
	FORM_BILLING_CITY,
	FORM_SHIPPING_CITY,
	FORM_BILLING_STATE,
	FORM_SHIPPING_STATE,
	FORM_BILLING_POSTAL_CODE,
	FORM_SHIPPING_POSTAL_CODE,
	FORM_BILLING_COUNTRY,
	FORM_SHIPPING_COUNTRY,
	FORM_COPY_ADDRESS,
	FORM_ANY_PHONE,
	FORM_ANY_CITY,
	
	ERROR_ACCOUNT_NAME_IS_NULL,
	
	MAIL_CREATE_ITEM_SUBJECT,
	MAIL_UPDATE_ITEM_SUBJECT,
	MAIL_COMMENT_ITEM_SUBJECT,
	MAIL_CREATE_ITEM_HEADING,
	MAIL_UPDATE_ITEM_HEADING,
	MAIL_COMMENT_ITEM_HEADING
}
