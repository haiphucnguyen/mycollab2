package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.module.project.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */

@ViewComponent
public class TaskGroupListViewImpl extends
		AbstractListViewComp<TaskListSearchCriteria, SimpleTaskList> implements
		TaskGroupListView {

	private static final long serialVersionUID = 1604670567813647489L;

	@Override
	protected AbstractPagedBeanList<TaskListSearchCriteria, SimpleTaskList> createBeanTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Component createRightComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}
