package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

public class ConsoleView extends AbstractView {
	private static final long serialVersionUID = 1L;

	public ConsoleView() {
		this.addComponent(new Label("Console View"));
	}
}
