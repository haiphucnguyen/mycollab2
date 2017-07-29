package com.mycollab.module.project.dao;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface TaskMapperExt extends ISearchableDAO<TaskSearchCriteria> {

    SimpleTask findTaskById(int taskId);

    Integer getMaxKey(int projectId);

    List<GroupItem> getPrioritySummary(@Param("searchCriteria") TaskSearchCriteria criteria);

    List<GroupItem> getStatusSummary(@Param("searchCriteria") TaskSearchCriteria criteria);

    List<GroupItem> getAssignedDefectsSummary(@Param("searchCriteria") TaskSearchCriteria criteria);

    SimpleTask findByProjectAndTaskKey(@Param("taskkey") int taskkey, @Param("prjShortName") String projectShortName,
                                       @Param("sAccountId") int sAccountId);

}
