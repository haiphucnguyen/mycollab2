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

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.ICustomizeContainer;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent.ResetTheme;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent.SaveTheme;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.Page;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class ThemeCustomizePresenter extends
		AbstractPresenter<ThemeCustomizeView> {
	private static final long serialVersionUID = 5330315328389778202L;

	private AccountThemeService themeService;

	public ThemeCustomizePresenter() {
		super(ThemeCustomizeView.class);
		themeService = ApplicationContextUtil
				.getSpringBean(AccountThemeService.class);
	}

	@Override
	protected void postInitView() {
		EventBusFactory
				.getInstance()
				.register(
						new ApplicationEventListener<AccountCustomizeEvent.SaveTheme>() {
							private static final long serialVersionUID = -1060182248184670399L;

							@Subscribe
							@Override
							public void handle(SaveTheme event) {
								if (event.getData() instanceof AccountTheme) {
									saveTheme((AccountTheme) event.getData());
									Page.getCurrent()
											.getJavaScript()
											.execute(
													"window.location.reload();");
								}
							}
						});
		EventBusFactory
				.getInstance()
				.register(
						new ApplicationEventListener<AccountCustomizeEvent.ResetTheme>() {
							private static final long serialVersionUID = 1594676526731151824L;

							@Subscribe
							@Override
							public void handle(ResetTheme event) {
								ConfirmDialogExt.show(
										UI.getCurrent(),
										"Reset to Default Theme",
										"This action will reset all your customizations to default. Are you really want to do this?",
										AppContext
												.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
										AppContext
												.getMessage(GenericI18Enum.BUTTON_CANCEL),
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 2086515060473457749L;

											@Override
											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													AccountTheme defaultTheme = themeService
															.getDefaultTheme();
													defaultTheme.setId(null);
													themeService
															.saveAccountTheme(
																	defaultTheme,
																	AppContext
																			.getAccountId());
													view.customizeTheme(defaultTheme);
													Page.getCurrent()
															.getJavaScript()
															.execute(
																	"window.location.reload();");
												}
											}
										});
							}
						});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ICustomizeContainer customizeContainer = (ICustomizeContainer) container;
		customizeContainer.removeAllComponents();
		customizeContainer.addComponent(view.getWidget());

		AccountTheme accountTheme = null;
		if (data.getParams() == null) {
			accountTheme = themeService.getAccountTheme(AppContext
					.getAccountId());
		} else {
			accountTheme = (AccountTheme) data.getParams();
		}

		view.customizeTheme(accountTheme);

		AccountSettingBreadcrumb breadcrumb = ViewManager
				.getCacheComponent(AccountSettingBreadcrumb.class);
		breadcrumb.gotoCustomizationPage();
	}

	private void saveTheme(AccountTheme accountTheme) {
		themeService.saveAccountTheme(accountTheme, AppContext.getAccountId());
	}
}
