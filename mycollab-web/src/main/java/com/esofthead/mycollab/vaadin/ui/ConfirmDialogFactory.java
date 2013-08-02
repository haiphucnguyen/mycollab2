package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;

public class ConfirmDialogFactory extends DefaultConfirmDialogFactory {
	private static final long serialVersionUID = 1L;

	@Override
	public ConfirmDialog create(final String caption, final String message,
			final String okCaption, final String cancelCaption) {
		final ConfirmDialog d = super.create(caption, message, okCaption,
				cancelCaption);

		d.setHeight(Sizeable.SIZE_UNDEFINED, 0);

		d.getContent().setStyleName("custom-dialog");

		final Button ok = d.getOkButton();
		ok.setStyleName(UIConstants.THEME_BLUE_LINK);

		final Button cancel = d.getCancelButton();
		cancel.setStyleName(UIConstants.THEME_LINK);
		cancel.focus();

		return d;
	}

}
