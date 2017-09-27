package com.mycollab.ondemand.module.billing.esb

import com.mycollab.common.domain.CustomerFeedbackWithBLOBs

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class DeleteAccountEvent(val accountId: Int, val feedback: CustomerFeedbackWithBLOBs?)