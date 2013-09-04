package com.esofthead.mycollab.module.crm.view.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.localization.WebExceptionI18nEnum;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericListPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.MassUpdatePresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class AccountListPresenter
		extends
		CrmGenericListPresenter<AccountListView, AccountSearchCriteria, SimpleAccount>
		implements MassUpdatePresenter<Account> {

	private static final long serialVersionUID = 1L;

	private AccountService accountService;

	public AccountListPresenter() {
		super(AccountListView.class);
		accountService = AppContext.getSpringBean(AccountService.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					public void onSelect(String id, String caption) {
						super.onSelect(id, caption);

						if ("mail".equals(id)) {
							if (isSelectAll) {
								AppContext
										.getApplication()
										.getMainWindow()
										.showNotification(
												LocalizationHelper
														.getMessage(WebExceptionI18nEnum.NOT_SUPPORT_SENDING_EMAIL_TO_ALL_USERS));
							} else {
								List<String> lstMail = new ArrayList<String>();
								List<SimpleAccount> tableData = view
										.getPagedBeanTable()
										.getCurrentDataList();
								for (SimpleAccount item : tableData) {
									if (item.isSelected()) {
										lstMail.add(item.getAccountname()
												+ " <" + item.getEmail() + ">");
									}
								}
								view.getWidget().getWindow()
										.addWindow(new MailFormWindow(lstMail));
							}
						} else if ("massUpdate".equals(id)) {
							MassUpdateAccountWindow massUpdateWindow = new MassUpdateAccountWindow(
									LocalizationHelper
											.getMessage(
													GenericI18Enum.MASS_UPDATE_WINDOW_TITLE,
													"Account"),
									AccountListPresenter.this);
							view.getWindow().addWindow(massUpdateWindow);
						}
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleAccount.class;
					}

					@Override
					protected String getReportTitle() {
						return "Account List";
					}
				});
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleAccount> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleAccount item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				accountService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			accountService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNTS_HEADER));

			super.onGo(container, data);
			doSearch((AccountSearchCriteria) data.getParams());
			AppContext.addFragment("crm/account/list", LocalizationHelper
					.getMessage(GenericI18Enum.BROWSER_LIST_ITEMS_TITLE,
							"Account"));
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	public void massUpdate(Account value) {
		if (!isSelectAll) {
			Collection<SimpleAccount> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleAccount item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				accountService.massUpdateWithSession(value, keyList,
						AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			accountService.updateBySearchCriteria(value, searchCriteria);
			doSearch(searchCriteria);
		}
	}

	@Override
	public ISearchableService<AccountSearchCriteria> getSearchService() {
		return AppContext.getSpringBean(AccountService.class);
	}
}
