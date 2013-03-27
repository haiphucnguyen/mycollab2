/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.TaskListMapper;
import com.esofthead.mycollab.module.project.dao.TaskListMapperExt;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author haiphucnguyen
 */
@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, type = ProjectContants.TASK_LIST, nameField = "name", extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.TASK_LIST)
public class ProjectTaskListServiceImpl extends DefaultService<Integer, TaskList, TaskListSearchCriteria> implements
        ProjectTaskListService {

    @Autowired
    protected TaskListMapper projectTaskListMapper;
    @Autowired
    protected TaskListMapperExt projectTaskListMapperExt;

    @Override
    public ICrudGenericDAO<Integer, TaskList> getCrudMapper() {
        return projectTaskListMapper;
    }

    @Override
    public ISearchableDAO<TaskListSearchCriteria> getSearchMapper() {
        return projectTaskListMapperExt;
    }

    @Override
    public SimpleTaskList findTaskListById(int taskListId) {
        return projectTaskListMapperExt.findTaskListById(taskListId);
    }

    @Override
    public void updateTaskListIndex(TaskList[] taskLists) {
        for (TaskList taskList : taskLists) {
            projectTaskListMapper.updateByPrimaryKeySelective(taskList);
        }
    }
}
