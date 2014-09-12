package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.ListPresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TaskGroupI18nEnum;
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
			if (data.getParams() == null
					|| !(data.getParams() instanceof TaskListSearchCriteria)) {
				criteria = new TaskListSearchCriteria();
				criteria.setProjectId(new NumberSearchField(
						CurrentProjectVariables.getProjectId()));
				criteria.setStatus(new StringSearchField(StatusI18nEnum.Open
						.name()));
			} else {
				criteria = (TaskListSearchCriteria) data.getParams();
			}
			((NavigationManager) container).navigateTo(view.getWidget());
			doSearch(criteria);
			AppContext
					.addFragment(
							"project/task/group/list/"
									+ GenericLinkUtils
											.encodeParam(new Object[] { CurrentProjectVariables
													.getProjectId() }),
							AppContext
									.getMessage(TaskGroupI18nEnum.M_VIEW_LIST_TITLE));
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}

	}

}
