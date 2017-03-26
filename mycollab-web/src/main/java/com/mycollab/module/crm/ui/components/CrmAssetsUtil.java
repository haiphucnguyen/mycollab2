package com.mycollab.module.crm.ui.components;

import com.mycollab.configuration.StorageFactory;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.project.i18n.ClientI18nEnum;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * @author MyCollab Ltd
 * @since 5.4.8
 */
public class CrmAssetsUtil {

    public static Component buildAccountLogo(SimpleAccount account, int size) {
        AbstractComponent wrapper;
        if (!StringUtils.isBlank(account.getAvatarid())) {
            wrapper = new Image(null, new ExternalResource(StorageFactory.getEntityLogoPath(MyCollabUI
                    .getAccountId(), account.getAvatarid(), 100)));
        } else {
            String accountName = account.getAccountname();
            accountName = (accountName.length() > 3) ? accountName.substring(0, 3) : accountName;
            ELabel projectIcon = new ELabel(accountName).withStyleName(UIConstants.TEXT_ELLIPSIS, "center");
            wrapper = new VerticalLayout();
            ((VerticalLayout) wrapper).addComponent(projectIcon);
            ((VerticalLayout) wrapper).setComponentAlignment(projectIcon, Alignment.MIDDLE_CENTER);
        }
        wrapper.setWidth(size, Sizeable.Unit.PIXELS);
        wrapper.setHeight(size, Sizeable.Unit.PIXELS);
        wrapper.addStyleName(UIConstants.CIRCLE_BOX);
        wrapper.setDescription(UserUIContext.getMessage(ClientI18nEnum.OPT_CHANGE_LOGO_HELP, UserUIContext.getMessage(ClientI18nEnum.EDIT)));
        return wrapper;
    }
}
