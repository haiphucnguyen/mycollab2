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
import ru.bazon.vaadin.ganttdiagram.model.GANTTCOLUMN;
import ru.bazon.vaadin.ganttdiagram.model.GANTTDIAGRAMSCALE;
import ru.bazon.vaadin.ganttdiagram.model.GanttDiagramModel;
import ru.bazon.vaadin.ganttdiagram.model.GanttTask;
import ru.bazon.vaadin.ganttdiagram.model.GanttTaskParameterDescription;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;

@ViewComponent
public class TaskGanttChartViewImpl extends AbstractView implements
		TaskGanttChartView {
	private static final long serialVersionUID = 1L;

	private static DateTimeFormatter dateFormat = DateTimeFormat
			.forPattern("yyyy-MM-dd");

	private TaskSearchCriteria searchCriteria;

	@Override
	public void displayGanttChart(TaskSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		this.removeAllComponents();
		this.setSizeFull();

		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setStatus(new StringSearchField("Open"));
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));

		ProjectTaskListService taskService = AppContext
				.getSpringBean(ProjectTaskListService.class);
		List<SimpleTaskList> tasks = taskService
				.findPagableListByCriteria(new SearchRequest<TaskListSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));

		GanttDiagramModel gdata = new GanttDiagramModel();
		gdata.setGanttColumnVisible(GANTTCOLUMN.STARTTIME,
				"Start         Date", null, null);
		gdata.setGanttColumnVisible(GANTTCOLUMN.ENDTIME, "End           Date",
				null, null);
		gdata.setGanttColumnVisible(GANTTCOLUMN.COMPLETE, "Percentage", null,
				null);
		gdata.addParametersDescription(new GanttTaskParameterDescription(
				"name", String.class, "", "Task                     Name"));

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
				GANTTCOLUMN.COMPLETE, GANTTCOLUMN.STARTTIME,
				GANTTCOLUMN.ENDTIME }));

		gdata.setTaskTooltipTemplate("<b>Name :</b> ${name}<br><b>Start time:</b> ${startTime}<br><b>End time:</b> ${endTime}");

		for (SimpleTaskList taskList : tasks) {
			GanttTask parentGanttTask = new GanttTask(
					convertDateTime(taskList.getStartDate()),
					convertDateTime(taskList.getEndDate()),
					taskList.getPercentageComplete());
			parentGanttTask.addParameter("name", taskList.getName());
			List<SimpleTask> subTasks = taskList.getSubTasks();
			for (SimpleTask subTask : subTasks) {
				GanttTask ganttTask = new GanttTask(
						convertDateTime(subTask.getStartdate()),
						convertDateTime(subTask.getEnddate()),
						subTask.getPercentagecomplete(), parentGanttTask);
				ganttTask.addParameter("name", subTask.getTaskname());

			}
			gdata.addTask(parentGanttTask);
		}
		GanttDiagram diagram = new GanttDiagram(gdata);
		diagram.setGanttDiagramCellStyleGenerator(new GanttDiagramDemoCellStyleGenerator());
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.DAY, "Day");
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.DAY_MONTH, "Day of Month");
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.WEEK, "Week");
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.MONTH, "Month");
		diagram.addAvailableScale(GANTTDIAGRAMSCALE.YEAR, "Year");

		diagram.setScale(GANTTDIAGRAMSCALE.MONTH);
		this.addComponent(diagram);
		this.setExpandRatio(diagram, 1.0f);
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
