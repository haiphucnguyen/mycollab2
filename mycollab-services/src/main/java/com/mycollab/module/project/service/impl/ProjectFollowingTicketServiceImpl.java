package com.mycollab.module.project.service.impl;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultSearchService;
import com.mycollab.module.project.dao.ProjectFollowingTicketMapperExt;
import com.mycollab.module.project.domain.criteria.FollowingTicketSearchCriteria;
import com.mycollab.module.project.service.ProjectFollowingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class ProjectFollowingTicketServiceImpl extends DefaultSearchService<FollowingTicketSearchCriteria> implements ProjectFollowingTicketService {

    @Autowired
    private ProjectFollowingTicketMapperExt projectFollowingTicketMapperExt;

    @Override
    public ISearchableDAO<FollowingTicketSearchCriteria> getSearchMapper() {
        return projectFollowingTicketMapperExt;
    }

}
