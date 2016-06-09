package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.reporting.CustomizeReportOutputWindow;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class TaskCustomizeReportOutputWindow extends CustomizeReportOutputWindow<TaskSearchCriteria, SimpleTask> {
    public TaskCustomizeReportOutputWindow(VariableInjector<TaskSearchCriteria> variableInjector) {
        super(ProjectTypeConstants.TASK, AppContext.getMessage(TaskI18nEnum.LIST), SimpleTask.class,
                AppContextUtil.getSpringBean(ProjectTaskService.class), variableInjector);
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"Task A", AppContext.formatDate(new LocalDate().minusDays(2).toDate()), AppContext.formatDate(new LocalDate().plusDays(1).toDate()),
                OptionI18nEnum.TaskPriority.High.name(), "50", "Jonh Adam", "3", "2"};
    }

    @Override
    protected Collection<TableViewField> getDefaultColumns() {
        return Arrays.asList(TaskTableFieldDef.taskname(), TaskTableFieldDef.startdate(), TaskTableFieldDef.duedate(),
                TaskTableFieldDef.priority(), TaskTableFieldDef.percentagecomplete(), TaskTableFieldDef.assignee(),
                TaskTableFieldDef.billableHours(), TaskTableFieldDef.nonBillableHours());
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(TaskTableFieldDef.taskname(), TaskTableFieldDef.startdate(), TaskTableFieldDef.duedate(),
                TaskTableFieldDef.priority(), TaskTableFieldDef.percentagecomplete(), TaskTableFieldDef.assignee(),
                TaskTableFieldDef.billableHours(), TaskTableFieldDef.nonBillableHours());
    }
}
