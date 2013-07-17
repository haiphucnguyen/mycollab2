package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class FileMainViewImpl extends AbstractView implements FileMainView {
	private static final long serialVersionUID = 1L;

	public FileMainViewImpl() {
		this.addComponent(new Label("ABC"));
	}

	@Override
	public void display() {
		
	}
}
