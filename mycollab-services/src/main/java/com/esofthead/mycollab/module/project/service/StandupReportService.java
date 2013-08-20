package com.esofthead.mycollab.module.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;

@RemotingDestination
public interface StandupReportService
		extends
		IDefaultService<Integer, StandupReportWithBLOBs, StandupReportSearchCriteria> {
	@Cacheable
	SimpleStandupReport findStandupReportById(int standupId,
			@CacheKey Integer sAccountId);

	@Cacheable
	SimpleStandupReport findStandupReportByDateUser(int projectId,
			String username, Date onDate, @CacheKey Integer sAccountId);

	@Cacheable
	List<GroupItem> getReportsCount(
			@CacheKey StandupReportSearchCriteria criteria);

}
