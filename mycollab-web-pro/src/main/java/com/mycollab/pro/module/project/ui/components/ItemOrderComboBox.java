package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.Order;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.ComboBox;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class ItemOrderComboBox extends ComboBox {
    private static final long serialVersionUID = 1L;

    public ItemOrderComboBox() {
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
        this.setNullSelectionAllowed(false);
        this.addItem(Order.ASCENDING);
        this.setItemCaption(Order.ASCENDING, UserUIContext.getMessage(GenericI18Enum.OPT_SORT_ASCENDING));

        this.addItem(Order.DESCENDING);
        this.setItemCaption(Order.DESCENDING, UserUIContext.getMessage(GenericI18Enum.OPT_SORT_DESCENDING));
        this.select(Order.ASCENDING);
    }
}
