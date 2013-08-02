package com.esofthead.mycollab.module.tracker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

public interface BugMapperExt extends ISearchableDAO<BugSearchCriteria> {

    SimpleBug getBugById(int bugid);

    List<GroupItem> getStatusSummary(@Param("searchCriteria") BugSearchCriteria criteria);

    List<GroupItem> getPrioritySummary(@Param("searchCriteria")BugSearchCriteria criteria);

    List<GroupItem> getAssignedDefectsSummary(@Param("searchCriteria")BugSearchCriteria criteria);

    List<GroupItem> getResolutionDefectsSummary(@Param("searchCriteria")BugSearchCriteria criteria);

    List<GroupItem> getReporterDefectsSummary(@Param("searchCriteria")BugSearchCriteria criteria);

    List<GroupItem> getVersionDefectsSummary(@Param("searchCriteria")BugSearchCriteria criteria);

    List<GroupItem> getComponentDefectsSummary(@Param("searchCriteria")BugSearchCriteria criteria);
    
    List<GroupItem> getBugStatusTrendSummary(@Param("searchCriteria")BugSearchCriteria criteria);
    
    Integer getMaxKey(int projectId);
}
