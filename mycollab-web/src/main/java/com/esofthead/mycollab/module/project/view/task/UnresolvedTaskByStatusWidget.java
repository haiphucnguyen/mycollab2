package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ButtonI18nComp;
import com.esofthead.mycollab.vaadin.ui.DepotWithChart;
import com.esofthead.mycollab.vaadin.ui.ProgressBarIndicator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.google.common.eventbus.Subscribe;
import com.rits.cloning.Cloner;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class UnresolvedTaskByStatusWidget extends DepotWithChart {
    private static final long serialVersionUID = 1L;

    private TaskSearchCriteria searchCriteria;
    private int totalCount;
    private List<GroupItem> groupItems;

    private ApplicationEventListener<TaskEvent.HasTaskChange> taskChangeHandler = new
            ApplicationEventListener<TaskEvent.HasTaskChange>() {
                @Override
                @Subscribe
                public void handle(TaskEvent.HasTaskChange event) {
                    if (searchCriteria != null) {
                        UI.getCurrent().access(new Runnable() {
                            @Override
                            public void run() {
                                UnresolvedTaskByStatusWidget.this.setSearchCriteria(searchCriteria);
                            }
                        });
                    }
                }
            };

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(taskChangeHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(taskChangeHandler);
        super.detach();
    }

    public void setSearchCriteria(TaskSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;

        ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        totalCount = taskService.getTotalCount(searchCriteria);
        groupItems = taskService.getStatusSummary(searchCriteria);
        displayPlainMode();
    }

    @Override
    protected void displayPlainMode() {
        this.bodyContent.removeAllComponents();
        TaskStatusClickListener listener = new TaskStatusClickListener();
        this.setTitle(AppContext.getMessage(TaskI18nEnum.WIDGET_UNRESOLVED_BY_STATUS_TITLE) + " (" + totalCount + ")");

        if (!groupItems.isEmpty()) {
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
                        if (optionVal.getTypeval().equals(item.getGroupid())) {
                            isFound = true;
                            MHorizontalLayout statusLayout = new MHorizontalLayout().withWidth("100%");
                            MButton statusLink = new ButtonI18nComp(optionVal.getTypeval())
                                    .withCaption(AppContext.getMessage(OptionI18nEnum.StatusI18nEnum.class, optionVal.getTypeval()))
                                    .withListener(listener).withStyleName(UIConstants.THEME_LINK).withIcon(FontAwesome.FLAG);
                            statusLink.setWidth("110px");

                            statusLayout.addComponent(statusLink);
                            ProgressBarIndicator indicator = new ProgressBarIndicator(totalCount, totalCount - item.getValue(), false);
                            indicator.setWidth("100%");
                            statusLayout.with(indicator).expand(indicator);

                            this.bodyContent.addComponent(statusLayout);
                        }
                    }
                }
                if (!isFound) {
                    MHorizontalLayout statusLayout = new MHorizontalLayout().withWidth("100%");
                    MButton statusLink = new ButtonI18nComp(optionVal.getTypeval())
                            .withCaption(AppContext.getMessage(OptionI18nEnum.StatusI18nEnum.class, optionVal.getTypeval()))
                            .withListener(listener).withStyleName(UIConstants.THEME_LINK).withIcon(FontAwesome.FLAG);
                    statusLink.setWidth("110px");
                    statusLayout.addComponent(statusLink);
                    ProgressBarIndicator indicator = new ProgressBarIndicator(totalCount, totalCount, false);
                    indicator.setWidth("100%");
                    statusLayout.with(indicator).expand(indicator);
                    this.bodyContent.addComponent(statusLayout);
                }
            }
        }
    }

    @Override
    protected void displayChartMode() {
        this.bodyContent.removeAllComponents();
        ITaskStatusChartWidget taskStatusChartWidget = ViewManager.getCacheComponent(ITaskStatusChartWidget.class);
        taskStatusChartWidget.displayChart(searchCriteria);
        bodyContent.addComponent(taskStatusChartWidget);
    }

    private class TaskStatusClickListener implements Button.ClickListener {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(final Button.ClickEvent event) {
            String key = ((ButtonI18nComp) event.getButton()).getKey();
            Cloner cloner = new Cloner();
            TaskSearchCriteria criteria = cloner.deepClone(searchCriteria);
            criteria.setStatuses(new SetSearchField<>(key));
            EventBusFactory.getInstance().post(new TaskEvent.SearchRequest(this, criteria));
        }
    }
}
