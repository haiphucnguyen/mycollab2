package com.mycollab.ondemand.module.billing.esb

import com.mycollab.common.domain.CustomerFeedbackWithBLOBs

/**
  * @author MyCollab Ltd
  * @since 5.1.0
  */
class DeleteAccountEvent(val accountId: Integer, val feedback: CustomerFeedbackWithBLOBs) {}
