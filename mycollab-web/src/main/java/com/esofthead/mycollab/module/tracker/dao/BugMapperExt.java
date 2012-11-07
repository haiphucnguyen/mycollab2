package com.esofthead.mycollab.module.tracker.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

public interface BugMapperExt {
	int getTotalCount(BugSearchCriteria criteria);

	List<Bug> findPagableList(BugSearchCriteria criteria, RowBounds rowBounds);

	void insertAndReturnKey(Bug bug);

	SimpleBug getBugById(int bugid);

	List<GroupItem> getStatusSummary(BugSearchCriteria criteria);

	List<GroupItem> getPrioritySummary(BugSearchCriteria criteria);

	List<GroupItem> getAssignedDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getResolutionDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getReporterDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getVersionDefectsSummary(BugSearchCriteria criteria);

	List<GroupItem> getComponentDefectsSummary(BugSearchCriteria criteria);
}
