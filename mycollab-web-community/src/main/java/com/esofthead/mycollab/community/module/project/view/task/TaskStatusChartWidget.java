package com.esofthead.mycollab.community.module.project.view.task;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.community.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.task.ITaskStatusChartWidget;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class TaskStatusChartWidget extends PieChartWrapper<TaskSearchCriteria> implements ITaskStatusChartWidget {
    public TaskStatusChartWidget() {
        super(OptionI18nEnum.TaskPriority.class, 400, 280);
    }

    @Override
    protected DefaultPieDataset createDataset() {
        // create the dataset...
        final DefaultPieDataset dataset = new DefaultPieDataset();

        OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);
        List<OptionVal> optionVals = optionValService.findOptionVals(ProjectTypeConstants.TASK,
                CurrentProjectVariables.getProjectId(), AppContext.getAccountId());
        for (OptionVal optionVal : optionVals) {
            if (com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum.Closed.name().equals(optionVal.getTypeval())) {
                continue;
            }
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (optionVal.getTypeval().equals(item.getGroupid())) {
                    dataset.setValue(optionVal.getTypeval(), item.getValue());
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                dataset.setValue(optionVal.getTypeval(), 0);
            }
        }

        return dataset;
    }

    @Override
    protected List<GroupItem> loadGroupItems() {
        ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        return taskService.getStatusSummary(searchCriteria);
    }

    @Override
    public void clickLegendItem(String key) {
        TaskSearchCriteria cloneSearchCriteria = BeanUtility.deepClone(searchCriteria);
        cloneSearchCriteria.setStatuses(new SetSearchField<>(key));
        EventBusFactory.getInstance().post(new TaskEvent.SearchRequest(this, cloneSearchCriteria));
    }
}
