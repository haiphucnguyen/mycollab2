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

import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.service.CustomViewStoreService;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPagedBeanTable<S extends SearchCriteria, T>
		extends VerticalLayout implements IPagedBeanTable<S, T> {
	private static final long serialVersionUID = 1L;

	protected int displayNumItems = SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS;
	protected List<T> currentListData;

	protected HorizontalLayout pageManagement;

	protected boolean isAscending = true;
	protected Object sortColumnId;

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

	private TableViewField requiredColumn;
	private List<TableViewField> displayColumns;
	private List<TableViewField> defaultSelectedColumns;

	protected final Map<Object, ColumnGenerator> columnGenerators = new HashMap<Object, Table.ColumnGenerator>();

	public AbstractPagedBeanTable(Class<T> type,
			List<TableViewField> displayColumns) {
		this(type, null, displayColumns);
	}

	public AbstractPagedBeanTable(Class<T> type, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(type, null, requiredColumn, displayColumns);
	}

	public AbstractPagedBeanTable(Class<T> type, String viewId,
			TableViewField requiredColumn, List<TableViewField> displayColumns) {
		if (viewId != null) {
			CustomViewStoreService customViewStoreService = AppContext
					.getSpringBean(CustomViewStoreService.class);
			CustomViewStore viewLayoutDef = customViewStoreService
					.getViewLayoutDef(AppContext.getAccountId(),
							AppContext.getUsername(), viewId);
			if (viewLayoutDef != null) {
				XStream xstream = new XStream(new StaxDriver());
				List<TableViewField> selectedColumns = (List<TableViewField>) xstream
						.fromXML(viewLayoutDef.getViewinfo());
				this.displayColumns = selectedColumns;
			} else {
				this.displayColumns = displayColumns;
			}
		} else {
			this.displayColumns = displayColumns;
		}
		this.defaultSelectedColumns = displayColumns;
		this.requiredColumn = requiredColumn;
		this.type = type;

		this.setStyleName("list-view");
	}

	public void setTableViewFieldCollection(List<TableViewField> viewFields) {
		this.displayColumns = viewFields;
		setTableViewFieldCollection(displayColumns, true);
	}

	private void setTableViewFieldCollection(List<TableViewField> viewFields,
			boolean requestRepaint) {
		List<String> visibleColumnsCol = new ArrayList<String>();
		List<String> columnHeadersCol = new ArrayList<String>();

		if (requiredColumn != null) {
			visibleColumnsCol.add(requiredColumn.getField());
			columnHeadersCol.add(requiredColumn.getDesc());
		}

		for (int i = 0; i < viewFields.size(); i++) {
			TableViewField viewField = viewFields.get(i);
			visibleColumnsCol.add(viewField.getField());
			columnHeadersCol.add(viewField.getDesc());

			if (i == 0) {
				this.tableItem.setColumnExpandRatio(viewField.getField(), 1.0f);
			} else {
				this.tableItem.setColumnWidth(viewField.getField(),
						viewField.getDefaultWidth());
			}
		}

		String[] visibleColumns = (String[]) visibleColumnsCol
				.toArray(new String[0]);
		String[] columnHeaders = (String[]) columnHeadersCol
				.toArray(new String[0]);

		this.tableItem.setVisibleColumns(visibleColumns);
		this.tableItem.setColumnHeaders(columnHeaders);

		if (requestRepaint) {
			this.tableItem.requestRepaint();
		}
	}

	@Override
	public void addSelectableItemHandler(final SelectableItemHandler<T> handler) {
		if (this.selectableHandlers == null) {
			this.selectableHandlers = new HashSet<SelectableItemHandler<T>>();
		}
		this.selectableHandlers.add(handler);
	}

	@Override
	public int currentViewCount() {
		return this.currentViewCount;
	}

	@Override
	public int totalItemsCount() {
		return this.totalCount;
	}

	@Override
	public void addPagableHandler(final PagableHandler handler) {
		if (this.pagableHandlers == null) {
			this.pagableHandlers = new HashSet<PagableHandler>();
		}
		this.pagableHandlers.add(handler);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getCurrentDataList() {
		final BeanItemContainer<T> containerDataSource = (BeanItemContainer<T>) this.tableItem
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
		if (this.mapEventListener == null) {
			this.mapEventListener = new HashMap<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>>();
		}

		Set<ApplicationEventListener<?>> listenerSet = this.mapEventListener
				.get(listener.getEventType());
		if (listenerSet == null) {
			listenerSet = new LinkedHashSet<ApplicationEventListener<?>>();
			this.mapEventListener.put(listener.getEventType(), listenerSet);
		}

		listenerSet.add(listener);
	}

	@Override
	public void addGeneratedColumn(final Object id,
			final ColumnGenerator generatedColumn) {
		this.columnGenerators.put(id, generatedColumn);
	}

	@Override
	public void setSearchCriteria(final S searchCriteria) {
		this.searchRequest = new SearchRequest<S>(searchCriteria,
				this.currentPage, this.displayNumItems);
		this.doSearch();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getBeanByIndex(final Object itemId) {
		final Container container = this.tableItem.getContainerDataSource();
		final BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}

	@Override
	public void refresh() {
		this.doSearch();
	}

	protected void pageChange(final int currentPage) {
		if (this.searchRequest != null) {
			this.currentPage = currentPage;
			this.searchRequest.setCurrentPage(currentPage);
			this.doSearch();

			if (this.pagableHandlers != null) {
				for (final PagableHandler handler : this.pagableHandlers) {
					handler.move(currentPage);
				}
			}
		}
	}

	protected void fireTableEvent(final ApplicationEvent event) {

		final Class<? extends ApplicationEvent> eventType = event.getClass();
		if (this.mapEventListener == null) {
			return;
		}

		final Set<ApplicationEventListener<?>> eventSet = this.mapEventListener
				.get(eventType);
		if (eventSet != null) {
			final Iterator<ApplicationEventListener<?>> listenerSet = this.mapEventListener
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
		if (this.selectableHandlers != null) {
			for (final SelectableItemHandler<T> handler : this.selectableHandlers) {
				handler.onSelect(item);
			}
		}
	}

	private CssLayout createControls() {
		this.controlBarWrapper = new CssLayout();
		this.controlBarWrapper.setStyleName("listControl");
		this.controlBarWrapper.setWidth("100%");

		final HorizontalLayout controlBar = new HorizontalLayout();
		controlBar.setWidth("100%");
		this.controlBarWrapper.addComponent(controlBar);

		this.pageManagement = new HorizontalLayout();

		// defined layout here ---------------------------

		if (this.currentPage > 1) {
			final Button firstLink = new ButtonLink("1", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					AbstractPagedBeanTable.this.pageChange(1);
				}
			});
			firstLink.addStyleName("buttonPaging");
			this.pageManagement.addComponent(firstLink);
		}
		if (this.currentPage >= 5) {
			final Label ss1 = new Label("...");
			ss1.addStyleName("buttonPaging");
			this.pageManagement.addComponent(ss1);
		}
		if (this.currentPage > 3) {
			final Button previous2 = new ButtonLink(
					"" + (this.currentPage - 2), new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractPagedBeanTable.this
									.pageChange(AbstractPagedBeanTable.this.currentPage - 2);
						}
					});
			previous2.addStyleName("buttonPaging");
			this.pageManagement.addComponent(previous2);
		}
		if (this.currentPage > 2) {
			final Button previous1 = new ButtonLink(
					"" + (this.currentPage - 1), new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractPagedBeanTable.this
									.pageChange(AbstractPagedBeanTable.this.currentPage - 1);
						}
					});
			previous1.addStyleName("buttonPaging");
			this.pageManagement.addComponent(previous1);
		}
		// Here add current ButtonLink
		final Button current = new ButtonLink("" + this.currentPage,
				new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						AbstractPagedBeanTable.this
								.pageChange(AbstractPagedBeanTable.this.currentPage);
					}
				});
		current.addStyleName("buttonPaging");
		current.addStyleName("buttonPagingcurrent");

		this.pageManagement.addComponent(current);
		final int range = this.totalPage - this.currentPage;
		if (range >= 1) {
			final Button next1 = new ButtonLink("" + (this.currentPage + 1),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractPagedBeanTable.this
									.pageChange(AbstractPagedBeanTable.this.currentPage + 1);
						}
					});
			next1.addStyleName("buttonPaging");
			this.pageManagement.addComponent(next1);
		}
		if (range >= 2) {
			final Button next2 = new ButtonLink("" + (this.currentPage + 2),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractPagedBeanTable.this
									.pageChange(AbstractPagedBeanTable.this.currentPage + 2);
						}
					});
			next2.addStyleName("buttonPaging");
			this.pageManagement.addComponent(next2);
		}
		if (range >= 4) {
			final Label ss2 = new Label("...");
			ss2.addStyleName("buttonPaging");
			this.pageManagement.addComponent(ss2);
		}
		if (range >= 3) {
			final Button last = new ButtonLink("" + this.totalPage,
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractPagedBeanTable.this
									.pageChange(AbstractPagedBeanTable.this.totalPage);
						}
					});
			last.addStyleName("buttonPaging");
			this.pageManagement.addComponent(last);
		}

		this.pageManagement.setWidth(null);
		this.pageManagement.setSpacing(true);
		controlBar.addComponent(this.pageManagement);
		controlBar.setComponentAlignment(this.pageManagement,
				Alignment.MIDDLE_RIGHT);

		return this.controlBarWrapper;
	}

	abstract protected int queryTotalCount();

	abstract protected List<T> queryCurrentData();

	protected void doSearch() {
		this.totalCount = this.queryTotalCount();
		this.totalPage = (this.totalCount - 1)
				/ this.searchRequest.getNumberOfItems() + 1;
		if (this.searchRequest.getCurrentPage() > this.totalPage) {
			this.searchRequest.setCurrentPage(this.totalPage);
		}

		if (this.totalPage > 1) {
			// Define button layout
			if (this.controlBarWrapper != null) {
				this.removeComponent(this.controlBarWrapper);
			}
			this.addComponent(this.createControls());
		} else {
			if (this.getComponentCount() == 2) {
				this.removeComponent(this.getComponent(1));
			}
		}

		this.currentListData = this.queryCurrentData();
		this.currentViewCount = this.currentListData.size();

		this.tableItem = new Table();
		this.tableItem.setWidth("100%");
		final CustomComponent tableWrap = new CustomComponent(this.tableItem);
		LazyLoadWrapper tableLazyLoadContainer = new LazyLoadWrapper(tableWrap);
		this.tableItem.addStyleName("striped");
		this.tableItem.setSortDisabled(true);

		// set column generator
		for (final Object propertyId : this.columnGenerators.keySet()) {
			this.tableItem.addGeneratedColumn(propertyId,
					this.columnGenerators.get(propertyId));
		}

		if (this.sortColumnId != null && !this.sortColumnId.equals("")) {
			this.tableItem.setColumnIcon(
					this.sortColumnId,
					this.isAscending ? MyCollabResource
							.newResource("icons/16/arrow_down.png")
							: MyCollabResource
									.newResource("icons/16/arrow_up.png"));
		}

		this.tableItem.addListener(new Table.HeaderClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void headerClick(final Table.HeaderClickEvent event) {
				final String propertyId = (String) event.getPropertyId();

				if (propertyId.equals("selected")) {
					return;
				}

				if (AbstractPagedBeanTable.this.searchRequest != null) {
					AbstractPagedBeanTable.this.sortColumnId = propertyId;

					final S searchCriteria = AbstractPagedBeanTable.this.searchRequest
							.getSearchCriteria();
					if (searchCriteria.getOrderByField() == null) {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
						AbstractPagedBeanTable.this.isAscending = false;
					} else if (propertyId.equals(searchCriteria
							.getOrderByField())) {
						AbstractPagedBeanTable.this.isAscending = !AbstractPagedBeanTable.this.isAscending;
						searchCriteria
								.setSortDirection(searchCriteria
										.getSortDirection().equals(
												SearchCriteria.ASC) ? SearchCriteria.DESC
										: SearchCriteria.ASC);
					} else {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
						AbstractPagedBeanTable.this.isAscending = false;
					}

					AbstractPagedBeanTable.this
							.setSearchCriteria(searchCriteria);
				}
			}
		});

		final BeanItemContainer<T> container = new BeanItemContainer<T>(
				this.type, this.currentListData);
		this.tableItem.setPageLength(0);
		this.tableItem.setContainerDataSource(container);
		setTableViewFieldCollection(this.displayColumns);
		this.tableItem.setWidth("100%");

		if (this.getComponentCount() > 0) {
			final Component component0 = this.getComponent(0);
			if (component0 instanceof LazyLoadWrapper) {
				this.replaceComponent(component0, tableLazyLoadContainer);
			} else {
				this.addComponent(tableLazyLoadContainer, 0);
			}
		} else {
			this.addComponent(tableLazyLoadContainer, 0);
		}

	}

	public List<TableViewField> getDefaultSelectedColumns() {
		return defaultSelectedColumns;
	}

	public Object[] getVisibleColumns() {
		return tableItem.getVisibleColumns();
	}

}
