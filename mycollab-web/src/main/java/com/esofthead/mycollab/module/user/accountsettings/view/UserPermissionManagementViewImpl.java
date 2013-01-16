/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserPermissionManagementViewImpl extends AbstractView implements
		UserPermissionManagementView {

	private final TabSheet tabContainer;
	private UserPresenter userPresenter;
	private RolePresenter rolePresenter;

	public UserPermissionManagementViewImpl() {
		this.setWidth("100%");
		this.setMargin(true);
		tabContainer = new TabSheet();
		tabContainer.setWidth("100%");
		constructTabs();
		this.addComponent(tabContainer);

		tabContainer.addListener(new TabSheet.SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				Component selectedTab = tabContainer.getSelectedTab();
				if (selectedTab instanceof RoleContainer) {
					rolePresenter.go(tabContainer, null);
				} else if (selectedTab instanceof UserContainer) {
					userPresenter.go(tabContainer, null);
				}
			}
		});
	}

	private void constructTabs() {
		userPresenter = PresenterResolver.getPresenter(UserPresenter.class);
		tabContainer.addTab(userPresenter.getView(), "Users");
		// goto user list by default
		userPresenter.go(UserPermissionManagementViewImpl.this, null);

		rolePresenter = PresenterResolver.getPresenter(RolePresenter.class);
		tabContainer.addTab(rolePresenter.getView(), "Roles");
	}
}
