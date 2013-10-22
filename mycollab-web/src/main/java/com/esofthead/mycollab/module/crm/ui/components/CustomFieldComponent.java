package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class CustomFieldComponent extends CssLayout {

    private static final long serialVersionUID = 1L;

    private Label fieldNameLbl;

    public CustomFieldComponent(String fieldName) {
        this.setStyleName(UIConstants.CUSTOM_FIELD_COMPONENT);
        fieldNameLbl = new Label(fieldName);

        this.addComponent(fieldNameLbl);
    }

}
