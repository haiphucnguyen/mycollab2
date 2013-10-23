package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class CustomFieldComponent extends CssLayout {

    private static final long serialVersionUID = 1L;

    private Label fieldNameLbl;
    private String _fieldName;

    public CustomFieldComponent(String fieldName) {
        this.setStyleName(UIConstants.CUSTOM_FIELD_COMPONENT);
        if (fieldName == null) {
            _fieldName = "";
            this.addStyleName("emptyField");
        } else {
            _fieldName = fieldName;
        }
        fieldNameLbl = new Label(_fieldName);

        this.addComponent(fieldNameLbl);
    }

    public String getFieldName() {
        return _fieldName;
    }

    public void setMandatory(Boolean isMandatory) {

        if (isMandatory) {
            fieldNameLbl.setContentMode(Label.CONTENT_XHTML);
            fieldNameLbl.setValue("<img src='"
                    + MyCollabResource.newResourceLink("icons/16/lock.png")
                    + "'>&nbsp;" + _fieldName);

        } else if (fieldNameLbl.getContentMode() == Label.CONTENT_XHTML) {
            fieldNameLbl.setContentMode(Label.CONTENT_DEFAULT);
            fieldNameLbl.setValue(_fieldName);
        }
    }
}
