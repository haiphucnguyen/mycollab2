package com.esofthead.mycollab.vaadin.web.ui;

import com.vaadin.ui.ComboBox;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class StyleComboBox extends ComboBox {
    private String styleName;
    @Override
    protected void setInternalValue(Object newValue) {
        ItemStyleGenerator itemStyleGenerator = this.getItemStyleGenerator();
        if (itemStyleGenerator != null) {
            String style = itemStyleGenerator.getStyle(this, newValue);
            if (styleName != null) {
                this.removeStyleName(styleName);
            }
            styleName = style;
            this.addStyleName(styleName);
        }
        super.setInternalValue(newValue);
    }
}
