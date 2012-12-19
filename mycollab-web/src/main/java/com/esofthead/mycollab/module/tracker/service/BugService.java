package com.esofthead.mycollab.module.tracker.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.DefectComment;
import com.esofthead.mycollab.module.tracker.domain.History;
import com.esofthead.mycollab.module.tracker.domain.MetaField;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

public interface BugService extends
		IDefaultService<Integer, Bug, BugSearchCriteria> {

	List<History> getHistoriesOfBug(int bugid);

	List<MetaField> getProjectTrackerMetaData(int projectid);

	int saveBugExt(SimpleBug bug, String userSessionId);

	int updateBug(String username, Bug bug, DefectComment comment);

	int updateBugExt(String username, SimpleBug bug, DefectComment comment);

	SimpleBug findBugById(int bugid);

	List<GroupItem> getStatusSummary(BugSearchCriteria criteria);

	List<GroupItem> getPrioritySummary(BugSearchCriteria criteria);

	List<GroupItem> getAssignedDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getResolutionDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getReporterDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getVersionDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getComponentDefectsSummary(BugSearchCriteria criteria);
}
