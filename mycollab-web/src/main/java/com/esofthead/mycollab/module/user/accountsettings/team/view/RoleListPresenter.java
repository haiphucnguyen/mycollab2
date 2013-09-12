/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class RoleListPresenter extends
		ListSelectionPresenter<RoleListView, RoleSearchCriteria, SimpleRole> {
	private static final long serialVersionUID = 1L;

	private RoleService roleService;

	public RoleListPresenter() {
		super(RoleListView.class);
		roleService = AppContext.getSpringBean(RoleService.class);

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
						return "Role List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleRole.class;
					}
				});
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleRole> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (Role item : currentDataList) {
				if (item.isSelected()
						&& (item.getIssystemrole() == null || item
								.getIssystemrole() == Boolean.FALSE)) {
					keyList.add(item.getId());
				} else {
					MessageBox mb = new MessageBox(
							AppContext.getApplication().getMainWindow(),
							LocalizationHelper
									.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
							MessageBox.Icon.WARN,
							"Can not delete role " + item.getRolename()
									+ " because it is the system role.",
							new MessageBox.ButtonConfig(
									ButtonType.OK,
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_OK_LABEL)));
					mb.show();
				}
			}

			if (keyList.size() > 0) {
				roleService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			roleService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.USER_ROLE)) {
			RoleContainer roleContainer = (RoleContainer) container;
			roleContainer.removeAllComponents();
			roleContainer.addComponent(view.getWidget());
			RoleSearchCriteria roleSearchCriteria = (RoleSearchCriteria) data
					.getParams();
			doSearch(roleSearchCriteria);

			AccountSettingBreadcrumb breadcrumb = ViewManager
					.getView(AccountSettingBreadcrumb.class);
			breadcrumb.gotoRoleList();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	public ISearchableService<RoleSearchCriteria> getSearchService() {
		return AppContext.getSpringBean(RoleService.class);
	}
}
