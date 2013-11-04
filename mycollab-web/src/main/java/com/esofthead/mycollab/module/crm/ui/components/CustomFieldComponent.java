package com.esofthead.mycollab.module.crm.ui.components;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class CustomFieldComponent extends CssLayout {

    private static final long serialVersionUID = 1L;

    private AbstractDynaField field;
    private Label fieldNameLbl;
    private String fieldName;
    private Panel fieldEditPanel;
    private PopupButton editFieldBtn;

    public CustomFieldComponent(AbstractDynaField field) {
        this.setStyleName(UIConstants.CUSTOM_FIELD_COMPONENT);
        this.field = field;
        if (field == null) {
            fieldName = "&nbsp;";
            this.addStyleName("emptyField");
        } else {
            fieldName = field.getDisplayName();
        }
        fieldNameLbl = new Label(fieldName);
        if (isEmptyField()) {
            fieldNameLbl.setContentMode(Label.CONTENT_XHTML);
            this.addComponent(fieldNameLbl);
        } else {

            HorizontalLayout fieldWrapper = new HorizontalLayout();
            fieldWrapper.setWidth("100%");
            fieldWrapper.addComponent(fieldNameLbl);
            fieldWrapper.setExpandRatio(fieldNameLbl, 1.0f);

            editFieldBtn = new PopupButton();
            editFieldBtn.setStyleName(UIConstants.POPUP_WITHOUT_INDICATOR);
            editFieldBtn.addStyleName("editFieldBtn");
            editFieldBtn.addStyleName("link");
            editFieldBtn.setIcon(MyCollabResource
                    .newResource("icons/16/edit.png"));

            fieldEditPanel = new Panel();
            VerticalLayout panelLayout = new VerticalLayout();
            panelLayout.setSpacing(true);
            panelLayout.setMargin(true);
            panelLayout.setWidth("100%");
            fieldEditPanel.setContent(panelLayout);
            fieldEditPanel.setStyleName(Reindeer.PANEL_LIGHT);
            fieldEditPanel.setWidth("300px");

            createDefaultEditOption();

            editFieldBtn.addComponent(fieldEditPanel);
            fieldWrapper.addComponent(editFieldBtn);
            this.addComponent(fieldWrapper);
        }
    }

    protected void createDefaultEditOption() {
        final CheckBox isRequired = new CheckBox("Is Required Field",
                field.isRequired());
        fieldEditPanel.addComponent(isRequired);

        Button saveBtn = new Button("Save", new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent arg0) {
                editFieldBtn.setPopupVisible(false);
                CustomFieldComponent.this.setRequired((Boolean) isRequired
                        .getValue());
                CustomFieldComponent.this.requestRepaint();
            }
        });
        saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        fieldEditPanel.addComponent(saveBtn);
        ((VerticalLayout) fieldEditPanel.getContent()).setComponentAlignment(
                saveBtn, Alignment.MIDDLE_CENTER);
    }

    protected void addFieldEditOption(Component comp) {
        fieldEditPanel.addComponent(comp);
    }

    public String getFieldName() {
        return fieldName;
    }

    public boolean isEmptyField() {
        return (field == null);
    }

    public AbstractDynaField getField() {
        return field;
    }

    public void setMandatory(boolean isMandatory) {
        if (isMandatory) {
            fieldNameLbl.setContentMode(Label.CONTENT_XHTML);
            fieldNameLbl.setValue("<img src='"
                    + MyCollabResource.newResourceLink("icons/16/lock.png")
                    + "'>&nbsp;" + fieldName);

        } else if (fieldNameLbl.getContentMode() == Label.CONTENT_XHTML) {
            fieldNameLbl.setContentMode(Label.CONTENT_DEFAULT);
            fieldNameLbl.setValue(fieldName);
        }
    }

    public void setRequired(boolean isRequired) {
        if (isRequired) {
            fieldNameLbl.addStyleName("isRequiredField");
        } else {
            fieldNameLbl.removeStyleName("isRequiredField");
        }
        field.setRequired(isRequired);
    }
}
