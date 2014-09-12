package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.ListPresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
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
public class TaskListPresenter extends
		ListPresenter<TaskListView, TaskSearchCriteria, SimpleTask> {

	private static final long serialVersionUID = -2899902106379842031L;

	public TaskListPresenter() {
		super(TaskListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS)) {
			if (data.getParams() != null && data.getParams() instanceof Integer) {
				ProjectTaskListService searchService = ApplicationContextUtil
						.getSpringBean(ProjectTaskListService.class);
				SimpleTaskList taskList = searchService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				view.setCaption(taskList.getName());

				TaskSearchCriteria criteria = new TaskSearchCriteria();
				criteria.setProjectid(new NumberSearchField(
						CurrentProjectVariables.getProjectId()));
				criteria.setTaskListId(new NumberSearchField(taskList.getId()));
				criteria.setStatuses(new SetSearchField<String>(
						SearchField.AND, new String[] { "Open" }));

				((NavigationManager) container).navigateTo(view.getWidget());
				doSearch(criteria);

				AppContext.addFragment(
						"project/task/list/"
								+ GenericLinkUtils.encodeParam(new Object[] {
										CurrentProjectVariables.getProjectId(),
										taskList.getId() }),
						AppContext.getMessage(TaskI18nEnum.M_VIEW_LIST_TITLE));
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

}
