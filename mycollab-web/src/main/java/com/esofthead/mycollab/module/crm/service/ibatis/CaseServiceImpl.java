package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.PlatformManager;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.CaseMapperExt;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class CaseServiceImpl extends DefaultCrudService<Case, Integer>
		implements CaseService {

	private CaseMapperExt caseExtDAO;

	private AuditLogService auditLogService;

	public void setCaseExtDAO(CaseMapperExt caseExtDAO) {
		this.caseExtDAO = caseExtDAO;
	}

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	@Override
	public int updateWithSession(Case record, String userSessionId) {
		Case oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-case-" + record.getId();
		auditLogService.saveAuditLog(
				PlatformManager.getInstance().getSession(userSessionId)
						.getRemoteUser(), refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, userSessionId);
	}

	@Override
	public List findPagableListByCriteria(CaseSearchCriteria criteria,
			int skipNum, int maxResult) {
		return caseExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(CaseSearchCriteria criteria) {
		return caseExtDAO.getTotalCount(criteria);
	}

}
