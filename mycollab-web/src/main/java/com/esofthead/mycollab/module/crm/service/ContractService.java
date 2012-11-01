package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Contract;
import com.esofthead.mycollab.module.crm.domain.criteria.ContractSearchCriteria;

public interface ContractService extends
		IDefaultService<Integer, Contract, ContractSearchCriteria> {

}
