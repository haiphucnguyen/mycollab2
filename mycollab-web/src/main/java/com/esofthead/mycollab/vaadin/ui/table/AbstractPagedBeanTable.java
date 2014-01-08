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
package com.esofthead.mycollab.vaadin.ui.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.domain.NullCustomViewStore;
import com.esofthead.mycollab.common.service.CustomViewStoreService;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.JsonDeSerializer;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.MyCollabResource;
import com.google.gson.reflect.TypeToken;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 * @param <S>
 * @param <B>
 */
public abstract class AbstractPagedBeanTable<S extends SearchCriteria, B>
		extends VerticalLayout implements IPagedBeanTable<S, B> {
	private static final long serialVersionUID = 1L;

	protected int displayNumItems = SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS;
	protected List<B> currentListData;

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
	protected Set<SelectableItemHandler<B>> selectableHandlers;
	protected Set<PagableHandler> pagableHandlers;

	protected final Class<B> type;

	private TableViewField requiredColumn;
	private List<TableViewField> displayColumns;
	private List<TableViewField> defaultSelectedColumns;

	protected final Map<Object, ColumnGenerator> columnGenerators = new HashMap<Object, Table.ColumnGenerator>();

	public AbstractPagedBeanTable(Class<B> type,
			List<TableViewField> displayColumns) {
		this(type, null, displayColumns);
	}

	public AbstractPagedBeanTable(Class<B> type, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(type, null, requiredColumn, displayColumns);
	}

	public AbstractPagedBeanTable(Class<B> type, String viewId,
			TableViewField requiredColumn, List<TableViewField> displayColumns) {
		if (viewId != null) {
			CustomViewStoreService customViewStoreService = ApplicationContextUtil
					.getSpringBean(CustomViewStoreService.class);
			CustomViewStore viewLayoutDef = customViewStoreService
					.getViewLayoutDef(AppContext.getAccountId(),
							AppContext.getUsername(), viewId);
			if (!(viewLayoutDef instanceof NullCustomViewStore)) {

				List<TableViewField> selectedColumns = JsonDeSerializer
						.fromJson(viewLayoutDef.getViewinfo(),
								new TypeToken<List<TableViewField>>() {
								}.getType());
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

	public void setDisplayColumns(List<TableViewField> viewFields) {
		this.displayColumns = viewFields;
		displayTableColumns();
		this.markAsDirty();
	}

	private void displayTableColumns() {
		List<String> visibleColumnsCol = new ArrayList<String>();
		List<String> columnHeadersCol = new ArrayList<String>();

		if (requiredColumn != null) {
			visibleColumnsCol.add(requiredColumn.getField());
			columnHeadersCol.add(requiredColumn.getDesc());
			this.tableItem.setColumnWidth(requiredColumn.getField(),
					requiredColumn.getDefaultWidth());
		}

		for (int i = 0; i < displayColumns.size(); i++) {
			TableViewField viewField = displayColumns.get(i);
			visibleColumnsCol.add(viewField.getField());
			columnHeadersCol.add(viewField.getDesc());

			if (i == 0) {
				this.tableItem.setColumnExpandRatio(viewField.getField(), 1.0f);
			} else {
				this.tableItem.setColumnWidth(viewField.getField(),
						viewField.getDefaultWidth());
			}
		}

		String[] visibleColumns = visibleColumnsCol.toArray(new String[0]);
		String[] columnHeaders = columnHeadersCol.toArray(new String[0]);

		this.tableItem.setVisibleColumns(visibleColumns);
		this.tableItem.setColumnHeaders(columnHeaders);
	}

	@Override
	public void addSelectableItemHandler(final SelectableItemHandler<B> handler) {
		if (this.selectableHandlers == null) {
			this.selectableHandlers = new HashSet<SelectableItemHandler<B>>();
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

	@Override
	public List<B> getCurrentDataList() {
		// final BeanItemContainer<B> containerDataSource =
		// (BeanItemContainer<B>) this.tableItem
		// .getContainerDataSource();
		// final Collection<B> itemIds = containerDataSource.getItemIds();
		// if (itemIds instanceof List) {
		// return (List<B>) itemIds;
		// } else {
		// return new ArrayList<B>(itemIds);
		// }
		return currentListData;
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
	public B getBeanByIndex(final Object itemId) {
		final Container container = this.tableItem.getContainerDataSource();
		final BeanItem<B> item = (BeanItem<B>) container.getItem(itemId);
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

	public void fireSelectItemEvent(final B item) {
		if (this.selectableHandlers != null) {
			for (final SelectableItemHandler<B> handler : this.selectableHandlers) {
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
			}, false);
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
					}, false);
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
					}, false);
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
				}, false);
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
					}, false);
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
					}, false);
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
					}, false);
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

	abstract protected List<B> queryCurrentData();

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
		this.tableItem.addStyleName("striped");
		this.tableItem.setSortEnabled(false);

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

		this.tableItem.addHeaderClickListener(new Table.HeaderClickListener() {
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

		final BeanItemContainer<B> container = new BeanItemContainer<B>(
				this.type, this.currentListData);
		this.tableItem.setPageLength(0);
		this.tableItem.setContainerDataSource(container);
		displayTableColumns();
		this.tableItem.setWidth("100%");

		if (this.getComponentCount() > 0) {
			final Component component0 = this.getComponent(0);
			if (component0 instanceof Table) {
				this.replaceComponent(component0, tableItem);
			} else {
				this.addComponent(tableItem, 0);
			}
		} else {
			this.addComponent(tableItem, 0);
		}

	}

	public List<TableViewField> getDefaultSelectedColumns() {
		return defaultSelectedColumns;
	}

	public List<TableViewField> getDisplayColumns() {
		return displayColumns;
	}

	public Object[] getVisibleColumns() {
		return tableItem.getVisibleColumns();
	}

}
