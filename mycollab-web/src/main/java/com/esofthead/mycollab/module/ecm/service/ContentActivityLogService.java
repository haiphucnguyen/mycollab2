package com.esofthead.mycollab.module.ecm.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLog;
import com.esofthead.mycollab.module.ecm.domain.criteria.ContentActivityLogSearchCriteria;

public interface ContentActivityLogService
		extends
		IDefaultService<Integer, ContentActivityLog, ContentActivityLogSearchCriteria> {

}
