package com.mycollab.module.crm.domain.criteria

import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.db.arguments.StringSearchField
import com.mycollab.db.query.*
import com.mycollab.module.crm.CrmDataTypeFactory
import com.mycollab.module.crm.CrmTypeConstants

import java.util.Arrays

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class CampaignSearchCriteria : SearchCriteria() {

    var campaignName: StringSearchField? = null
    var assignUser: StringSearchField? = null
    var leadId: NumberSearchField? = null
    var statuses: SetSearchField<String>? = null
    var types: SetSearchField<String>? = null
    var assignUsers: SetSearchField<String>? = null
    var id: NumberSearchField? = null

    companion object {
        private val serialVersionUID = 1L

        val p_campaignName: Param = CacheParamMapper.register(CrmTypeConstants.CAMPAIGN, GenericI18Enum.FORM_NAME,
                StringParam("name", "m_crm_campaign", "campaignName"))

        val p_startDate: Param = CacheParamMapper.register(CrmTypeConstants.CAMPAIGN, GenericI18Enum.FORM_START_DATE,
                DateParam("startdate", "m_crm_campaign", "startDate"))

        val p_endDate: Param = CacheParamMapper.register(CrmTypeConstants.CAMPAIGN, GenericI18Enum.FORM_END_DATE,
                DateParam("enddate", "m_crm_campaign", "endDate"))

        val p_createdtime: Param = CacheParamMapper.register(CrmTypeConstants.CAMPAIGN, GenericI18Enum.FORM_CREATED_TIME,
                DateParam("createdtime", "m_crm_campaign", "createdTime"))

        val p_lastUpdatedTime: Param = CacheParamMapper.register(CrmTypeConstants.CAMPAIGN,
                GenericI18Enum.FORM_LAST_UPDATED_TIME, DateParam("lastUpdatedTime", "m_crm_campaign", "lastUpdatedTime"))

        val p_types: Param = CacheParamMapper.register(CrmTypeConstants.CAMPAIGN, GenericI18Enum.FORM_TYPE,
                I18nStringListParam("type", "m_crm_campaign", "type", listOf(*CrmDataTypeFactory.campaignTypeList)))

        val p_statuses: Param = CacheParamMapper.register(CrmTypeConstants.CAMPAIGN, GenericI18Enum.FORM_STATUS,
                I18nStringListParam("status", "m_crm_campaign", "status", listOf(*CrmDataTypeFactory.campaignStatusList)))

        val p_assignee: Param = CacheParamMapper.register<PropertyListParam<*>>(CrmTypeConstants.CAMPAIGN, GenericI18Enum.FORM_ASSIGNEE,
                PropertyListParam<String>("assignuser", "m_crm_campaign", "assignUser"))
    }
}
