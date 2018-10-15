package com.mycollab.pro.module.crm.view.setting.customlayout;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.builder.type.AbstractDynaField;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
// TODO
public class CustomFieldComponent extends CssLayout {
    private static final long serialVersionUID = 1L;

    private AbstractDynaField field;
    private Label fieldNameLbl;
    private String fieldName;
    private Panel fieldEditPanel;
    private MVerticalLayout panelContentLayout;
    private PopupButton editFieldBtn;

    public CustomFieldComponent(AbstractDynaField field) {
        this.setStyleName(WebThemes.CUSTOM_FIELD_COMPONENT);
        this.field = field;
        if (field == null) {
            fieldName = "&nbsp;";
            this.addStyleName("emptyField");
        } else {
//            fieldName = field.getDisplayName();
        }
        fieldNameLbl = new Label(fieldName);
        if (isEmptyField()) {
            fieldNameLbl.setContentMode(ContentMode.HTML);
            this.addComponent(fieldNameLbl);
        } else {
            MHorizontalLayout fieldWrapper = new MHorizontalLayout().withSpacing(false).withFullWidth();
            fieldWrapper.with(fieldNameLbl).expand(fieldNameLbl);

            editFieldBtn = new PopupButton();
            editFieldBtn.setStyleName(WebThemes.POPUP_WITHOUT_INDICATOR);
            editFieldBtn.addStyleName("editFieldBtn");
            editFieldBtn.addStyleName(WebThemes.BUTTON_ICON_ONLY);
            editFieldBtn.setIcon(FontAwesome.EDIT);

            fieldEditPanel = new Panel();
            panelContentLayout = new MVerticalLayout().withFullWidth();
            fieldEditPanel.setContent(panelContentLayout);
//            fieldEditPanel.setStyleName(Reindeer.PANEL_LIGHT);
            fieldEditPanel.setWidth("300px");

            createDefaultEditOption();

            editFieldBtn.setContent(fieldEditPanel);
            fieldWrapper.addComponent(editFieldBtn);
            this.addComponent(fieldWrapper);
        }
    }

    protected void createDefaultEditOption() {
        final CheckBox isRequired = new CheckBox("Is Required Field",
                field.isRequired());
        panelContentLayout.addComponent(isRequired);

        Button saveBtn = new Button(
                UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent arg0) {
                editFieldBtn.setPopupVisible(false);
                CustomFieldComponent.this.setRequired(isRequired
                        .getValue());
            }
        });
        saveBtn.setStyleName(WebThemes.BUTTON_ACTION);
        saveBtn.setIcon(FontAwesome.SAVE);
        panelContentLayout.addComponent(saveBtn);
        ((VerticalLayout) fieldEditPanel.getContent()).setComponentAlignment(
                saveBtn, Alignment.MIDDLE_CENTER);
    }

    protected void addFieldEditOption(Component comp) {
        panelContentLayout.addComponent(comp);
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
            fieldNameLbl.setContentMode(ContentMode.HTML);
            fieldNameLbl.setValue(FontAwesome.LOCK.getHtml() + " "
                    + fieldName);

        } else if (fieldNameLbl.getContentMode() == ContentMode.HTML) {
            fieldNameLbl.setContentMode(ContentMode.TEXT);
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
