package com.esofthead.mycollab.module.project.view.task;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ru.bazon.vaadin.ganttdiagram.GanttColumnFormatter;
import ru.bazon.vaadin.ganttdiagram.GanttDiagram;
import ru.bazon.vaadin.ganttdiagram.GanttDiagramCellStyleGeneratorAdapter;
import ru.bazon.vaadin.ganttdiagram.GanttGeneratedColumn;
import ru.bazon.vaadin.ganttdiagram.model.GANTTCOLUMN;
import ru.bazon.vaadin.ganttdiagram.model.GANTTDIAGRAMSCALE;
import ru.bazon.vaadin.ganttdiagram.model.GanttDiagramModel;
import ru.bazon.vaadin.ganttdiagram.model.GanttTask;
import ru.bazon.vaadin.ganttdiagram.model.GanttTaskGeneratedColumnDescription;
import ru.bazon.vaadin.ganttdiagram.treetable.GanttTreeTable;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class GanttChartDisplayWidget extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private static DateTimeFormatter dateFormat = DateTimeFormat
			.forPattern("MM-dd-yy");

	public void setSearchCriteria(TaskListSearchCriteria searchCriteria) {
		ProjectTaskListService taskService = AppContext
				.getSpringBean(ProjectTaskListService.class);
		List<SimpleTaskList> tasks = taskService
				.findPagableListByCriteria(new SearchRequest<TaskListSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

		GanttDiagramModel gdata = new GanttDiagramModel();
		gdata.setGanttColumnVisible(GANTTCOLUMN.STARTTIME, "Start Date", null,
				null);
		gdata.setGanttColumnVisible(GANTTCOLUMN.ENDTIME, "End Date", null, null);
		gdata.setGanttColumnVisible(GANTTCOLUMN.COMPLETE, "%Complete", null,
				null);
		// gdata.addParametersDescription(new GanttTaskParameterDescription(
		// "name", String.class, "", "Name"));

		gdata.addGanttGeneratedColumn(new GanttTaskGeneratedColumnDescription(
				"name", new GanttGeneratedColumn() {
					private static final long serialVersionUID = 1L;

					@Override
					public Object generateCell(GanttTreeTable treeTable,
							final GanttTask task, String columnId) {
						Button btn = new Button((String) task
								.getParameter("name"),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										Object myTask = task
												.getParameter("task");
										if (myTask instanceof TaskList) {
											EventBus.getInstance()
													.fireEvent(
															new TaskListEvent.GotoRead(
																	GanttChartDisplayWidget.this,
																	((TaskList) myTask)
																			.getId()));
										} else if (myTask instanceof Task) {
											EventBus.getInstance()
													.fireEvent(
															new TaskEvent.GotoRead(
																	GanttChartDisplayWidget.this,
																	((Task) myTask)
																			.getId()));
										}
									}
								});
						btn.setStyleName("link");
						btn.setWidth("200px");
						return btn;
					}
				}, "Name"));

		gdata.setColumnFormatter(GANTTCOLUMN.STARTTIME.getId(),
				new GanttColumnFormatter<DateTime>() {
					@Override
					public String formatValue(String columnName, DateTime value) {
						String sqlTimeString = dateFormat.print(value);
						return sqlTimeString;
					}

				});

		gdata.setColumnFormatter(GANTTCOLUMN.ENDTIME.getId(),
				new GanttColumnFormatter<DateTime>() {
					@Override
					public String formatValue(String columnName, DateTime value) {
						String sqlTimeString = dateFormat.print(value);
						return sqlTimeString;
					}

				});
		gdata.setColumnsOrder(Arrays.asList(new Object[] { "name",
				GANTTCOLUMN.STARTTIME, GANTTCOLUMN.ENDTIME,
				GANTTCOLUMN.COMPLETE, }));

		// gdata.setTaskTooltipTemplate("<b>Name :</b> ${name}<br><b>Start time:</b> ${startTime}<br><b>End time:</b> ${endTime}");

		for (SimpleTaskList taskList : tasks) {
			GanttTask parentGanttTask = new GanttTask(
					convertDateTime(taskList.getStartDate()),
					convertDateTime(taskList.getEndDate()),
					taskList.getPercentageComplete());
			parentGanttTask.addParameter("name", taskList.getName());
			parentGanttTask.addParameter("task", taskList);
			List<SimpleTask> subTasks = taskList.getSubTasks();
			for (SimpleTask subTask : subTasks) {
				GanttTask ganttTask = new GanttTask(
						convertDateTime(subTask.getStartdate()),
						convertDateTime(subTask.getEnddate()),
						subTask.getPercentagecomplete(), parentGanttTask);
				ganttTask.addParameter("name", subTask.getTaskname());
				ganttTask.addParameter("task", subTask);
			}
			gdata.addTask(parentGanttTask);
		}
		GanttDiagram diagram = new GanttDiagram(gdata);
		diagram.addStyleName("gantt-chart");
		diagram.setGanttDiagramCellStyleGenerator(new GanttDiagramDemoCellStyleGenerator());
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.DAY, "Day");
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.DAY_MONTH, "Day of Month");
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.WEEK, "Week");
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.MONTH, "Month");

		diagram.setScale(GANTTDIAGRAMSCALE.DAY);
		diagram.setWidth("100%");
		diagram.setHeight("100%");

		this.removeAllComponents();
		this.addComponent(diagram);
	}

	private static DateTime convertDateTime(Date date) {
		if (date == null) {
			return new DateTime();
		}
		return new DateTime(date.getTime(), DateTimeZone.UTC);
	}

	public class GanttDiagramDemoCellStyleGenerator extends
			GanttDiagramCellStyleGeneratorAdapter {

		@Override
		public String getRowStyle(GanttDiagram diagram, GanttTask task) {

			return super.getRowStyle(diagram, task);
		}

		@Override
		public String getCellStyle(GanttDiagram diagram, GanttTask task,
				GANTTCOLUMN column) {
			if (column == GANTTCOLUMN.COMPLETE) {
				return "background-red v-table-cell-content-font-bold";
			}

			return super.getCellStyle(diagram, task, column);
		}

		@Override
		public String getCellStyle(GanttDiagram diagram, GanttTask task,
				String column) {
			if (column.equals("overflow") && task.getCompleteState() == 100) {
				return "font-bold";
			}

			return super.getCellStyle(diagram, task, column);
		}

	}
}
