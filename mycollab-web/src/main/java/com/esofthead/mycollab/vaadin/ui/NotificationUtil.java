package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Window;

public class NotificationUtil {

	public static void showNotification(String caption) {
		showNotification(caption, null,
				Window.Notification.TYPE_HUMANIZED_MESSAGE);
	}

	public static void showNotification(String caption, int type) {
		showNotification(caption, null, type);
	}

	public static void showNotification(String caption, String description,
			int type) {
		Window.Notification warnNotif = new Window.Notification(caption,
				description, type);
		warnNotif.setDelayMsec(3000);
		AppContext.getApplication().getMainWindow().showNotification(warnNotif);
	}

	public static void showGotoLastRecordNotification() {
		showNotification(
				LocalizationHelper
						.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
				LocalizationHelper
						.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
				Window.Notification.TYPE_HUMANIZED_MESSAGE);
	}

	public static void showGotoFirstRecordNotification() {
		showNotification(
				LocalizationHelper
						.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
				LocalizationHelper
						.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
				Window.Notification.TYPE_HUMANIZED_MESSAGE);
	}

	public static void showRecordNotExistNotification() {
		showNotification(
				LocalizationHelper
						.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
				LocalizationHelper
						.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
				Window.Notification.TYPE_HUMANIZED_MESSAGE);
	}
}
