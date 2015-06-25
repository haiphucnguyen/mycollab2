/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.UnsupportedFeatureException;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.fieldinfo.DetailFieldInfoPanel;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.fieldinfo.IntegerDetailFieldInfoPanel;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.fieldinfo.TextDetailFieldInfoPanel;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AssetResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table.ColumnHeaderMode;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
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
            {new AssetResource("icons/16/customform/text.png"),
                    TEXT_FIELD},
            {new AssetResource("icons/16/customform/integer.png"),
                    INTEGER_FIELD},
            {new AssetResource("icons/16/customform/percent.png"),
                    PERCENT_FIELD},
            {new AssetResource("icons/16/customform/decimal.png"),
                    DECIMAL_FIELD},
            {new AssetResource("icons/16/customform/currency.png"),
                    CURRENCY_FIELD},
            {new AssetResource("icons/16/customform/date.png"),
                    DATE_FIELD},
            {new AssetResource("icons/16/customform/date_time.png"),
                    DATETIME_FIELD},
            {new AssetResource("icons/16/customform/mail.png"),
                    EMAIL_FIELD},
            {new AssetResource("icons/16/customform/phone.png"),
                    PHONE_FIELD},
            {
                    new AssetResource("icons/16/customform/pick_list.png"),
                    PICK_LIST_FIELD},
            {new AssetResource("icons/16/customform/url.png"),
                    URL_FIELD},
            {
                    new AssetResource("icons/16/customform/text_area.png"),
                    TEXTAREA_FIELD},
            {
                    new AssetResource("icons/16/customform/check_box.png"),
                    CHECKBOX_FIELD},
            {
                    new AssetResource("icons/16/customform/select_pick_list.png"),
                    MULTIPLE_SELECT_LIST_FIELD},
            {new AssetResource("icons/16/customform/long.png"),
                    LONG_FIELD},
            {
                    new AssetResource("icons/16/customform/auto_number.png"),
                    AUTO_NUMBER}};

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

        MHorizontalLayout buttonControls = new MHorizontalLayout();
        Button saveBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        DynaSection section = fieldPanel.updateCustomField();
                        viewParent.refreshSectionLayout(section);
                        CreateCustomFieldWindow.this.close();
                    }
                });
        saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        saveBtn.setIcon(FontAwesome.SAVE);
        buttonControls.with(saveBtn).withAlign(saveBtn, Alignment.MIDDLE_CENTER);

        Button cancelBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        CreateCustomFieldWindow.this.close();

                    }
                });
        cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
        buttonControls.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);

        content.with(buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
    }

    private VerticalLayout constructLeftBodyPanel() {
        MVerticalLayout panel = new MVerticalLayout().withSpacing(false).withMargin(false);
        Label title = new Label("Select Field Type");
        MHorizontalLayout header = new MHorizontalLayout().withSpacing(false).with
                (title).expand(title).withStyleName("panel-header");
        header.setWidthUndefined();

        Table fieldSelectionTable = new Table();
        fieldSelectionTable.setWidth("100%");
        fieldSelectionTable.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
        fieldSelectionTable.addContainerProperty("icon", Embedded.class, null);
        fieldSelectionTable.addContainerProperty("type", Button.class, null);
        fieldSelectionTable.setColumnWidth("icon", 20);
        fieldSelectionTable.setVisibleColumns(new String[]{"icon", "type"});

        for (int i = 0; i < fieldsTable.length; i++) {
            final Object[] rowItems = fieldsTable[i];
            final Button typeLink = new Button((String) rowItems[1],
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            constructFieldPanel((String) rowItems[1]);

                        }
                    });
            typeLink.addStyleName("link");
            fieldSelectionTable.addItem(new Object[]{
                            new Embedded("", (Resource) rowItems[0]), typeLink},
                    rowItems[1]);
        }

        panel.with(header, fieldSelectionTable);
        return panel;
    }

    private VerticalLayout constructRightBodyPanel() {
        MVerticalLayout panel = new MVerticalLayout().withMargin(false).withWidth("300px");
        Label title = new Label("Provide Field Detail");
        MHorizontalLayout header = new MHorizontalLayout().withWidth("100%").withSpacing(false).with(title).expand(title).withStyleName
                ("panel-header");

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
            fieldPanel = new TextDetailFieldInfoPanel(candidateFieldName,
                    viewParent.getActiveSections());
        } else if (INTEGER_FIELD.equals(type)) {
            fieldPanel = new IntegerDetailFieldInfoPanel(candidateFieldName,
                    viewParent.getActiveSections());
        } else {
            throw new UnsupportedFeatureException(
                    "Do not support customize field type " + type);
        }

        fieldLayoutWrapper.addComponent(fieldPanel);
    }
}
