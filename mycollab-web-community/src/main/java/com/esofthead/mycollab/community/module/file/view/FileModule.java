package com.esofthead.mycollab.community.module.file.view;

import com.esofthead.mycollab.module.file.view.IFileModule;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class FileModule extends AbstractView implements IFileModule {
	private static final long serialVersionUID = 1L;

	public FileModule() {
		this.addComponent(new Label(
				"Module is not presented for community edition"));
	}
}
