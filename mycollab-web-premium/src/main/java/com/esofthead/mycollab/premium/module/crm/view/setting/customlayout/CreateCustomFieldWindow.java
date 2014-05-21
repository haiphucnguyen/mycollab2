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

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.fieldinfo.DetailFieldInfoPanel;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.fieldinfo.IntegerDetailFieldInfoPanel;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.fieldinfo.TextDetailFieldInfoPanel;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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

	private static final Object[][] fieldsTable = new Object[][] {
			{ MyCollabResource.newResource("icons/16/customform/text.png"),
					TEXT_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/integer.png"),
					INTEGER_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/percent.png"),
					PERCENT_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/decimal.png"),
					DECIMAL_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/currency.png"),
					CURRENCY_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/date.png"),
					DATE_FIELD },
			{
					MyCollabResource
							.newResource("icons/16/customform/date_time.png"),
					DATETIME_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/mail.png"),
					EMAIL_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/phone.png"),
					PHONE_FIELD },
			{
					MyCollabResource
							.newResource("icons/16/customform/pick_list.png"),
					PICK_LIST_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/url.png"),
					URL_FIELD },
			{
					MyCollabResource
							.newResource("icons/16/customform/text_area.png"),
					TEXTAREA_FIELD },
			{
					MyCollabResource
							.newResource("icons/16/customform/check_box.png"),
					CHECKBOX_FIELD },
			{
					MyCollabResource
							.newResource("icons/16/customform/select_pick_list.png"),
					MULTIPLE_SELECT_LIST_FIELD },
			{ MyCollabResource.newResource("icons/16/customform/long.png"),
					LONG_FIELD },
			{
					MyCollabResource
							.newResource("icons/16/customform/auto_number.png"),
					AUTO_NUMBER } };

	private CssLayout fieldLayoutWrapper;
	private DetailFieldInfoPanel fieldPanel;
	private ICrmCustomView viewParent;

	public CreateCustomFieldWindow(final ICrmCustomView crmCustomView) {
		super("Custom Field");
		this.setResizable(false);
		this.viewParent = crmCustomView;
		center();
		this.setWidth("600px");

		VerticalLayout content = new VerticalLayout();
		content.setStyleName("createCustomFieldWindow");
		this.setContent(content);
		content.setSpacing(true);
		content.setMargin(true);

		HorizontalLayout body = new HorizontalLayout();
		body.setSpacing(true);
		body.addComponent(constructLeftBodyPanel());
		VerticalLayout rightPanel = constructRightBodyPanel();
		body.addComponent(rightPanel);
		body.setExpandRatio(rightPanel, 1.0f);
		body.setComponentAlignment(rightPanel, Alignment.TOP_RIGHT);
		content.addComponent(body);
		content.setExpandRatio(body, 1.0f);

		HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.setSpacing(true);
		Button saveBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
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
		saveBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
		buttonControls.addComponent(saveBtn);
		buttonControls.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

		Button cancelBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						CreateCustomFieldWindow.this.close();

					}
				});
		cancelBtn.addStyleName("link");
		buttonControls.addComponent(cancelBtn);
		buttonControls
				.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);

		content.addComponent(buttonControls);
		content.setComponentAlignment(buttonControls, Alignment.MIDDLE_CENTER);
	}

	private VerticalLayout constructLeftBodyPanel() {
		VerticalLayout panel = new VerticalLayout();
		panel.setSpacing(true);
		Label title = new Label("Select Field Type:");
		title.addStyleName("panel-header");
		panel.addComponent(title);

		Table fieldSelectionTable = new Table();
		fieldSelectionTable.setWidth("100%");
		fieldSelectionTable.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		fieldSelectionTable.addContainerProperty("icon", Embedded.class, null);
		fieldSelectionTable.addContainerProperty("type", Button.class, null);
		fieldSelectionTable.setColumnWidth("icon", 20);
		fieldSelectionTable.setVisibleColumns(new String[] { "icon", "type" });

		for (int i = 0; i < fieldsTable.length; i++) {
			final Object[] rowItems = fieldsTable[i];
			final Button typeLink = new Button((String) rowItems[1],
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							contructFieldPanel((String) rowItems[1]);

						}
					});
			typeLink.addStyleName("link");
			fieldSelectionTable.addItem(new Object[] {
					new Embedded("", (Resource) rowItems[0]), typeLink },
					rowItems[1]);
		}

		panel.addComponent(fieldSelectionTable);
		return panel;
	}

	private VerticalLayout constructRightBodyPanel() {
		VerticalLayout panel = new VerticalLayout();
		panel.setWidth("300px");
		panel.setSpacing(true);
		Label title = new Label("Provide Field Detail:");
		title.addStyleName("panel-header");
		panel.addComponent(title);

		fieldLayoutWrapper = new CssLayout();
		fieldLayoutWrapper.setWidth("100%");
		panel.addComponent(fieldLayoutWrapper);
		return panel;
	}

	private void contructFieldPanel(String type) {
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
			throw new MyCollabException("Do not support customize field type "
					+ type);
		}

		fieldLayoutWrapper.addComponent(fieldPanel);
	}
}
