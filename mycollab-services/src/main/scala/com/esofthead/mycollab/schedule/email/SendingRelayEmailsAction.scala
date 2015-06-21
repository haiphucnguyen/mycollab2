package com.esofthead.mycollab.schedule.email

import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
trait SendingRelayEmailsAction {
    def sendEmail(relayEmail: RelayEmailWithBLOBs): Unit
}
