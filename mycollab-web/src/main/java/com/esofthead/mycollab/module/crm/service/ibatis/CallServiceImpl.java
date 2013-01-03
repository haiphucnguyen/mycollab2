package com.esofthead.mycollab.module.crm.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.CallMapper;
import com.esofthead.mycollab.module.crm.dao.CallMapperExt;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Crm", type = "Call", nameField = "subject")
@Auditable(module = "Crm", type = "Call")
public class CallServiceImpl extends DefaultCrudService<Integer, Call>
        implements CallService {

    @Autowired
    protected CallMapper callMapper;
    @Autowired
    protected CallMapperExt callMapperExt;

    @SuppressWarnings("unchecked")
    @Override
    public ICrudGenericDAO<Integer, Call> getCrudMapper() {
        return callMapper;
    }

    @Override
    public SimpleCall findCallById(int callId) {
        return callMapperExt.findCallById(callId);
    }
}
