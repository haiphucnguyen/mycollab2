package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ProjectNotificationSettingPresenter extends
		AbstractPresenter<ProjectNotificationSettingView> {
	private static final long serialVersionUID = 1L;

	public ProjectNotificationSettingPresenter() {
		super(ProjectNotificationSettingView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		UserSettingView userSettingView = (UserSettingView) container;
		userSettingView.gotoSubView("Notification Settings");

		ProjectNotificationSettingService projectNotificationSettingService = ApplicationContextUtil
				.getSpringBean(ProjectNotificationSettingService.class);
		ProjectNotificationSetting notification = projectNotificationSettingService
				.findNotification(AppContext.getUsername(),
						CurrentProjectVariables.getProjectId(),
						AppContext.getAccountId());

		ProjectBreadcrumb breadCrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadCrumb.gotoNotificationSetting(notification);
		view.showNotificationSettings(notification);
	}
}
