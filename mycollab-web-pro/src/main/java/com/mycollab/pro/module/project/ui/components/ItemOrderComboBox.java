package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ItemCaptionGenerator;

import static com.mycollab.db.arguments.Order.ASCENDING;
import static com.mycollab.db.arguments.Order.DESCENDING;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class ItemOrderComboBox extends ComboBox<Enum> {
    private static final long serialVersionUID = 1L;

    public ItemOrderComboBox() {
        this.setItems(ASCENDING, DESCENDING);
        this.setEmptySelectionAllowed(false);
        this.setItemCaptionGenerator((ItemCaptionGenerator<Enum>) order -> {
            if (order == ASCENDING) {
                return UserUIContext.getMessage(GenericI18Enum.OPT_SORT_ASCENDING);
            } else {
                return UserUIContext.getMessage(GenericI18Enum.OPT_SORT_DESCENDING);
            }
        });
    }
}
