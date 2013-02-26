/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountDashboardView;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.RoleScreenData;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.UserScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class UserPermissionManagementPresenter extends
		AbstractPresenter<UserPermissionManagementView> {
	private static final long serialVersionUID = 1L;

	public UserPermissionManagementPresenter() {
		super(UserPermissionManagementView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		AccountDashboardView accountContaier = (AccountDashboardView) container;
		accountContaier.gotoSubView("Users & Permissions");

		AbstractPresenter<?> presenter = null;
		if ((data == null) || (data instanceof UserScreenData.Read)
				|| (data instanceof UserScreenData.Search)
				|| (data instanceof UserScreenData.Add)
				|| (data instanceof UserScreenData.Edit)) {
			presenter = PresenterResolver.getPresenter(UserPresenter.class);
		} else if ((data instanceof RoleScreenData.Read)
				|| (data instanceof RoleScreenData.Add)
				|| (data instanceof RoleScreenData.Edit)
				|| (data instanceof RoleScreenData.Search)) {
			presenter = PresenterResolver.getPresenter(RolePresenter.class);
		} else {
			throw new MyCollabException("There is no presenter handle data "
					+ BeanUtility.printBeanObj(data));
		}

		presenter.go(view.getWidget(), data);
	}
}
