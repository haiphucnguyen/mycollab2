/**
 * This file is part of mycollab-web-premium.
 *
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.ICustomizeContainer;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent.SaveTheme;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class ThemeCustomizePresenter extends
		AbstractPresenter<ThemeCustomizeView> {
	private static final long serialVersionUID = 5330315328389778202L;

	public ThemeCustomizePresenter() {
		super(ThemeCustomizeView.class);
	}

	@Override
	protected void postInitView() {
		EventBus.getInstance()
				.addListener(
						new ApplicationEventListener<AccountCustomizeEvent.SaveTheme>() {
							private static final long serialVersionUID = -1060182248184670399L;

							@Override
							public Class<? extends ApplicationEvent> getEventType() {
								return AccountCustomizeEvent.SaveTheme.class;
							}

							@Override
							public void handle(SaveTheme event) {
								if (event.getData() instanceof AccountTheme) {
									saveTheme((AccountTheme) event.getData());
									NotificationUtil
											.showNotification("Save theme successfully! Please sign in again to see changes.");
								}
							}
						});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ICustomizeContainer customizeContainer = (ICustomizeContainer) container;
		customizeContainer.removeAllComponents();
		customizeContainer.addComponent(view.getWidget());

		AccountThemeService themeService = ApplicationContextUtil
				.getSpringBean(AccountThemeService.class);

		AccountTheme accountTheme = null;
		if (data.getParams() == null) {
			accountTheme = themeService.getAccountTheme(AppContext
					.getAccountId());
		} else {
			accountTheme = (AccountTheme) data.getParams();
		}

		view.customizeTheme(accountTheme);

		AccountSettingBreadcrumb breadcrumb = ViewManager
				.getView(AccountSettingBreadcrumb.class);
		breadcrumb.gotoCustomizationPage();
	}

	private void saveTheme(AccountTheme accountTheme) {
		AccountThemeService service = ApplicationContextUtil
				.getSpringBean(AccountThemeService.class);
		service.saveAccountTheme(accountTheme, AppContext.getAccountId());
	}
}
