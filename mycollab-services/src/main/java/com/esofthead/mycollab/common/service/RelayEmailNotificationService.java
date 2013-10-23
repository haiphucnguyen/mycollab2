package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface RelayEmailNotificationService
		extends
		IDefaultService<Integer, RelayEmailNotification, RelayEmailNotificationSearchCriteria> {
}
