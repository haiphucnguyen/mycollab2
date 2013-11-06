package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.view.setting.CrmCustomView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
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

	public CreateCustomFieldWindow(final CrmCustomView crmCustomView) {
		super("Custom Field");
		center();
		this.setWidth("600px");

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		this.addComponent(content);
		content.setSpacing(true);

		HorizontalLayout body = new HorizontalLayout();
		body.setSizeFull();
		body.setSpacing(true);
		body.addComponent(constructLeftBodyPanel());
		body.addComponent(constructRightBodyPanel());
		content.addComponent(body);

		HorizontalLayout buttonControls = new HorizontalLayout();
		Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {

					}
				});
		saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(saveBtn);
		buttonControls.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

		Button cancelBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
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
		Label title = new Label("Select Field Type:");
		panel.addComponent(title);

		Table fieldSelectionTable = new Table();
		fieldSelectionTable
				.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
		fieldSelectionTable.setSizeFull();
		fieldSelectionTable.addContainerProperty("icon", Embedded.class, null);
		fieldSelectionTable.addContainerProperty("type", Button.class, null);
		fieldSelectionTable.setColumnWidth("icon", 20);
		fieldSelectionTable.setVisibleColumns(new String[] { "icon", "type" });

		for (int i = 0; i < fieldsTable.length; i++) {
			Object[] rowItems = fieldsTable[i];
			Button typeLink = new Button((String) rowItems[1],
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							// TODO Auto-generated method stub

						}
					});
			typeLink.addStyleName("link");
			fieldSelectionTable.addItem(new Object[] {
					new Embedded("", (Resource) rowItems[0]), typeLink },
					rowItems[1]);
		}

		panel.setWidth("170px");
		panel.addComponent(fieldSelectionTable);
		return panel;
	}

	private VerticalLayout constructRightBodyPanel() {
		VerticalLayout panel = new VerticalLayout();
		Label title = new Label("Provide Field Detail:");
		panel.addComponent(title);
		return panel;
	}
}
