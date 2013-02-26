/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserPermissionManagementViewImpl extends AbstractView implements
		UserPermissionManagementView {
	private static final long serialVersionUID = 1L;
	private DetachedTabs myProjectTab;
	private UserPresenter userPresenter;
	private RolePresenter rolePresenter;
	private CssLayout mySpaceArea = new CssLayout();

	public UserPermissionManagementViewImpl() {
		this.setMargin(true);
		myProjectTab = new DetachedTabs.Horizontal(mySpaceArea);
		myProjectTab.setSizeUndefined();

		HorizontalLayout menu = new HorizontalLayout();
		menu.setWidth("100%");
		menu.setStyleName(UIConstants.THEME_TAB_STYLE3);
		menu.setHeight("40px");
		menu.addComponent(myProjectTab);

		this.addComponent(menu);
		mySpaceArea.setWidth("100%");
		mySpaceArea.setHeight(null);
		this.addComponent(mySpaceArea);
		this.setExpandRatio(mySpaceArea, 1.0f);
		this.setWidth("100%");

		buildComponents();
	}

	private void buildComponents() {
		userPresenter = PresenterResolver.getPresenter(UserPresenter.class);
		myProjectTab.addTab(userPresenter.getView(), "Users");

		rolePresenter = PresenterResolver.getPresenter(RolePresenter.class);
		myProjectTab.addTab(rolePresenter.getView(), "Roles");

		myProjectTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {
					@Override
					public void tabChanged(DetachedTabs.TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						if ("Users".equals(caption)) {
							userPresenter.go(myProjectTab, null);
						} else if ("Roles".equals(caption)) {
							rolePresenter.go(myProjectTab, null);
						}
					}
				});
		userPresenter.go(myProjectTab, null);

	}

}
