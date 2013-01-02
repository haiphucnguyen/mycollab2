package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

public interface AuditLogService extends ICrudService<Integer, AuditLog> {

    void saveAuditLog(String username, String type, int typeid, Object oldObj,
            Object newObj);
}
