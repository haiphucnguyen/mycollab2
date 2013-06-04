package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface AuditLogService extends IDefaultService<Integer, AuditLog, AuditLogSearchCriteria> {

    int saveAuditLog(String username, String module, String type, int typeid, int sAccountId, Object oldObj,
            Object newObj);
    
    SimpleAuditLog findById(int auditLogId);
}
