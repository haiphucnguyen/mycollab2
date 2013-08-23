/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTaskCount;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;

/**
 * 
 * @author haiphucnguyen
 */
@RemotingDestination
public interface ProjectGenericTaskService extends
		ISearchableService<ProjectGenericTaskSearchCriteria> {
	
	List<ProjectGenericTaskCount> findPagableTaskCountListByCriteria(
			SearchRequest<ProjectGenericTaskSearchCriteria> searchRequest);
}
