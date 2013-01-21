/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;

/**
 *
 * @author haiphucnguyen
 */
public interface ProjectGenericTaskMapper extends ISearchableDAO<ProjectGenericTaskSearchCriteria> {
    int getTotalCountFromProblem(ProjectGenericTaskSearchCriteria criteria);
    
    int getTotalCountFromRisk(ProjectGenericTaskSearchCriteria criteria);
    
    int getTotalCountFromBug(ProjectGenericTaskSearchCriteria criteria);
    
    int getTotalCountFromTask(ProjectGenericTaskSearchCriteria criteria);
}
