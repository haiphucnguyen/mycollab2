package com.esofthead.mycollab.module.crm.view.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.tepi.listbuilder.ListBuilder;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AccountListCustomizeWindow extends Window {
	private static final long serialVersionUID = 1L;

	private ListBuilder listBuilder;

	public AccountListCustomizeWindow(final AbstractPagedBeanTable table) {
		super("Customize View");
		this.setWidth("800px");
		this.setHeight("300px");

		center();

		VerticalLayout body = new VerticalLayout();
		body.setSpacing(true);
		body.setSizeFull();
		this.addComponent(body);

		this.listBuilder = new ListBuilder("");
		listBuilder.setImmediate(true);
		listBuilder.setColumns(15);
		listBuilder.setLeftColumnCaption("Available Columns");
		listBuilder.setRightColumnCaption("View Columns");
		listBuilder.setSizeFull();

		listBuilder
				.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
		listBuilder.setItemCaptionPropertyId("desc");
		BeanItemContainer<TableViewField> container = new BeanItemContainer<TableViewField>(
				TableViewField.class, getAvailableColumns());
		listBuilder.setContainerDataSource(container);
		setSelectedViewColumns();
		body.addComponent(listBuilder);

		HorizontalLayout buttonControls = new HorizontalLayout();
		Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						List<TableViewField> selectedColumns = (List<TableViewField>) listBuilder
								.getValue();
						table.setTableViewFieldCollection(selectedColumns);
						AccountListCustomizeWindow.this.close();
					}
				});
		saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(saveBtn);

		Button cancelBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						AccountListCustomizeWindow.this.close();
					}
				});
		cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(cancelBtn);

		body.addComponent(buttonControls);
		body.setComponentAlignment(buttonControls, Alignment.MIDDLE_CENTER);
	}

	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(AccountTableFieldDef.accountname,
				AccountTableFieldDef.assignUser, AccountTableFieldDef.city,
				AccountTableFieldDef.email, AccountTableFieldDef.phoneoffice,
				AccountTableFieldDef.website, AccountTableFieldDef.type);
	}

	private void setSelectedViewColumns() {
		Collection<String> viewColumnIds = getViewColumns();

		BeanItemContainer<TableViewField> container = (BeanItemContainer<TableViewField>) listBuilder
				.getContainerDataSource();
		Collection<TableViewField> itemIds = container.getItemIds();
		List<TableViewField> selectedColumns = new ArrayList<TableViewField>();

		for (TableViewField viewField : itemIds) {
			if (viewColumnIds.contains(viewField.getField())) {
				selectedColumns.add(viewField);
			}
		}

		listBuilder.setValue(selectedColumns);
	}

	protected Collection<String> getViewColumns() {
		return Arrays.asList("accountname", "phoneoffice", "city", "email",
				"assignUserFullName");
	}

}
