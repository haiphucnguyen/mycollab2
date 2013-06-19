package com.esofthead.mycollab.vaadin.ui.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class PagedBeanTable2<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends VerticalLayout implements IPagedBeanTable<S, T> {

	private static final long serialVersionUID = 1L;
	private int displayNumItems = SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS;
	private final String[] visibleColumns;
	private final String[] columnHeaders;
	private int currentPage = 1;
	private int totalPage = 1;
	private int currentViewCount;
	private int totalCount;
	private Button first, previous, next, last;
	private Label totalPagesLabel;
	private TextField currentPageTextField;
	private ComboBox itemsPerPageSelect;
	private Set<SelectableItemHandler<T>> selectableHandlers;
	private Set<PagableHandler> pagableHandlers;
	private SearchRequest<S> searchRequest;
	private final SearchService searchService;
	private List<T> currentListData;
	private final Class<T> type;
	private LazyLoadWrapper tableLazyLoadContainer;
	private Table tableItem;
	private Object columnExpandId;
	private Object sortColumnId;
	private boolean isAscending = true;
	private final Map<Object, ColumnGenerator> columnGenerators = new HashMap<Object, Table.ColumnGenerator>();
	private final Map<Object, Integer> columnWidths = new HashMap<Object, Integer>();
	private Map<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> mapEventListener;

	public PagedBeanTable2(final SearchService searchService,
			final Class<T> type, final String[] visibleColumns,
			final String[] columnHeaders) {
		this.searchService = searchService;
		this.visibleColumns = visibleColumns;
		this.columnHeaders = columnHeaders;
		this.type = type;

		setStyleName("list-view");
	}

	@Override
	public void addGeneratedColumn(final Object id,
			final ColumnGenerator generatedColumn) {
		columnGenerators.put(id, generatedColumn);
	}

	@Override
	public void addPagableHandler(final PagableHandler handler) {
		if (pagableHandlers == null) {
			pagableHandlers = new HashSet<PagableHandler>();
		}
		pagableHandlers.add(handler);

	}

	@Override
	public void addSelectableItemHandler(final SelectableItemHandler<T> handler) {
		if (selectableHandlers == null) {
			selectableHandlers = new HashSet<SelectableItemHandler<T>>();
		}
		selectableHandlers.add(handler);
	}

	@Override
	public void addTableListener(
			final ApplicationEventListener<? extends ApplicationEvent> listener) {
		if (mapEventListener == null) {
			mapEventListener = new HashMap<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>>();
		}

		Set<ApplicationEventListener<?>> listenerSet = mapEventListener
				.get(listener.getEventType());
		if (listenerSet == null) {
			listenerSet = new LinkedHashSet<ApplicationEventListener<?>>();
			mapEventListener.put(listener.getEventType(), listenerSet);
		}

		listenerSet.add(listener);
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

	private CssLayout createControls() {
		final Label itemsPerPageLabel = new Label("Items per page:");
		itemsPerPageSelect = new ComboBox();

		itemsPerPageSelect.addItem("5");
		itemsPerPageSelect.addItem("10");
		itemsPerPageSelect.addItem("25");
		itemsPerPageSelect.addItem("50");
		itemsPerPageSelect.addItem("100");
		itemsPerPageSelect.addItem("600");
		itemsPerPageSelect.select(displayNumItems);
		itemsPerPageSelect.setImmediate(true);
		itemsPerPageSelect.setNullSelectionAllowed(false);
		itemsPerPageSelect.setWidth("50px");
		itemsPerPageSelect.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = -2255853716069800092L;

			@Override
			public void valueChange(
					final com.vaadin.data.Property.ValueChangeEvent event) {
				displayNumItems = Integer.parseInt((String) itemsPerPageSelect
						.getValue());
				displayItemChange(displayNumItems);
			}
		});

		final Label pageLabel = new Label("Page:&nbsp;", Label.CONTENT_XHTML);
		currentPageTextField = new TextField();
		currentPageTextField.setValue(String.valueOf(currentPage));
		currentPageTextField.addValidator(new IntegerValidator(null));
		final Label separatorLabel = new Label("&nbsp;/&nbsp;",
				Label.CONTENT_XHTML);
		totalPagesLabel = new Label(String.valueOf(totalPage),
				Label.CONTENT_XHTML);
		currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
		currentPageTextField.setImmediate(true);
		currentPageTextField.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = -2255853716069800092L;

			@Override
			public void valueChange(
					final com.vaadin.data.Property.ValueChangeEvent event) {
			}
		});
		pageLabel.setWidth(null);
		currentPageTextField.setWidth("20px");
		separatorLabel.setWidth(null);
		totalPagesLabel.setWidth(null);

		final CssLayout controlBarWrapper = new CssLayout();
		controlBarWrapper.setStyleName("listControl");
		controlBarWrapper.setWidth("100%");

		final HorizontalLayout controlBar = new HorizontalLayout();
		controlBar.setWidth("100%");
		controlBarWrapper.addComponent(controlBar);

		final HorizontalLayout pageSize = new HorizontalLayout();
		final HorizontalLayout pageManagement = new HorizontalLayout();
		first = new ButtonLink("<<", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(1);
			}
		});
		previous = new ButtonLink("<", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(PagedBeanTable2.this.currentPage - 1);
			}
		});
		next = new ButtonLink(">", new ClickListener() {
			private static final long serialVersionUID = -1927138212640638452L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(PagedBeanTable2.this.currentPage + 1);
			}
		});
		last = new ButtonLink(">>", new ClickListener() {
			private static final long serialVersionUID = -355520120491283992L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(PagedBeanTable2.this.totalPage);
			}
		});

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
		controlBar.setComponentAlignment(pageSize, Alignment.MIDDLE_LEFT);
		controlBar.setExpandRatio(pageSize, 1.0f);
		controlBar.addComponent(pageManagement);
		controlBar
				.setComponentAlignment(pageManagement, Alignment.MIDDLE_RIGHT);

		return controlBarWrapper;
	}

	@Override
	public int currentViewCount() {
		return currentViewCount;
	}

	private void displayItemChange(final int numOfItems) {
		if (searchRequest != null) {
			searchRequest.setNumberOfItems(numOfItems);
			doSearch();

			if (pagableHandlers != null) {
				for (final PagableHandler handler : pagableHandlers) {
					handler.displayItemChange(numOfItems);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void doSearch() {
		totalCount = searchService.getTotalCount(searchRequest
				.getSearchCriteria());
		totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		if (totalPage > 1) {
			if (getComponentCount() == 0 || getComponentCount() == 1) {
				this.addComponent(createControls());
			}
			this.setCurrentPage(currentPage);
			this.setTotalPage(totalPage);
		} else {
			if (getComponentCount() == 2) {
				removeComponent(getComponent(1));
			}
		}

		currentListData = searchService
				.findPagableListByCriteria(searchRequest);
		currentViewCount = currentListData.size();

		tableItem = new Table();
		tableItem.setWidth("100%");
		final CustomComponent tableWrap = new CustomComponent(tableItem);
		tableLazyLoadContainer = new LazyLoadWrapper(tableWrap);
		tableItem.addStyleName("striped");
		tableItem.setSortDisabled(true);

		// set column generator
		for (final Object propertyId : columnGenerators.keySet()) {
			tableItem.addGeneratedColumn(propertyId,
					columnGenerators.get(propertyId));
		}

		// set column width
		for (final Object propertyId : columnWidths.keySet()) {
			tableItem.setColumnWidth(propertyId, columnWidths.get(propertyId));
		}

		if (columnExpandId != null && !columnExpandId.equals("")) {
			tableItem.setColumnExpandRatio(columnExpandId, 1.0f);
		}

		if (sortColumnId != null && !sortColumnId.equals("")) {
			tableItem.setColumnIcon(
					sortColumnId,
					isAscending ? MyCollabResource
							.newResource("icons/16/arrow_down.png")
							: MyCollabResource
									.newResource("icons/16/arrow_up.png"));
		}

		tableItem.addListener(new Table.HeaderClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void headerClick(final Table.HeaderClickEvent event) {
				final String propertyId = (String) event.getPropertyId();

				if (propertyId.equals("selected")) {
					return;
				}

				if (searchRequest != null) {
					sortColumnId = propertyId;

					final S searchCriteria = searchRequest.getSearchCriteria();
					if (searchCriteria.getOrderByField() == null) {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
						isAscending = false;
					} else if (propertyId.equals(searchCriteria
							.getOrderByField())) {
						isAscending = !isAscending;
						searchCriteria
								.setSortDirection(searchCriteria
										.getSortDirection().equals(
												SearchCriteria.ASC) ? SearchCriteria.DESC
										: SearchCriteria.ASC);
					} else {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
						isAscending = false;
					}

					PagedBeanTable2.this.setSearchCriteria(searchCriteria);
				}
			}
		});

		final BeanItemContainer<T> container = new BeanItemContainer<T>(type,
				currentListData);
		tableItem.setPageLength(0);
		tableItem.setContainerDataSource(container);
		tableItem.setVisibleColumns(visibleColumns);
		tableItem.setColumnHeaders(columnHeaders);
		tableItem.setWidth("100%");

		if (getComponentCount() > 0) {
			final Component component0 = getComponent(0);
			if (component0 instanceof LazyLoadWrapper) {
				replaceComponent(component0, tableLazyLoadContainer);
			} else {
				this.addComponent(tableLazyLoadContainer, 0);
			}
		} else {
			this.addComponent(tableLazyLoadContainer, 0);
		}

	}

	public void fireSelectItemEvent(final T item) {
		if (selectableHandlers != null) {
			for (final SelectableItemHandler<T> handler : selectableHandlers) {
				handler.onSelect(item);
			}
		}
	}

	protected void fireTableEvent(final ApplicationEvent event) {

		final Class<? extends ApplicationEvent> eventType = event.getClass();
		if (mapEventListener == null) {
			return;
		}

		final Set<ApplicationEventListener<?>> eventSet = mapEventListener
				.get(eventType);
		if (eventSet != null) {
			final Iterator<ApplicationEventListener<?>> listenerSet = mapEventListener
					.get(eventType).iterator();

			while (listenerSet.hasNext()) {
				final ApplicationEventListener<?> listener = listenerSet.next();
				@SuppressWarnings("unchecked")
				final ApplicationEventListener<ApplicationEvent> l = (ApplicationEventListener<ApplicationEvent>) listener;
				l.handle(event);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getBeanByIndex(final Object itemId) {
		final Container container = tableItem.getContainerDataSource();
		final BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}

	public BeanItem<T> getBeanItem(final Object itemId) {
		final Container container = tableItem.getContainerDataSource();
		return (BeanItem<T>) container.getItem(itemId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getCurrentDataList() {
		final BeanItemContainer<T> containerDataSource = (BeanItemContainer<T>) tableItem
				.getContainerDataSource();
		final Collection<T> itemIds = containerDataSource.getItemIds();
		if (itemIds instanceof List) {
			return (List<T>) itemIds;
		} else {
			return new ArrayList<T>(itemIds);
		}
	}
	
	public List<T> getAllDataList(SearchRequest<S> searRequest){
		return (List<T>) searchService.findPagableListByCriteria(searchRequest);
	}

	private void pageChange(final int currentPage) {
		if (searchRequest != null) {
			this.currentPage = currentPage;
			searchRequest.setCurrentPage(currentPage);
			doSearch();

			if (pagableHandlers != null) {
				for (final PagableHandler handler : pagableHandlers) {
					handler.move(currentPage);
				}
			}
		}
	}

	@Override
	public void refresh() {
		doSearch();
	}

	@Override
	public void setColumnExpandRatio(final Object propertyId,
			final float expandRation) {
		columnExpandId = propertyId;
	}

	@Override
	public void setColumnWidth(final Object propertyId, final int width) {
		columnWidths.put(propertyId, width);
	}

	private void setCurrentPage(final int currentPage) {
		this.currentPage = currentPage;
		currentPageTextField.setValue(currentPage);
		checkButtonStatus();
	}

	@Override
	public void setSearchCriteria(final S searchCriteria) {
		searchRequest = new SearchRequest<S>(searchCriteria, currentPage,
				displayNumItems);
		doSearch();
	}

	private void setTotalPage(final int totalPage) {
		this.totalPage = totalPage;
		totalPagesLabel.setValue(String.valueOf(totalPage));
		checkButtonStatus();
	}

	@Override
	public int totalItemsCount() {
		return totalCount;
	}
}
