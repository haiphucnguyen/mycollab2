package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import java.util.List;

public interface BugMapperExt extends ISearchableDAO<BugSearchCriteria> {

    SimpleBug getBugById(int bugid);

    List<GroupItem> getStatusSummary(BugSearchCriteria criteria);

    List<GroupItem> getPrioritySummary(BugSearchCriteria criteria);

    List<GroupItem> getAssignedDefectsSummary(BugSearchCriteria criteria);

    List<GroupItem> getResolutionDefectsSummary(BugSearchCriteria criteria);

    List<GroupItem> getReporterDefectsSummary(BugSearchCriteria criteria);

    List<GroupItem> getVersionDefectsSummary(BugSearchCriteria criteria);

    List<GroupItem> getComponentDefectsSummary(BugSearchCriteria criteria);
}
