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
package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.events.AccountEvent;
import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.mobile.utils.NotificationUtil;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class AccountReadPresenter extends AbstractPresenter<AccountReadView> {

	private static final long serialVersionUID = 1L;

	public AccountReadPresenter() {
		super(AccountReadView.class);
	}

	@Override
	protected void postInitView() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleAccount>() {
					@Override
					public void onEdit(SimpleAccount data) {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final SimpleAccount data) {
						/*ConfirmDialogExt.show(
								UI.getCurrent(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											AccountService accountService = ApplicationContextUtil
													.getSpringBean(AccountService.class);
											accountService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											EventBus.getInstance().fireEvent(
													new AccountEvent.GotoList(
															this, null));
										}
									}
								});*/

					}

					@Override
					public void onClone(SimpleAccount data) {
						Account cloneData = (Account) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(SimpleAccount data) {
						AccountService accountService = ApplicationContextUtil
								.getSpringBean(AccountService.class);
						AccountSearchCriteria criteria = new AccountSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = accountService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(SimpleAccount data) {
						AccountService accountService = ApplicationContextUtil
								.getSpringBean(AccountService.class);
						AccountSearchCriteria criteria = new AccountSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = accountService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoFirstRecordNotification();
						}
					}
				});

		
	}

	@Override
	protected void onGo(MobileNavigationManager container, ScreenData<?> data) {

		if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {

			if (data.getParams() instanceof Integer) {
				AccountService accountService = ApplicationContextUtil
						.getSpringBean(AccountService.class);
				SimpleAccount account = accountService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (account != null) {
					container.navigateTo(view.getWidget());
					view.previewItem(account);
					AppContext.addFragment(CrmLinkGenerator
							.generateAccountPreviewLink(account.getId()),
							LocalizationHelper.getMessage(
									GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE,
									"Account", account.getAccountname()));
				} else {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
