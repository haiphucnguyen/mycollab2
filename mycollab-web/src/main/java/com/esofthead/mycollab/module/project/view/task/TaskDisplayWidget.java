package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TaskDisplayWidget extends
		BeanList<ProjectTaskService, TaskSearchCriteria, SimpleTask> {
	private static final long serialVersionUID = 1L;

	public TaskDisplayWidget() {
		super(null, ApplicationContextUtil.getSpringBean(ProjectTaskService.class),
				TaskRowDisplayHandler.class, false);
	}

	public static class TaskRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleTask> {

		@Override
		public Component generateRow(final SimpleTask task, int rowIndex) {
			HorizontalLayout layout = new HorizontalLayout();
			Button taskLink = new Button("Task #" + task.getTaskkey() + ": ",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new TaskEvent.GotoRead(this, task.getId()));
						}
					});
			taskLink.setStyleName("link");
			String taskStatus = task.getStatus();
			if ("Closed".equalsIgnoreCase(taskStatus)) {
				taskLink.addStyleName(UIConstants.LINK_COMPLETED);
			} else if ("Pending".equalsIgnoreCase(taskStatus)) {
				taskLink.addStyleName(UIConstants.LINK_PENDING);
			} else if (task.isOverdue()) {
				taskLink.addStyleName(UIConstants.LINK_OVERDUE);
			}
			layout.addComponent(taskLink);
			layout.addComponent(new Label(task.getTaskname()));
			layout.setDescription("Task Information");
			return layout;
		}
	}
}
