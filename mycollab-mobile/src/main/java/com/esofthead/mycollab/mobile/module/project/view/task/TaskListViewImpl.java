package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.module.project.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */

@ViewComponent
public class TaskListViewImpl extends
		AbstractListViewComp<TaskSearchCriteria, SimpleTask> implements
		TaskListView {

	private static final long serialVersionUID = -3705209608075399509L;

	public TaskListViewImpl() {
		this.addStyleName("task-list-view");
	}

	@Override
	protected AbstractPagedBeanList<TaskSearchCriteria, SimpleTask> createBeanTable() {
		return new TaskListDisplay();
	}

	@Override
	protected Component createRightComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}
