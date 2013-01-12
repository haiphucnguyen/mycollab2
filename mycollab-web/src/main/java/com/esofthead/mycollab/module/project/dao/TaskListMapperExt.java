/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;

/**
 *
 * @author haiphucnguyen
 */
public interface TaskListMapperExt extends ISearchableDAO<TaskListSearchCriteria>{
    SimpleTaskList findTaskListById(int taskListId);
}
