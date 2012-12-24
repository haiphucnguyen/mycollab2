package com.esofthead.mycollab.shared.audit.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.shared.audit.domain.AuditLog;
import com.esofthead.mycollab.shared.audit.domain.SimpleAuditLog;

public interface AuditLogService extends ICrudService<Integer, AuditLog> {
	List<SimpleAuditLog> getAuditLog(String refid);

	void saveAuditLog(String username, String refid, Object oldObj,
			Object newObj);
}
