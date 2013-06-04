package com.esofthead.mycollab.module.crm.view.account;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class AccountAddPresenter extends CrmGenericPresenter<AccountAddView> {

	private static final long serialVersionUID = 1L;

	public AccountAddPresenter() {
		super(AccountAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Account>() {
					@Override
					public void onSave(final Account account) {
						saveAccount(account);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Account account) {
						saveAccount(account);
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNTS_HEADER));

			Account account = null;
			if (data.getParams() instanceof Account) {
				account = (Account) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				AccountService accountService = AppContext
						.getSpringBean(AccountService.class);
				account = accountService.findByPrimaryKey((Integer) data
						.getParams());
				if (account == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			}

			super.onGo(container, data);
			view.editItem(account);
			if (account.getId() == null) {
				AppContext.addFragment("crm/account/add", "Add Account");

			} else {
				AppContext.addFragment(
						"crm/account/edit/"
								+ UrlEncodeDecoder.encode(account.getId()),
						"Edit Account: " + account.getAccountname());
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	public void saveAccount(Account account) {
		AccountService accountService = AppContext
				.getSpringBean(AccountService.class);

		account.setSaccountid(AppContext.getAccountId());
		if (account.getId() == null) {
			accountService.saveWithSession(account, AppContext.getUsername());

			if (account.getExtraData() != null
					&& account.getExtraData() instanceof SimpleCampaign) {
				CampaignAccount assoAccount = new CampaignAccount();
				assoAccount.setAccountid(account.getId());
				assoAccount.setCampaignid(((SimpleCampaign) account
						.getExtraData()).getId());
				assoAccount.setCreatedtime(new GregorianCalendar().getTime());

				CampaignService campaignService = AppContext
						.getSpringBean(CampaignService.class);
				campaignService.saveCampaignAccountRelationship(Arrays
						.asList(assoAccount));
			}
		} else {
			accountService.updateWithSession(account, AppContext.getUsername());
		}

	}
}
