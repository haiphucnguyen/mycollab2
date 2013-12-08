/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserPermissionManagementViewImpl extends AbstractPageView implements
		UserPermissionManagementView {
	private static final long serialVersionUID = 1L;
	private DetachedTabs groupTab;
	private UserPresenter userPresenter;
	private RolePresenter rolePresenter;
	private CssLayout mySpaceArea = new CssLayout();

	public UserPermissionManagementViewImpl() {
		this.setMargin(true);
		groupTab = new DetachedTabs.Horizontal(mySpaceArea);
		groupTab.setSizeUndefined();

		HorizontalLayout menu = new HorizontalLayout();
		menu.setWidth("100%");
		menu.setStyleName(UIConstants.THEME_TAB_STYLE3);
		menu.setHeight("40px");
		menu.setStyleName(UIConstants.THEME_TAB_STYLE3);
		menu.addComponent(groupTab);

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
		groupTab.addTab(userPresenter.getView(), "Users");

		rolePresenter = PresenterResolver.getPresenter(RolePresenter.class);
		groupTab.addTab(rolePresenter.getView(), "Roles");

		groupTab.addTabChangedListener(new DetachedTabs.TabChangedListener() {
			@Override
			public void tabChanged(DetachedTabs.TabChangedEvent event) {
				Button btn = event.getSource();
				String caption = btn.getCaption();
				if ("Users".equals(caption)) {
					userPresenter.go(UserPermissionManagementViewImpl.this,
							null);
				} else if ("Roles".equals(caption)) {
					rolePresenter.go(UserPermissionManagementViewImpl.this,
							null);
				}
			}
		});
		userPresenter.go(this, null);

	}

	@Override
	public Component gotoSubView(String name) {
		PageView component = (PageView) groupTab.selectTab(name);
		return component;
	}

}
