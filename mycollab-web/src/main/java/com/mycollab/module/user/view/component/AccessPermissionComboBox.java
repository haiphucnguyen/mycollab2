package com.mycollab.module.user.view.component;

import com.mycollab.common.i18n.SecurityI18nEnum;
import com.mycollab.security.AccessPermissionFlag;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.KeyCaptionComboBox;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class AccessPermissionComboBox extends KeyCaptionComboBox {
    private static final long serialVersionUID = 1L;

    public AccessPermissionComboBox() {
        super(false);

        this.addItem(AccessPermissionFlag.Companion.getNO_ACCESS(), UserUIContext.getMessage(SecurityI18nEnum.NO_ACCESS));
        this.addItem(AccessPermissionFlag.Companion.getREAD_ONLY(), UserUIContext.getMessage(SecurityI18nEnum.READONLY));
        this.addItem(AccessPermissionFlag.Companion.getREAD_WRITE(), UserUIContext.getMessage(SecurityI18nEnum.READ_WRITE));
        this.addItem(AccessPermissionFlag.Companion.getACCESS(), UserUIContext.getMessage(SecurityI18nEnum.ACCESS));
        this.setValue(AccessPermissionFlag.Companion.getREAD_ONLY());
    }
}
