package com.mycollab.pro.module.project.service.impl;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.concurrent.DistributionLockUtil;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.*;
import com.mycollab.pro.module.project.dao.GanttMapperExt;
import com.mycollab.module.project.dao.MilestoneMapper;
import com.mycollab.module.project.dao.PredecessorMapper;
import com.mycollab.module.project.dao.TaskMapper;
import com.mycollab.module.project.service.GanttAssignmentService;
import com.mycollab.module.tracker.dao.BugMapper;
import com.mycollab.module.tracker.domain.BugExample;
import com.mycollab.spring.AppContextUtil;
import com.google.common.base.MoreObjects;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Service
public class GanttAssignmentServiceImpl implements GanttAssignmentService {

    @Autowired
    private GanttMapperExt ganttMapperExt;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<AssignWithPredecessors> getTaskWithPredecessors(List<Integer> projectIds, @CacheKey Integer sAccountId) {
        return ganttMapperExt.getTaskWithPredecessors(projectIds, sAccountId);
    }

    @Override
    public void massUpdateGanttItems(final List<AssignWithPredecessors> ganttItems, Integer sAccountId) {
        if (CollectionUtils.isNotEmpty(ganttItems)) {
            List<MilestoneGanttItem> milestoneGanttItems = new ArrayList<>();
            List<TaskGanttItem> taskGanttItems = new ArrayList<>();
            List<TaskGanttItem> bugGanttItems = new ArrayList<>();

            for (AssignWithPredecessors ganttItem : ganttItems) {
                if (ganttItem instanceof MilestoneGanttItem) {
                    milestoneGanttItems.add((MilestoneGanttItem) ganttItem);
                } else if (ganttItem instanceof TaskGanttItem) {
                    if (ProjectTypeConstants.BUG.equals(ganttItem.getType())) {
                        bugGanttItems.add((TaskGanttItem) ganttItem);
                    } else if (ProjectTypeConstants.TASK.equals(ganttItem.getType())) {
                        taskGanttItems.add((TaskGanttItem) ganttItem);
                    }
                } else {
                    throw new MyCollabException("Do not support save gantt item " + ganttItem);
                }
            }
            massUpdateMilestoneGanttItems(milestoneGanttItems, sAccountId);
            massUpdateTaskGanttItems(taskGanttItems, sAccountId);
            massUpdateBugGanttItems(bugGanttItems, sAccountId);
        }
    }

