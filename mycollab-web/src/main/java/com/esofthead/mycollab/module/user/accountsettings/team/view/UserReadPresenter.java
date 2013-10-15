/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_USER, impliedPermissionVal = AccessPermissionFlag.READ_ONLY)
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
					public void onDelete(final User data) {
						ConfirmDialogExt.show(
								view.getWindow(),
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
											UserService userService = ApplicationContextUtil
													.getSpringBean(UserService.class);
											userService.pendingUserAccount(
													data.getUsername(),
													AppContext.getAccountId());
											EventBus.getInstance().fireEvent(
													new UserEvent.GotoList(
															this, null));
										}
									}
								});

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

			UserService userService = ApplicationContextUtil
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
						MessageBox.Icon.WARN,
						"There is no user " + username + " in this account",
						new MessageBox.ButtonConfig(
								ButtonType.OK,
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_OK_LABEL)));
				mb.show();
			}

		} else {
			MessageConstants.showMessagePermissionAlert();
		}

	}
}
