/**
 * This file is part of mycollab-web.
 * <p/>
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.task.gantt.GanttExt;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.DateFieldExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.Gantt.MoveEvent;
import org.tltv.gantt.Gantt.ResizeEvent;
import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.Step;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@ViewComponent
public class GanttChartViewImpl extends AbstractPageView implements GanttChartView {
    private static final long serialVersionUID = 1L;

    private GanttExt gantt;
    private NativeSelect reso;
    private TaskHierarchyComp taskTable;

    private ProjectTaskListService taskListService;

    public GanttChartViewImpl() {
        this.setStyleName("gantt-view");
        this.setSizeFull();
        this.withMargin(new MarginInfo(false, true, true, true));

        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withStyleName("hdr-view").withWidth("100%");
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label headerText = new Label(String.format("%s %s (Gantt chart)", FontAwesome.BAR_CHART_O.getHtml(),
                CurrentProjectVariables.getProject().getName()), ContentMode.HTML);
        headerText.setStyleName(UIConstants.HEADER_TEXT);
        CssLayout headerWrapper = new CssLayout();
        headerWrapper.addComponent(headerText);

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, new String[]{
                        "task", "dashboard", UrlEncodeDecoder.encode(CurrentProjectVariables.getProjectId())}));
            }
        });
        cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

        header.with(headerWrapper, cancelBtn).withAlign(headerWrapper, Alignment.MIDDLE_LEFT).withAlign(cancelBtn,
                Alignment.MIDDLE_RIGHT).expand(headerWrapper);

        taskListService = ApplicationContextUtil.getSpringBean(ProjectTaskListService.class);

        HorizontalLayout ganttLayout = constructGanttChart();

        MVerticalLayout wrapContent = new MVerticalLayout().withSpacing(false).withMargin(false)
                .with(createControls(), ganttLayout).expand(ganttLayout);
        wrapContent.addStyleName("gantt-view");
        this.with(header, wrapContent).expand(wrapContent);
    }

    private MHorizontalLayout constructGanttChart() {
        MHorizontalLayout mainLayout = new MHorizontalLayout().withSpacing(false).withWidth("100%");

        taskTable = new TaskHierarchyComp();
        taskTable.setWidth("300px");

        gantt = new GanttExt();
        gantt.setWidth(100, Unit.PERCENTAGE);
        gantt.setResizableSteps(true);
        gantt.setMovableSteps(true);
        gantt.setVerticalScrollDelegateTarget(taskTable);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 1);
        cal.add(Calendar.DATE, -14);

        gantt.setStartDate(cal.getTime());
        cal.add(Calendar.DATE, 28);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        gantt.setEndDate(cal.getTime());

        gantt.addMoveListener(new Gantt.MoveListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttMove(MoveEvent event) {
                updateTasksInfo(event.getStep(), event.getStartDate(), event.getEndDate());
            }
        });

        gantt.addResizeListener(new Gantt.ResizeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttResize(ResizeEvent event) {
                updateTasksInfo(event.getStep(), event.getStartDate(), event.getEndDate());
            }
        });

        mainLayout.with(taskTable, gantt).expand(gantt);
        return mainLayout;
    }

    private void updateTasksInfo(AbstractStep step, long startDate, long endDate) {
//        SimpleTask task = stepMap.get(step);
//        GregorianCalendar calendar = new GregorianCalendar();
//        calendar.setTimeInMillis(startDate);
//        task.setStartdate(calendar.getTime());
//
//        calendar.setTimeInMillis(endDate);
//        task.setEnddate(calendar.getTime());
//
//        taskListService.updateWithSession(task, AppContext.getUsername());
//        taskTable.setCurrentDataList(stepMap.values());
    }

    public void displayGanttChart() {
        updateStepList();
    }

    @SuppressWarnings("unchecked")
    private void updateStepList() {
        gantt.removeSteps();
        TaskListSearchCriteria criteria = new TaskListSearchCriteria();
        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        criteria.setStatus(new StringSearchField(OptionI18nEnum.StatusI18nEnum.Open.name()));
        List<SimpleTaskList> taskList = taskListService.findPagableListByCriteria(new SearchRequest<>(criteria, 0, Integer.MAX_VALUE));

        if (!taskList.isEmpty()) {
            for (SimpleTaskList task : taskList) {
                GanttItemWrapper itemWrapper = new TaskListGanttItemWrapper(task);
                taskTable.addTaskList(itemWrapper);
                gantt.addStep(itemWrapper.getStep());

					/* Add style for row block */
