package com.esofthead.mycollab.module.project.view.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.Gantt.MoveEvent;
import org.tltv.gantt.Gantt.ResizeEvent;
import org.tltv.gantt.client.shared.Step;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Notification;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
@ViewComponent
public class GanttChartViewImpl extends AbstractPageView implements
		GanttChartView {
	private static final long serialVersionUID = 1L;

	@Override
	public void displayGanttChart() {
		this.removeAllComponents();
		ProjectTaskListService taskListService = ApplicationContextUtil
				.getSpringBean(ProjectTaskListService.class);
		TaskListSearchCriteria searchCriteria = new TaskListSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		List taskList = taskListService
				.findPagableListByCriteria(new SearchRequest<TaskListSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

		Gantt gantt = new Gantt();
		gantt.setWidth(100, Unit.PERCENTAGE);
		gantt.setHeight(100, Unit.PERCENTAGE);
		gantt.setResizableSteps(true);
		gantt.setMovableSteps(true);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		gantt.setStartDate(cal.getTime());
		cal.add(Calendar.YEAR, 1);
		gantt.setEndDate(cal.getTime());

		cal.setTime(new Date());
		Step step1 = new Step("First step");
		step1.setStartDate(cal.getTime().getTime());
		cal.add(Calendar.MONTH, 2);
		step1.setEndDate(cal.getTime().getTime());

		gantt.addStep(step1);

		gantt.addClickListener(new Gantt.ClickListener() {

			@Override
			public void onGanttClick(org.tltv.gantt.Gantt.ClickEvent event) {
				Notification.show("Clicked" + event.getStep().getCaption());
			}
		});

		gantt.addMoveListener(new Gantt.MoveListener() {

			@Override
			public void onGanttMove(MoveEvent event) {
				Notification.show("Moved " + event.getStep().getCaption());
			}
		});

		gantt.addResizeListener(new Gantt.ResizeListener() {

			@Override
			public void onGanttResize(ResizeEvent event) {
				Notification.show("Resized " + event.getStep().getCaption());
			}
		});

		this.addComponent(gantt);
	}

}
