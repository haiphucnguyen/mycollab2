package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class ProjectNotificationSettingViewImpl extends AbstractView implements
		ProjectNotificationSettingView {
	private static final long serialVersionUID = 1L;
	private NotificationSettingViewComponent<ProjectNotificationSetting, ProjectNotificationSettingService> component;

	@Override
	public void showNotificationSettings(ProjectNotificationSetting notification) {
		this.removeAllComponents();

		if (notification == null) {
			notification = new ProjectNotificationSetting();
		}
		component = new NotificationSettingViewComponent<ProjectNotificationSetting, ProjectNotificationSettingService>(
				notification,
				ApplicationContextUtil
						.getSpringBean(ProjectNotificationSettingService.class)) {
		};
		this.addComponent(component);
	}
}
