/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.domain.criteria

import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.db.arguments.StringSearchField
import com.mycollab.db.query.*
import com.mycollab.module.crm.CrmDataTypeFactory
import com.mycollab.module.crm.CrmTypeConstants
import com.mycollab.module.crm.i18n.CaseI18nEnum

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class CaseSearchCriteria : SearchCriteria() {

    var subject: StringSearchField? = null

    var assignUser: StringSearchField? = null

    var accountId: NumberSearchField? = null

    var contactId: NumberSearchField? = null

    var statuses: SetSearchField<String>? = null

    var priorities: SetSearchField<String>? = null

    var assignUsers: SetSearchField<String>? = null

    var id: NumberSearchField? = null

    companion object {
        private val serialVersionUID = 1L

        @JvmField val p_priority: Param = CacheParamMapper.register(CrmTypeConstants.CASE, CaseI18nEnum.FORM_PRIORITY,
                I18nStringListParam("priority", "m_crm_case", "priority", listOf(*CrmDataTypeFactory.casesPriorities)))

        @JvmField val p_account: Param = CacheParamMapper.register(CrmTypeConstants.CASE, CaseI18nEnum.FORM_ACCOUNT,
                PropertyParam("account", "m_crm_case", "accountId"))

        @JvmField val p_status: Param = CacheParamMapper.register(CrmTypeConstants.CASE, GenericI18Enum.FORM_STATUS,
                I18nStringListParam("status", "m_crm_case", "status", listOf(*CrmDataTypeFactory.casesStatuses)))

        @JvmField val p_type: Param = CacheParamMapper.register(CrmTypeConstants.CASE, GenericI18Enum.FORM_TYPE,
                I18nStringListParam("type", "m_crm_case", "type", listOf(*CrmDataTypeFactory.casesTypes)))

        @JvmField val p_reason: Param = CacheParamMapper.register(CrmTypeConstants.CASE, CaseI18nEnum.FORM_REASON,
                I18nStringListParam("reason", "m_crm_case", "reason", listOf(*CrmDataTypeFactory.casesReasons)))

        @JvmField val p_origin: Param = CacheParamMapper.register(CrmTypeConstants.CASE, CaseI18nEnum.FORM_ORIGIN,
                I18nStringListParam("origin", "m_crm_case", "origin", listOf(*CrmDataTypeFactory.casesOrigins)))

        @JvmField val p_subject: Param = CacheParamMapper.register(CrmTypeConstants.CASE, CaseI18nEnum.FORM_SUBJECT,
                StringParam("subject", "m_crm_case", "subject"))

        @JvmField val p_email: Param = CacheParamMapper.register(CrmTypeConstants.CASE, GenericI18Enum.FORM_EMAIL,
                StringParam("email", "m_crm_case", "email"))

        @JvmField val p_assignee: Param = CacheParamMapper.register<PropertyListParam<*>>(CrmTypeConstants.CASE, GenericI18Enum.FORM_ASSIGNEE,
                PropertyListParam<String>("assignuser", "m_crm_case", "assignUser"))

        @JvmField val p_createdtime: Param = CacheParamMapper.register(CrmTypeConstants.CASE, GenericI18Enum.FORM_CREATED_TIME,
                DateParam("createdtime", "m_crm_case", "createdTime"))

        @JvmField val p_lastupdatedtime: Param = CacheParamMapper.register(CrmTypeConstants.CASE, GenericI18Enum.FORM_LAST_UPDATED_TIME,
                DateParam("lastupdatedtime", "m_crm_case", "lastUpdatedTime"))
    }
}
