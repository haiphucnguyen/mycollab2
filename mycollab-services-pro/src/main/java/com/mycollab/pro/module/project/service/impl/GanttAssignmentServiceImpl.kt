package com.mycollab.pro.module.project.service.impl

import com.mycollab.core.MyCollabException
import com.mycollab.core.cache.CacheKey
import com.mycollab.concurrent.DistributionLockUtil
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.domain.*
import com.mycollab.pro.module.project.dao.GanttMapperExt
import com.mycollab.module.project.dao.MilestoneMapper
import com.mycollab.module.project.dao.PredecessorMapper
import com.mycollab.module.project.dao.TaskMapper
import com.mycollab.module.project.service.GanttAssignmentService
import com.mycollab.module.tracker.dao.BugMapper
import com.mycollab.module.tracker.domain.BugExample
import com.mycollab.spring.AppContextUtil
import com.google.common.base.MoreObjects
import org.apache.commons.collections.CollectionUtils
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

import javax.sql.DataSource
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.SQLException
import java.util.ArrayList
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit

@Service
class GanttAssignmentServiceImpl(private val ganttMapperExt: GanttMapperExt,
                                 private val dataSource: DataSource ) : GanttAssignmentService {

    override fun getTaskWithPredecessors(projectIds: List<Int>, @CacheKey sAccountId: Int): List<AssignWithPredecessors> {
        return ganttMapperExt.getTaskWithPredecessors(projectIds, sAccountId)
    }

    override fun massUpdateGanttItems(ganttItems: List<AssignWithPredecessors>, sAccountId: Int) {
        if (CollectionUtils.isNotEmpty(ganttItems)) {
            val milestoneGanttItems = ArrayList<MilestoneGanttItem>()
            val taskGanttItems = ArrayList<TaskGanttItem>()
            val bugGanttItems = ArrayList<TaskGanttItem>()

            ganttItems.forEach {
                when (it) {
                    is MilestoneGanttItem -> milestoneGanttItems.add(it)
                    is TaskGanttItem -> if (ProjectTypeConstants.BUG == it.type) {
                        bugGanttItems.add(it)
                    } else if (ProjectTypeConstants.TASK == it.type) {
                        taskGanttItems.add(it)
                    }
                    else -> throw MyCollabException("Do not support save gantt item $it")
                }
            }
            massUpdateMilestoneGanttItems(milestoneGanttItems, sAccountId)
            massUpdateTaskGanttItems(taskGanttItems, sAccountId)
            massUpdateBugGanttItems(bugGanttItems, sAccountId)
        }
    }

    override fun massUpdatePredecessors(taskSourceId: Int, predecessors: List<TaskPredecessor>, sAccountId: Int) {
        val lock = DistributionLockUtil.getLock("task-service$sAccountId")
        try {
            val predecessorMapper = AppContextUtil.getSpringBean(PredecessorMapper::class.java)
            val ex = PredecessorExample()
            ex.createCriteria().andSourceidEqualTo(taskSourceId)
            predecessorMapper.deleteByExample(ex)

            val jdbcTemplate = JdbcTemplate(dataSource)
            val now = GregorianCalendar().timeInMillis
            if (lock.tryLock(30, TimeUnit.SECONDS)) {
                jdbcTemplate.batchUpdate("INSERT INTO `m_prj_predecessor`(`sourceType`, `descType`, `predestype`,`lagDay`, `sourceId`,`descId`, `createdTime`) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        object : BatchPreparedStatementSetter {
                            @Throws(SQLException::class)
                            override fun setValues(preparedStatement: PreparedStatement, i: Int) {
                                preparedStatement.setString(1, predecessors[i].sourcetype)
                                preparedStatement.setString(2, predecessors[i].desctype)
                                preparedStatement.setString(3, predecessors[i].predestype)
                                preparedStatement.setInt(4, predecessors[i].lagday!!)
                                preparedStatement.setInt(5, predecessors[i].sourceid!!)
                                preparedStatement.setInt(6, predecessors[i].descid!!)
                                preparedStatement.setDate(7, Date(now))
                            }

                            override fun getBatchSize(): Int {
                                return predecessors.size
                            }
                        })
            }
        } catch (e: Exception) {
            throw MyCollabException(e)
        } finally {
            DistributionLockUtil.removeLock("task-service$sAccountId")
            lock.unlock()
        }
    }

    override fun massDeletePredecessors(predecessors: List<TaskPredecessor>, @CacheKey sAccountId: Int) {
        val lock = DistributionLockUtil.getLock("gantt-predecessor-service$sAccountId")
        try {
            if (lock.tryLock(30, TimeUnit.SECONDS)) {
                dataSource.connection.use { connection ->
                    connection.autoCommit = false
                    val preparedStatement = connection.prepareStatement("DELETE FROM " + "`m_prj_predecessor` WHERE sourceType=? AND predestype=? AND sourceId=? AND descId=? AND descType=?")
                    for (predecessor in predecessors) {
                        preparedStatement.setString(1, predecessor.sourcetype)
                        preparedStatement.setString(2, predecessor.predestype)
                        preparedStatement.setInt(3, predecessor.sourceid!!)
                        preparedStatement.setInt(4, predecessor.descid!!)
                        preparedStatement.setString(5, predecessor.desctype)
                        preparedStatement.addBatch()
                    }
                    preparedStatement.executeBatch()
                    connection.commit()
                }
            }
        } catch (e: Exception) {
            throw MyCollabException(e)
        } finally {
            DistributionLockUtil.removeLock("gantt-predecessor-service$sAccountId")
            lock.unlock()
        }
    }

    private fun massUpdateMilestoneGanttItems(milestoneGanttItems: List<MilestoneGanttItem>, sAccountId: Int) {
        if (CollectionUtils.isNotEmpty(milestoneGanttItems)) {
            val lock = DistributionLockUtil.getLock("gantt-milestone-service$sAccountId")
            try {
                val now = GregorianCalendar().timeInMillis
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    dataSource.connection.use { connection ->
                        connection.autoCommit = false
                        val preparedStatement = connection.prepareStatement("UPDATE `m_prj_milestone` SET " +
                                "name = ?, `startdate` = ?, `enddate` = ?, " +
                                "`lastUpdatedTime`=?, `assignUser`=?, `ganttIndex`=? WHERE `id` = ?")
                        for (milestoneGanttItem in milestoneGanttItems) {
                            preparedStatement.setString(1, milestoneGanttItem.name)
                            preparedStatement.setDate(2, getDateWithNullValue(milestoneGanttItem.startDate))
                            preparedStatement.setDate(3, getDateWithNullValue(milestoneGanttItem.endDate))
                            preparedStatement.setDate(4, Date(now))
                            preparedStatement.setString(5, milestoneGanttItem.assignUser)
                            preparedStatement.setInt(6, milestoneGanttItem.ganttIndex!!)
                            preparedStatement.setInt(7, milestoneGanttItem.id!!)
                            preparedStatement.addBatch()

                        }
                        preparedStatement.executeBatch()
                        connection.commit()
                    }
                }
            } catch (e: Exception) {
                throw MyCollabException(e)
            } finally {
                DistributionLockUtil.removeLock("gantt-milestone-service" + sAccountId)
                lock.unlock()
            }
        }
    }

    private fun massUpdateTaskGanttItems(taskGanttItems: List<TaskGanttItem>, sAccountId: Int) {
        if (CollectionUtils.isNotEmpty(taskGanttItems)) {
            val lock = DistributionLockUtil.getLock("gantt-task-service$sAccountId")
            try {
                val now = GregorianCalendar().timeInMillis
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    dataSource.connection.use { connection ->
                        connection.autoCommit = false
                        val batchTasksStatement = connection.prepareStatement("UPDATE `m_prj_task` SET " +
                                "name = ?, `startdate` = ?, `enddate` = ?, " +
                                "`lastUpdatedTime`=?, `percentagecomplete`=?, `assignUser`=?, `ganttindex`=?, " +
                                "`milestoneId`=?, `parentTaskId`=? WHERE `id` = ?")
                        taskGanttItems.forEach {
                            if (ProjectTypeConstants.TASK == it.type) {
                                batchTasksStatement.setString(1, it.name)
                                batchTasksStatement.setDate(2, getDateWithNullValue(it.startDate))
                                batchTasksStatement.setDate(3, getDateWithNullValue(it.endDate))
                                batchTasksStatement.setDate(4, Date(now))
                                batchTasksStatement.setDouble(5, it.progress!!)
                                batchTasksStatement.setString(6, it.assignUser)
                                batchTasksStatement.setInt(7, it.ganttIndex!!)
                                batchTasksStatement.setObject(8, it.milestoneId)
                                batchTasksStatement.setObject(9, it.parentTaskId)
                                batchTasksStatement.setInt(10, it.id!!)
                                batchTasksStatement.addBatch()
                            }
                        }
                        batchTasksStatement.executeBatch()
                        connection.commit()
                    }
                }
            } catch (e: Exception) {
                throw MyCollabException(e)
            } finally {
                DistributionLockUtil.removeLock("gantt-task-service$sAccountId")
                lock.unlock()
            }
        }
    }

    private fun massUpdateBugGanttItems(taskGanttItems: List<TaskGanttItem>, sAccountId: Int) {
        if (CollectionUtils.isNotEmpty(taskGanttItems)) {
            val lock = DistributionLockUtil.getLock("gantt-bug-service$sAccountId")
            try {
                val now = GregorianCalendar().timeInMillis
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    dataSource.connection.use { connection ->
                        connection.autoCommit = false
                        val batchTasksStatement = connection.prepareStatement("UPDATE `m_tracker_bug` SET " +
                                "name = ?, `startdate` = ?, `enddate` = ?, " +
                                "`lastUpdatedTime`=?, `percentagecomplete`=?, `assignuser`=?, `ganttindex`=?, " +
                                "`milestoneId`=? WHERE `id` = ?")
                        taskGanttItems.forEach {
                            if (ProjectTypeConstants.BUG == it.type) {
                                batchTasksStatement.setString(1, it.name)
                                batchTasksStatement.setDate(2, getDateWithNullValue(it.startDate))
                                batchTasksStatement.setDate(3, getDateWithNullValue(it.endDate))
                                batchTasksStatement.setDate(4, Date(now))
                                batchTasksStatement.setDouble(5, MoreObjects.firstNonNull(it.progress, 0.0)!!)
                                batchTasksStatement.setString(6, it.assignUser)
                                batchTasksStatement.setInt(7, it.ganttIndex!!)
                                batchTasksStatement.setObject(8, it.milestoneId)
                                batchTasksStatement.setInt(9, it.id!!)
                                batchTasksStatement.addBatch()
                            }
                        }
                        batchTasksStatement.executeBatch()
                        connection.commit()
                    }
                }
            } catch (e: Exception) {
                throw MyCollabException(e)
            } finally {
                DistributionLockUtil.removeLock("gantt-bug-service" + sAccountId)
                lock.unlock()
            }
        }
    }

    override fun massDeleteGanttItems(ganttItems: List<AssignWithPredecessors>, sAccountId: Int) {
        if (CollectionUtils.isNotEmpty(ganttItems)) {
            val milestoneIds = ArrayList<Int>()
            val taskIds = ArrayList<Int>()
            val bugIds = ArrayList<Int>()

            ganttItems.forEach {
                when (it) {
                    is MilestoneGanttItem -> if (it.id != null) {
                        milestoneIds.add(it.id!!)
                    }
                    is TaskGanttItem -> if (ProjectTypeConstants.TASK == it.type && it.id != null) {
                        taskIds.add(it.id!!)
                    } else if (ProjectTypeConstants.BUG == it.type && it.id != null) {
                        bugIds.add(it.id!!)
                    }
                    else -> throw MyCollabException("Do not support delete gantt item $it")
                }
            }
            massDeleteMilestoneGanttItems(milestoneIds)
            massDeleteTaskGanttItems(taskIds)
            massDeleteBugGanttItems(bugIds)
        }
    }

    private fun massDeleteMilestoneGanttItems(milestoneIds: List<Int>) {
        if (CollectionUtils.isNotEmpty(milestoneIds)) {
            val milestoneMapper = AppContextUtil.getSpringBean(MilestoneMapper::class.java)
            val ex = MilestoneExample()
            ex.createCriteria().andIdIn(milestoneIds)
            milestoneMapper.deleteByExample(ex)
        }
    }

    private fun massDeleteTaskGanttItems(taskIds: List<Int>) {
        if (CollectionUtils.isNotEmpty(taskIds)) {
            val taskMapper = AppContextUtil.getSpringBean(TaskMapper::class.java)
            val ex = TaskExample()
            ex.createCriteria().andIdIn(taskIds)
            taskMapper.deleteByExample(ex)
        }
    }

    private fun massDeleteBugGanttItems(bugIds: List<Int>) {
        if (CollectionUtils.isNotEmpty(bugIds)) {
            val bugMapper = AppContextUtil.getSpringBean(BugMapper::class.java)
            val ex = BugExample()
            ex.createCriteria().andIdIn(bugIds)
            bugMapper.deleteByExample(ex)
        }
    }

    private fun getDateWithNullValue(date: java.util.Date?): Date? = if (date != null) Date(date.time) else null
}
