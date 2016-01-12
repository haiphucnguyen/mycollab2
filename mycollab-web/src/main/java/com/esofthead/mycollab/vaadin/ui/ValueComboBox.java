package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.ComboBox;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ValueComboBox extends ComboBox {
    private static final long serialVersionUID = 1L;

    public ValueComboBox() {
        super();
        this.setPageLength(20);
    }

    /**
     * @param nullIsAllowable
     * @param values
     */
    public ValueComboBox(boolean nullIsAllowable, String... values) {
        this();
        this.setNullSelectionAllowed(nullIsAllowable);
        this.setImmediate(true);
        this.loadData(values);

        this.select(this.getItemIds().iterator().next());
    }

    public final void loadData(String... values) {
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);

        for (int i = 0; i < values.length; i++) {
            this.addItem(values[i]);
        }

        if (!this.isNullSelectionAllowed()) {
            this.select(this.getItemIds().iterator().next());
        }
    }
}
