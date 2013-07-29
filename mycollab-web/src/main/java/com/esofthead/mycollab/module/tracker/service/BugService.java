package com.esofthead.mycollab.module.tracker.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

public interface BugService extends
		IDefaultService<Integer, BugWithBLOBs, BugSearchCriteria> {

	SimpleBug findBugById(int bugid);

	List<GroupItem> getStatusSummary(BugSearchCriteria criteria);

	List<GroupItem> getPrioritySummary(BugSearchCriteria criteria);

	List<GroupItem> getAssignedDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getResolutionDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getReporterDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getVersionDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getComponentDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getBugStatusTrendSummary(BugSearchCriteria criteria);
}
