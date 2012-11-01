package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.ContractMapperExt;
import com.esofthead.mycollab.module.crm.domain.Contract;
import com.esofthead.mycollab.module.crm.domain.criteria.ContractSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContractService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class ContractServiceImpl extends DefaultCrudService<Integer, Contract>
		implements ContractService {

	private ContractMapperExt contractExtDAO;

	private AuditLogService auditLogService;

	public void setContractExtDAO(ContractMapperExt contractExtDAO) {
		this.contractExtDAO = contractExtDAO;
	}

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	@Override
	public int updateWithSession(Contract record, String username) {
		Contract oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-contract-" + record.getId();
		auditLogService.saveAuditLog(
				username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}

	@Override
	public List findPagableListByCriteria(ContractSearchCriteria criteria,
			int skipNum, int maxResult) {
		return contractExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(ContractSearchCriteria criteria) {
		return contractExtDAO.getTotalCount(criteria);
	}

}
