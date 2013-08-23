package com.esofthead.mycollab.module.tracker.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.SimpleRelatedBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugRelatedSearchCriteria;

@RemotingDestination
public interface RelatedBugService extends
		IDefaultService<Integer, SimpleRelatedBug, BugRelatedSearchCriteria> {

}
