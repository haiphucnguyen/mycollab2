package com.esofthead.mycollab.shared.audit.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.shared.audit.domain.AuditLog;
import com.esofthead.mycollab.shared.audit.domain.SimpleAuditLog;

public interface AuditLogService extends ICrudService<AuditLog, Integer> {
	List<SimpleAuditLog> getAuditLog(String refid);

	void saveAuditLog(String username, String refid, Object oldObj,
			Object newObj);
}
