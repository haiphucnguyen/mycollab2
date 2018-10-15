package com.mycollab.pro.module.crm.view.setting.customlayout;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.UnsupportedFeatureException;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.module.crm.view.setting.ICrmCustomView;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo.DetailFieldInfoPanel;
import com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo.IntegerDetailFieldInfoPanel;
import com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo.TextDetailFieldInfoPanel;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
// TODO
public class CreateCustomFieldWindow extends Window {
    private static final long serialVersionUID = 1L;

    private static final String TEXT_FIELD = "Text";
    private static final String INTEGER_FIELD = "Integer";
    private static final String PERCENT_FIELD = "Percent";
    private static final String DECIMAL_FIELD = "Decimal";
    private static final String CURRENCY_FIELD = "Currency";
    private static final String DATE_FIELD = "Date";
    private static final String DATETIME_FIELD = "DateTime";
    private static final String EMAIL_FIELD = "Email";
    private static final String PHONE_FIELD = "Phone";
    private static final String PICK_LIST_FIELD = "Pick List";
    private static final String URL_FIELD = "Url";
    private static final String TEXTAREA_FIELD = "TextArea";
    private static final String CHECKBOX_FIELD = "Checkbox";
    private static final String MULTIPLE_SELECT_LIST_FIELD = "Multiselect Pick List";
    private static final String LONG_FIELD = "Long Number";
    private static final String AUTO_NUMBER = "Auto Number";

    private static final Object[][] fieldsTable = new Object[][]{
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/text.png")), TEXT_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/integer.png")), INTEGER_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/percent.png")), PERCENT_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/decimal.png")), DECIMAL_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/currency.png")), CURRENCY_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/date.png")), DATE_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/date_time.png")), DATETIME_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/mail.png")), EMAIL_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/phone.png")), PHONE_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/pick_list.png")), PICK_LIST_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/url.png")), URL_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/text_area.png")), TEXTAREA_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/check_box.png")), CHECKBOX_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/select_pick_list.png")), MULTIPLE_SELECT_LIST_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/long.png")), LONG_FIELD},
            {new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/customform/auto_number.png")), AUTO_NUMBER}};

    private CssLayout fieldLayoutWrapper;
    @SuppressWarnings("rawtypes")
    private DetailFieldInfoPanel fieldPanel;
    private ICrmCustomView viewParent;

    public CreateCustomFieldWindow(final ICrmCustomView crmCustomView) {
        super("Custom Field");
        this.setResizable(false);
        this.setModal(true);
        this.viewParent = crmCustomView;
        center();

        MVerticalLayout content = new MVerticalLayout().withStyleName("createCustomFieldWindow").withWidth("600px")
                .withHeight("600px");
        this.setContent(content);

        MHorizontalLayout body = new MHorizontalLayout();
        VerticalLayout rightPanel = constructRightBodyPanel();
        body.with(constructLeftBodyPanel(), rightPanel);
        content.with(body).expand(body);


        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            DynaSection section = fieldPanel.updateCustomField();
            viewParent.refreshSectionLayout(section);
            close();
        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.PENCIL);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);

        MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(true);
        content.with(buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
    }

    private VerticalLayout constructLeftBodyPanel() {
        MVerticalLayout panel = new MVerticalLayout().withSpacing(false).withMargin(false);
        Label title = new Label("Select Field Type");
        MHorizontalLayout header = new MHorizontalLayout().withSpacing(false).with(title).
                expand(title).withStyleName(WebThemes.PANEL_HEADER);
        header.setWidthUndefined();

//        Table fieldSelectionTable = new Table();
//        fieldSelectionTable.setWidth("100%");
//        fieldSelectionTable.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
//        fieldSelectionTable.addContainerProperty("icon", Embedded.class, null);
//        fieldSelectionTable.addContainerProperty("type", Button.class, null);
//        fieldSelectionTable.setColumnWidth("icon", 20);
//        fieldSelectionTable.setVisibleColumns("icon", "type");
//
//        for (final Object[] rowItems : fieldsTable) {
//            final Button typeLink = new Button((String) rowItems[1], clickEvent -> constructFieldPanel((String) rowItems[1]));
//            typeLink.addStyleName(WebThemes.BUTTON_LINK);
//            fieldSelectionTable.addItem(new Object[]{new Embedded("", (Resource) rowItems[0]), typeLink}, rowItems[1]);
//        }
//
//        panel.with(header, fieldSelectionTable);
        return panel;
    }

    private VerticalLayout constructRightBodyPanel() {
        MVerticalLayout panel = new MVerticalLayout().withMargin(false).withWidth("300px");
        Label title = new Label("Provide Field Detail");
        MHorizontalLayout header = new MHorizontalLayout().withFullWidth().withSpacing(false).with(title).expand(title)
                .withStyleName(WebThemes.PANEL_HEADER);

        fieldLayoutWrapper = new CssLayout();
        fieldLayoutWrapper.setWidth("100%");
        panel.with(header, fieldLayoutWrapper);
        return panel;
    }

    private void constructFieldPanel(String type) {
        fieldLayoutWrapper.removeAllComponents();
        String candidateFieldName = null;

        if (TEXT_FIELD.equals(type)) {
            candidateFieldName = viewParent.getCandidateTextFieldName();
            fieldPanel = new TextDetailFieldInfoPanel(candidateFieldName, viewParent.getActiveSections());
        } else if (INTEGER_FIELD.equals(type)) {
            fieldPanel = new IntegerDetailFieldInfoPanel(candidateFieldName, viewParent.getActiveSections());
        } else {
            throw new UnsupportedFeatureException("Do not support customize field type " + type);
        }

        fieldLayoutWrapper.addComponent(fieldPanel);
    }
}
