package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ContractMapper;
import com.esofthead.mycollab.module.crm.dao.ContractMapperExt;
import com.esofthead.mycollab.module.crm.domain.Contract;
import com.esofthead.mycollab.module.crm.domain.criteria.ContractSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContractService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Contract", nameField = "contractname")
@Auditable(module = "Crm", type = "Contract")
public class ContractServiceImpl extends DefaultService<Integer, Contract, ContractSearchCriteria> implements
        ContractService {

    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private ContractMapperExt contractMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Contract> getCrudMapper() {
        return contractMapper;
    }

    @Override
    public ISearchableDAO<ContractSearchCriteria> getSearchMapper() {
        return contractMapperExt;
    }
}
