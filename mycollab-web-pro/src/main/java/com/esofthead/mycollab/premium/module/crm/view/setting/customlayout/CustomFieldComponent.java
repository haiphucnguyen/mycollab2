package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class CustomFieldComponent extends CssLayout {
    private static final long serialVersionUID = 1L;

    private AbstractDynaField field;
    private Label fieldNameLbl;
    private String fieldName;
    private Panel fieldEditPanel;
    private MVerticalLayout panelContentLayout;
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
            fieldNameLbl.setContentMode(ContentMode.HTML);
            this.addComponent(fieldNameLbl);
        } else {
            MHorizontalLayout fieldWrapper = new MHorizontalLayout().withSpacing(false).withWidth("100%");
            fieldWrapper.with(fieldNameLbl).expand(fieldNameLbl);

            editFieldBtn = new PopupButton();
            editFieldBtn.setStyleName(UIConstants.POPUP_WITHOUT_INDICATOR);
            editFieldBtn.addStyleName("editFieldBtn");
            editFieldBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);
            editFieldBtn.setIcon(FontAwesome.EDIT);

            fieldEditPanel = new Panel();
            panelContentLayout = new MVerticalLayout().withWidth("100%");
            fieldEditPanel.setContent(panelContentLayout);
            fieldEditPanel.setStyleName(Reindeer.PANEL_LIGHT);
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
                AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent arg0) {
                editFieldBtn.setPopupVisible(false);
                CustomFieldComponent.this.setRequired(isRequired
                        .getValue());
            }
        });
        saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
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
