package com.mycollab.common.service;

import com.mycollab.common.domain.AuditLog;
import com.mycollab.common.domain.SimpleAuditLog;
import com.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.mycollab.db.persistence.service.IDefaultService;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface AuditLogService extends IDefaultService<Integer, AuditLog, AuditLogSearchCriteria> {

    SimpleAuditLog findLastestLog(int auditLogId, int sAccountId);
}
