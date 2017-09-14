package com.mycollab.module.crm.ui.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.view.account.AccountLogoUpdateWindow;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * @author MyCollab Ltd
 * @since 5.4.8
 */
public class CrmAssetsUtil {

    public static Component accountLogoComp(SimpleAccount account, int size) {
        AbstractComponent wrapper;
        if (!StringUtils.isBlank(account.getAvatarid())) {
            wrapper = new Image(null, new ExternalResource(StorageUtils.getEntityLogoPath(AppUI
                    .getAccountId(), account.getAvatarid(), 100)));
        } else {
            String accountName = account.getAccountname();
            accountName = (accountName.length() > 3) ? accountName.substring(0, 3) : accountName;
            ELabel accountIcon = new ELabel(accountName).withStyleName(UIConstants.TEXT_ELLIPSIS, "center");
            accountIcon.setWidth(size, Sizeable.Unit.PIXELS);
            accountIcon.setHeight(size, Sizeable.Unit.PIXELS);
            wrapper = new VerticalLayout();
            ((VerticalLayout) wrapper).addComponent(accountIcon);
            ((VerticalLayout) wrapper).setComponentAlignment(accountIcon, Alignment.MIDDLE_CENTER);
        }
        wrapper.setWidth(size, Sizeable.Unit.PIXELS);
        wrapper.setHeight(size, Sizeable.Unit.PIXELS);
        wrapper.addStyleName(UIConstants.CIRCLE_BOX);
        wrapper.setDescription(UserUIContext.getMessage(GenericI18Enum.OPT_CHANGE_IMAGE));
        return wrapper;
    }

    public static Component editableAccountLogoComp(SimpleAccount account, int size) {
        VerticalLayout wrapper = new VerticalLayout();

        if (UserUIContext.canWrite(RolePermissionCollections.CRM_ACCOUNT)) {
            wrapper.addStyleName("cursor_pointer");
            wrapper.setDescription(UserUIContext.getMessage(GenericI18Enum.OPT_CHANGE_IMAGE));
            wrapper.addLayoutClickListener((LayoutEvents.LayoutClickListener) layoutClickEvent ->
                    UI.getCurrent().addWindow(new AccountLogoUpdateWindow(account)));
        }

        if (!StringUtils.isBlank(account.getAvatarid())) {
            Image image = new Image(null, new ExternalResource(StorageUtils.getEntityLogoPath(AppUI.getAccountId(), account.getAvatarid(), size)));
            image.addStyleName(UIConstants.CIRCLE_BOX);
            wrapper.addComponent(image);
        } else {
            String accountName = account.getAccountname();
            accountName = (accountName.length() > 3) ? accountName.substring(0, 3) : accountName;
            ELabel accountIcon = new ELabel(accountName).withStyleName(UIConstants.TEXT_ELLIPSIS, "center");
            accountIcon.setWidth(size, Sizeable.Unit.PIXELS);
            accountIcon.setHeight(size, Sizeable.Unit.PIXELS);
            accountIcon.addStyleName(UIConstants.CIRCLE_BOX);
            wrapper.addComponent(accountIcon);
            wrapper.setComponentAlignment(accountIcon, Alignment.MIDDLE_CENTER);
        }
        wrapper.setWidth(size, Sizeable.Unit.PIXELS);
        wrapper.setHeight(size, Sizeable.Unit.PIXELS);
        return wrapper;
    }
}
