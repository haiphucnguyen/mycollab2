package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.parameters.TaskFilterParameter;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.ListCommand;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

public class TaskListPresenter extends AbstractPresenter<TaskListView>
		implements ListCommand<TaskSearchCriteria> {
	private static final long serialVersionUID = 1L;

	public TaskListPresenter() {
		super(TaskListView.class);
	}

	@Override
	protected void postInitView() {
		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<TaskSearchCriteria>() {
					@Override
					public void onSearch(TaskSearchCriteria criteria) {
						doSearch(criteria);
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS)) {
			TaskContainer trackerContainer = (TaskContainer) container;
			trackerContainer.removeAllComponents();
			trackerContainer.addComponent(view.getWidget());

			TaskFilterParameter param = (TaskFilterParameter) data.getParams();

			view.setTitle(param.getScreenTitle());
			doSearch(param.getSearchCriteria());

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadcrumb.gotoBugList();
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

	@Override
	public void doSearch(TaskSearchCriteria searchCriteria) {

		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
	}
}