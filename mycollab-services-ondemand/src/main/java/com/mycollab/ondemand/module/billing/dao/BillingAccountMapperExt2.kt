package com.mycollab.ondemand.module.billing.dao

import com.mycollab.module.user.domain.BillingAccountWithOwners
import com.mycollab.module.user.domain.User
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.session.RowBounds

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
interface BillingAccountMapperExt2 {
    fun findPageableListByCriteria(@Param("searchCriteria") criteria: BillingAccountSearchCriteria,
                                   rowBounds: RowBounds): List<SimpleBillingAccount2>

    fun findSubDomainsOfUser(@Param("username") username: String): List<String>

    fun findTrialAccountsWithOwners(): List<BillingAccountWithOwners>

    fun findUsersNotBelongToAnyAccount(): List<User>

    fun removeUsersNotBelongToAnyAccount()
}
