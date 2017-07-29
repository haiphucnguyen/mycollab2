package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.module.crm.dao.CustomerMapper;
import com.mycollab.module.crm.domain.Customer;
import com.mycollab.module.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl extends DefaultCrudService<Integer, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ICrudGenericDAO<Integer, Customer> getCrudMapper() {
        return customerMapper;
    }
}
