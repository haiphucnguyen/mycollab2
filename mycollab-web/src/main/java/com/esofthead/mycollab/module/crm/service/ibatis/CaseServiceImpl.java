package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.CaseMapper;
import com.esofthead.mycollab.module.crm.dao.CaseMapperExt;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Case", nameField="subject")
public class CaseServiceImpl extends
		DefaultService<Integer, Case, CaseSearchCriteria> implements
		CaseService {

	@Autowired
	protected AuditLogService auditLogService;

	@Autowired
	protected CaseMapper caseMapper;

	@Autowired
	protected CaseMapperExt caseMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Case> getCrudMapper() {
		return caseMapper;
	}

	@Override
	public ISearchableDAO<CaseSearchCriteria> getSearchMapper() {
		return caseMapperExt;
	}

	@Override
	public SimpleCase findCaseById(int caseId) {
		return caseMapperExt.findCaseById(caseId);
	}

	@Override
	public int updateWithSession(Case record, String username) {
		Case oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-case-" + record.getId();
		auditLogService.saveAuditLog(username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}
}
