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
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPagedBeanTable<S extends SearchCriteria, T>
		extends VerticalLayout implements IPagedBeanTable<S, T> {
	private static final long serialVersionUID = 1L;

	protected int displayNumItems = SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS;
	protected Object columnExpandId;
	protected SearchRequest<S> searchRequest;
	protected int currentPage = 1;
	protected int totalPage = 1;
	protected int currentViewCount;
	protected int totalCount;
	protected Table tableItem;
	protected Map<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> mapEventListener;
	protected Set<SelectableItemHandler<T>> selectableHandlers;
	protected Set<PagableHandler> pagableHandlers;

	protected final Map<Object, ColumnGenerator> columnGenerators = new HashMap<Object, Table.ColumnGenerator>();
	protected final Map<Object, Integer> columnWidths = new HashMap<Object, Integer>();

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

	abstract protected void doSearch();

}
