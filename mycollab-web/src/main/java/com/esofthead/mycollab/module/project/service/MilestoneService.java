/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;

/**
 *
 * @author haiphucnguyen
 */
public interface MilestoneService extends
        IDefaultService<Integer, Milestone, MilestoneSearchCriteria> {

    SimpleMilestone findMilestoneById(int milestoneId);
}
