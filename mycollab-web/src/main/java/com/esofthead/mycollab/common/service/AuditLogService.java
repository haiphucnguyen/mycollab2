package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import java.util.List;

public interface AuditLogService extends ICrudService<Integer, AuditLog> {

    List<SimpleAuditLog> getAuditLog(String refid);

    void saveAuditLog(String username, String refid, Object oldObj,
            Object newObj);
}
