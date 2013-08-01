package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;

import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;

public class MessageConstants {

	public final static String PERMISSION_ALERT = "Sorry! You do not have permission to do this task.";

	public static void showMessagePermissionAlert() {
		MessageBox mb = new MessageBox(AppContext.getApplication()
				.getMainWindow(),
				LocalizationHelper
						.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
				MessageBox.Icon.WARN, MessageConstants.PERMISSION_ALERT,
				new MessageBox.ButtonConfig(ButtonType.OK, "OK"));
		mb.show();
	}

}
