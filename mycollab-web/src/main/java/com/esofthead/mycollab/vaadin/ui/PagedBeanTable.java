package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

public class PagedBeanTable<T> extends Table {
	private static final long serialVersionUID = 1L;

	private int currentPage = 0;

	private int totalPage = 0;

	public PagedBeanTable() {
		this.addStyleName("striped");
	}

	@SuppressWarnings("unchecked")
	public T getBeanByIndex(Object itemId) {
		Container container = this.getContainerDataSource();
		BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public void setContainerDataSource(Container newDataSource) {
		super.setContainerDataSource(newDataSource);
		this.setPageLength(newDataSource.size() + 1);
	}

	public HorizontalLayout createControls() {
		Label itemsPerPageLabel = new Label("Items per page:");
		final ComboBox itemsPerPageSelect = new ComboBox();

		itemsPerPageSelect.addItem("5");
		itemsPerPageSelect.addItem("10");
		itemsPerPageSelect.addItem("25");
		itemsPerPageSelect.addItem("50");
		itemsPerPageSelect.addItem("100");
		itemsPerPageSelect.addItem("600");
		itemsPerPageSelect.setImmediate(true);
		itemsPerPageSelect.setNullSelectionAllowed(false);
		itemsPerPageSelect.setWidth("50px");
		itemsPerPageSelect.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = -2255853716069800092L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				
			}
		});
		
		Label pageLabel = new Label("Page:&nbsp;", Label.CONTENT_XHTML);
		final TextField currentPageTextField = new TextField();
		currentPageTextField.setValue(String.valueOf(currentPage));
		currentPageTextField.addValidator(new IntegerValidator(null));
		Label separatorLabel = new Label("&nbsp;/&nbsp;", Label.CONTENT_XHTML);
		final Label totalPagesLabel = new Label(
				String.valueOf(totalPage), Label.CONTENT_XHTML);
		currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
		currentPageTextField.setImmediate(true);
		currentPageTextField.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = -2255853716069800092L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
			
			}
		});
		pageLabel.setWidth(null);
		currentPageTextField.setWidth("20px");
		separatorLabel.setWidth(null);
		totalPagesLabel.setWidth(null);

		HorizontalLayout controlBar = new HorizontalLayout();
		controlBar.setStyleName("listControl");
		HorizontalLayout pageSize = new HorizontalLayout();
		HorizontalLayout pageManagement = new HorizontalLayout();
		final Button first = new Button("<<", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(ClickEvent event) {
				setCurrentPage(0);
			}
		});
		final Button previous = new Button("<", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
		final Button next = new Button(">", new ClickListener() {
			private static final long serialVersionUID = -1927138212640638452L;

			@Override
			public void buttonClick(ClickEvent event) {
			}
		});
		final Button last = new Button(">>", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
		first.setStyleName(Reindeer.BUTTON_LINK);
		previous.setStyleName(Reindeer.BUTTON_LINK);
		next.setStyleName(Reindeer.BUTTON_LINK);
		last.setStyleName(Reindeer.BUTTON_LINK);

		itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
		itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
		pageLabel.addStyleName("pagedtable-pagecaption");
		currentPageTextField.addStyleName("pagedtable-pagefield");
		separatorLabel.addStyleName("pagedtable-separator");
		totalPagesLabel.addStyleName("pagedtable-total");
		first.addStyleName("pagedtable-first");
		previous.addStyleName("pagedtable-previous");
		next.addStyleName("pagedtable-next");
		last.addStyleName("pagedtable-last");

		itemsPerPageLabel.addStyleName("pagedtable-label");
		itemsPerPageSelect.addStyleName("pagedtable-combobox");
		pageLabel.addStyleName("pagedtable-label");
		currentPageTextField.addStyleName("pagedtable-label");
		separatorLabel.addStyleName("pagedtable-label");
		totalPagesLabel.addStyleName("pagedtable-label");
		first.addStyleName("pagedtable-button");
		previous.addStyleName("pagedtable-button");
		next.addStyleName("pagedtable-button");
		last.addStyleName("pagedtable-button");

		pageSize.addComponent(itemsPerPageLabel);
		pageSize.addComponent(itemsPerPageSelect);
		pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
		pageSize.setComponentAlignment(itemsPerPageSelect,
				Alignment.MIDDLE_LEFT);
		pageSize.setSpacing(true);
		pageManagement.addComponent(first);
		pageManagement.addComponent(previous);
		pageManagement.addComponent(pageLabel);
		pageManagement.addComponent(currentPageTextField);
		pageManagement.addComponent(separatorLabel);
		pageManagement.addComponent(totalPagesLabel);
		pageManagement.addComponent(next);
		pageManagement.addComponent(last);
		pageManagement.setComponentAlignment(first, Alignment.MIDDLE_LEFT);
		pageManagement.setComponentAlignment(previous, Alignment.MIDDLE_LEFT);
		pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
		pageManagement.setComponentAlignment(currentPageTextField,
				Alignment.MIDDLE_LEFT);
		pageManagement.setComponentAlignment(separatorLabel,
				Alignment.MIDDLE_LEFT);
		pageManagement.setComponentAlignment(totalPagesLabel,
				Alignment.MIDDLE_LEFT);
		pageManagement.setComponentAlignment(next, Alignment.MIDDLE_LEFT);
		pageManagement.setComponentAlignment(last, Alignment.MIDDLE_LEFT);
		pageManagement.setWidth(null);
		pageManagement.setSpacing(true);
		controlBar.addComponent(pageSize);
		controlBar.addComponent(pageManagement);
		controlBar.setComponentAlignment(pageManagement,
				Alignment.MIDDLE_CENTER);
		controlBar.setWidth("100%");
		controlBar.setExpandRatio(pageSize, 1);
		
		itemsPerPageSelect.select("25");
		return controlBar;
	}
}
