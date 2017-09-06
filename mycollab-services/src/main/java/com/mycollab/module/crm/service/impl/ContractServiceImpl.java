package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.crm.dao.ContractMapper;
import com.mycollab.module.crm.dao.ContractMapperExt;
import com.mycollab.module.crm.domain.Contract;
import com.mycollab.module.crm.domain.criteria.ContractSearchCriteria;
import com.mycollab.module.crm.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
