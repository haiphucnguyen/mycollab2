/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.events.AccountEvent;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class AccountAddPresenter extends
		AbstractMobilePresenter<AccountAddView> {
	private static final long serialVersionUID = -3664699848882470039L;

	public AccountAddPresenter() {
		super(AccountAddView.class);
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<SimpleAccount>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final SimpleAccount account) {
						saveAccount(account);
						EventBusFactory.getInstance().post(
								new ShellEvent.NavigateBack(this, null));
					}

					@Override
					public void onCancel() {
					}

					@Override
					public void onSaveAndNew(final SimpleAccount account) {
						saveAccount(account);
						EventBusFactory.getInstance().post(
								new AccountEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT)) {

			SimpleAccount account = null;
			if (data.getParams() instanceof SimpleAccount) {
				account = (SimpleAccount) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				AccountService accountService = ApplicationContextUtil
						.getSpringBean(AccountService.class);
				account = accountService.findById((Integer) data.getParams(),
						AppContext.getAccountId());
			}
			if (account == null) {
				NotificationUtil.showRecordNotExistNotification();
				return;
			}
			super.onGo(container, data);
			view.editItem(account);
			if (account.getId() == null) {
				AppContext.addFragment("crm/account/add", AppContext
						.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
								"Account"));

			} else {
				AppContext.addFragment(
						"crm/account/edit/"
								+ UrlEncodeDecoder.encode(account.getId()),
						AppContext.getMessage(
								GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
								"Account", account.getAccountname()));
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

	private void saveAccount(Account account) {
		AccountService accountService = ApplicationContextUtil
				.getSpringBean(AccountService.class);

		account.setSaccountid(AppContext.getAccountId());
		if (account.getId() == null) {
			accountService.saveWithSession(account, AppContext.getUsername());
		} else {
			accountService.updateWithSession(account, AppContext.getUsername());
		}

	}

}
