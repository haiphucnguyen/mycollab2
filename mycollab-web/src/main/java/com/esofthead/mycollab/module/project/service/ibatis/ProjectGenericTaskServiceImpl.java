/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultSearchService;
import com.esofthead.mycollab.module.project.dao.ProjectGenericTaskMapper;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author haiphucnguyen
 */
@Service
public class ProjectGenericTaskServiceImpl extends DefaultSearchService<ProjectGenericTaskSearchCriteria> implements ProjectGenericTaskService {
    
    @Autowired
    protected ProjectGenericTaskMapper projectGenericTaskMapper;    
    
    @Override
    public ISearchableDAO<ProjectGenericTaskSearchCriteria> getSearchMapper() {
        return projectGenericTaskMapper;
    }
    
    @Override
    public int getTotalCount(ProjectGenericTaskSearchCriteria criteria) {
        return projectGenericTaskMapper.getTotalCountFromProblem(criteria) + 
                projectGenericTaskMapper.getTotalCountFromRisk(criteria) +
                projectGenericTaskMapper.getTotalCountFromBug(criteria) + 
                projectGenericTaskMapper.getTotalCountFromTask(criteria);
    }
}
