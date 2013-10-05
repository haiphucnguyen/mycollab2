/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class RoleReadPresenter extends AbstractPresenter<RoleReadView> {
	private static final long serialVersionUID = 1L;

	public RoleReadPresenter() {
		super(RoleReadView.class);

		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Role>() {
					@Override
					public void onEdit(Role data) {
						EventBus.getInstance().fireEvent(
								new RoleEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(Role data) {
						RoleService roleService = ApplicationContextUtil
								.getSpringBean(RoleService.class);
						roleService.removeWithSession(data.getId(),
								AppContext.getUsername(),
								AppContext.getAccountId());
						EventBus.getInstance().fireEvent(
								new RoleEvent.GotoList(this, null));
					}

					@Override
					public void onClone(Role data) {
						Role cloneData = (Role) data.copy();
						cloneData.setRolename(null);
						EventBus.getInstance().fireEvent(
								new RoleEvent.GotoAdd(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new RoleEvent.GotoList(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.ACCOUNT_ROLE)) {
			RoleService roleService = ApplicationContextUtil
					.getSpringBean(RoleService.class);
			SimpleRole role = roleService.findById((Integer) data.getParams(),
					AppContext.getAccountId());
			if (role != null) {
				RoleContainer roleContainer = (RoleContainer) container;
				roleContainer.removeAllComponents();
				roleContainer.addComponent(view.getWidget());
				view.previewItem(role);

				AccountSettingBreadcrumb breadcrumb = ViewManager
						.getView(AccountSettingBreadcrumb.class);
				breadcrumb.gotoRoleRead(role);
			} else {
				AppContext
						.getApplication()
						.getMainWindow()
						.showNotification(
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
								Window.Notification.TYPE_HUMANIZED_MESSAGE);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
