/**
 * This file is part of mycollab-ui.
 *
 * mycollab-ui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-ui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-ui.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import static com.esofthead.mycollab.vaadin.MyCollabSession.CURRENT_APP;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.MyCollabSession;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class NotificationUtil {

	public static void showNotification(String caption) {
		showNotification(caption, null, Type.HUMANIZED_MESSAGE);
	}

	public static void showWarningNotification(String description) {
		showNotification(
				LocalizationHelper
				.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
				description, Type.WARNING_MESSAGE);
	}

	public static void showErrorNotification(String description) {
		showNotification(
				LocalizationHelper
				.getMessage(GenericI18Enum.ERROR_WINDOW_TITLE),
				description, Type.ERROR_MESSAGE);
	}

	public static void showNotification(String caption, String description,
			Type type) {
		Notification warnNotif = new Notification(caption, description, type);
		warnNotif.setHtmlContentAllowed(true);
		warnNotif.setDelayMsec(3000);

		if (Page.getCurrent() != null) {
			warnNotif.show(Page.getCurrent());
		} else {
			warnNotif.show(((UI) MyCollabSession.getVariable(CURRENT_APP)).getPage());
		}

	}

	public static void showGotoLastRecordNotification() {
		showNotification(
				LocalizationHelper
				.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
				LocalizationHelper
				.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
				Type.HUMANIZED_MESSAGE);
	}

	public static void showGotoFirstRecordNotification() {
		showNotification(
				LocalizationHelper
				.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
				LocalizationHelper
				.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
				Type.HUMANIZED_MESSAGE);
	}

	public static void showRecordNotExistNotification() {
		showNotification(
				LocalizationHelper
				.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
				LocalizationHelper
				.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
				Type.HUMANIZED_MESSAGE);
	}

	public static void showMessagePermissionAlert() {
		showNotification(
				LocalizationHelper
				.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
				"Sorry! You do not have permission to do this task.",
				Type.WARNING_MESSAGE);
	}
}
