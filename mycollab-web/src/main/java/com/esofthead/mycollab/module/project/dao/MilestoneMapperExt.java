/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;

/**
 *
 * @author haiphucnguyen
 */
public interface MilestoneMapperExt extends ISearchableDAO<MilestoneSearchCriteria> {

    SimpleMilestone findMilestoneById(int milestoneId);
}
