package com.mycollab.module.crm.domain.criteria

import com.mycollab.common.CountryValueFactory
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.db.arguments.StringSearchField
import com.mycollab.db.query.*
import com.mycollab.module.crm.CrmDataTypeFactory
import com.mycollab.module.crm.CrmTypeConstants
import com.mycollab.module.crm.i18n.LeadI18nEnum
import java.util.*

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class LeadSearchCriteria : SearchCriteria() {

    var leadName: StringSearchField? = null
    var assignUsers: SetSearchField<String>? = null
    var campaignId: NumberSearchField? = null
    var opportunityId: NumberSearchField? = null
    var id: NumberSearchField? = null
    var accountId: NumberSearchField? = null

    companion object {
        private val serialVersionUID = 1L

        @JvmField val p_leadContactName: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, GenericI18Enum.FORM_NAME,
                ConcatStringParam("contactname", "m_crm_lead", arrayOf("firstname", "lastname")))

        @JvmField val p_accountName: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_ACCOUNT_NAME,
                StringParam("accountname", "m_crm_lead", "accountName"))

        @JvmField val p_website: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_WEBSITE,
                StringParam("website", "m_crm_lead", "website"))

        @JvmField val p_anyEmail: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_ANY_EMAIL,
                CompositionStringParam("anyEmail", StringParam("", "m_crm_lead", "email")))

        @JvmField val p_anyPhone: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_ANY_PHONE,
                CompositionStringParam("anyPhone", StringParam("", "m_crm_lead", "officePhone"),
                        StringParam("", "m_crm_lead", "homePhone"),
                        StringParam("", "m_crm_lead", "mobile"),
                        StringParam("", "m_crm_lead", "otherPhone"),
                        StringParam("", "m_crm_lead", "fax")))

        @JvmField val p_anyCity: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_ANY_CITY,
                CompositionStringParam("anyCity",
                        StringParam("", "m_crm_lead", "primCity"),
                        StringParam("", "m_crm_lead", "otherCity")))

        @JvmField val p_billingCountry: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_PRIMARY_COUNTRY,
                StringListParam("billingCountry", "m_crm_lead", "primCountry", Arrays.asList(*CountryValueFactory.countryList!!)))

        @JvmField val p_shippingCountry: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_OTHER_COUNTRY,
                StringListParam("shippingCountry", "m_crm_lead", "otherCountry",
                        Arrays.asList(*CountryValueFactory.countryList!!)))

        @JvmField val p_statuses: Param = CacheParamMapper.register(CrmTypeConstants.LEAD, GenericI18Enum.FORM_STATUS,
                I18nStringListParam("status", "m_crm_lead", "status", listOf(*CrmDataTypeFactory.leadStatusList)))

        @JvmField val p_sources = CacheParamMapper.register(CrmTypeConstants.LEAD, LeadI18nEnum.FORM_LEAD_SOURCE,
                I18nStringListParam("source", "m_crm_lead", "source", listOf(*CrmDataTypeFactory.leadSourceList)))

        @JvmField val p_assignee: Param = CacheParamMapper.register<PropertyListParam<*>>(CrmTypeConstants.LEAD, GenericI18Enum.FORM_ASSIGNEE,
                PropertyListParam<String>("assignuser", "m_crm_lead", "assignUser"))
    }
}
