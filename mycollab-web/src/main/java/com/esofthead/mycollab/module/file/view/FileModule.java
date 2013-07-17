package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class FileModule extends AbstractView implements IModule {
	private static final long serialVersionUID = 1L;

	public void gotoFileDashboard() {
		this.addComponent(new Label("File module"));
	}
}
