/**
 * This file is part of mycollab-web.
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
				Window.Notification.TYPE_WARNING_MESSAGE);
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
