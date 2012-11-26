package com.esofthead.mycollab.vaadin.ui;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.ISearchableService;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.terminal.ThemeResource;
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

public class PagedBeanTable2<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends Table implements HasSelectableItemHandlers<T>,
		IPagedBeanTable<SearchService, S, T> {
	private static final long serialVersionUID = 1L;

	private String[] visibleColumns;

	private String[] columnHeaders;

	private int currentPage = 1;

	private int totalPage = 1;

	private Button first, previous, next, last;

	private Label totalPagesLabel;

	private TextField currentPageTextField;

	private ComboBox itemsPerPageSelect;

	private Set<SelectableItemHandler<T>> selectableHandlers;

	private SearchRequest<S> searchRequest;

	private SearchService searchService;

	private List<T> currentListData;

	private Class<T> type;

	public PagedBeanTable2(SearchService searchService, Class<T> type,
			final String[] visibleColumns, String[] columnHeaders) {
		this.addStyleName("striped");
		this.searchService = searchService;
		this.visibleColumns = visibleColumns;
		this.columnHeaders = columnHeaders;
		this.type = type;

		this.setSortDisabled(true);
		this.addListener(new HeaderClickListener() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void headerClick(HeaderClickEvent event) {
				String propertyId = (String) event.getPropertyId();

				if (propertyId.equals("selected")) {
					return;
				}

				if (searchRequest != null) {

					S searchCriteria = searchRequest.getSearchCriteria();
					if (searchCriteria.getOrderByField() == null) {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
					} else if (propertyId.equals(searchCriteria
							.getOrderByField())) {
						searchCriteria
								.setSortDirection(searchCriteria
										.getSortDirection().equals(
												SearchCriteria.ASC) ? SearchCriteria.DESC
										: SearchCriteria.ASC);
					} else {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
					}

					PagedBeanTable2.this
							.setColumnIcon(
									propertyId,
									searchCriteria.getSortDirection().equals(
											SearchCriteria.DESC) ? new ThemeResource(
											"icons/16/arrow_down.png")
											: new ThemeResource(
													"icons/16/arrow_up.png"));

					Collection<String> containerIds = (Collection<String>) PagedBeanTable2.this
							.getContainerPropertyIds();
					for (String id : containerIds) {
						if (!id.equals(propertyId)) {
							PagedBeanTable2.this.setColumnIcon(id, null);
						}
					}

					PagedBeanTable2.this.setSearchCriteria(searchCriteria);
				}
			}
		});
	}

	public void setSearchCriteria(S searchCriteria) {
		searchRequest = new SearchRequest<S>(searchCriteria, currentPage,
				SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
		doSearch();
	}

	@SuppressWarnings("unchecked")
	private void doSearch() {
		int totalCount = searchService.getTotalCount(searchRequest
				.getSearchCriteria());
		int totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		currentListData = searchService
				.findPagableListByCriteria(searchRequest);

		this.setCurrentPage(currentPage);
		this.setTotalPage(totalPage);

		BeanItemContainer<T> container = new BeanItemContainer<T>(type,
				currentListData);
		this.setContainerDataSource(container);
		this.setVisibleColumns(visibleColumns);
		this.setColumnHeaders(columnHeaders);
	}

	@SuppressWarnings("unchecked")
	public T getBeanByIndex(Object itemId) {
		Container container = this.getContainerDataSource();
		BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}

	private void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		currentPageTextField.setValue(currentPage);
		checkButtonStatus();
	}

	private void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		totalPagesLabel.setValue(String.valueOf(totalPage));
		checkButtonStatus();
	}

	private void checkButtonStatus() {
		if (this.currentPage == 1) {
			this.previous.setEnabled(false);
			this.first.setEnabled(false);
		} else {
			this.previous.setEnabled(true);
			this.first.setEnabled(true);
		}

		if (this.currentPage == totalPage) {
			this.last.setEnabled(false);
			this.next.setEnabled(false);
		} else {
			this.last.setEnabled(true);
			this.next.setEnabled(true);
		}
	}

	@Override
	public void setContainerDataSource(Container newDataSource) {
		super.setContainerDataSource(newDataSource);
		this.setPageLength(newDataSource.size() + 1);
	}

	public HorizontalLayout createControls() {
		Label itemsPerPageLabel = new Label("Items per page:");
		itemsPerPageSelect = new ComboBox();

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
				Integer numberOfItems = Integer
						.parseInt((String) itemsPerPageSelect.getValue());
				displayItemChange(numberOfItems);
			}
		});

		Label pageLabel = new Label("Page:&nbsp;", Label.CONTENT_XHTML);
		currentPageTextField = new TextField();
		currentPageTextField.setValue(String.valueOf(currentPage));
		currentPageTextField.addValidator(new IntegerValidator(null));
		Label separatorLabel = new Label("&nbsp;/&nbsp;", Label.CONTENT_XHTML);
		totalPagesLabel = new Label(String.valueOf(totalPage),
				Label.CONTENT_XHTML);
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
		first = new Button("<<", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageChange(1);
			}
		});
		previous = new Button("<", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageChange(PagedBeanTable2.this.currentPage - 1);
			}
		});
		next = new Button(">", new ClickListener() {
			private static final long serialVersionUID = -1927138212640638452L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageChange(PagedBeanTable2.this.currentPage + 1);
			}
		});
		last = new Button(">>", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageChange(PagedBeanTable2.this.totalPage);
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

	private void displayItemChange(int numOfItems) {
		if (searchRequest != null) {
			searchRequest.setNumberOfItems(numOfItems);
			doSearch();
		}
	}

	private void pageChange(int currentPage) {
		if (searchRequest != null) {
			this.currentPage = currentPage;
			searchRequest.setCurrentPage(currentPage);
			doSearch();
		}
	}

	public void fireSelectItemEvent(T item) {
		if (selectableHandlers != null) {
			for (SelectableItemHandler<T> handler : selectableHandlers) {
				handler.onSelect(item);
			}
		}
	}

	@Override
	public void addSelectableItemHandler(SelectableItemHandler<T> handler) {
		if (selectableHandlers == null) {
			selectableHandlers = new HashSet<SelectableItemHandler<T>>();
		}
		selectableHandlers.add(handler);
	}
}
