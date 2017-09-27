package com.mycollab.module.user.accountsettings.team.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.domain.User;
import com.mycollab.module.user.event.UserEvent;
import com.mycollab.module.user.service.UserService;
import com.mycollab.security.AccessPermissionFlag;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewPermission;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_USER, impliedPermissionVal = AccessPermissionFlag.READ_ONLY)
public class UserReadPresenter extends AbstractPresenter<UserReadView> {
    private static final long serialVersionUID = 1L;

    public UserReadPresenter() {
        super(UserReadView.class);
    }

    @Override
    protected void postInitView() {
        view.getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<User>() {
            @Override
            public void onAdd(User data) {
                EventBusFactory.getInstance().post(new UserEvent.GotoAdd(this, null));
            }

            @Override
            public void onEdit(User data) {
                EventBusFactory.getInstance().post(new UserEvent.GotoEdit(this, data));
            }

            @Override
            public void onDelete(final User data) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                UserService userService = AppContextUtil.getSpringBean(UserService.class);
                                userService.pendingUserAccount(data.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new UserEvent.GotoList(this, null));
                            }
                        });
            }

            @Override
            public void onClone(User data) {
                User cloneData = (User) data.copy();
                cloneData.setUsername(null);
                EventBusFactory.getInstance().post(new UserEvent.GotoAdd(this, cloneData));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new UserEvent.GotoList(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canRead(RolePermissionCollections.ACCOUNT_USER)) {
            String username = (String) data.getParams();

            UserService userService = AppContextUtil.getSpringBean(UserService.class);
            SimpleUser user = userService.findUserByUserNameInAccount(username, AppUI.getAccountId());
            if (user != null) {
                UserContainer userContainer = (UserContainer) container;
                userContainer.removeAllComponents();
                userContainer.addComponent(view);
                view.previewItem(user);

                AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
                breadcrumb.gotoUserRead(user);
            } else {
                NotificationUtil.showErrorNotification(UserUIContext.getMessage(UserI18nEnum.ERROR_NO_USER_IN_ACCOUNT, username));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
