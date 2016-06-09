package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.reporting.CustomizeReportOutputWindow;
import org.joda.time.LocalDate;

import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class TaskCustomizeReportOutputWindow extends CustomizeReportOutputWindow<TaskSearchCriteria, SimpleTask> {
    public TaskCustomizeReportOutputWindow(VariableInjector<TaskSearchCriteria> variableInjector) {
        super(variableInjector);

    }

    @Override
    protected SimpleTask buildSampleData() {
        SimpleTask task = new SimpleTask();
        task.setTaskname("Task 1");
        task.setStatus(OptionI18nEnum.StatusI18nEnum.Open.name());
        task.setPriority(com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.TaskPriority.High.name());
        task.setStartdate(new LocalDate().minusDays(2).toDate());
        task.setDeadline(new LocalDate().plusDays(1).toDate());
        task.setPercentagecomplete(0.5d);
        task.setAssignUserFullName("Jonh Adam");
        task.setBillableHours(11d);
        task.setNonBillableHours(2d);
        return task;
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return null;
    }
}
