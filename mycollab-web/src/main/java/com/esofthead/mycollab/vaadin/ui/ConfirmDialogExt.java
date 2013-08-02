package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.ConfirmDialog.Listener;

import com.vaadin.ui.Window;

public class ConfirmDialogExt {
	public static void show(Window parentWindow, String windowCaption,
			String message, String okCaption, String cancelCaption,
			Listener listener) {
		ConfirmDialog.setFactory(new ConfirmDialogFactory());
		ConfirmDialog.show(parentWindow, windowCaption, message, okCaption,
				cancelCaption, listener);
	}
}
