package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 */
public class TaskGroupReadPresenter extends
		AbstractMobilePresenter<TaskGroupReadView> {

	private static final long serialVersionUID = 5446981407457723179L;

	public TaskGroupReadPresenter() {
		super(TaskGroupReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS)) {

			if (data.getParams() instanceof Integer) {
				ProjectTaskListService service = ApplicationContextUtil
						.getSpringBean(ProjectTaskListService.class);
				SimpleTaskList taskList = service.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (taskList != null) {
					super.onGo(navigator, data);
					this.view.previewItem(taskList);
					AppContext.addFragment(
							"project/task/group/preview/"
									+ GenericLinkUtils
											.encodeParam(new Object[] {
													CurrentProjectVariables
															.getProjectId(),
													taskList.getId() }),
							taskList.getName());
				} else {
					NotificationUtil.showRecordNotExistNotification();
				}
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

}
