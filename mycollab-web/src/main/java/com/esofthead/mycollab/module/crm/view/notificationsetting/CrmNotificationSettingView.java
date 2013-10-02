package com.esofthead.mycollab.module.crm.view.notificationsetting;

import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface CrmNotificationSettingView extends View {

	void showNotificationSettings(CrmNotificationSetting notification);
}
