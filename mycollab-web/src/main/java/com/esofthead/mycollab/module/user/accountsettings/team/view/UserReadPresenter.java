/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;

/**
 * 
 * @author haiphucnguyen
 */
public class UserReadPresenter extends AbstractPresenter<UserReadView> {
	private static final long serialVersionUID = 1L;

	public UserReadPresenter() {
		super(UserReadView.class);

		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<User>() {
					@Override
					public void onEdit(User data) {
						EventBus.getInstance().fireEvent(
								new UserEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(User data) {
						UserService userService = AppContext
								.getSpringBean(UserService.class);
						userService.removeUserAccount(data.getUsername(),
								AppContext.getAccountId());
						EventBus.getInstance().fireEvent(
								new UserEvent.GotoList(this, null));
					}

					@Override
					public void onClone(User data) {
						User cloneData = (User) data.copy();
						cloneData.setUsername(null);
						EventBus.getInstance().fireEvent(
								new UserEvent.GotoAdd(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new UserEvent.GotoList(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
			String username = (String) data.getParams();

			UserService userService = AppContext
					.getSpringBean(UserService.class);
			SimpleUser user = userService.findUserByUserNameInAccount(username,
					AppContext.getAccountId());
			if (user != null) {
				UserContainer userContainer = (UserContainer) container;
				userContainer.removeAllComponents();
				userContainer.addComponent(view.getWidget());
				view.previewItem(user);

				AccountSettingBreadcrumb breadcrumb = ViewManager
						.getView(AccountSettingBreadcrumb.class);
				breadcrumb.gotoUserRead(user);
			} else {
				MessageBox mb = new MessageBox(
						AppContext.getApplication().getMainWindow(),
						LocalizationHelper
								.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
						MessageBox.Icon.WARN, "There is no user " + username
								+ " in this account",
						new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				mb.show();
			}

		} else {
			MessageConstants.showMessagePermissionAlert();
		}

	}
}
