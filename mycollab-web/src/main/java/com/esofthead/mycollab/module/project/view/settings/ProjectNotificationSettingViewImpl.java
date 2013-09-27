package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class ProjectNotificationSettingViewImpl extends AbstractView implements
		ProjectNotificationSettingView {
	private static final long serialVersionUID = 1L;

	@Override
	public void showNotificationSettings(ProjectNotificationSetting notification) {
		this.addComponent(new Label("Default"));
	}

}
