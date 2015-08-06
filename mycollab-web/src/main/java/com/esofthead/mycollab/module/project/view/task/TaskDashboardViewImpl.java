/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.TaskGroupI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandler;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.vaadin.floatingcomponent.FloatingComponent;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class TaskDashboardViewImpl extends AbstractLazyPageView implements TaskDashboardView {
    private static final long serialVersionUID = 1L;

    static final String GROUP_DUE_DATE = "Due Date";
    static final String GROUP_START_DATE = "Start Date";
    static final String PLAIN_LIST = "Plain";

    private String groupByState;
    private TaskSearchCriteria baseCriteria;

    private TaskSearchPanel taskSearchPanel;
    private CssLayout wrapBody;

    private VerticalLayout rightColumn;

    private MHorizontalLayout mainLayout;

    public TaskDashboardViewImpl() {
        this.withMargin(new MarginInfo(false, true, true, true));
        taskSearchPanel = new TaskSearchPanel();
        MHorizontalLayout groupWrapLayout = new MHorizontalLayout();
        groupWrapLayout.addComponent(new Label("Group by:"));
        final ComboBox groupCombo = new ValueComboBox(false, GROUP_DUE_DATE, GROUP_START_DATE, PLAIN_LIST);
        groupCombo.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                groupByState = (String) groupCombo.getValue();
                queryAndDisplayTasks();
            }
        });
        groupByState = GROUP_DUE_DATE;
        groupWrapLayout.addComponent(groupCombo);

        taskSearchPanel.addHeaderRight(groupWrapLayout);

        Button newTaskListBtn = new Button(AppContext.getMessage(TaskI18nEnum.BUTTON_NEW_TASK), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                EventBusFactory.getInstance().post(new TaskEvent.GotoAdd(TaskDashboardViewImpl.this, null));
            }
        });
        newTaskListBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
        newTaskListBtn.setIcon(FontAwesome.PLUS);
        newTaskListBtn.setDescription(AppContext.getMessage(TaskI18nEnum.BUTTON_NEW_TASKGROUP));
        newTaskListBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        groupWrapLayout.addComponent(newTaskListBtn);

        Button advanceDisplayBtn = new Button();
        advanceDisplayBtn.setIcon(FontAwesome.SITEMAP);
        advanceDisplayBtn.setDescription(AppContext.getMessage(TaskGroupI18nEnum.ADVANCED_VIEW_TOOLTIP));

        Button chartDisplayBtn = new Button(null, new Button.ClickListener() {
            private static final long serialVersionUID = -5707546605789537298L;

            @Override
            public void buttonClick(ClickEvent event) {
                displayGanttChartView();
            }
        });
        chartDisplayBtn.setDescription("Display Gantt chart");
        chartDisplayBtn.setIcon(FontAwesome.BAR_CHART_O);

        Button kanbanBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                displayKanbanView();
            }
        });
        kanbanBtn.setDescription("Kanban View");
        kanbanBtn.setIcon(FontAwesome.TH);

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.addButton(chartDisplayBtn);
        viewButtons.setDefaultButton(advanceDisplayBtn);
        groupWrapLayout.addComponent(viewButtons);

        mainLayout = new MHorizontalLayout().withFullHeight().withFullWidth();
        this.wrapBody = new CssLayout();

        this.rightColumn = new MVerticalLayout().withWidth("300px").withMargin(false);

        mainLayout.with(wrapBody, rightColumn).expand(wrapBody);

        FloatingComponent floatSidebar = FloatingComponent.floatThis(this.rightColumn);
        floatSidebar.setContainerId("main-body");
    }

    private void constructUI() {
        this.with(taskSearchPanel, mainLayout);
    }

    @Override
    protected void displayView() {
        constructUI();
        baseCriteria = new TaskSearchCriteria();
        baseCriteria.setProjectid(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);
        List<OptionVal> options = optionValService.findOptionValsExcludeClosed(ProjectTypeConstants.TASK,
                CurrentProjectVariables.getProjectId(), AppContext.getAccountId());

        SetSearchField<String> statuses = new SetSearchField<>();
        for (OptionVal option : options) {
            statuses.addValue(option.getTypeval());
        }
        baseCriteria.setStatuses(statuses);
        queryAndDisplayTasks();
        displayTaskStatistic();
    }

    private void displayTaskStatistic() {
        rightColumn.removeAllComponents();
        UnresolvedTaskByAssigneeWidget unresolvedTaskByAssigneeWidget = new UnresolvedTaskByAssigneeWidget();
        rightColumn.addComponent(unresolvedTaskByAssigneeWidget);

        unresolvedTaskByAssigneeWidget.setSearchCriteria(baseCriteria);

        UnresolvedTaskByPriorityWidget unresolvedTaskByPriorityWidget = new UnresolvedTaskByPriorityWidget();
        rightColumn.addComponent(unresolvedTaskByPriorityWidget);
        unresolvedTaskByPriorityWidget.setSearchCriteria(baseCriteria);
    }

    @Override
    public void queryTask(TaskSearchCriteria searchCriteria) {
        wrapBody.removeAllComponents();
        TaskGroupOrderComponent taskGroupOrderComponent;
        if (GROUP_DUE_DATE.equals(groupByState)) {
            searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField("deadline", SearchCriteria.DESC)));
            taskGroupOrderComponent = new DueDateOrderComponent();
        } else if (GROUP_START_DATE.equals(groupByState)) {
            searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField("startdate", SearchCriteria.DESC)));
            taskGroupOrderComponent = new StartDateOrderComponent();
        } else if (PLAIN_LIST.equals(groupByState)) {
            searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField("lastupdatedtime", SearchCriteria.DESC)));
            taskGroupOrderComponent = new SimpleListOrderComponent();
        } else {
            throw new MyCollabException("Do not support group view by " + groupByState);
        }
        wrapBody.addComponent(taskGroupOrderComponent);
        ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        List tasks = projectTaskService.findPagableListByCriteria(new SearchRequest<>(searchCriteria));
        taskGroupOrderComponent.insertTasks(tasks);
    }

    private void queryAndDisplayTasks() {
        queryTask(baseCriteria);
    }

    private void displayGanttChartView() {
        EventBusFactory.getInstance().post(new TaskEvent.GotoGanttChart(this, null));
    }

    private void displayKanbanView() {
        EventBusFactory.getInstance().post(new TaskEvent.GotoKanbanView(this, null));
    }

    @Override
    public void enableActionControls(int numOfSelectedItem) {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }

    @Override
    public void disableActionControls() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }

    @Override
    public HasSearchHandlers<TaskSearchCriteria> getSearchHandlers() {
        return taskSearchPanel;
    }

    @Override
    public HasSelectionOptionHandlers getOptionSelectionHandlers() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }

    @Override
    public HasMassItemActionHandler getPopupActionHandlers() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }

    @Override
    public HasSelectableItemHandlers<SimpleTask> getSelectableItemHandlers() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }

    @Override
    public AbstractPagedBeanTable<TaskSearchCriteria, SimpleTask> getPagedBeanTable() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }
}