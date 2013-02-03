package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
public class ValueComboBox extends ComboBox {

    public ValueComboBox() {
        super();
    }

    /**
     *
     * @param nullIsAllowable
     * @param values
     */
    public ValueComboBox(boolean nullIsAllowable, String... values) {
        super();
        this.setNullSelectionAllowed(nullIsAllowable);
        this.setImmediate(true);
        this.loadData(values);
        
        this.select(this.getItemIds().iterator().next());
    }
    
    public ValueComboBox(boolean nullIsAllowable, Number... values) {
        super();
        this.setNullSelectionAllowed(nullIsAllowable);
        this.setImmediate(true);
        this.loadData(values);
        
        this.select(this.getItemIds().iterator().next());
    }

    public final void loadData(String... values) {
        this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

        for (int i = 0; i < values.length; i++) {
            this.addItem(values[i]);
        }
    }
    
    public final void loadData(Number... values) {
        this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

        for (int i = 0; i < values.length; i++) {
            this.addItem(values[i]);
        }
    }
}
