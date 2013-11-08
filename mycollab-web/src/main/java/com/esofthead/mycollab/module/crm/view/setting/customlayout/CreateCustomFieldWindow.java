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
package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.TextDynaField;
import com.esofthead.mycollab.module.crm.view.setting.CrmCustomView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
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
	private CrmCustomView viewParent;

	public CreateCustomFieldWindow(final CrmCustomView crmCustomView) {
		super("Custom Field");
		this.viewParent = crmCustomView;
		center();
		this.setWidth("600px");

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		this.addComponent(content);
		content.setSpacing(true);

		HorizontalLayout body = new HorizontalLayout();
		body.setSizeFull();
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
		panel.setWidth("170px");
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
		panel.setSizeFull();
		Label title = new Label("Provide Field Detail:");
		panel.addComponent(title);

		fieldLayoutWrapper = new CssLayout();
		fieldLayoutWrapper.setSizeFull();
		panel.addComponent(fieldLayoutWrapper);
		return panel;
	}

	private void contructFieldPanel(String type) {
		fieldLayoutWrapper.removeAllComponents();

		if (TEXT_FIELD.equals(type)) {
			fieldPanel = new TextDetailFieldInfoPanel(
					viewParent.getActiveSections());
		}

		fieldLayoutWrapper.addComponent(fieldPanel);
	}

	private static abstract class DetailFieldInfoPanel<F extends AbstractDynaField>
			extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private List<DynaSection> activeSections;
		protected F field;

		public DetailFieldInfoPanel(List<DynaSection> activeSections) {
			this.activeSections = activeSections;
		}

		abstract void updateCustomField();
	}

	private static class TextDetailFieldInfoPanel extends
			DetailFieldInfoPanel<TextDynaField> {
		private static final long serialVersionUID = 1L;

		private TextField labelField = new TextField();
		private TextField lengthField = new TextField();
		private SectionSelectList sectionList;

		public TextDetailFieldInfoPanel(List<DynaSection> activeSections) {
			super(activeSections);

			GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 3);
			layoutHelper.addComponent(labelField, "Label", 0, 0);
			sectionList = new SectionSelectList(activeSections);
			layoutHelper.addComponent(sectionList, "Section", 0, 1);
			layoutHelper.addComponent(lengthField, "Length", 0, 2);
			this.addComponent(layoutHelper.getLayout());
		}

		@Override
		void updateCustomField() {
			String displayName = (String) labelField.getValue();
			DynaSection ownSection = (DynaSection) sectionList.getValue();
			TextDynaField customField = new TextDynaField();
			customField.setCustom(true);

		}
	}

	private static class SectionSelectList extends ComboBox {
		private static final long serialVersionUID = 1L;

		public SectionSelectList(List<DynaSection> sections) {
			super();
			this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);

			for (DynaSection section : sections) {
				this.addItem(section);
				this.setItemCaption(section, section.getHeader());
			}

			if (sections.size() > 0) {
				this.select(sections.get(0));
			}
		}
	}
}
