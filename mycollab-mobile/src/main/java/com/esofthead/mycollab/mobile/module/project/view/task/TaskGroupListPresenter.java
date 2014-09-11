package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.ListPresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.ComponentContainer;

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

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS)) {
			TaskListSearchCriteria criteria;
			if (data == null || data.getParams() == null) {
				criteria = new TaskListSearchCriteria();
				criteria.setProjectId(new NumberSearchField(
						CurrentProjectVariables.getProjectId()));
			} else {
				criteria = (TaskListSearchCriteria) data.getParams();
			}
			if (!view.isAttached()) {
				((NavigationManager) container).navigateTo(view.getWidget());
			}
			doSearch(criteria);
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}

	}

}
