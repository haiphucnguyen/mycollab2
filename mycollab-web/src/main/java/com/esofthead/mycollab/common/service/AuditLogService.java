package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface AuditLogService extends IDefaultService<Integer, AuditLog, AuditLogSearchCriteria> {

    void saveAuditLog(String username, String type, int typeid, Object oldObj,
            Object newObj);
}
