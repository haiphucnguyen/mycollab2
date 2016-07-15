package com.mycollab.ondemand.module.billing.esb

import com.mycollab.module.user.domain.BillingPlan

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class UpdateBillingPlanEvent(val accountId: Integer, val billingPlan: BillingPlan) {
  
}
