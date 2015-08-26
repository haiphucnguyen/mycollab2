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

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.common.interceptor.aspect.ClassInfo;
import com.esofthead.mycollab.common.interceptor.aspect.ClassInfoMap;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.lock.DistributionLockUtil;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.dao.PredecessorMapper;
import com.esofthead.mycollab.module.project.dao.TaskMapper;
import com.esofthead.mycollab.module.project.dao.TaskMapperExt;
import com.esofthead.mycollab.module.project.domain.PredecessorExample;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.esb.DeleteProjectTaskEvent;
import com.esofthead.mycollab.module.project.service.*;
import com.esofthead.mycollab.schedule.email.project.ProjectTaskRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.google.common.eventbus.AsyncEventBus;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(nameField = "taskname", extraFieldName = "projectid", notifyAgent = ProjectTaskRelayEmailNotificationAction.class)
@Watchable(userFieldName = "assignuser", extraTypeId = "projectid")
public class ProjectTaskServiceImpl extends DefaultService<Integer, Task, TaskSearchCriteria> implements ProjectTaskService {

    static {
        ClassInfo taskInfo = new ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.TASK);
        taskInfo.addExcludeHistoryField(Task.Field.taskindex.name());
        ClassInfoMap.put(ProjectTaskServiceImpl.class, taskInfo);
    }

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskMapperExt taskMapperExt;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private DataSource dataSource;

    @Override
    public ICrudGenericDAO<Integer, Task> getCrudMapper() {
        return taskMapper;
    }

    @Override
    public ISearchableDAO<TaskSearchCriteria> getSearchMapper() {
        return taskMapperExt;
    }

    @Override
    public SimpleTask findById(Integer taskId, Integer sAccountId) {
        return taskMapperExt.findTaskById(taskId);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Integer saveWithSession(Task record, String username) {
        if (record.getPercentagecomplete() == 100d) {
            record.setStatus(StatusI18nEnum.Closed.name());
        }

        if (record.getStatus() == null) {
            record.setStatus(StatusI18nEnum.Open.name());
        }
        record.setLogby(username);
        Lock lock = DistributionLockUtil.getLock("task-" + record.getSaccountid());

        try {
            if (lock.tryLock(120, TimeUnit.SECONDS)) {
                Integer key = taskMapperExt.getMaxKey(record.getProjectid());
                record.setTaskkey((key == null) ? 1 : (key + 1));

                CacheUtils.cleanCaches(record.getSaccountid(), ProjectService.class, ProjectGenericTaskService.class,
                        ProjectActivityStreamService.class, ProjectMemberService.class, MilestoneService.class);

                return super.saveWithSession(record, username);
            } else {
                throw new MyCollabException("Timeout operation.");
            }
        } catch (InterruptedException e) {
            throw new MyCollabException(e);
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    @Override
    public Integer updateWithSession(Task record, String username) {
        beforeUpdate(record);
        return super.updateWithSession(record, username);
    }

    private void beforeUpdate(Task record) {
        if ((record.getPercentagecomplete() != null) && (record.getPercentagecomplete() == 100)) {
            record.setStatus(StatusI18nEnum.Closed.name());
        } else if (record.getStatus() == null) {
            record.setStatus(StatusI18nEnum.Open.name());
        }

        CacheUtils.cleanCaches(record.getSaccountid(), ProjectService.class,
                ProjectGenericTaskService.class, ProjectActivityStreamService.class, ProjectMemberService.class,
                MilestoneService.class, ItemTimeLoggingService.class);
    }

    @Override
    public Integer updateSelectiveWithSession(Task record, String username) {
        beforeUpdate(record);
        return super.updateSelectiveWithSession(record, username);
    }

    @Override
    public void massRemoveWithSession(List<Task> items, String username, Integer accountId) {
        super.massRemoveWithSession(items, username, accountId);
        CacheUtils.cleanCaches(accountId, ProjectService.class, ProjectGenericTaskService.class,
                ProjectActivityStreamService.class, MilestoneService.class, ItemTimeLoggingService.class);
        DeleteProjectTaskEvent event = new DeleteProjectTaskEvent(items.toArray(new Task[items.size()]),
                username, accountId);
        asyncEventBus.post(event);
    }

    @Override
    public List<GroupItem> getPrioritySummary(TaskSearchCriteria criteria) {
        return taskMapperExt.getPrioritySummary(criteria);
    }

    @Override
    public List<GroupItem> getAssignedDefectsSummary(TaskSearchCriteria criteria) {
        return taskMapperExt.getAssignedDefectsSummary(criteria);
    }

    @Override
    public SimpleTask findByProjectAndTaskKey(Integer taskkey, String projectShortName, Integer sAccountId) {
        return taskMapperExt.findByProjectAndTaskKey(taskkey, projectShortName, sAccountId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SimpleTask> findSubTasks(Integer parentTaskId, Integer sAccountId, SearchCriteria.OrderField orderField) {
        TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(sAccountId));
        searchCriteria.setParentTaskId(new NumberSearchField(parentTaskId));
        searchCriteria.setOrderFields(Arrays.asList(orderField));
        return taskMapperExt.findPagableListByCriteria(searchCriteria, new RowBounds(0, Integer.MAX_VALUE));
    }

    @Override
    public void massUpdateTaskIndexes(final List<Map<String, Integer>> mapIndexes, @CacheKey Integer sAccountId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.batchUpdate("UPDATE `m_prj_task` SET `taskindex`=? WHERE `id`=?", new
                BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setInt(1, mapIndexes.get(i).get("index"));
                        preparedStatement.setInt(2, mapIndexes.get(i).get("id"));
                    }

                    @Override
                    public int getBatchSize() {
                        return mapIndexes.size();
                    }
                });
    }

    @Override
    public void massUpdateGanttIndexes(final List<Map<String, Integer>> mapIndexes, @CacheKey Integer sAccountId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.batchUpdate("UPDATE `m_prj_task` SET `ganttindex`=? WHERE `id`=?", new
                BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setInt(1, mapIndexes.get(i).get("index"));
                        preparedStatement.setInt(2, mapIndexes.get(i).get("id"));
                    }

                    @Override
                    public int getBatchSize() {
                        return mapIndexes.size();
                    }
                });
    }

    @Override
    public void massUpdatePredecessors(Integer taskSourceId, final List<TaskPredecessor> predecessors, @CacheKey Integer sAccountId) {
        Lock lock = DistributionLockUtil.getLock("task-service" + sAccountId);
        try {
            PredecessorMapper predecessorMapper = ApplicationContextUtil.getSpringBean(PredecessorMapper.class);
            PredecessorExample ex = new PredecessorExample();
            ex.createCriteria().andSourceidEqualTo(taskSourceId);
            predecessorMapper.deleteByExample(ex);

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            final long now = new GregorianCalendar().getTimeInMillis();
            if (lock.tryLock(30, TimeUnit.SECONDS)) {
                jdbcTemplate.batchUpdate("INSERT INTO `m_prj_predecessor`(`type`, `predestype`, `lagDay`, `sourceId`," +
                                "`descId`, `createdTime`) VALUES ('Project-Task', ?, ?, ?, ?, ?)",
                        new BatchPreparedStatementSetter() {
                            @Override
                            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                                preparedStatement.setString(1, predecessors.get(i).getPredestype());
                                preparedStatement.setInt(2, predecessors.get(i).getLagday());
                                preparedStatement.setInt(3, predecessors.get(i).getSourceid());
                                preparedStatement.setInt(4, predecessors.get(i).getDescid());
                                preparedStatement.setDate(5, new Date(now));
                            }

                            @Override
                            public int getBatchSize() {
                                return predecessors.size();
                            }
                        });
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        }

    }
}
