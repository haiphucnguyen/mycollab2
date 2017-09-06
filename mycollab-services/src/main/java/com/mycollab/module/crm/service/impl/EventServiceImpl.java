package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultSearchService;
import com.mycollab.module.crm.dao.EventMapperExt;
import com.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.mycollab.module.crm.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
public class EventServiceImpl extends DefaultSearchService<ActivitySearchCriteria> implements EventService {

    @Autowired
    private EventMapperExt eventMapperExt;

    @Override
    public ISearchableDAO<ActivitySearchCriteria> getSearchMapper() {
        return eventMapperExt;
    }

    @Override
    public Integer getTotalCount(ActivitySearchCriteria criteria) {
        return eventMapperExt.getTotalCountFromCall(criteria)
                + eventMapperExt.getTotalCountFromTask(criteria)
                + eventMapperExt.getTotalCountFromMeeting(criteria);
    }
}
