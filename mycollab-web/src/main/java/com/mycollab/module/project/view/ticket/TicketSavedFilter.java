package com.mycollab.module.project.view.ticket;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.db.arguments.SearchField;
import com.mycollab.db.query.*;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.query.CurrentProjectIdInjector;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.SavedFilterComboBox;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class TicketSavedFilter extends SavedFilterComboBox {
    public static final String ALL_TASKS = "ALL_TASKS";
    public static final String OPEN_TASKS = "OPEN_TASKS";
    public static final String OVERDUE_TASKS = "OVERDUE_TASKS";
    public static final String MY_TASKS = "MY_TASKS";
    public static final String TASKS_CREATED_BY_ME = "TASKS_CREATED_BY_ME";
    public static final String NEW_TASKS_THIS_WEEK = "NEW_TASKS_THIS_WEEK";
    public static final String UPDATE_TASKS_THIS_WEEK = "UPDATE_TASKS_THIS_WEEK";
    public static final String NEW_TASKS_LAST_WEEK = "NEW_TASKS_LAST_WEEK";
    public static final String UPDATE_TASKS_LAST_WEEK = "UPDATE_TASKS_LAST_WEEK";

    public TicketSavedFilter() {
        super(ProjectTypeConstants.TICKET);

        SearchQueryInfo allTasksQuery = new SearchQueryInfo(ALL_TASKS, UserUIContext.getMessage(TaskI18nEnum.VAL_ALL_TASKS),
                SearchFieldInfo.inCollection(ProjectTicketSearchCriteria.p_projectIds, new CurrentProjectIdInjector()));

        SearchQueryInfo allOpenTaskQuery = new SearchQueryInfo(OPEN_TASKS, UserUIContext.getMessage(TaskI18nEnum.VAL_ALL_OPEN_TASKS),
                SearchFieldInfo.notInCollection(ProjectTicketSearchCriteria.p_status, new VariableInjector() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(StatusI18nEnum.Closed, BugStatus.Verified);
                    }

                    @Override
                    public Class getType() {
                        return String.class;
                    }

                    @Override
                    public boolean isArray() {
                        return false;
                    }

                    @Override
                    public boolean isCollection() {
                        return true;
                    }
                }));

        SearchQueryInfo overdueTaskQuery = new SearchQueryInfo(OVERDUE_TASKS, UserUIContext.getMessage(TaskI18nEnum.VAL_OVERDUE_TASKS),
                new SearchFieldInfo(SearchField.AND, ProjectTicketSearchCriteria.p_dueDate, DateParam.BEFORE, new LazyValueInjector() {
                    @Override
                    protected Object doEval() {
                        return new LocalDate().toDate();
                    }
                }), SearchFieldInfo.notInCollection(ProjectTicketSearchCriteria.p_status, new VariableInjector() {
            @Override
            public Object eval() {
                return Arrays.asList(StatusI18nEnum.Closed, BugStatus.Verified);
            }

            @Override
            public Class getType() {
                return String.class;
            }

            @Override
            public boolean isArray() {
                return false;
            }

            @Override
            public boolean isCollection() {
                return true;
            }
        }));

        SearchQueryInfo myTasksQuery = new SearchQueryInfo(MY_TASKS, UserUIContext.getMessage(TaskI18nEnum.VAL_MY_TASKS),
                SearchFieldInfo.inCollection(ProjectTicketSearchCriteria.p_assignee,
                        ConstantValueInjector.valueOf(Collections.singletonList(UserUIContext.getUsername()))));

        SearchQueryInfo tasksCreatedByMeQuery = new SearchQueryInfo(TASKS_CREATED_BY_ME, UserUIContext.getMessage(TaskI18nEnum.VAL_TASKS_CREATED_BY_ME),
                SearchFieldInfo.inCollection(ProjectTicketSearchCriteria.p_createdUser, ConstantValueInjector.valueOf(Collections.singletonList(UserUIContext.getUsername()))));

        SearchQueryInfo newTasksThisWeekQuery = new SearchQueryInfo(NEW_TASKS_THIS_WEEK, UserUIContext.getMessage(TaskI18nEnum.VAL_NEW_THIS_WEEK),
                SearchFieldInfo.inDateRange(ProjectTicketSearchCriteria.p_createtime, VariableInjector.THIS_WEEK));

        SearchQueryInfo updateTasksThisWeekQuery = new SearchQueryInfo(UPDATE_TASKS_THIS_WEEK, UserUIContext.getMessage(TaskI18nEnum.VAL_UPDATE_THIS_WEEK),
                SearchFieldInfo.inDateRange(ProjectTicketSearchCriteria.p_lastupdatedtime, VariableInjector.THIS_WEEK));

        SearchQueryInfo newTasksLastWeekQuery = new SearchQueryInfo(NEW_TASKS_LAST_WEEK, UserUIContext.getMessage(TaskI18nEnum.VAL_NEW_LAST_WEEK),
                SearchFieldInfo.inDateRange(ProjectTicketSearchCriteria.p_createtime, VariableInjector.LAST_WEEK));

        SearchQueryInfo updateTasksLastWeekQuery = new SearchQueryInfo(UPDATE_TASKS_LAST_WEEK, UserUIContext.getMessage(TaskI18nEnum.VAL_UPDATE_LAST_WEEK),
                SearchFieldInfo.inDateRange(ProjectTicketSearchCriteria.p_lastupdatedtime, VariableInjector.LAST_WEEK));

        this.addSharedSearchQueryInfo(allTasksQuery);
        this.addSharedSearchQueryInfo(allOpenTaskQuery);
        this.addSharedSearchQueryInfo(overdueTaskQuery);
        this.addSharedSearchQueryInfo(myTasksQuery);
        this.addSharedSearchQueryInfo(tasksCreatedByMeQuery);
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
