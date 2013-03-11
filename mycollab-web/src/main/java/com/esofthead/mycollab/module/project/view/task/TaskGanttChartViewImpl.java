package com.esofthead.mycollab.module.project.view.task;

import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;

@ViewComponent
public class TaskGanttChartViewImpl extends AbstractView implements
		TaskGanttChartView {
	private static final long serialVersionUID = 1L;

	@Override
	public void displayGanttChart() {
		this.setSizeFull();
		JFreeChart chart = createChart();
		JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);

		this.addComponent(chartWrapper);
		this.setComponentAlignment(chartWrapper, Alignment.MIDDLE_CENTER);
	}

	private JFreeChart createChart() {
		/**
		 * Creating a task series And adding planned tasks dates on the series.
		 */
		TaskSeries seriesOne = new TaskSeries("Planned Implementation");

		/** Adding data in this series **/
		seriesOne.add(new Task("Sanjaal Domain Registration",
				new SimpleTimePeriod(makeDate(10, Calendar.JUNE, 2007),
						makeDate(15, Calendar.JUNE, 2007))));

		seriesOne.add(new Task("Feature Addition - Java Blog",
				new SimpleTimePeriod(makeDate(9, Calendar.JULY, 2007),
						makeDate(19, Calendar.JULY, 2007))));

		seriesOne.add(new Task("Feature Addition - PHPBB Forum",
				new SimpleTimePeriod(makeDate(10, Calendar.AUGUST, 2007),
						makeDate(15, Calendar.AUGUST, 2007))));

		seriesOne.add(new Task("Feature Addition - Tagged Mails",
				new SimpleTimePeriod(makeDate(6, Calendar.MAY, 2007), makeDate(
						30, Calendar.MAY, 2007))));

		seriesOne.add(new Task("Feature Addition - H1B Visa Portal",
				new SimpleTimePeriod(makeDate(2, Calendar.JUNE, 2007),
						makeDate(2, Calendar.JUNE, 2007))));

		seriesOne.add(new Task("Feature Addition - Events Gallery",
				new SimpleTimePeriod(makeDate(3, Calendar.JUNE, 2007),
						makeDate(31, Calendar.JULY, 2007))));

		seriesOne.add(new Task("Google Adsense Integration",
				new SimpleTimePeriod(makeDate(1, Calendar.AUGUST, 2007),
						makeDate(8, Calendar.AUGUST, 2007))));

		seriesOne.add(new Task("Adbrite Advertisement Integration",
				new SimpleTimePeriod(makeDate(10, Calendar.AUGUST, 2007),
						makeDate(10, Calendar.AUGUST, 2007))));

		seriesOne.add(new Task("InfoLink Advertisement Integration",
				new SimpleTimePeriod(makeDate(12, Calendar.AUGUST, 2007),
						makeDate(12, Calendar.SEPTEMBER, 2007))));

		seriesOne.add(new Task("Feature Testing", new SimpleTimePeriod(
				makeDate(13, Calendar.SEPTEMBER, 2007), makeDate(31,
						Calendar.OCTOBER, 2007))));

		seriesOne.add(new Task("Public Release", new SimpleTimePeriod(makeDate(
				1, Calendar.NOVEMBER, 2007), makeDate(15, Calendar.NOVEMBER,
				2007))));

		seriesOne.add(new Task("Post Release Bugs Collection",
				new SimpleTimePeriod(makeDate(28, Calendar.NOVEMBER, 2007),
						makeDate(30, Calendar.NOVEMBER, 2007))));
		final TaskSeriesCollection collection = new TaskSeriesCollection();

		/**
		 * Adding the series to the collection Holds actual Dates.
		 */
		collection.add(seriesOne);
		JFreeChart chart = ChartFactory.createGanttChart(
				"Gantt Chart - Sanjaal.com Feature Implmentation", // chart
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
