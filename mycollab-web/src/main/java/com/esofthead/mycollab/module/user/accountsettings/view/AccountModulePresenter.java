package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.shell.view.FragmentNavigator;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class AccountModulePresenter extends AbstractPresenter<AccountModule> {
	private static final long serialVersionUID = 1L;

	public AccountModulePresenter() {
		super(AccountModule.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		MainView mainView = (MainView) container;
		mainView.addModule(view);

		String[] params = (String[]) data.getParams();
		if (params == null || params.length == 0) {
			view.gotoUserProfilePage();
		} else {
			FragmentNavigator.shellUrlResolver.getSubResolver("account")
					.handle(params);
		}

		AppContext.updateLastModuleVisit(ModuleNameConstants.ACCOUNT);
	}
}