    @Override
    public void massUpdatePredecessors(Integer taskSourceId, final List<TaskPredecessor> predecessors, Integer sAccountId) {
        Lock lock = DistributionLockUtil.INSTANCE.getLock("task-service" + sAccountId);
        try {
            PredecessorMapper predecessorMapper = AppContextUtil.getSpringBean(PredecessorMapper.class);
            PredecessorExample ex = new PredecessorExample();
            ex.createCriteria().andSourceidEqualTo(taskSourceId);
            predecessorMapper.deleteByExample(ex);

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            final long now = new GregorianCalendar().getTimeInMillis();
            if (lock.tryLock(30, TimeUnit.SECONDS)) {
                jdbcTemplate.batchUpdate("INSERT INTO `m_prj_predecessor`(`sourceType`, `descType`, `predestype`,`lagDay`, " +
                                "`sourceId`,`descId`, `createdTime`) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        new BatchPreparedStatementSetter() {
                            @Override
                            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                                preparedStatement.setString(1, predecessors.get(i).getSourcetype());
                                preparedStatement.setString(2, predecessors.get(i).getDesctype());
                                preparedStatement.setString(3, predecessors.get(i).getPredestype());
                                preparedStatement.setInt(4, predecessors.get(i).getLagday());
                                preparedStatement.setInt(5, predecessors.get(i).getSourceid());
                                preparedStatement.setInt(6, predecessors.get(i).getDescid());
                                preparedStatement.setDate(7, new Date(now));
                            }

                            @Override
                            public int getBatchSize() {
                                return predecessors.size();
                            }
                        });
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        } finally {
            DistributionLockUtil.INSTANCE.removeLock("task-service" + sAccountId);
            lock.unlock();
        }
    }

    @Override
    public void massDeletePredecessors(List<TaskPredecessor> predecessors, @CacheKey Integer sAccountId) {
        Lock lock = DistributionLockUtil.INSTANCE.getLock("gantt-predecessor-service" + sAccountId);
        try {
            if (lock.tryLock(30, TimeUnit.SECONDS)) {
                try (Connection connection = dataSource.getConnection()) {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " +
                            "`m_prj_predecessor` WHERE sourceType=? AND predestype=? AND sourceId=? AND descId=? AND descType=?");
                    for (TaskPredecessor predecessor : predecessors) {
                        preparedStatement.setString(1, predecessor.getSourcetype());
                        preparedStatement.setString(2, predecessor.getPredestype());
                        preparedStatement.setInt(3, predecessor.getSourceid());
                        preparedStatement.setInt(4, predecessor.getDescid());
                        preparedStatement.setString(5, predecessor.getDesctype());
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
                    connection.commit();
                }
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        } finally {
            DistributionLockUtil.INSTANCE.removeLock("gantt-predecessor-service" + sAccountId);
            lock.unlock();
        }
    }

    private void massUpdateMilestoneGanttItems(final List<MilestoneGanttItem> milestoneGanttItems, Integer sAccountId) {
        if (CollectionUtils.isNotEmpty(milestoneGanttItems)) {
            Lock lock = DistributionLockUtil.INSTANCE.getLock("gantt-milestone-service" + sAccountId);
            try {
                final long now = new GregorianCalendar().getTimeInMillis();
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    try (Connection connection = dataSource.getConnection()) {
                        connection.setAutoCommit(false);
                        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `m_prj_milestone` SET " +
                                "name = ?, `startdate` = ?, `enddate` = ?, " +
                                "`lastUpdatedTime`=?, `assignUser`=?, `ganttIndex`=? WHERE `id` = ?");
                        for (MilestoneGanttItem milestoneGanttItem : milestoneGanttItems) {
                            preparedStatement.setString(1, milestoneGanttItem.getName());
                            preparedStatement.setDate(2, getDateWithNullValue(milestoneGanttItem.getStartDate()));
                            preparedStatement.setDate(3, getDateWithNullValue(milestoneGanttItem.getEndDate()));
                            preparedStatement.setDate(4, new Date(now));
                            preparedStatement.setString(5, milestoneGanttItem.getAssignUser());
                            preparedStatement.setInt(6, milestoneGanttItem.getGanttIndex());
                            preparedStatement.setInt(7, milestoneGanttItem.getId());
                            preparedStatement.addBatch();

                        }
                        preparedStatement.executeBatch();
                        connection.commit();
                    }
                }
            } catch (Exception e) {
                throw new MyCollabException(e);
            } finally {
                DistributionLockUtil.INSTANCE.removeLock("gantt-milestone-service" + sAccountId);
                lock.unlock();
            }
        }
    }

    private void massUpdateTaskGanttItems(final List<TaskGanttItem> taskGanttItems, Integer sAccountId) {
        if (CollectionUtils.isNotEmpty(taskGanttItems)) {
            Lock lock = DistributionLockUtil.INSTANCE.getLock("gantt-task-service" + sAccountId);
            try {
                final long now = new GregorianCalendar().getTimeInMillis();
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    try (Connection connection = dataSource.getConnection()) {
                        connection.setAutoCommit(false);
                        PreparedStatement batchTasksStatement = connection.prepareStatement("UPDATE `m_prj_task` SET " +
                                "name = ?, `startdate` = ?, `enddate` = ?, " +
                                "`lastUpdatedTime`=?, `percentagecomplete`=?, `assignUser`=?, `ganttindex`=?, " +
                                "`milestoneId`=?, `parentTaskId`=? WHERE `id` = ?");
                        for (TaskGanttItem ganttItem : taskGanttItems) {
                            if (ProjectTypeConstants.TASK.equals(ganttItem.getType())) {
                                batchTasksStatement.setString(1, ganttItem.getName());
                                batchTasksStatement.setDate(2, getDateWithNullValue(ganttItem.getStartDate()));
                                batchTasksStatement.setDate(3, getDateWithNullValue(ganttItem.getEndDate()));
                                batchTasksStatement.setDate(4, new Date(now));
                                batchTasksStatement.setDouble(5, ganttItem.getProgress());
                                batchTasksStatement.setString(6, ganttItem.getAssignUser());
                                batchTasksStatement.setInt(7, ganttItem.getGanttIndex());
                                batchTasksStatement.setObject(8, ganttItem.getMilestoneId());
                                batchTasksStatement.setObject(9, ganttItem.getParentTaskId());
                                batchTasksStatement.setInt(10, ganttItem.getId());
                                batchTasksStatement.addBatch();
                            }

                        }
                        batchTasksStatement.executeBatch();
                        connection.commit();
                    }
                }
            } catch (Exception e) {
                throw new MyCollabException(e);
            } finally {
                DistributionLockUtil.INSTANCE.removeLock("gantt-task-service" + sAccountId);
                lock.unlock();
            }
        }
    }

    private void massUpdateBugGanttItems(final List<TaskGanttItem> taskGanttItems, Integer sAccountId) {
        if (CollectionUtils.isNotEmpty(taskGanttItems)) {
            Lock lock = DistributionLockUtil.INSTANCE.getLock("gantt-bug-service" + sAccountId);
            try {
                final long now = new GregorianCalendar().getTimeInMillis();
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    try (Connection connection = dataSource.getConnection()) {
                        connection.setAutoCommit(false);
                        PreparedStatement batchTasksStatement = connection.prepareStatement("UPDATE `m_tracker_bug` SET " +
                                "name = ?, `startdate` = ?, `enddate` = ?, " +
                                "`lastUpdatedTime`=?, `percentagecomplete`=?, `assignuser`=?, `ganttindex`=?, " +
                                "`milestoneId`=? WHERE `id` = ?");
                        for (TaskGanttItem ganttItem : taskGanttItems) {
                            if (ProjectTypeConstants.BUG.equals(ganttItem.getType())) {
                                batchTasksStatement.setString(1, ganttItem.getName());
                                batchTasksStatement.setDate(2, getDateWithNullValue(ganttItem.getStartDate()));
                                batchTasksStatement.setDate(3, getDateWithNullValue(ganttItem.getEndDate()));
                                batchTasksStatement.setDate(4, new Date(now));
                                batchTasksStatement.setDouble(5, MoreObjects.firstNonNull(ganttItem.getProgress(), 0d));
                                batchTasksStatement.setString(6, ganttItem.getAssignUser());
                                batchTasksStatement.setInt(7, ganttItem.getGanttIndex());
                                batchTasksStatement.setObject(8, ganttItem.getMilestoneId());
                                batchTasksStatement.setInt(9, ganttItem.getId());
                                batchTasksStatement.addBatch();
                            }

                        }
                        batchTasksStatement.executeBatch();
                        connection.commit();
                    }
                }
            } catch (Exception e) {
                throw new MyCollabException(e);
            } finally {
                DistributionLockUtil.INSTANCE.removeLock("gantt-bug-service" + sAccountId);
                lock.unlock();
            }
        }
    }

    @Override
    public void massDeleteGanttItems(List<AssignWithPredecessors> ganttItems, Integer sAccountId) {
        if (CollectionUtils.isNotEmpty(ganttItems)) {
            List<Integer> milestoneIds = new ArrayList<>();
            List<Integer> taskIds = new ArrayList<>();
            List<Integer> bugIds = new ArrayList<>();

            for (AssignWithPredecessors ganttItem : ganttItems) {
                if (ganttItem instanceof MilestoneGanttItem) {
                    if (ganttItem.getId() != null) {
                        milestoneIds.add(ganttItem.getId());
                    }

                } else if (ganttItem instanceof TaskGanttItem) {
                    if (ProjectTypeConstants.TASK.equals(ganttItem.getType()) && ganttItem.getId() != null) {
                        taskIds.add(ganttItem.getId());
                    } else if (ProjectTypeConstants.BUG.equals(ganttItem.getType()) && ganttItem.getId() != null) {
                        bugIds.add(ganttItem.getId());
                    }
                } else {
                    throw new MyCollabException("Do not support delete gantt item " + ganttItem);
                }
            }
            massDeleteMilestoneGanttItems(milestoneIds);
            massDeleteTaskGanttItems(taskIds);
            massDeleteBugGanttItems(bugIds);
        }
    }

    private void massDeleteMilestoneGanttItems(List<Integer> milestoneIds) {
        if (CollectionUtils.isNotEmpty(milestoneIds)) {
            MilestoneMapper milestoneMapper = AppContextUtil.getSpringBean(MilestoneMapper.class);
            MilestoneExample ex = new MilestoneExample();
            ex.createCriteria().andIdIn(milestoneIds);
            milestoneMapper.deleteByExample(ex);
        }
    }

    private void massDeleteTaskGanttItems(List<Integer> taskIds) {
        if (CollectionUtils.isNotEmpty(taskIds)) {
            TaskMapper taskMapper = AppContextUtil.getSpringBean(TaskMapper.class);
            TaskExample ex = new TaskExample();
            ex.createCriteria().andIdIn(taskIds);
            taskMapper.deleteByExample(ex);
        }
    }

    private void massDeleteBugGanttItems(List<Integer> bugIds) {
        if (CollectionUtils.isNotEmpty(bugIds)) {
            BugMapper bugMapper = AppContextUtil.getSpringBean(BugMapper.class);
            BugExample ex = new BugExample();
            ex.createCriteria().andIdIn(bugIds);
            bugMapper.deleteByExample(ex);
        }
    }

    private static Date getDateWithNullValue(java.util.Date date) {
        return (date != null) ? new Date(date.getTime()) : null;
    }
}
