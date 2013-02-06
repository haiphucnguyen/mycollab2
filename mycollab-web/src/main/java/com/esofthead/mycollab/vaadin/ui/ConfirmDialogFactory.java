package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;

import com.vaadin.ui.Button;

public class ConfirmDialogFactory extends DefaultConfirmDialogFactory {
	private static final long serialVersionUID = 1L;

	@Override
	public ConfirmDialog create(String caption, String message,
			String okCaption, String cancelCaption) {
		ConfirmDialog d = super.create(caption, message, okCaption,
				cancelCaption);

		Button ok = d.getOkButton();
		ok.setStyleName(UIConstants.THEME_BLUE_LINK);

		Button cancel = d.getCancelButton();
		cancel.setStyleName(UIConstants.THEME_LINK);
		cancel.focus();

		return d;
	}

}
