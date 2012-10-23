package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.crm.domain.Contract;
import com.esofthead.mycollab.module.crm.domain.criteria.ContractSearchCriteria;

public interface ContractService extends ICrudService<Contract, Integer>,
        IPagableService<ContractSearchCriteria> {

}
