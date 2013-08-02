package com.esofthead.mycollab.module.tracker.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.SimpleRelatedBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugRelatedSearchCriteria;

public interface RelatedBugService
		extends
		IDefaultService<Integer, SimpleRelatedBug, BugRelatedSearchCriteria> {

}
