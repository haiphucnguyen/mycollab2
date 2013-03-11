package com.esofthead.mycollab.module.project.view.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;

@ViewComponent
public class TaskGanttChartViewImpl extends AbstractView implements
		TaskGanttChartView {
	private static final long serialVersionUID = 1L;

	private TaskSearchCriteria searchCriteria;

	@Override
	public void displayGanttChart(TaskSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		this.removeAllComponents();
		this.setSizeFull();
		JFreeChart chart = createChart();
		JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);

		this.addComponent(chartWrapper);
		this.setComponentAlignment(chartWrapper, Alignment.MIDDLE_CENTER);
	}

	private JFreeChart createChart() {
		ProjectTaskService taskService = AppContext
				.getSpringBean(ProjectTaskService.class);
		List<SimpleTask> tasks = taskService
				.findPagableListByCriteria(new SearchRequest<TaskSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

		/**
		 * Creating a task series And adding planned tasks dates on the series.
		 */
		TaskSeries seriesOne = new TaskSeries("Planned Implementation");

		final TaskSeriesCollection collection = new TaskSeriesCollection();

		for (SimpleTask task : tasks) {
			if (task.getStartdate() != null && task.getEnddate() != null) {
				seriesOne.add(new Task(task.getTaskname(),
						new SimpleTimePeriod(task.getStartdate(), task
								.getEnddate())));
			}
		}

		/**
		 * Adding the series to the collection Holds actual Dates.
		 */
		collection.add(seriesOne);
		JFreeChart chart = ChartFactory.createGanttChart("", // chart
																// title
				"Task", // domain axis label
				"Date", // range axis label
				collection, // data
				true, // include legend
				true, // tooltips
				false // urls
				);
		return chart;
	}

	private static Date makeDate(final int day, final int month, final int year) {

		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;

	}
}
