package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class FileMainPresenter extends AbstractPresenter<FileMainView> {
	private static final long serialVersionUID = 1L;

	public FileMainPresenter() {
		super(FileMainView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		FileModule fileModule = (FileModule) container;
		fileModule.removeAllComponents();

		fileModule.addComponent(view);
		view.display();
	}

}
