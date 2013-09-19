/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;

/**
 * 
 * @author haiphucnguyen
 */
public interface AuditLogMapperExt extends
		ISearchableDAO<AuditLogSearchCriteria> {
	SimpleAuditLog findLatestLog(int auditLogId);
}
