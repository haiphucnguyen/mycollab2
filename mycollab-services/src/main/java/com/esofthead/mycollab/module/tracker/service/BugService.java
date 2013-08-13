package com.esofthead.mycollab.module.tracker.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

public interface BugService extends
		IDefaultService<Integer, BugWithBLOBs, BugSearchCriteria> {

	@Cacheable
	SimpleBug findById(Integer bugid, @CacheKey Integer sAccountId);

	@Cacheable
	List<GroupItem> getStatusSummary(BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getPrioritySummary(BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getAssignedDefectsSummary(BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getResolutionDefectsSummary(BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getReporterDefectsSummary(BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getVersionDefectsSummary(BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getComponentDefectsSummary(BugSearchCriteria searchCriteria);

	@Cacheable
	List<GroupItem> getBugStatusTrendSummary(BugSearchCriteria criteria);
}
