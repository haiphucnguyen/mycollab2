/**
 * This file is part of mycollab-web.
 * <p/>
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task.components;

import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.core.db.query.SearchFieldInfo;
import com.esofthead.mycollab.core.db.query.SearchQueryInfo;
import com.esofthead.mycollab.core.db.query.VariableInjecter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.query.CurrentProjectIdInjecter;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.SavedFilterComboBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class TaskSavedFilterComboBox extends SavedFilterComboBox {
    public static final String ALL_TASKS = "ALL_TASKS";
    public static final String OPEN_TASKS = "OPEN_TASKS";
    public static final String MY_TASKS = "MY_TASKS";
    public static final String NEW_TASKS_THIS_WEEK = "NEW_TASKS_THIS_WEEK";
    public static final String UPDATE_TASKS_THIS_WEEK = "UPDATE_TASKS_THIS_WEEK";
    public static final String NEW_TASKS_LAST_WEEK = "NEW_TASKS_LAST_WEEK";
    public static final String UPDATE_TASKS_LAST_WEEK = "UPDATE_TASKS_LAST_WEEK";

    public TaskSavedFilterComboBox() {
        super(ProjectTypeConstants.TASK);

        SearchQueryInfo allTasksQuery = new SearchQueryInfo(ALL_TASKS, "All Tasks", SearchFieldInfo.inCollection
                (TaskSearchCriteria.p_projectIds, new CurrentProjectIdInjecter()));

        SearchQueryInfo allOpenTaskQuery = new SearchQueryInfo(OPEN_TASKS, "All Open Task", SearchFieldInfo
                .inCollection(TaskSearchCriteria.p_status, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);
                        List<OptionVal> options = optionValService.findOptionValsExcludeClosed(ProjectTypeConstants.TASK,
                                CurrentProjectVariables.getProjectId(), AppContext.getAccountId());
                        List<String> statuses = new ArrayList<>();
                        for (OptionVal option : options) {
                            statuses.add(option.getTypeval());
                        }
                        return statuses;
                    }
                }));

        SearchQueryInfo myTasksQuery = new SearchQueryInfo(MY_TASKS, "My Tasks", SearchFieldInfo.inCollection
                (TaskSearchCriteria.p_assignee, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(AppContext.getUsername());
                    }
                }));

        SearchQueryInfo newTasksThisWeekQuery = new SearchQueryInfo(NEW_TASKS_THIS_WEEK, "New This Week",
                SearchFieldInfo.inDateRange(TaskSearchCriteria.p_createtime, VariableInjecter.THIS_WEEK));

        SearchQueryInfo updateTasksThisWeekQuery = new SearchQueryInfo(UPDATE_TASKS_THIS_WEEK, "Update This Week",
                SearchFieldInfo.inDateRange(TaskSearchCriteria.p_lastupdatedtime, VariableInjecter.THIS_WEEK));

        SearchQueryInfo newTasksLastWeekQuery = new SearchQueryInfo(NEW_TASKS_LAST_WEEK, "New Last Week",
                SearchFieldInfo.inDateRange(TaskSearchCriteria.p_createtime, VariableInjecter.LAST_WEEK));

        SearchQueryInfo updateTasksLastWeekQuery = new SearchQueryInfo(UPDATE_TASKS_LAST_WEEK, "Update Last Week",
                SearchFieldInfo.inDateRange(TaskSearchCriteria.p_lastupdatedtime, VariableInjecter.LAST_WEEK));

        this.addSharedSearchQueryInfo(allTasksQuery);
        this.addSharedSearchQueryInfo(allOpenTaskQuery);
        this.addSharedSearchQueryInfo(myTasksQuery);
        this.addSharedSearchQueryInfo(newTasksThisWeekQuery);
        this.addSharedSearchQueryInfo(updateTasksThisWeekQuery);
        this.addSharedSearchQueryInfo(newTasksLastWeekQuery);
        this.addSharedSearchQueryInfo(updateTasksLastWeekQuery);
    }

    public void setTotalCountNumber(int countNumber) {
        componentsText.setReadOnly(false);
        componentsText.setValue(selectedQueryName + " (" + countNumber + ")");
        componentsText.setReadOnly(true);
    }
}
