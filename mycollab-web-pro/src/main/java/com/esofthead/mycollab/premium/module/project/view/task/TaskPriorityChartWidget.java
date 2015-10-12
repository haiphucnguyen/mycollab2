package com.esofthead.mycollab.premium.module.project.view.task;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.task.ITaskPriorityChartWidget;
import com.esofthead.mycollab.premium.ui.chart.DataSeriesItemExt;
import com.esofthead.mycollab.premium.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.model.DataSeries;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class TaskPriorityChartWidget extends PieChartWrapper<TaskSearchCriteria> implements ITaskPriorityChartWidget {
    @Override
    protected List<GroupItem> loadGroupItems() {
        ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        return taskService.getPrioritySummary(searchCriteria);
    }

    @Override
    protected DataSeries buildChartSeries() {
        DataSeries series = new DataSeries("Priority");
        OptionI18nEnum.TaskPriority[] priorities = OptionI18nEnum.task_priorities;
        for (OptionI18nEnum.TaskPriority priority : priorities) {
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (priority.name().equals(item.getGroupid())) {
                    series.add(new DataSeriesItemExt(priority.name(), AppContext.getMessage(OptionI18nEnum
                            .TaskPriority.class, priority.name()), item.getValue()));
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                series.add(new DataSeriesItemExt(priority.name(), AppContext.getMessage(OptionI18nEnum
                        .TaskPriority.class, priority.name()), 0));
            }
        }
        return series;
    }

    @Override
    public void clickLegendItem(String key) {
        TaskSearchCriteria cloneSearchCriteria = BeanUtility.deepClone(searchCriteria);
        cloneSearchCriteria.setPriorities(new SetSearchField<>(key));
        EventBusFactory.getInstance().post(new TaskEvent.SearchRequest(this, cloneSearchCriteria));
    }
}
