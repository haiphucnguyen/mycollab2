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

	private final ListBuilder listBuilder;

	public AccountListCustomizeWindow(final AbstractPagedBeanTable table) {
		super("Customize View");
		this.setWidth("800px");

		this.center();

		this.listBuilder = new ListBuilder();
		this.listBuilder.setImmediate(true);
		this.listBuilder.setLeftColumnCaption("Available Columns");
		this.listBuilder.setRightColumnCaption("View Columns");
		this.listBuilder.setWidth("100%");

		this.listBuilder
				.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
		this.listBuilder.setItemCaptionPropertyId("desc");
		final BeanItemContainer<TableViewField> container = new BeanItemContainer<TableViewField>(
				TableViewField.class, this.getAvailableColumns());
		this.listBuilder.setContainerDataSource(container);
		this.setSelectedViewColumns();
		this.addComponent(this.listBuilder);

		final HorizontalLayout buttonControls = new HorizontalLayout();
		final Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						final List<TableViewField> selectedColumns = (List<TableViewField>) AccountListCustomizeWindow.this.listBuilder
								.getValue();
						table.setTableViewFieldCollection(selectedColumns);
						AccountListCustomizeWindow.this.close();
					}
				});
		saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(saveBtn);

		final Button cancelBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						AccountListCustomizeWindow.this.close();
					}
				});
		cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(cancelBtn);

		this.addComponent(buttonControls);
		final VerticalLayout thisContainer = (VerticalLayout) this.getContent();
		thisContainer.setComponentAlignment(buttonControls,
				Alignment.MIDDLE_CENTER);
		thisContainer.setSpacing(true);
	}

	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(AccountTableFieldDef.accountname,
				AccountTableFieldDef.assignUser, AccountTableFieldDef.city,
				AccountTableFieldDef.email, AccountTableFieldDef.phoneoffice,
				AccountTableFieldDef.website, AccountTableFieldDef.type);
	}

	private void setSelectedViewColumns() {
		final Collection<String> viewColumnIds = this.getViewColumns();

		final BeanItemContainer<TableViewField> container = (BeanItemContainer<TableViewField>) this.listBuilder
				.getContainerDataSource();
		final Collection<TableViewField> itemIds = container.getItemIds();
		final List<TableViewField> selectedColumns = new ArrayList<TableViewField>();

		for (final TableViewField viewField : itemIds) {
			if (viewColumnIds.contains(viewField.getField())) {
				selectedColumns.add(viewField);
			}
		}

		this.listBuilder.setValue(selectedColumns);
	}

	protected Collection<String> getViewColumns() {
		return Arrays.asList("accountname", "phoneoffice", "city", "email",
				"assignUserFullName");
	}

}
