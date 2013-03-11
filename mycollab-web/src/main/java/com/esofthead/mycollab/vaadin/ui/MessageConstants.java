package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.web.AppContext;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class MessageConstants {

	public final static String PERMISSION_ALERT = "Sorry! You do not have permission to do this task."; 
	
	public static void showMessagePermissionAlert() {
		MessageBox mb = new MessageBox(AppContext.getApplication()
				.getMainWindow(), "Warming!", MessageBox.Icon.WARN,
				MessageConstants.PERMISSION_ALERT,
				new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
		mb.show();
	}
	
}
