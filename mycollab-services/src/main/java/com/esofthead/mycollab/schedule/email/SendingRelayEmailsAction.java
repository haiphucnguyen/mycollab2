package com.esofthead.mycollab.schedule.email;

import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;

public interface SendingRelayEmailsAction {
	void sendEmail(RelayEmailWithBLOBs relayEmail);
}
