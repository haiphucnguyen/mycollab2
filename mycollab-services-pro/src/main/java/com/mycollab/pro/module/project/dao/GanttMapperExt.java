package com.mycollab.pro.module.project.dao;

import com.mycollab.module.project.domain.AssignWithPredecessors;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 4.0.0
 */
public interface GanttMapperExt {

    List<AssignWithPredecessors> getTaskWithPredecessors(@Param("projectIds") List<Integer> projectIds,
                                                         @Param("accountId") Integer sAccountId);
}
