package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;

public interface CallMapperExt extends ISearchableDAO<CallSearchCriteria> {

    SimpleCall findById(int callId);
}
