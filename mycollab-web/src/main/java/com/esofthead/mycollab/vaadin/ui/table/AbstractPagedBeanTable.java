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
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPagedBeanTable<S extends SearchCriteria, T>
		extends VerticalLayout implements IPagedBeanTable<S, T> {
	private static final long serialVersionUID = 1L;

	protected int displayNumItems = 20;

	protected Button first, previous1, previous2, next1, next2, last, current;
	protected Label ss1, ss2;
	protected List<T> currentListData;

	protected LazyLoadWrapper tableLazyLoadContainer;

	protected Object sortColumnId;
	protected HorizontalLayout pageManagement;
	protected boolean isAscending = true;

	protected Object columnExpandId;
	protected SearchRequest<S> searchRequest;
	protected int currentPage = 1;
	protected int totalPage = 1;
	protected int currentViewCount;
	protected int totalCount;
	protected Table tableItem;
	protected CssLayout controlBarWrapper;
	protected Map<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> mapEventListener;
	protected Set<SelectableItemHandler<T>> selectableHandlers;
	protected Set<PagableHandler> pagableHandlers;
	protected final Class<T> type;

	protected final String[] visibleColumns;
	protected final String[] columnHeaders;

	protected final Map<Object, ColumnGenerator> columnGenerators = new HashMap<Object, Table.ColumnGenerator>();
	protected final Map<Object, Integer> columnWidths = new HashMap<Object, Integer>();

	public AbstractPagedBeanTable(final Class<T> type,
			final String[] visibleColumns, final String[] columnHeaders) {
		this.visibleColumns = visibleColumns;
		this.columnHeaders = columnHeaders;
		this.type = type;

		setStyleName("list-view");
	}

	@Override
	public void addSelectableItemHandler(final SelectableItemHandler<T> handler) {
		if (selectableHandlers == null) {
			selectableHandlers = new HashSet<SelectableItemHandler<T>>();
		}
		selectableHandlers.add(handler);
	}

	@Override
	public int currentViewCount() {
		return currentViewCount;
	}

	@Override
	public int totalItemsCount() {
		return totalCount;
	}

	@Override
	public void addPagableHandler(final PagableHandler handler) {
		if (pagableHandlers == null) {
			pagableHandlers = new HashSet<PagableHandler>();
		}
		pagableHandlers.add(handler);

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

	@Override
	public void addGeneratedColumn(final Object id,
			final ColumnGenerator generatedColumn) {
		columnGenerators.put(id, generatedColumn);
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

	@Override
	public void setSearchCriteria(final S searchCriteria) {
		searchRequest = new SearchRequest<S>(searchCriteria, currentPage,
				displayNumItems);
		doSearch();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getBeanByIndex(final Object itemId) {
		final Container container = tableItem.getContainerDataSource();
		final BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}

	@Override
	public void refresh() {
		doSearch();
	}

	protected void pageChange(final int currentPage) {
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

	public void fireSelectItemEvent(final T item) {
		if (selectableHandlers != null) {
			for (final SelectableItemHandler<T> handler : selectableHandlers) {
				handler.onSelect(item);
			}
		}
	}

	private void addStylePagingButton() {
		if (first != null)
			first.addStyleName("buttonPaging");
		if (last != null)
			last.addStyleName("buttonPaging");
		if (ss1 != null)
			ss1.addStyleName("buttonPaging");
		if (ss2 != null)
			ss2.addStyleName("buttonPaging");
		if (next1 != null)
			next1.addStyleName("buttonPaging");
		if (next2 != null)
			next2.addStyleName("buttonPaging");
		if (previous2 != null)
			previous2.addStyleName("buttonPaging");
		if (previous1 != null)
			previous1.addStyleName("buttonPaging");
		if (current != null)
			current.addStyleName("buttonPaging");
	}

	private CssLayout createControls() {
		controlBarWrapper = new CssLayout();
		controlBarWrapper.setStyleName("listControl");
		controlBarWrapper.setWidth("100%");

		final HorizontalLayout controlBar = new HorizontalLayout();
		controlBar.setWidth("100%");
		controlBarWrapper.addComponent(controlBar);

		pageManagement = new HorizontalLayout();

		// defined layout here ---------------------------
		ss1 = new Label("...");
		ss2 = new Label("...");

		current = new ButtonLink("" + currentPage, new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageChange(currentPage);
			}
		});

		first = new ButtonLink("1", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(1);
			}
		});

		previous1 = new ButtonLink("" + (currentPage - 1), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage - 1);
			}
		});
		previous2 = new ButtonLink("" + (currentPage - 2), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage - 2);
			}
		});
		next1 = new ButtonLink("" + (currentPage + 1), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage + 1);
			}
		});
		next2 = new ButtonLink("" + (currentPage + 2), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage + 2);
			}
		});
		last = new ButtonLink("" + totalPage, new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(AbstractPagedBeanTable.this.totalPage);
			}
		});
		first.addStyleName("pagedtable-first");
		previous1.addStyleName("pagedtable-previous");
		previous2.addStyleName("pagedtable-previous");
		next1.addStyleName("pagedtable-next");
		next2.addStyleName("pagedtable-next");
		last.addStyleName("pagedtable-last");

		first.addStyleName("pagedtable-button");
		previous1.addStyleName("pagedtable-button");
		previous2.addStyleName("pagedtable-button");
		next1.addStyleName("pagedtable-button");
		next2.addStyleName("pagedtable-button");
		last.addStyleName("pagedtable-button");

		addStylePagingButton();
		current.removeStyleName("buttonPaging");

		handleAddComponent(pageManagement, currentPage);

		pageManagement.setWidth(null);
		pageManagement.setSpacing(true);
		controlBar.addComponent(pageManagement);
		controlBar
				.setComponentAlignment(pageManagement, Alignment.MIDDLE_RIGHT);

		return controlBarWrapper;
	}

	private void handleAddComponent(HorizontalLayout page, int currentPage) {
		page.removeAllComponents();
		int first = 1, pre1 = currentPage - 1, pre2 = currentPage - 2;
		if (first < currentPage)
			page.addComponent(this.first);
		if (currentPage >= 5)
			page.addComponent(ss1);
		if (first < pre2)
			page.addComponent(previous2);
		if (first < pre1)
			page.addComponent(previous1);
		page.addComponent(current);
		int range = totalPage - currentPage;
		if (range >= 1)
			page.addComponent(this.next1);
		if (range >= 2)
			page.addComponent(this.next2);
		if (range >= 4)
			page.addComponent(ss2);
		if (range >= 3)
			page.addComponent(last);
	}

	abstract protected int queryTotalCount();

	abstract protected List<T> queryCurrentData();

	protected void doSearch() {
		totalCount = queryTotalCount();
		totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		if (totalPage > 1) {
			// Define button layout
			if (controlBarWrapper != null)
				this.removeComponent(controlBarWrapper);
			this.addComponent(createControls());
		} else {
			if (getComponentCount() == 2) {
				removeComponent(getComponent(1));
			}
		}

		currentListData = queryCurrentData();
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

					AbstractPagedBeanTable.this
							.setSearchCriteria(searchCriteria);
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

}
