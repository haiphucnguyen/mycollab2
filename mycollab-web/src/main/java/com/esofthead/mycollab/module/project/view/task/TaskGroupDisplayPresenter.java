package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.vaadin.ui.ComponentContainer;

public class TaskGroupDisplayPresenter extends
		AbstractPresenter<TaskGroupDisplayView> {

	private static final long serialVersionUID = 1L;

	public TaskGroupDisplayPresenter() {
		super(TaskGroupDisplayView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS)) {
			TaskContainer taskContainer = (TaskContainer) container;
			taskContainer.removeAllComponents();

			taskContainer.addComponent(view.getWidget());
			view.displayActiveTakLists();

			ProjectBreadcrumb breadCrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadCrumb.gotoTaskDashboard();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
