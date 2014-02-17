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
import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TreeTable;

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

	private TreeTable taskTree;
	private Gantt gantt;

	@Override
	public void displayGanttChart() {
		this.removeAllComponents();
		HorizontalLayout container = new HorizontalLayout();
		container.setSizeFull();
		this.addComponent(container);

		ProjectTaskListService taskListService = ApplicationContextUtil
				.getSpringBean(ProjectTaskListService.class);
		TaskListSearchCriteria searchCriteria = new TaskListSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		List<SimpleTaskList> taskList = taskListService
				.findPagableListByCriteria(new SearchRequest<TaskListSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

		taskTree = new TreeTable();
		container.addComponent(taskTree);

		taskTree.addContainerProperty("Task Name", String.class, null);
		taskTree.addContainerProperty("Start Date", Date.class, null);
		taskTree.addContainerProperty("End Date", Date.class, null);
		taskTree.addContainerProperty("Assign User", String.class, null);
		taskTree.setSizeFull();

		taskTree.addGeneratedColumn("Task Name", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				// TODO Auto-generated method stub
				System.out.println("Item ud: " + itemId);
				return null;
			}
		});

		gantt = new Gantt();
		gantt.setWidth(100, Unit.PERCENTAGE);
		gantt.setHeight(100, Unit.PERCENTAGE);
		gantt.setResizableSteps(true);
		gantt.setMovableSteps(true);
		container.addComponent(gantt);

		Calendar cal = Calendar.getInstance();
		Date projectStartDate = cal.getTime(), projectEndDate = cal.getTime();

		for (SimpleTaskList subTaskList : taskList) {
			List<SimpleTask> subTasks = subTaskList.getSubTasks();

			// Add parent task for task list
			Object parentId = taskTree.addItem(
					new Object[] { subTaskList.getName(),
							subTaskList.getStartDate(),
							subTaskList.getEndDate(),
							subTaskList.getOwnerFullName() }, subTaskList);

			Step parentStep = new Step(subTaskList.getName());
			parentStep.setStartDate(subTaskList.getStartDate().getTime());
			parentStep.setEndDate(subTaskList.getEndDate().getTime());
			gantt.addStep(parentStep);

			for (SimpleTask subTask : subTasks) {
				if (subTask.getStartdate().before(projectStartDate)) {
					projectStartDate = subTask.getStartdate();
				}

				if (subTask.getEnddate().after(projectEndDate)) {
					projectEndDate = subTask.getEnddate();
				}

				cal.setTime(subTask.getStartdate());
				Step step = new Step(subTask.getTaskname());
				step.setStartDate(cal.getTime().getTime());
				cal.setTime(subTask.getEnddate());
				step.setEndDate(cal.getTime().getTime());
				gantt.addStep(step);

				Object childItem = taskTree.addItem(
						new Object[] { subTask.getTaskname(),
								subTask.getStartdate(), subTask.getEnddate(),
								subTask.getAssignUserFullName() }, subTask);
				taskTree.setParent(childItem, parentId);
			}
		}

		gantt.setStartDate(projectStartDate);
		gantt.setEndDate(projectEndDate);

		gantt.addClickListener(new Gantt.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onGanttClick(org.tltv.gantt.Gantt.ClickEvent event) {
				Notification.show("Clicked" + event.getStep().getCaption());
			}
		});

		gantt.addMoveListener(new Gantt.MoveListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onGanttMove(MoveEvent event) {
				Notification.show("Moved " + event.getStep().getCaption());
			}
		});

		gantt.addResizeListener(new Gantt.ResizeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onGanttResize(ResizeEvent event) {
				Notification.show("Resized " + event.getStep().getCaption());
			}
		});

	}

}
