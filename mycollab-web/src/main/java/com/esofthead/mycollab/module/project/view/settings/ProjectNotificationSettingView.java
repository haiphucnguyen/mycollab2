package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface ProjectNotificationSettingView extends View {

	void showNotificationSettings(ProjectNotificationSetting notification);
}
