package com.esofthead.mycollab.pro.module.project.ui.components;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.Order;
import com.esofthead.mycollab.vaadin.AppContext;
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
        this.setItemCaption(Order.ASCENDING, AppContext.getMessage(GenericI18Enum.OPT_SORT_ASCENDING));

        this.addItem(Order.DESCENDING);
        this.setItemCaption(Order.DESCENDING, AppContext.getMessage(GenericI18Enum.OPT_SORT_DESCENDING));
        this.select(Order.ASCENDING);
    }
}