//                if (task.isCompleted()) {
//                    step.setBackgroundColor("53C540");
//                    step.setStyleName("completed");
//                } else if (task.isPending()) {
//                    step.setBackgroundColor("e2f852");
//                } else if (task.isOverdue()) {
//                    step.setBackgroundColor("FC4350");
//                }
//                stepMap.put(taskList, step);
            }
        }


    }

    private Panel createControls() {
        Panel panel = new Panel();
        panel.setWidth(100, Unit.PERCENTAGE);

        MHorizontalLayout controls = new MHorizontalLayout().withMargin(true);
        panel.setContent(controls);

        DateFieldExt start = new DateFieldExt(AppContext.getMessage(TaskI18nEnum.FORM_START_DATE));
        start.setValue(gantt.getStartDate());
        start.setResolution(Resolution.DAY);
        start.setImmediate(true);
        start.addValueChangeListener(startDateValueChangeListener);

        DateField end = new DateFieldExt(AppContext.getMessage(TaskI18nEnum.FORM_END_DATE));
        end.setValue(gantt.getEndDate());
        end.setResolution(Resolution.DAY);
        end.setImmediate(true);
        end.addValueChangeListener(endDateValueChangeListener);

        reso = new NativeSelect("Resolution");
        reso.setNullSelectionAllowed(false);
        reso.addItem(org.tltv.gantt.client.shared.Resolution.Hour);
        reso.addItem(org.tltv.gantt.client.shared.Resolution.Day);
        reso.addItem(org.tltv.gantt.client.shared.Resolution.Week);
        reso.setValue(gantt.getResolution());
        reso.setImmediate(true);
        reso.addValueChangeListener(resolutionValueChangeListener);

        controls.with(start, end, reso);
        panel.setStyleName(UIConstants.THEME_NO_BORDER);

        return panel;
    }

    private Property.ValueChangeListener startDateValueChangeListener = new Property.ValueChangeListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            gantt.setStartDate((Date) event.getProperty().getValue());
            updateStepList();
        }
    };

    private Property.ValueChangeListener endDateValueChangeListener = new Property.ValueChangeListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            gantt.setEndDate((Date) event.getProperty().getValue());
            updateStepList();
        }
    };

    private Property.ValueChangeListener resolutionValueChangeListener = new Property.ValueChangeListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            org.tltv.gantt.client.shared.Resolution res = (org.tltv.gantt.client.shared.Resolution) event.getProperty().getValue();
            if (validateResolutionChange(res)) {
                gantt.setResolution(res);
            }
        }
    };

    private boolean validateResolutionChange(final org.tltv.gantt.client.shared.Resolution res) {
        long max = 4 * 7 * 24 * 60 * 60000L;
        if (res == org.tltv.gantt.client.shared.Resolution.Hour
                && (gantt.getEndDate().getTime() - gantt.getStartDate().getTime()) > max) {

            // revert to previous resolution
            setResolution(gantt.getResolution());

            // make user to confirm hour resolution, if the timeline range is
            // more than one week long.

            ConfirmDialogExt.show(UI.getCurrent(),
                    AppContext.getMessage(GenericI18Enum.WINDOW_WARNING_TITLE),
                    "Timeline range is a quite long for hour resolution. Rendering may be slow. Continue anyway?",
                    AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                    AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                    new ConfirmDialog.Listener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void onClose(final ConfirmDialog dialog) {
                            if (dialog.isConfirmed()) {
                                setResolution(res);
                                gantt.setResolution(res);
                            }
                        }
                    });
            return false;
        }
        return true;
    }

    private void setResolution(org.tltv.gantt.client.shared.Resolution resolution) {
        reso.removeValueChangeListener(resolutionValueChangeListener);
        try {
            reso.setValue(resolution);
        } finally {
            reso.addValueChangeListener(resolutionValueChangeListener);
        }
    }

    void insertSteps(GanttItemWrapper parent, List<GanttItemWrapper> childs) {
        int stepIndex = gantt.getStepIndex(parent.getStep());
        if (stepIndex != -1) {
            int order = 1;
            for (GanttItemWrapper child : childs) {
                taskTable.addItem(child);
                taskTable.setParent(child, parent);
                gantt.addStep(stepIndex + order, child.getStep());
                order++;
            }
        }
    }

    class TaskHierarchyComp extends TreeTable {
        TaskHierarchyComp() {
            super();
            this.addContainerProperty("name", String.class, "");
            this.setColumnHeader("name", "Name");
            this.addGeneratedColumn("name", new ColumnGenerator() {
                @Override
                public Object generateCell(Table table, Object itemId, Object columnId) {
                    GanttItemWrapper item = (GanttItemWrapper) itemId;
                    return new Label(item.getName());
                }
            });

            this.addExpandListener(new Tree.ExpandListener() {
                @Override
                public void nodeExpand(Tree.ExpandEvent expandEvent) {
                    GanttItemWrapper item = (GanttItemWrapper) expandEvent.getItemId();
                    List<GanttItemWrapper> subTasks = item.subTasks();
                    GanttChartViewImpl.this.insertSteps(item, subTasks);
                }
            });

            this.addCollapseListener(new Tree.CollapseListener() {
                @Override
                public void nodeCollapse(Tree.CollapseEvent collapseEvent) {
                    GanttItemWrapper item = (GanttItemWrapper) collapseEvent.getItemId();
                    List<GanttItemWrapper> subTasks = item.subTasks();
                    if (subTasks.size() > 0) {
                        for (GanttItemWrapper subTask : subTasks) {

                        }
                    }
                }
            });
        }

        void addTaskList(GanttItemWrapper itemWrapper) {
            this.addItem(itemWrapper);
        }
    }

    abstract static class GanttItemWrapper {
        GanttItemWrapper parent;
        Step ownStep;
        List<GanttItemWrapper> subItems;

        abstract String getName();

        abstract List<GanttItemWrapper> subTasks();

        abstract Date getStartDate();

        abstract Date getEndDate();

        abstract String buildCaption();

        Step getStep() {
            return ownStep;
        }

        void setStep(Step step) {
            ownStep = step;
        }

        Step generateStep() {
            Date startDate = this.getStartDate();
            Date endDate = this.getEndDate();
            Step step = new Step();
            step.setCaption(buildCaption());
            step.setCaptionMode(Step.CaptionMode.HTML);
            step.setStartDate(startDate);
            step.setEndDate(endDate);
            return step;
        }

        public GanttItemWrapper getParent() {
            return parent;
        }

        public void setParent(GanttItemWrapper parent) {
            this.parent = parent;
        }
    }

    static class TaskGanttItemWrapper extends GanttItemWrapper {
        private ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        private SimpleTask task;
        private Date startDate, endDate;

        TaskGanttItemWrapper(SimpleTask task) {
            this.task = task;
            calculateDates();
            this.ownStep = generateStep();
        }

        @Override
        public String getName() {
            return task.getTaskname();
        }

        @Override
        public List<GanttItemWrapper> subTasks() {
            List<SimpleTask> subTasks = projectTaskService.findSubTasks(task.getId(), AppContext.getAccountId());
            if (subItems == null) {
                subItems = new ArrayList<>();
                for (SimpleTask subTask : subTasks) {
                    TaskGanttItemWrapper subItem = new TaskGanttItemWrapper(subTask);
                    subItem.setParent(this);
                    subItems.add(subItem);
                }
            }

            return subItems;
        }

        private void calculateDates() {
            startDate = task.getStartdate();
            endDate = task.getEnddate();

            if (endDate == null) {
                endDate = task.getDeadline();
            }

            if (startDate == null) {
                if (endDate == null) {
                    startDate = DateTimeUtils.getCurrentDateWithoutMS();
                    endDate = DateTimeUtils.subtractOrAddDayDuration(startDate, 1);
                } else {
                    endDate = DateTimeUtils.trimHMSOfDate(endDate);
                    startDate = DateTimeUtils.subtractOrAddDayDuration(endDate, -1);
                }
            } else {
                startDate = DateTimeUtils.trimHMSOfDate(startDate);
                if (endDate == null) {
                    endDate = DateTimeUtils.subtractOrAddDayDuration(startDate, 1);
                } else {
                    endDate = DateTimeUtils.trimHMSOfDate(endDate);
                    endDate = DateTimeUtils.subtractOrAddDayDuration(endDate, 1);
                }
            }
        }

        @Override
        Date getStartDate() {
            return startDate;
        }

        @Override
        Date getEndDate() {
            return endDate;
        }

        @Override
        String buildCaption() {
            return task.getTaskname();
        }
    }

    static class TaskListGanttItemWrapper extends GanttItemWrapper {
        private ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        private SimpleTaskList taskList;

        TaskListGanttItemWrapper(SimpleTaskList taskList) {
            this.taskList = taskList;
            this.ownStep = generateStep();
        }

        @Override
        public String getName() {
            return taskList.getName();
        }

        @Override
        public List<GanttItemWrapper> subTasks() {
            List<SimpleTask> subTasks = projectTaskService.findSubTasksOfGroup(taskList.getId(), AppContext.getAccountId());
            if (subItems == null) {
                subItems = new ArrayList<>();
                for (SimpleTask subTask : subTasks) {
                    TaskGanttItemWrapper subItem = new TaskGanttItemWrapper(subTask);
                    subItem.setParent(this);
                    subItems.add(subItem);
                }
            }

            return subItems;
        }

        @Override
        Date getStartDate() {
            return taskList.getStartDate();
        }

        @Override
        Date getEndDate() {
            return taskList.getEndDate();
        }

        @Override
        String buildCaption() {
            return taskList.getName();
        }
    }
}
