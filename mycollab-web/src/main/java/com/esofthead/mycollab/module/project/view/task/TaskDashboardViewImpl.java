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

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.TaskGroupI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandler;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.vaadin.floatingcomponent.FloatingComponent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class TaskDashboardViewImpl extends AbstractLazyPageView implements TaskDashboardView {
    private static final long serialVersionUID = 1L;

    private MilestoneGroupComponent taskListsWidget;

    private VerticalLayout rightColumn;

    private TextField nameField;

    private MHorizontalLayout header;
    private MHorizontalLayout mainLayout;
    private ToggleButtonGroup viewButtons;

    private void constructUI() {
        this.removeAllComponents();
        this.withMargin(new MarginInfo(false, true, true, true));

        header = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withStyleName("hdr-view").withWidth("100%");
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        Label taskGroupSelection = new Label(AppContext.getMessage(TaskGroupI18nEnum.FILTER_ACTIVE_TASK_GROUPS_TITLE));
        taskGroupSelection.setEnabled(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TASKS));
        taskGroupSelection.addStyleName("hdr-text");
        taskGroupSelection.setIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK));
        header.with(taskGroupSelection).withAlign(taskGroupSelection, Alignment.MIDDLE_LEFT).expand(taskGroupSelection);

        Button newTaskListBtn = new Button(AppContext.getMessage(TaskI18nEnum.BUTTON_NEW_TASK),
                new Button.ClickListener() {
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
        header.addComponent(newTaskListBtn);
        header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

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

        viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.addButton(chartDisplayBtn);
        viewButtons.setDefaultButton(advanceDisplayBtn);

        mainLayout = new MHorizontalLayout().withFullHeight().withFullWidth();
        this.taskListsWidget = new MilestoneGroupComponent();

        MVerticalLayout leftColumn = new MVerticalLayout().withMargin(new MarginInfo(false, true, false, false)).with(taskListsWidget);

        this.rightColumn = new MVerticalLayout().withWidth("300px").withMargin(new MarginInfo(true, false, false, false));

        mainLayout.with(leftColumn, rightColumn).expand(leftColumn);

        FloatingComponent floatSidebar = FloatingComponent.floatThis(this.rightColumn);
        floatSidebar.setContainerId("main-body");

        displayAdvancedView();
    }

    @Override
    protected void displayView() {
        constructUI();
        displayTaskStatistic();
    }

    private void displayTaskStatistic() {
        rightColumn.removeAllComponents();

        UnresolvedTaskByAssigneeWidget unresolvedTaskByAssigneeWidget = new UnresolvedTaskByAssigneeWidget();
        rightColumn.addComponent(unresolvedTaskByAssigneeWidget);

        TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
        searchCriteria.setProjectid(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        searchCriteria.setStatuses(new SetSearchField<>(StatusI18nEnum.Open.name()));

        unresolvedTaskByAssigneeWidget.setSearchCriteria(searchCriteria);

        UnresolvedTaskByPriorityWidget unresolvedTaskByPriorityWidget = new UnresolvedTaskByPriorityWidget();
        rightColumn.addComponent(unresolvedTaskByPriorityWidget);
        unresolvedTaskByPriorityWidget.setSearchCriteria(searchCriteria);
    }

    private void displayAdvancedView() {
        this.removeAllComponents();
        header.with(viewButtons).withAlign(viewButtons, Alignment.MIDDLE_RIGHT);
        this.with(header, mainLayout).withAlign(header, Alignment.TOP_RIGHT);
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
    public HasSearchHandlers<TaskListSearchCriteria> getSearchHandlers() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
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
    public HasSelectableItemHandlers<SimpleTaskList> getSelectableItemHandlers() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }

    @Override
    public AbstractPagedBeanTable<TaskListSearchCriteria, SimpleTaskList> getPagedBeanTable() {
        throw new UnsupportedOperationException("This view doesn't support this operation");
    }
}