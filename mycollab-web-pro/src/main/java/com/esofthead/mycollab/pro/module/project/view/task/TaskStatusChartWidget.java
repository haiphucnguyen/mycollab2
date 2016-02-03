package com.esofthead.mycollab.pro.module.project.view.task;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.task.ITaskStatusChartWidget;
import com.esofthead.mycollab.pro.ui.chart.DataSeriesItemExt;
import com.esofthead.mycollab.pro.ui.chart.PieChartWrapper;
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
public class TaskStatusChartWidget extends PieChartWrapper<TaskSearchCriteria> implements ITaskStatusChartWidget {
    @Override
    protected List<GroupItem> loadGroupItems() {
        ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        return taskService.getStatusSummary(searchCriteria);
    }

    @Override
    protected DataSeries buildChartSeries() {
        DataSeries series = new DataSeries("Status");
        OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);
        List<OptionVal> optionVals = optionValService.findOptionVals(ProjectTypeConstants.TASK,
                CurrentProjectVariables.getProjectId(), AppContext.getAccountId());
        for (OptionVal optionVal : optionVals) {
            if (OptionI18nEnum.StatusI18nEnum.Closed.name().equals(optionVal.getTypeval())) {
                continue;
            }
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (optionVal.getTypeval().equals(item.getGroupid())) {
                    series.add(new DataSeriesItemExt(optionVal.getTypeval(), AppContext.getMessage(com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum.class,
                            optionVal.getTypeval()), item.getValue()));
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                series.add(new DataSeriesItemExt(optionVal.getTypeval(), AppContext.getMessage(com.esofthead.mycollab
                        .common.i18n.OptionI18nEnum.StatusI18nEnum.class, optionVal
                        .getTypeval()), 0));
            }
        }
        return series;
    }

    @Override
    public void clickLegendItem(String key) {
        TaskSearchCriteria cloneSearchCriteria = BeanUtility.deepClone(searchCriteria);
        cloneSearchCriteria.setStatuses(new SetSearchField<>(key));
        EventBusFactory.getInstance().post(new TaskEvent.SearchRequest(this, cloneSearchCriteria));
    }
}
