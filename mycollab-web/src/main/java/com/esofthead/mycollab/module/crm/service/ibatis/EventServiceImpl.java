package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultSearchService;
import com.esofthead.mycollab.module.crm.dao.EventMapperExt;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.service.EventService;

@Service
@Transactional
public class EventServiceImpl extends DefaultSearchService<EventSearchCriteria>
        implements EventService {

    @Autowired
    protected EventMapperExt eventMapperExt;

    @Override
    public ISearchableDAO<EventSearchCriteria> getSearchMapper() {
        return eventMapperExt;
    }

    @Override
    public int getTotalCount(EventSearchCriteria criteria) {
        return eventMapperExt.getTotalCountFromCall(criteria)
                + eventMapperExt.getTotalCountFromTask(criteria)
                + eventMapperExt.getTotalCountFromMeeting(criteria);
    }

    @Override
    public void removeByCriteria(EventSearchCriteria criteria) {
        super.removeByCriteria(criteria);
    }
}
