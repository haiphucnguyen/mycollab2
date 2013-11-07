package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.module.file.view.IFileModule;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class FileModule extends AbstractView implements IFileModule {
	private static final long serialVersionUID = 1L;

	public FileModule() {
		ControllerRegistry.addController(new FileController(this));
	}
}
