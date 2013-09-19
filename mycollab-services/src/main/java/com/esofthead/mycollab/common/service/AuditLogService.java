package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface AuditLogService extends
		IDefaultService<Integer, AuditLog, AuditLogSearchCriteria> {

	@Cacheable
	SimpleAuditLog findLatestLog(int auditLogId, @CacheKey int sAccountId);
}
