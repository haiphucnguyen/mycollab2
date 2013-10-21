package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.events.CrmSettingEvent;
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;

public class CrmSettingUrlResolver extends CrmUrlResolver {

	public CrmSettingUrlResolver() {
		this.addSubResolver("notification",
				new NotificationSettingUrlResolver());
		this.addSubResolver("customlayout", new CustomLayoutUrlResolver());
	}

	public static class NotificationSettingUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new CrmSettingEvent.GotoNotificationSetting(this, null));
		}
	}

	public static class CustomLayoutUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new CrmSettingEvent.GotoCustomViewSetting(this, null));
		}
	}
}
