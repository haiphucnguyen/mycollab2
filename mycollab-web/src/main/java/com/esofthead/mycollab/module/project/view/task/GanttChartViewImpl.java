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

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.components.ProjectMemberLink;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.task.gantt.GanttExt;
import com.esofthead.mycollab.module.project.view.task.gantt.GanttItemWrapper;
import com.esofthead.mycollab.module.project.view.task.gantt.QuickEditTaskWindow;
import com.esofthead.mycollab.module.project.view.task.gantt.StepExt;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.google.common.eventbus.Subscribe;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.Gantt.MoveEvent;
import org.tltv.gantt.Gantt.ResizeEvent;
import org.tltv.gantt.client.shared.Step;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@ViewComponent
public class GanttChartViewImpl extends AbstractPageView implements GanttChartView {
    private static final long serialVersionUID = 1L;

    private boolean projectNavigatorVisibility = false;

    private GanttExt gantt;
    private TaskHierarchyComp taskTable;
    private Button toogleMenuShowBtn;
    private ProjectTaskService taskService;

    private GregorianCalendar minDate;
    private GregorianCalendar maxDate;

    public GanttChartViewImpl() {
        this.setSizeFull();
        this.withMargin(true);

        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withStyleName("hdr-view").withWidth("100%");
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label headerText = new Label("Gantt chart", ContentMode.HTML);
        headerText.setStyleName(UIConstants.HEADER_TEXT);
        CssLayout headerWrapper = new CssLayout();
        headerWrapper.addComponent(headerText);

        toogleMenuShowBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                projectNavigatorVisibility = !projectNavigatorVisibility;
                setProjectNavigatorVisibility(projectNavigatorVisibility);
                if (projectNavigatorVisibility) {
                    toogleMenuShowBtn.setCaption("Hide menu");
                } else {
                    toogleMenuShowBtn.setCaption("Show menu");
                }
            }
        });
        toogleMenuShowBtn.addStyleName(UIConstants.THEME_LINK);

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, new String[]{
                        "task", "dashboard", UrlEncodeDecoder.encode(CurrentProjectVariables.getProjectId())}));
            }
        });
        cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

        header.with(headerWrapper, toogleMenuShowBtn, cancelBtn).withAlign(headerWrapper, Alignment.MIDDLE_LEFT)
                .withAlign(toogleMenuShowBtn, Alignment.MIDDLE_RIGHT)
                .withAlign(cancelBtn, Alignment.MIDDLE_RIGHT).expand(headerWrapper);
        taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);

        HorizontalLayout ganttLayout = constructGanttChart();
        this.with(header, ganttLayout).expand(ganttLayout);
    }

    @Override
    public void detach() {
        setProjectNavigatorVisibility(true);
        super.detach();
    }

    private void setProjectNavigatorVisibility(boolean visibility) {
        ProjectView view = UIUtils.getRoot(this, ProjectView.class);
        if (view != null) {
            view.setNavigatorVisibility(visibility);
        }
    }

    private MHorizontalLayout constructGanttChart() {
        MHorizontalLayout mainLayout = new MHorizontalLayout().withSpacing(false).withWidth("100%");
        mainLayout.addStyleName("gantt_container");

        taskTable = new TaskHierarchyComp();
        taskTable.setWidth("800px");

        gantt = new GanttExt();
        gantt.setHeight("500px");
        gantt.setResizableSteps(true);
        gantt.setMovableSteps(true);
        gantt.setVerticalScrollDelegateTarget(taskTable);

        gantt.addMoveListener(new Gantt.MoveListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttMove(MoveEvent event) {
                updateTasksInfo((StepExt) event.getStep(), event.getStartDate(), event.getEndDate());
            }
        });

        gantt.addResizeListener(new Gantt.ResizeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttResize(ResizeEvent event) {
                updateTasksInfo((StepExt) event.getStep(), event.getStartDate(), event.getEndDate());
            }
        });

        mainLayout.with(taskTable, gantt).expand(gantt);
        return mainLayout;
    }

    private void updateTasksInfo(StepExt step, long startDate, long endDate) {
        GanttItemWrapper ganttItemWrapper = step.getGanttItemWrapper();
        SimpleTask task = ganttItemWrapper.getTask();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(startDate);
        ganttItemWrapper.setStartDate(calendar.getTime());

        calendar.setTimeInMillis(endDate);
        ganttItemWrapper.setEndDate(calendar.getTime());
        taskService.updateSelectiveWithSession(task, AppContext.getUsername());
        EventBusFactory.getInstance().post(new TaskEvent.GanttTaskUpdate(GanttChartViewImpl.this, ganttItemWrapper));
        ganttItemWrapper.markAsDirty();
    }

    public void displayGanttChart() {
        toogleMenuShowBtn.setCaption("Show menu");
        setProjectNavigatorVisibility(false);
        updateStepList();
    }

    @SuppressWarnings("unchecked")
    private void updateStepList() {
        gantt.removeSteps();
        minDate = new GregorianCalendar();
        maxDate = new GregorianCalendar();
        taskTable.removeAllItems();
        final TaskSearchCriteria criteria = new TaskSearchCriteria();
        criteria.setProjectid(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        criteria.setHasParentTask(new BooleanSearchField());
        criteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField("createdTime", SearchCriteria.ASC)));
        int totalTasks = taskService.getTotalCount(criteria);
        final int pages = totalTasks / 20;
        UI.getCurrent().access(new Runnable() {
            @Override
            public void run() {
                for (int page = 0; page < pages + 1; page++) {
                    List<SimpleTask> tasks = taskService.findPagableListByCriteria(new SearchRequest<>(criteria, page + 1, 20));

                    if (!tasks.isEmpty()) {
                        for (final SimpleTask task : tasks) {
                            final GanttItemWrapper itemWrapper = new GanttItemWrapper(task);
                            taskTable.addTask(itemWrapper);
                            Step step = itemWrapper.getStep();
                            gantt.addStep(step);
                            gantt.addClickListener(new Gantt.ClickListener() {
                                @Override
                                public void onGanttClick(Gantt.ClickEvent clickEvent) {
//                                    UI.getCurrent().addWindow(new QuickEditTaskWindow(gantt, itemWrapper));
                                }
                            });

                            if (task.isCompleted()) {
//                    step.setBackgroundColor("53C540");
//                    step.setStyleName("completed");
                            } else if (task.isPending()) {
//                    step.setBackgroundColor("e2f852");
                            } else if (task.isOverdue()) {
//                    step.setBackgroundColor("FC4350");
                            }

                            if (minDate.getTimeInMillis() > itemWrapper.getStartDate().getTime()) {
                                minDate.setTimeInMillis(itemWrapper.getStartDate().getTime());
                            }

                            if (maxDate.getTimeInMillis() < itemWrapper.getEndDate().getTime()) {
                                maxDate.setTimeInMillis(itemWrapper.getEndDate().getTime());
                            }
                        }
                    }
                    minDate.add(Calendar.DATE, -14);
                    gantt.setStartDate(minDate.getTime());

                    maxDate.add(Calendar.DATE, 14);
                    gantt.setEndDate(maxDate.getTime());
                    UI.getCurrent().push();
                }
            }
        });
    }

    void insertSteps(final GanttItemWrapper parent, final List<GanttItemWrapper> childs) {
        final int stepIndex = gantt.getStepIndex(parent.getStep());
        if (stepIndex != -1) {
            for (GanttItemWrapper child : childs) {
                taskTable.addItem(child);
                taskTable.setParent(child, parent);
                taskTable.setChildrenAllowed(child, child.hasSubTasks());

            }
        }
    }

    class TaskHierarchyComp extends TreeTable {
        private BeanItemContainer<GanttItemWrapper> beanContainer;

        private ApplicationEventListener<TaskEvent.GanttTaskUpdate> taskUpdateHandler = new
                ApplicationEventListener<TaskEvent.GanttTaskUpdate>() {
                    @Override
                    @Subscribe
                    public void handle(TaskEvent.GanttTaskUpdate event) {
                        GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) event.getData();
                        updateTaskTree(ganttItemWrapper);
                    }
                };

        TaskHierarchyComp() {
            super();
            beanContainer = new BeanItemContainer<>(GanttItemWrapper.class);
            this.setContainerDataSource(beanContainer);
            this.setVisibleColumns("name", "startDate", "endDate", "assignUser");
            this.setColumnHeader("name", "Task");
            this.setColumnExpandRatio("name", 1.0f);
            this.setColumnHeader("startDate", "Start");
            this.setColumnWidth("startDate", 75);
            this.setColumnHeader("endDate", "End");
            this.setColumnWidth("endDate", 75);
            this.setColumnHeader("assignUser", "Assignee");
            this.setColumnWidth("assignUser", UIConstants.TABLE_X_LABEL_WIDTH);

            this.addGeneratedColumn("name", new ColumnGenerator() {
                @Override
                public Object generateCell(Table table, Object itemId, Object columnId) {
                    GanttItemWrapper item = (GanttItemWrapper) itemId;
                    Label taskLbl = new Label(item.getName());
                    taskLbl.setDescription(ProjectTooltipGenerator.generateToolTipTask(AppContext.getUserLocale(),
                            item.getTask(), AppContext.getSiteUrl(), AppContext.getTimezone()));
                    return taskLbl;
                }
            });

            this.addGeneratedColumn("startDate", new ColumnGenerator() {
                @Override
                public Object generateCell(Table table, Object itemId, Object columnId) {
                    GanttItemWrapper item = (GanttItemWrapper) itemId;
                    return new Label(AppContext.formatDate(item.getStartDate()));
                }
            });

            this.addGeneratedColumn("endDate", new ColumnGenerator() {
                @Override
                public Object generateCell(Table table, Object itemId, Object columnId) {
                    GanttItemWrapper item = (GanttItemWrapper) itemId;
                    return new Label(AppContext.formatDate(item.getEndDate()));
                }
            });

            this.addGeneratedColumn("assignUser", new ColumnGenerator() {
                @Override
                public Object generateCell(Table table, Object itemId, Object columnId) {
                    GanttItemWrapper item = (GanttItemWrapper) itemId;
                    SimpleTask task = item.getTask();
                    return new ProjectMemberLink(task.getAssignuser(), task.getAssignUserAvatarId(), task.getAssignUserFullName());
                }
            });

            this.addExpandListener(new Tree.ExpandListener() {
                @Override
                public void nodeExpand(Tree.ExpandEvent expandEvent) {
                    GanttItemWrapper item = (GanttItemWrapper) expandEvent.getItemId();
                    List<GanttItemWrapper> subTasks = item.subTasks();
                    insertSteps(item, subTasks);
                }
            });

            this.addCollapseListener(new Tree.CollapseListener() {
                @Override
                public void nodeCollapse(Tree.CollapseEvent collapseEvent) {

                }
            });

            this.setCellStyleGenerator(new CellStyleGenerator() {
                @Override
                public String getStyle(Table table, Object itemId, Object propertyId) {
                    if (propertyId == null) {
                        GanttItemWrapper item = (GanttItemWrapper) itemId;
                        return (item.hasSubTasks()) ? "hasChildTasks" : null;
                    } else {
                        return null;
                    }
                }
            });
        }

        @Override
        public void attach() {
            EventBusFactory.getInstance().register(taskUpdateHandler);
            super.attach();
        }

        @Override
        public void detach() {
            EventBusFactory.getInstance().unregister(taskUpdateHandler);
            super.detach();
        }

        private void updateTaskTree(GanttItemWrapper ganttItemWrapper) {

        }

        void addTask(GanttItemWrapper itemWrapper) {
            beanContainer.addBean(itemWrapper);
            this.setChildrenAllowed(itemWrapper, itemWrapper.hasSubTasks());
        }
    }
}
