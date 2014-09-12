package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.ui.DefaultPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public class TaskListDisplay
		extends
		DefaultPagedBeanList<ProjectTaskService, TaskSearchCriteria, SimpleTask> {

	private static final long serialVersionUID = 1469872908434812706L;

	public TaskListDisplay() {
		super(ApplicationContextUtil.getSpringBean(ProjectTaskService.class),
				new TaskRowDisplayHandler());
	}

	private static class TaskRowDisplayHandler implements
			RowDisplayHandler<SimpleTask> {

		@Override
		public Component generateRow(final SimpleTask task, int rowIndex) {
			Button b = new Button(task.getTaskname());
			b.setWidth("100%");
			b.addStyleName("list-item");
			return b;
		}

	}

}
