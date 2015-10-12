package com.esofthead.mycollab.premium.module.project.view.task;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.view.task.ITaskAssigneeChartWidget;
import com.esofthead.mycollab.premium.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.model.DataSeries;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class TaskAssigneeChartWidget extends PieChartWrapper<TaskSearchCriteria> implements ITaskAssigneeChartWidget {
    @Override
    protected List<GroupItem> loadGroupItems() {
        return null;
    }

    @Override
    protected DataSeries buildChartSeries() {
        return null;
    }

    @Override
    public void clickLegendItem(String key) {

    }
}
