package com.esofthead.mycollab.module.crm.view

import com.esofthead.mycollab.core.MyCollabException
import com.esofthead.mycollab.module.crm.CrmTypeConstants
import com.esofthead.mycollab.module.crm.i18n.CrmTypeI18nEnum

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object CrmLocalizationTypeMap {
    private val typeMap: Map[String, CrmTypeI18nEnum] = Map(
        CrmTypeConstants.ACCOUNT -> CrmTypeI18nEnum.ACCOUNT,
        CrmTypeConstants.CALL -> CrmTypeI18nEnum.CALL,
        CrmTypeConstants.CAMPAIGN -> CrmTypeI18nEnum.CAMPAIGN,
        CrmTypeConstants.CASE -> CrmTypeI18nEnum.CASES,
        CrmTypeConstants.CONTACT -> CrmTypeI18nEnum.CONTACT,
        CrmTypeConstants.LEAD -> CrmTypeI18nEnum.LEAD,
        CrmTypeConstants.MEETING -> CrmTypeI18nEnum.MEETING,
        CrmTypeConstants.OPPORTUNITY -> CrmTypeI18nEnum.OPPORTUNITY,
        CrmTypeConstants.TASK -> CrmTypeI18nEnum.TASK)
    typeMap.withDefaultValue(null)

    def getType(key: String): CrmTypeI18nEnum = {
        val result: CrmTypeI18nEnum = typeMap(key)
        if (result == null) {
            throw new MyCollabException("CAN NOT GET VALUE FOR KEY: " + key)
        }
        result
    }
}
