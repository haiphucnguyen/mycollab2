package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class MixValueComboBox extends ComboBox {
    private Class<? extends Enum> enumCls;

    public MixValueComboBox(Class<? extends Enum> enumCls) {
        super();
        this.setPageLength(20);
        this.setNullSelectionAllowed(false);
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        this.enumCls = enumCls;
    }

    public final void addEntry(String value) {
        try {
            Enum anEnum = Enum.valueOf(enumCls, value);
            this.addItem(value);
            this.setItemCaption(value, AppContext.getMessage(anEnum));
        } catch (Exception e) {
            this.addItem(value);
            this.setItemCaption(value, value);
        }
    }
}
