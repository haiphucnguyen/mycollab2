package com.mycollab.pro.module.project.dao

import com.mycollab.module.project.domain.AssignWithPredecessors
import org.apache.ibatis.annotations.Param

/**
 * @author MyCollab Ltd
 * @since 4.0.0
 */
interface GanttMapperExt {

    fun getTaskWithPredecessors(@Param("projectIds") projectIds: List<Int>,
                                @Param("accountId") sAccountId: Int?): List<AssignWithPredecessors>
}
