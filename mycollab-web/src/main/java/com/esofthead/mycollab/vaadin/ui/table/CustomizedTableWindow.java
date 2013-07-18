package com.esofthead.mycollab.vaadin.ui.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.tepi.listbuilder.ListBuilder;

import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.service.CustomViewStoreService;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class CustomizedTableWindow extends Window {
	private static final long serialVersionUID = 1L;

	private final ListBuilder listBuilder;

	private CustomViewStoreService customViewStoreService;
	private AbstractPagedBeanTable tableItem;

	protected String viewId;

	public CustomizedTableWindow(final String viewId,
			final AbstractPagedBeanTable table) {
		super("Customize View");
		this.viewId = viewId;
		this.setWidth("800px");
		this.center();

		this.tableItem = table;
		customViewStoreService = AppContext
				.getSpringBean(CustomViewStoreService.class);

		final VerticalLayout body = new VerticalLayout();
		body.setSpacing(true);
		body.setSizeFull();
		this.addComponent(body);

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
		body.addComponent(this.listBuilder);

		Button restoreLink = new Button("Restore to default",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						List<TableViewField> defaultSelectedColumns = tableItem
								.getDefaultSelectedColumns();
						if (defaultSelectedColumns != null) {
							final List<TableViewField> selectedColumns = new ArrayList<TableViewField>();
							final BeanItemContainer<TableViewField> container = (BeanItemContainer<TableViewField>) CustomizedTableWindow.this.listBuilder
									.getContainerDataSource();
							final Collection<TableViewField> itemIds = container
									.getItemIds();

							for (TableViewField column : defaultSelectedColumns) {
								for (final TableViewField viewField : itemIds) {
									if (column.getField().equals(
											viewField.getField())) {
										selectedColumns.add(viewField);
									}
								}
							}

							CustomizedTableWindow.this.listBuilder
									.setValue(selectedColumns);
						}

					}
				});
		restoreLink.setStyleName("link");
		body.addComponent(restoreLink);
		body.setComponentAlignment(restoreLink, Alignment.MIDDLE_RIGHT);

		final HorizontalLayout buttonControls = new HorizontalLayout();
		final Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						final List<TableViewField> selectedColumns = (List<TableViewField>) CustomizedTableWindow.this.listBuilder
								.getValue();
						table.setTableViewFieldCollection(selectedColumns);
						CustomizedTableWindow.this.close();

						// Save custom table view def
						CustomViewStore viewDef = new CustomViewStore();
						viewDef.setSaccountid(AppContext.getAccountId());
						viewDef.setCreateduser(AppContext.getUsername());
						viewDef.setViewid(viewId);
						XStream xstream = new XStream(new StaxDriver());
						viewDef.setViewinfo(xstream.toXML(selectedColumns));
						customViewStoreService
								.saveOrUpdateViewLayoutDef(viewDef);
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
						CustomizedTableWindow.this.close();
					}
				});
		cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(cancelBtn);

		body.addComponent(buttonControls);
		body.setComponentAlignment(buttonControls, Alignment.MIDDLE_CENTER);
	}

	private void setSelectedViewColumns() {
		final Collection<String> viewColumnIds = this.getViewColumns();

		final BeanItemContainer<TableViewField> container = (BeanItemContainer<TableViewField>) this.listBuilder
				.getContainerDataSource();
		final Collection<TableViewField> itemIds = container.getItemIds();
		final List<TableViewField> selectedColumns = new ArrayList<TableViewField>();

		for (String viewColumnId : viewColumnIds) {
			for (final TableViewField viewField : itemIds) {
				if (viewColumnId.equals(viewField.getField())) {
					selectedColumns.add(viewField);
				}
			}
		}

		this.listBuilder.setValue(selectedColumns);
	}

	abstract protected Collection<TableViewField> getAvailableColumns();

	protected Collection<String> getViewColumns() {
		Object[] visibleColumns = tableItem.getVisibleColumns();
		String[] copyArr = Arrays.copyOf(visibleColumns, visibleColumns.length,
				String[].class);
		return Arrays.asList(copyArr);
	}

}
