package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class CrmLoginPresenter extends AbstractPresenter<CrmLoginView> {

	private static final long serialVersionUID = -750325026975907368L;

	public CrmLoginPresenter() {
		super(CrmLoginView.class);
	}

	@Override
	protected void onGo(ComponentContainer navigationManager, ScreenData<?> data) {
		((NavigationManager) navigationManager).navigateTo(view.getWidget());

		AppContext.addFragment("crm/login", "CRM Login Page");
	}

}
