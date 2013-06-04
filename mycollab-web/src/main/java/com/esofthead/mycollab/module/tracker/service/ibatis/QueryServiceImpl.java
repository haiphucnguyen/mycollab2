package com.esofthead.mycollab.module.tracker.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.tracker.dao.QueryMapper;
import com.esofthead.mycollab.module.tracker.dao.QueryMapperExt;
import com.esofthead.mycollab.module.tracker.domain.Query;
import com.esofthead.mycollab.module.tracker.domain.criteria.QuerySearchCriteria;
import com.esofthead.mycollab.module.tracker.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Project", nameField = "queryname", type = "BugQuery")
public class QueryServiceImpl extends DefaultService<Integer, Query, QuerySearchCriteria>
        implements QueryService {

    @Autowired
    private QueryMapper queryMapper;
    @Autowired
    private QueryMapperExt queryMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Query> getCrudMapper() {
        return queryMapper;
    }

    @Override
    public ISearchableDAO<QuerySearchCriteria> getSearchMapper() {
        return queryMapperExt;
    }
}
