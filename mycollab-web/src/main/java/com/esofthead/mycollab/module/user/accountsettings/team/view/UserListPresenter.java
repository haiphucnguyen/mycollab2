/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_USER, impliedPermissionVal = AccessPermissionFlag.READ_ONLY)
public class UserListPresenter extends
		ListSelectionPresenter<UserListView, UserSearchCriteria, SimpleUser> {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public UserListPresenter() {
		super(UserListView.class);
		userService = ApplicationContextUtil.getSpringBean(UserService.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					protected void onSelectExtra(String id, String caption) {
						if (PopupActionHandler.MAIL_ACTION.equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
						}
					}

					@Override
					protected String getReportTitle() {
						return "User List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleUser.class;
					}
				});
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleUser> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<String> keyList = new ArrayList<String>();
			for (SimpleUser item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getUsername());
				}
			}

			if (keyList.size() > 0) {
				userService.updateUserAccountsStatus(keyList,
						AppContext.getAccountId(),
						RegisterStatusConstants.DELETE);
				doSearch(searchCriteria);
			}
		} else {
			userService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		UserContainer userContainer = (UserContainer) container;
		userContainer.removeAllComponents();
		userContainer.addComponent(view.getWidget());
		doSearch((UserSearchCriteria) data.getParams());

		AccountSettingBreadcrumb breadcrumb = ViewManager
				.getView(AccountSettingBreadcrumb.class);
		breadcrumb.gotoUserList();
	}

	@Override
	public ISearchableService<UserSearchCriteria> getSearchService() {
		return ApplicationContextUtil.getSpringBean(UserService.class);
	}

}
