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
public class TaskListPresenter extends
		ListPresenter<TaskListView, TaskListSearchCriteria, SimpleTaskList> {

	private static final long serialVersionUID = -2899902106379842031L;

	public TaskListPresenter() {
		super(TaskListView.class);
	}

}
