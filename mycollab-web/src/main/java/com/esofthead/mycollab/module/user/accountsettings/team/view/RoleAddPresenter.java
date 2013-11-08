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

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_ROLE, impliedPermissionVal = AccessPermissionFlag.READ_WRITE)
public class RoleAddPresenter extends AbstractPresenter<RoleAddView> {
	private static final long serialVersionUID = 1L;

	public RoleAddPresenter() {
		super(RoleAddView.class);

		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Role>() {
			@Override
			public void onSave(final Role item) {
				save(item);
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new RoleEvent.GotoList(this, null));
				}
			}

			@Override
			public void onCancel() {
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new RoleEvent.GotoList(this, null));
				}
			}

			@Override
			public void onSaveAndNew(Role item) {
				save(item);
				EventBus.getInstance().fireEvent(
						new RoleEvent.GotoAdd(this, null));
			}
		});
	}

	public void save(Role item) {
		RoleService roleService = ApplicationContextUtil
				.getSpringBean(RoleService.class);
		item.setSaccountid(AppContext.getAccountId());

		if (item.getId() == null) {
			roleService.saveWithSession(item, AppContext.getUsername());
		} else {
			roleService.updateWithSession(item, AppContext.getUsername());
		}

		roleService.savePermission(item.getId(), view.getPermissionMap(),
				item.getSaccountid());

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.ROLES)) {
			RoleContainer roleContainer = (RoleContainer) container;
			roleContainer.removeAllComponents();
			roleContainer.addComponent(view.getWidget());

			Role role = (Role) data.getParams();
			view.editItem(role);

			AccountSettingBreadcrumb breadcrumb = ViewManager
					.getView(AccountSettingBreadcrumb.class);

			if (role.getId() == null) {
				breadcrumb.gotoRoleAdd();
			} else {
				breadcrumb.gotoRoleEdit(role);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
