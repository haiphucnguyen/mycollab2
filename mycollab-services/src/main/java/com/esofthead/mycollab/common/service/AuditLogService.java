package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface AuditLogService extends
		IDefaultService<Integer, AuditLog, AuditLogSearchCriteria> {

	SimpleAuditLog findLatestLog(int auditLogId, int sAccountId);
}
