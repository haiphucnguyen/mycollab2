package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultSearchService;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericItemService;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
public class ProjectGenericItemServiceImpl extends DefaultSearchService<ProjectGenericItemSearchCriteria> implements
        ProjectGenericItemService{

    @Override
    public ISearchableDAO<ProjectGenericItemSearchCriteria> getSearchMapper() {
        return null;
    }
}
