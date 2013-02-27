package com.esofthead.mycollab.module.tracker.service;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.MetaField;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.schedule.INotificationSchedulable;

import java.util.List;

public interface BugService extends
		IDefaultService<Integer, Bug, BugSearchCriteria>,
		INotificationSchedulable {

	List<MetaField> getProjectTrackerMetaData(int projectid);

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
