package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.project.view.settings.NotificationSettingViewComponent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class CrmNotificationSettingViewImpl extends AbstractView implements
		CrmNotificationSettingView {
	private static final long serialVersionUID = 1L;
	private NotificationSettingViewComponent<CrmNotificationSetting, CrmNotificationSettingService> component;

	@Override
	public void showNotificationSettings(CrmNotificationSetting notification) {
		this.removeAllComponents();
		if (notification == null)
			notification = new CrmNotificationSetting();
		component = new NotificationSettingViewComponent<CrmNotificationSetting, CrmNotificationSettingService>(
				notification,
				ApplicationContextUtil
						.getSpringBean(CrmNotificationSettingService.class)) {
		};
		this.addComponent(component);
	}
}
