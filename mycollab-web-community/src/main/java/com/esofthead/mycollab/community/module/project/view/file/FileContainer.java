package com.esofthead.mycollab.community.module.project.view.file;

import com.esofthead.mycollab.module.project.file.IFileContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class FileContainer extends AbstractView implements IFileContainer {
	private static final long serialVersionUID = 1L;

	public FileContainer() {
		this.addComponent(new Label(
				"Module is not presented for community edition"));
	}
}
