package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.CallMapper;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.service.CallService;

@Service
public class CallServiceImpl extends DefaultCrudService<Integer, Call> implements CallService{

	@Autowired
	protected CallMapper callMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public ICrudGenericDAO<Integer, Call> getCrudMapper() {
		return callMapper;
	}

}
