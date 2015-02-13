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

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.i18n.TaskGroupI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.parameters.TaskFilterParameter;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.DateFieldExt;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.Gantt.MoveEvent;
import org.tltv.gantt.Gantt.ResizeEvent;
import org.tltv.gantt.client.shared.Step;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.*;
import java.util.Calendar;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class GanttChartViewImpl extends AbstractPageView implements
        GanttChartView {
    private static final long serialVersionUID = 1L;

    private List<SimpleTask> taskList;

    private Gantt gantt;
    private LinkedHashMap<Step, SimpleTask> stepMap;
    private NativeSelect reso;

    private TaskTableDisplay taskTable;

    private final ProjectTaskService taskService;

    public GanttChartViewImpl() {
        this.withMargin(new MarginInfo(false, true, true, true));

        MHorizontalLayout header = new MHorizontalLayout()
                .withMargin(new MarginInfo(true, false, true, false))
                .withStyleName("hdr-view").withWidth("100%");
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label headerText = new Label(FontAwesome.BAR_CHART_O.getHtml() + " Gantt chart", ContentMode.HTML);
        headerText.setStyleName(UIConstants.HEADER_TEXT);

        Button advanceDisplayBtn = new Button(null, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                EventBusFactory.getInstance().post(
                        new TaskListEvent.GotoTaskListScreen(this, null));
            }
        });
        advanceDisplayBtn.setIcon(FontAwesome.SITEMAP);
        advanceDisplayBtn.setDescription(AppContext
                .getMessage(TaskGroupI18nEnum.ADVANCED_VIEW_TOOLTIP));

        Button simpleDisplayBtn = new Button(null, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
                searchCriteria.setProjectid(new NumberSearchField(
                        CurrentProjectVariables.getProjectId()));
                searchCriteria.setStatuses(new SetSearchField<>(new String[]{OptionI18nEnum.StatusI18nEnum.Open
                        .name()}));
                TaskFilterParameter taskFilter = new TaskFilterParameter(
                        searchCriteria, "Task Search");
                taskFilter.setAdvanceSearch(true);
                EventBusFactory.getInstance().post(
                        new TaskEvent.Search(this, taskFilter));
            }
        });
        simpleDisplayBtn.setIcon(FontAwesome.LIST_UL);
        simpleDisplayBtn.setDescription(AppContext
                .getMessage(TaskGroupI18nEnum.LIST_VIEW_TOOLTIP));

        Button chartDisplayBtn = new Button();
        chartDisplayBtn.setIcon(FontAwesome.BAR_CHART_O);

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(simpleDisplayBtn);
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(chartDisplayBtn);
        viewButtons.setDefaultButton(chartDisplayBtn);

        header.with(headerText, viewButtons)
                .withAlign(headerText, Alignment.MIDDLE_LEFT).expand(headerText);

        taskService = ApplicationContextUtil
                .getSpringBean(ProjectTaskService.class);

        HorizontalLayout ganttLayout = constructGanttChart();

        MVerticalLayout wrapContent = new MVerticalLayout().withSpacing(false).withMargin(false).withStyleName
                ("gantt-view").with(createControls(), ganttLayout);
        this.with(header, wrapContent);
    }

    private MHorizontalLayout constructGanttChart() {
        MHorizontalLayout mainLayout = new MHorizontalLayout().withSpacing(false).withWidth("100%").withStyleName("gantt-wrap");

        stepMap = new LinkedHashMap<>();

        taskTable = new TaskTableDisplay(Arrays.asList(
                TaskTableFieldDef.taskname, TaskTableFieldDef.startdate,
                TaskTableFieldDef.duedate, TaskTableFieldDef.assignee));
        taskTable.setWidth("100%");
        taskTable.setHeightUndefined();
        taskTable.addStyleName("gantt-table");

        gantt = new Gantt();
        gantt.setWidth(100, Unit.PERCENTAGE);
        gantt.setResizableSteps(true);
        gantt.setMovableSteps(true);
        gantt.setVerticalScrollDelegateTarget(taskTable.getTable());

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -14);

        gantt.setStartDate(cal.getTime());
        cal.add(Calendar.DATE, 28);
        gantt.setEndDate(cal.getTime());

        gantt.addMoveListener(new Gantt.MoveListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttMove(MoveEvent event) {
                SimpleTask task = stepMap.get(event.getStep());
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());

                GregorianCalendar gc = new GregorianCalendar();

				/* check endate after deadline */
                gc.setTimeInMillis(event.getEndDate());
                if (task.getEnddate() != null
                        && task.getEnddate().after(gc.getTime())) {
                    task.setEnddate(null);
                }
                task.setDeadline(gc.getTime());

                gc.setTimeInMillis(event.getStartDate());
                task.setStartdate(gc.getTime());

                taskService.updateWithSession(task, AppContext.getUsername());
                taskTable.setCurrentDataList(stepMap.values());
            }
        });

        gantt.addResizeListener(new Gantt.ResizeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttResize(ResizeEvent event) {
                SimpleTask task = stepMap.get(event.getStep());
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());

                Date gc = DateTimeUtils.getCurrentDateWithoutMS();

				/* check endate after deadline */
                if (task.getEnddate() != null
                        && task.getEnddate().after(gc)) {
                    task.setEnddate(null);
                }
                task.setDeadline(gc);
                task.setStartdate(gc);

                taskService.updateWithSession(task, AppContext.getUsername());
                taskTable.setCurrentDataList(stepMap.values());
            }
        });

        mainLayout.with(taskTable, gantt);
        return mainLayout;
    }

    public void displayGanttChart() {
        updateStepList();
    }

    @SuppressWarnings("unchecked")
    private void updateStepList() {
        TaskSearchCriteria criteria = new TaskSearchCriteria();
        criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
                .getProjectId()));
        taskList = taskService.findPagableListByCriteria(new SearchRequest<>(criteria, 0, Integer.MAX_VALUE));

        gantt.removeSteps();
        stepMap = new LinkedHashMap<>();

		/* Add steps */
        if (!taskList.isEmpty()) {
            for (SimpleTask task : taskList) {
                Date startDate = null;
                Date endDate = null;

				/* Check for date */
                if (task.getActualstartdate() != null) {
                    startDate = task.getActualstartdate();
                } else if (task.getStartdate() != null) {
                    startDate = task.getStartdate();
                }

                if (task.getDeadline() != null) {
                    endDate = task.getDeadline();
                } else if (task.getActualenddate() != null) {
                    endDate = task.getActualenddate();
                }

				/* Add row block if both stardate and endate avalable */
                if ((startDate != null && endDate != null) && (gantt.getStartDate().before(startDate) || gantt.getEndDate().after(startDate))) {
                    Step step = new Step();
                    step.setCaption(tooltipGenerate(task));
                    step.setCaptionMode(Step.CaptionMode.HTML);
                    step.setStartDate(startDate.getTime());
                    step.setEndDate(endDate.getTime());

					/* Add style for row block */
                    if (task.isCompleted()) {
                        step.setBackgroundColor("53C540");
                        step.setStyleName("completed");
                    } else if (task.isPending()) {
                        step.setBackgroundColor("e2f852");
                    } else if (task.isOverdue()) {
                        step.setBackgroundColor("FC4350");
                    }
                    stepMap.put(step, task);
                }

            }

            taskTable.setCurrentDataList(stepMap.values());
        }

        for (Step key : stepMap.keySet()) {
            gantt.addStep(key);
        }

    }

    private String tooltipGenerate(SimpleTask task) {
        String uid = UUID.randomUUID().toString();
        DivLessFormatter div = new DivLessFormatter();
        A itemLink = new A();
        itemLink.setId("tag" + uid);
        itemLink.setHref(ProjectLinkBuilder.generateProjectItemLink(
                task.getProjectShortname(),
                task.getProjectid(), ProjectTypeConstants.TASK,
                task.getTaskkey() + ""));

        String arg17 = "'" + uid + "'";
        String arg18 = "'" + ProjectTypeConstants.TASK + "'";
        String arg19 = "'" + task.getId() + "'";
        String arg20 = "'" + AppContext.getSiteUrl() + "tooltip/'";
        String arg21 = "'" + AppContext.getAccountId() + "'";
        String arg22 = "'" + AppContext.getSiteUrl() + "'";
        String arg23 = AppContext.getSession().getTimezone();
        String arg24 = "'" + AppContext.getUserLocale().toString() + "'";

        String mouseOverFunc = String.format(
                "return overIt(%s,%s,%s,%s,%s,%s,%s,%s);", arg17, arg18, arg19,
                arg20, arg21, arg22, arg23, arg24);
        itemLink.setAttribute("onmouseover", mouseOverFunc);
        itemLink.appendText(task.getTaskname());

        div.appendChild(itemLink, DivLessFormatter.EMPTY_SPACE(),
                TooltipHelper.buildDivTooltipEnable(uid));
        return div.write();
    }

    private Panel createControls() {
        Panel panel = new Panel();
        panel.setWidth(100, Unit.PERCENTAGE);

        MHorizontalLayout controls = new MHorizontalLayout().withMargin(true);
        panel.setContent(controls);

        DateFieldExt start = new DateFieldExt(
                AppContext.getMessage(TaskI18nEnum.FORM_START_DATE));
        start.setValue(gantt.getStartDate());
        start.setResolution(Resolution.DAY);
        start.setImmediate(true);
        start.addValueChangeListener(startDateValueChangeListener);

        DateField end = new DateFieldExt(
                AppContext.getMessage(TaskI18nEnum.FORM_END_DATE));
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
            org.tltv.gantt.client.shared.Resolution res = (org.tltv.gantt.client.shared.Resolution) event
                    .getProperty().getValue();
            if (validateResolutionChange(res)) {
                gantt.setResolution(res);
            }
        }
    };

    private boolean validateResolutionChange(
            final org.tltv.gantt.client.shared.Resolution res) {
        long max = 4 * 7 * 24 * 60 * 60000L;
        if (res == org.tltv.gantt.client.shared.Resolution.Hour
                && (gantt.getEndDate().getTime() - gantt.getStartDate()
                .getTime()) > max) {

            // revert to previous resolution
            setResolution(gantt.getResolution());

            // make user to confirm hour resolution, if the timeline range is
            // more than one week long.

            ConfirmDialogExt
                    .show(UI.getCurrent(),
                            AppContext
                                    .getMessage(GenericI18Enum.WINDOW_WARNING_TITLE),
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

    private void setResolution(
            org.tltv.gantt.client.shared.Resolution resolution) {
        reso.removeValueChangeListener(resolutionValueChangeListener);
        try {
            reso.setValue(resolution);
        } finally {
            reso.addValueChangeListener(resolutionValueChangeListener);
        }
    }
}
