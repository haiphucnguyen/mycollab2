package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.shell.view.FragmentNavigator;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class FileModulePresenter extends AbstractPresenter<FileModule> {
	private static final long serialVersionUID = 1L;

	public FileModulePresenter() {
		super(FileModule.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		MainView mainView = (MainView) container;
		mainView.addModule((IModule) view);

		// String[] params = (String[]) data.getParams();
		// if (params == null || params.length == 0) {
		// FileMainPresenter mainPresenter = PresenterResolver
		// .getPresenter(FileMainPresenter.class);
		// mainPresenter.go(view, null);
		// } else {
		// FragmentNavigator.shellUrlResolver.getSubResolver("document").handle(
		// params);
		// }

		FileMainPresenter mainPresenter = PresenterResolver
				.getPresenter(FileMainPresenter.class);
		mainPresenter.go(view, null);

		AppContext.updateLastModuleVisit(ModuleNameConstants.FILE);
	}

}
