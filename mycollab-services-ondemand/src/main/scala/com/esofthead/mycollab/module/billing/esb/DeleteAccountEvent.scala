package com.esofthead.mycollab.module.billing.esb

import com.esofthead.mycollab.common.domain.CustomerFeedbackWithBLOBs

/**
  * @author MyCollab Ltd
  * @since 5.1.0
  */
class DeleteAccountEvent(val accountId: Integer, val feedback: CustomerFeedbackWithBLOBs) {}
