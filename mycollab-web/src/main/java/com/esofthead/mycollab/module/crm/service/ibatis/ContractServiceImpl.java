package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ContractMapper;
import com.esofthead.mycollab.module.crm.dao.ContractMapperExt;
import com.esofthead.mycollab.module.crm.domain.Contract;
import com.esofthead.mycollab.module.crm.domain.criteria.ContractSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContractService;
import com.esofthead.mycollab.common.service.AuditLogService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Contract", nameField = "contractname")
public class ContractServiceImpl extends
		DefaultService<Integer, Contract, ContractSearchCriteria> implements
		ContractService {

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private ContractMapperExt contractMapperExt;

	@Autowired
	private AuditLogService auditLogService;

	@Override
	public ICrudGenericDAO<Integer, Contract> getCrudMapper() {
		return contractMapper;
	}

	@Override
	public ISearchableDAO<ContractSearchCriteria> getSearchMapper() {
		return contractMapperExt;
	}

	@Override
	public int updateWithSession(Contract record, String username) {
		Contract oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-contract-" + record.getId();
		auditLogService.saveAuditLog(username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}

}
