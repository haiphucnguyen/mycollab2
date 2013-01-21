/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultSearchService;
import com.esofthead.mycollab.module.project.dao.ProjectGenericTaskMapper;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectGenericTaskServiceImpl extends DefaultSearchService<ProjectGenericTaskSearchCriteria> implements ProjectGenericTaskService {

    @Autowired
    protected ProjectGenericTaskMapper projectGenericTaskMapper;
    
    @Override
    public ISearchableDAO<ProjectGenericTaskSearchCriteria> getSearchMapper() {
        return projectGenericTaskMapper;
    }
    
}
