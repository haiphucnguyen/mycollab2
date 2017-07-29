package com.mycollab.module.project.service.impl;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultSearchService;
import com.mycollab.module.project.dao.ProjectGenericItemMapper;
import com.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.mycollab.module.project.service.ProjectGenericItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
@Service
public class ProjectGenericItemServiceImpl extends DefaultSearchService<ProjectGenericItemSearchCriteria> implements ProjectGenericItemService {

    @Autowired
    private ProjectGenericItemMapper projectGenericItemMapper;

    @Override
    public ISearchableDAO<ProjectGenericItemSearchCriteria> getSearchMapper() {
        return projectGenericItemMapper;
    }

    @Override
    public Integer getTotalCount(ProjectGenericItemSearchCriteria criteria) {
        return projectGenericItemMapper.getTotalCountFromTask(criteria) +
                projectGenericItemMapper.getTotalCountFromMessage(criteria) +
                projectGenericItemMapper.getTotalCountFromMilestone(criteria) +
                projectGenericItemMapper.getTotalCountFromBug(criteria) +
                projectGenericItemMapper.getTotalCountFromVersion(criteria) +
                projectGenericItemMapper.getTotalCountFromComponent(criteria) +
                projectGenericItemMapper.getTotalCountFromRisk(criteria);
    }
}
