/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.lock.DistributionLockUtil;
import com.esofthead.mycollab.module.project.dao.GanttMapperExt;
import com.esofthead.mycollab.module.project.domain.AssignWithPredecessors;
import com.esofthead.mycollab.module.project.domain.MilestoneGanttItem;
import com.esofthead.mycollab.module.project.domain.TaskGanttItem;
import com.esofthead.mycollab.module.project.service.GanttAssignmentService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Service
public class GanttAssignmentServiceImpl implements GanttAssignmentService {
    private static Logger LOG = LoggerFactory.getLogger(GanttAssignmentServiceImpl.class);

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
            for (AssignWithPredecessors ganttItem : ganttItems) {
                if (ganttItem instanceof MilestoneGanttItem) {
                    milestoneGanttItems.add((MilestoneGanttItem) ganttItem);
                } else if (ganttItem instanceof TaskGanttItem) {
                    taskGanttItems.add((TaskGanttItem) ganttItem);
                } else {
                    throw new MyCollabException("Do not support save gantt item " + ganttItem);
                }
            }
            massUpdateMilestoneGanttItems(milestoneGanttItems, sAccountId);
            massUpdateTaskGanttItems(taskGanttItems, sAccountId);
        }

    }

    private void massUpdateMilestoneGanttItems(final List<MilestoneGanttItem> milestoneGanttItems, Integer sAccountId) {
        if (CollectionUtils.isNotEmpty(milestoneGanttItems)) {
            Lock lock = DistributionLockUtil.getLock("gantt-task-service" + sAccountId);
            try {
                final long now = new GregorianCalendar().getTimeInMillis();
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    try (Connection connection = dataSource.getConnection()) {
                        connection.setAutoCommit(false);
                        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `m_prj_milestone` SET " +
                                "name = ?, `startdate` = ?, `enddate` = ?, `lastUpdatedTime`=? WHERE `id` = ?");
                        for (int i = 0; i < milestoneGanttItems.size(); i++) {
                            MilestoneGanttItem milestone = milestoneGanttItems.get(i);
                            if (milestone.getStartDate() != null && milestone.getEndDate() != null) {
                                preparedStatement.setString(1, milestoneGanttItems.get(i).getName());
                                preparedStatement.setDate(2, new Date(milestoneGanttItems.get(i).getStartDate().getTime()));
                                preparedStatement.setDate(3, new Date(milestoneGanttItems.get(i).getEndDate().getTime()));
                                preparedStatement.setDate(4, new Date(now));
                                preparedStatement.setInt(5, milestoneGanttItems.get(i).getId());
                                preparedStatement.addBatch();
                            } else {
//                                LOG.error("Task " + BeanUtility.printBeanObj(milestone) + " should have not null dates");
                            }

                        }
                        preparedStatement.executeBatch();
                        connection.commit();
                    }
                }
            } catch (Exception e) {
                throw new MyCollabException(e);
            }
        }
    }

    private void massUpdateTaskGanttItems(final List<TaskGanttItem> taskGanttItems, Integer sAccountId) {
        if (CollectionUtils.isNotEmpty(taskGanttItems)) {
            Lock lock = DistributionLockUtil.getLock("gantt-task-service" + sAccountId);
            try {
                final long now = new GregorianCalendar().getTimeInMillis();
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    try (Connection connection = dataSource.getConnection()) {
                        connection.setAutoCommit(false);
                        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `m_prj_task` SET " +
                                "taskname = ?, `startdate` = ?, `enddate` = ?, `lastUpdatedTime`=? WHERE `id` = ?");
                        for (int i = 0; i < taskGanttItems.size(); i++) {
                            preparedStatement.setString(1, taskGanttItems.get(i).getName());
                            preparedStatement.setDate(2, getDateWithNullValue(taskGanttItems.get(i).getStartDate()));
                            preparedStatement.setDate(3, getDateWithNullValue(taskGanttItems.get(i).getEndDate()));
                            preparedStatement.setDate(4, new Date(now));
                            preparedStatement.setInt(5, taskGanttItems.get(i).getId());
                            preparedStatement.addBatch();


                        }
                        preparedStatement.executeBatch();
                        connection.commit();
                    }
                }
            } catch (Exception e) {
                throw new MyCollabException(e);
            }
        }
    }

    private static Date getDateWithNullValue(java.util.Date date) {
        return (date != null) ? new Date(date.getTime()) : null;
    }
}
