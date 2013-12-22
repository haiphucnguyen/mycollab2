package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.file.view.IFileModule;
import com.esofthead.mycollab.module.file.view.IFileModulePresenter;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class FileModulePresenter extends AbstractPresenter<IFileModule>
		implements IFileModulePresenter {
	private static final long serialVersionUID = 1L;

	public FileModulePresenter() {
		super(IFileModule.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		MainView mainView = (MainView) container;
		mainView.addModule((IModule) cacheableView);

		FileMainPresenter mainPresenter = PresenterResolver
				.getPresenter(FileMainPresenter.class);
		mainPresenter.go(cacheableView, null);

		AppContext.getInstance()
				.updateLastModuleVisit(ModuleNameConstants.FILE);
	}

}
