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
	List<GroupItem> getStatusSummary(@CacheKey BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getPrioritySummary(@CacheKey BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getAssignedDefectsSummary(
			@CacheKey BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getResolutionDefectsSummary(
			@CacheKey BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getReporterDefectsSummary(
			@CacheKey BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getVersionDefectsSummary(
			@CacheKey BugSearchCriteria criteria);

	@Cacheable
	List<GroupItem> getComponentDefectsSummary(
			@CacheKey BugSearchCriteria searchCriteria);

	@Cacheable
	List<GroupItem> getBugStatusTrendSummary(
			@CacheKey BugSearchCriteria criteria);
}
