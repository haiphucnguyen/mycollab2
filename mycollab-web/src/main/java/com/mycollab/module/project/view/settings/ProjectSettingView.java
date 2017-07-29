package com.mycollab.module.project.view.settings;

import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public interface ProjectSettingView extends PageView {
    void showNotificationSettings(ProjectNotificationSetting notification);
}
