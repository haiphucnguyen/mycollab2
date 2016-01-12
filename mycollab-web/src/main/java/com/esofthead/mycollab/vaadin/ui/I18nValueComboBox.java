package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.AppContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.3.0
 */
public class I18nValueComboBox extends ValueComboBox {
    private static final long serialVersionUID = 1L;

    public I18nValueComboBox() {
        super();
    }

    public I18nValueComboBox(boolean nullIsAllowable, Enum<?>... keys) {
        this();
        setNullSelectionAllowed(nullIsAllowable);
        loadData(Arrays.asList(keys));
    }

    public final void loadData(List<? extends Enum<?>> values) {
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);

        for (Enum<?> entry : values) {
            this.addItem(entry.name());
            this.setItemCaption(entry.name(), AppContext.getMessage(entry));
        }

        if (!this.isNullSelectionAllowed()) {
            this.select(this.getItemIds().iterator().next());
        }
    }
}