/**
 * This file is part of mycollab-web-community.
 *
 * mycollab-web-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-community.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * This file is part of mycollab-web-community.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.community.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.project.view.settings.NotificationSettingViewComponent;
import com.esofthead.mycollab.module.project.view.settings.ProjectSettingView;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class ProjectSettingViewImpl extends AbstractPageView implements
		ProjectSettingView {
	private static final long serialVersionUID = 1L;

	private NotificationSettingViewComponent<ProjectNotificationSetting, ProjectNotificationSettingService> component;

	private final HorizontalLayout mainBody;

	public ProjectSettingViewImpl() {
		this.setWidth("100%");
		this.setSpacing(true);
		this.addStyleName("readview-layout");

		mainBody = new HorizontalLayout();
		mainBody.setMargin(true);
		mainBody.setSpacing(true);
		mainBody.setWidth("100%");
		mainBody.addStyleName("medium-spacing");

		this.addComponent(mainBody);
	}

	@Override
	public void showNotificationSettings(ProjectNotificationSetting notification) {
		mainBody.removeAllComponents();

		if (notification == null) {
			notification = new ProjectNotificationSetting();
		}
		component = new NotificationSettingViewComponent<ProjectNotificationSetting, ProjectNotificationSettingService>(
				notification,
				ApplicationContextUtil
						.getSpringBean(ProjectNotificationSettingService.class)) {
			private static final long serialVersionUID = 1L;
		};
		mainBody.addComponent(component);
	}
}
