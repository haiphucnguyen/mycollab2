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
package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.crm.data.CustomViewScreenData;
import com.esofthead.mycollab.module.crm.data.NotificationSettingScreenData;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@ViewComponent
public class CrmSettingContainer extends CssLayout implements View {
	private static final long serialVersionUID = 1L;

	private final HorizontalLayout root;
	private final DetachedTabs settingTab;
	private final CssLayout mySpaceArea = new CssLayout();

	private CrmCustomViewPresenter customViewPresenter;
	private CrmNotifcationSettingPresenter notificationPresenter;

	public CrmSettingContainer() {
		this.setWidth("100%");

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setStyleName("projectDashboardView");
		contentWrapper.addStyleName("main-content-wrapper");
		contentWrapper.setWidth("100%");
		this.addComponent(contentWrapper);

		root = new HorizontalLayout();
		root.setStyleName("menuContent");

		settingTab = new DetachedTabs.Vertical(mySpaceArea);
		settingTab.setSizeFull();
		settingTab.setHeight(null);

		CssLayout menu = new CssLayout();
		menu.setWidth("170px");
		menu.setStyleName("sidebar-menu");
		menu.addComponent(settingTab);

		root.addComponent(menu);
		mySpaceArea.setStyleName("projectTabContent");
		mySpaceArea.setWidth("100%");
		mySpaceArea.setHeight(null);
		root.addComponent(mySpaceArea);
		root.setExpandRatio(mySpaceArea, 1.0f);
		root.setWidth("100%");
		buildComponents();
		contentWrapper.addComponent(root);
	}

	private static class MenuButton extends Button {
		private static final long serialVersionUID = 1L;

		public MenuButton(String caption, String iconResource) {
			super(caption);
			this.setIcon(MyCollabResource.newResource("icons/22/crm/"
					+ iconResource));
			this.setStyleName("link");
		}
	}

	private void buildComponents() {
		settingTab.addTab(constructNotificationSettingView(), new MenuButton(
				"Notifications", "notification.png"));
		settingTab.addTab(constructCustomView(), new MenuButton(
				"Custom Layouts", "layout.png"));

		settingTab.addTabChangedListener(new DetachedTabs.TabChangedListener() {
			@Override
			public void tabChanged(TabChangedEvent event) {
				Button btn = event.getSource();
				String caption = btn.getCaption();
				mySpaceArea.setStyleName("projectTabContent");

				if ("Notifications".equals(caption)) {
					notificationPresenter.go(CrmSettingContainer.this,
							new NotificationSettingScreenData.Read());
				} else if ("Custom Layouts".equals(caption)) {
					customViewPresenter.go(CrmSettingContainer.this,
							new CustomViewScreenData.Read());
				}
			}
		});
	}

	private Component constructNotificationSettingView() {
		notificationPresenter = PresenterResolver
				.getPresenter(CrmNotifcationSettingPresenter.class);
		return notificationPresenter.getView();
	}

	private Component constructCustomView() {
		customViewPresenter = PresenterResolver
				.getPresenter(CrmCustomViewPresenter.class);
		return customViewPresenter.getView();
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {
		// TODO Auto-generated method stub

	}

	public Component gotoSubView(String name) {
		View component = (View) settingTab.selectTab(name);
		return component;
	}

}
