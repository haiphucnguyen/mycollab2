/**
 * mycollab-ui - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.ui.format

import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.crm.i18n.LeadI18nEnum
import com.mycollab.module.crm.i18n.OptionI18nEnum
import com.mycollab.module.user.ui.format.UserHistoryFieldFormat
import com.mycollab.vaadin.ui.formatter.CountryHistoryFieldFormat
import com.mycollab.vaadin.ui.formatter.FieldGroupFormatter
import com.mycollab.vaadin.ui.formatter.I18nHistoryFieldFormat

import com.mycollab.module.crm.i18n.OptionI18nEnum.*

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
class LeadFieldFormatter private constructor() : FieldGroupFormatter() {

    init {
        generateFieldDisplayHandler("prefixname", LeadI18nEnum.FORM_PREFIX)
        generateFieldDisplayHandler("firstname", GenericI18Enum.FORM_FIRSTNAME)
        generateFieldDisplayHandler("lastname", GenericI18Enum.FORM_LASTNAME)
        generateFieldDisplayHandler("title", LeadI18nEnum.FORM_TITLE)
        generateFieldDisplayHandler("department", LeadI18nEnum.FORM_DEPARTMENT)
        generateFieldDisplayHandler("accountname", LeadI18nEnum.FORM_ACCOUNT_NAME)
        generateFieldDisplayHandler("source", LeadI18nEnum.FORM_LEAD_SOURCE, I18nHistoryFieldFormat(OpportunityLeadSource::class.java))
        generateFieldDisplayHandler("industry", LeadI18nEnum.FORM_INDUSTRY, I18nHistoryFieldFormat(AccountIndustry::class.java))
        generateFieldDisplayHandler("noemployees", LeadI18nEnum.FORM_NO_EMPLOYEES)
        generateFieldDisplayHandler("email", GenericI18Enum.FORM_EMAIL)
        generateFieldDisplayHandler("officephone", LeadI18nEnum.FORM_OFFICE_PHONE)
        generateFieldDisplayHandler("mobile", LeadI18nEnum.FORM_MOBILE)
        generateFieldDisplayHandler("otherphone", LeadI18nEnum.FORM_OTHER_PHONE)
        generateFieldDisplayHandler("fax", LeadI18nEnum.FORM_FAX)
        generateFieldDisplayHandler("website", LeadI18nEnum.FORM_WEBSITE)
        generateFieldDisplayHandler("status", GenericI18Enum.FORM_STATUS, I18nHistoryFieldFormat(LeadStatus::class.java))
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, UserHistoryFieldFormat())
        generateFieldDisplayHandler("primaddress", LeadI18nEnum.FORM_PRIMARY_ADDRESS)
        generateFieldDisplayHandler("primcity", LeadI18nEnum.FORM_PRIMARY_CITY)
        generateFieldDisplayHandler("primstate", LeadI18nEnum.FORM_PRIMARY_STATE)
        generateFieldDisplayHandler("primpostalcode", LeadI18nEnum.FORM_PRIMARY_POSTAL_CODE)
        generateFieldDisplayHandler("primcountry", LeadI18nEnum.FORM_PRIMARY_COUNTRY, CountryHistoryFieldFormat())
        generateFieldDisplayHandler("otheraddress", LeadI18nEnum.FORM_OTHER_ADDRESS)
        generateFieldDisplayHandler("othercity", LeadI18nEnum.FORM_OTHER_CITY)
        generateFieldDisplayHandler("otherstate", LeadI18nEnum.FORM_OTHER_STATE)
        generateFieldDisplayHandler("otherpostalcode", LeadI18nEnum.FORM_OTHER_POSTAL_CODE)
        generateFieldDisplayHandler("othercountry", LeadI18nEnum.FORM_OTHER_COUNTRY, CountryHistoryFieldFormat())
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION)
    }

    companion object {
        private val _instance = LeadFieldFormatter()

        fun instance(): LeadFieldFormatter {
            return _instance
        }
    }
}
