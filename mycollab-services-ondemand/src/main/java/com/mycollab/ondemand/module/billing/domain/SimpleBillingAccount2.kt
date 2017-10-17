package com.mycollab.ondemand.module.billing.domain

import com.mycollab.module.user.domain.BillingAccount
import com.mycollab.module.user.domain.SimpleUser

import java.util.Date

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
class SimpleBillingAccount2 : BillingAccount() {
    var numUsers: Int = 0
    var numProjects: Int = 0
    var lastAccessTime: Date? = null
    var accountOwners: List<SimpleUser>? = null
}
