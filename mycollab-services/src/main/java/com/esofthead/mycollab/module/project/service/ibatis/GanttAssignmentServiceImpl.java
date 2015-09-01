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

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.module.project.dao.GanttMapperExt;
import com.esofthead.mycollab.module.project.domain.AssignWithPredecessors;
import com.esofthead.mycollab.module.project.service.GanttAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GanttAssignmentServiceImpl implements GanttAssignmentService {

    @Autowired
    private GanttMapperExt ganttMapperExt;

    @Override
    public List<AssignWithPredecessors> getTaskWithPredecessors(List<Integer> projectIds, @CacheKey Integer sAccountId) {
        return ganttMapperExt.getTaskWithPredecessors(projectIds, sAccountId);
    }

    @Override
    public void massUpdateTaskDates(final List<AssignWithPredecessors> tasks, @CacheKey Integer sAccountId) {
//        if (tasks.size() > 0) {
//            Lock lock = DistributionLockUtil.getLock("task-service" + sAccountId);
//            try {
//                final long now = new GregorianCalendar().getTimeInMillis();
//                if (lock.tryLock(30, TimeUnit.SECONDS)) {
//                    try (Connection connection = dataSource.getConnection()) {
//                        connection.setAutoCommit(false);
//                        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `m_prj_task` SET `startdate` = ?, " +
//                                "`enddate` = ?, `lastUpdatedTime`=? WHERE `id` = ?");
//                        for (int i = 0; i < tasks.size(); i++) {
//                            SimpleTask task = tasks.get(i);
//                            if (task.getStartdate() != null && task.getEnddate() != null) {
//                                preparedStatement.setDate(1, new Date(tasks.get(i).getStartdate().getTime()));
//                                preparedStatement.setDate(2, new Date(tasks.get(i).getEnddate().getTime()));
//                                preparedStatement.setDate(3, new Date(now));
//                                preparedStatement.setInt(4, tasks.get(i).getId());
//                                preparedStatement.addBatch();
//                            } else {
//                                LOG.error("Task " + BeanUtility.printBeanObj(task) + " should have not null dates");
//                            }
//
//                        }
//                        preparedStatement.executeBatch();
//                        connection.commit();
//                    }
//                }
//            } catch (Exception e) {
//                throw new MyCollabException(e);
//            }
//        }

    }
}
