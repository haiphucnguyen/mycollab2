package com.mycollab.ondemand.module.billing.esb

import com.mycollab.module.user.domain.BillingPlan

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class UpdateBillingPlanEvent(val accountId: Int, val billingPlan: BillingPlan)