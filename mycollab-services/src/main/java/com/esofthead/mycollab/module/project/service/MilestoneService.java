/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;

/**
 * 
 * @author haiphucnguyen
 */
@RemotingDestination
public interface MilestoneService extends
		IDefaultService<Integer, Milestone, MilestoneSearchCriteria> {

	@Cacheable
	SimpleMilestone findById(int milestoneId, @CacheKey int sAccountId);
}
