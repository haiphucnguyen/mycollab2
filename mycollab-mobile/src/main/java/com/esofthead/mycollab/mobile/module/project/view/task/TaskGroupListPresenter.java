package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.ui.ListPresenter;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public class TaskGroupListPresenter
		extends
		ListPresenter<TaskGroupListView, TaskListSearchCriteria, SimpleTaskList> {

	private static final long serialVersionUID = -8290099378507557230L;

	public TaskGroupListPresenter() {
		super(TaskGroupListView.class);
	}

}
