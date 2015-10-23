package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.core.db.query.SearchFieldInfo;
import com.esofthead.mycollab.core.db.query.SearchQueryInfo;
import com.esofthead.mycollab.core.db.query.VariableInjecter;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.query.CurrentProjectIdInjecter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.SavedFilterComboBox;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class TaskSavedFilterComboBox extends SavedFilterComboBox {
    public TaskSavedFilterComboBox() {
        super(ProjectTypeConstants.TASK);

        SearchQueryInfo allTasksQuery = new SearchQueryInfo("All Tasks", SearchFieldInfo.inCollection
                (TaskSearchCriteria.p_projectIds, new CurrentProjectIdInjecter()));

        SearchQueryInfo allOpenTaskQuery = new SearchQueryInfo("All Open Task", SearchFieldInfo.inCollection(
                TaskSearchCriteria.p_status, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(OptionI18nEnum.StatusI18nEnum.Open.name());
                    }
                }));

        SearchQueryInfo myTasksQuery = new SearchQueryInfo("My Tasks", SearchFieldInfo.inCollection
                (TaskSearchCriteria.p_assignee, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(AppContext.getUsername());
                    }
                }));

        SearchQueryInfo newTasksThisWeekQuery = new SearchQueryInfo("New This Week", SearchFieldInfo.inDateRange
                (TaskSearchCriteria.p_createtime, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        LocalDate date = new LocalDate(new Date());
                        LocalDate minDate = date.dayOfWeek().withMinimumValue();
                        LocalDate maxDate = date.dayOfWeek().withMaximumValue();
                        return new Date[]{minDate.toDate(), maxDate.toDate()};
                    }
                }));

        SearchQueryInfo newTasksLastWeekQuery = new SearchQueryInfo("New Last Week", SearchFieldInfo.inDateRange
                (TaskSearchCriteria.p_createtime, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        LocalDate date = new LocalDate(new Date());
                        date = date.minusWeeks(-1);
                        LocalDate minDate = date.dayOfWeek().withMinimumValue();
                        LocalDate maxDate = date.dayOfWeek().withMaximumValue();
                        return new Date[]{minDate.toDate(), maxDate.toDate()};
                    }
                }));

        this.addSharedSearchQueryInfo(allTasksQuery);
        this.addSharedSearchQueryInfo(allOpenTaskQuery);
        this.addSharedSearchQueryInfo(myTasksQuery);
        this.addSharedSearchQueryInfo(newTasksThisWeekQuery);
        this.addSharedSearchQueryInfo(newTasksLastWeekQuery);
    }


}
