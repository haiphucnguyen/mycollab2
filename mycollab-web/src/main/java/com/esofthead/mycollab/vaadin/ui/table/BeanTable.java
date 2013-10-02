package com.esofthead.mycollab.vaadin.ui.table;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

public class BeanTable<SearchService extends ISearchableService<S>, S extends SearchCriteria, T> extends Table implements IBeanTable {

    private static final long serialVersionUID = 1L;
    private String[] visibleColumns;
    private String[] columnHeaders;
    private Class typeClass;
    private SearchService searchService;
    private Map<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> mapEventListener;

    public BeanTable(SearchService searchService, Class typeClass, String[] visibleColumns, String[] columnHeaders) {
        super();
        this.searchService = searchService;
        this.typeClass = typeClass;
        this.visibleColumns = visibleColumns;
        this.columnHeaders = columnHeaders;
        this.setStyleName("list-view");
    }

	public void setSearchCriteria(S searchCriteria) {
        List itemsCol = searchService.findPagableListByCriteria(new SearchRequest<S>(searchCriteria, 0, Integer.MAX_VALUE));
        setItems(itemsCol);
    }

    public void setItems(List<T> itemsCol) {
        BeanItemContainer<T> container = new BeanItemContainer<T>(typeClass,
                itemsCol);
        this.setContainerDataSource(container);
        this.setVisibleColumns(visibleColumns);
        this.setColumnHeaders(columnHeaders);
        this.setPageLength(itemsCol.size());
    }

    @SuppressWarnings("unchecked")
    public T getBeanByIndex(Object itemId) {
        Container container = this.getContainerDataSource();
        BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
        return (item == null) ? null : item.getBean();
    }

    public void addTableListener(ApplicationEventListener<? extends ApplicationEvent> listener) {
        if (mapEventListener == null) {
            mapEventListener = new HashMap<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>>();
        }

        Set<ApplicationEventListener<?>> listenerSet = mapEventListener.get(listener
                .getEventType());
        if (listenerSet == null) {
            listenerSet = new LinkedHashSet<ApplicationEventListener<?>>();
            mapEventListener.put(listener.getEventType(), listenerSet);
        }

        listenerSet.add(listener);
    }

    protected void fireTableEvent(ApplicationEvent event) {

        Class<? extends ApplicationEvent> eventType = event.getClass();

        Set<ApplicationEventListener<?>> eventSet = mapEventListener.get(eventType);
        if (eventSet != null) {
            Iterator<ApplicationEventListener<?>> listenerSet = mapEventListener.get(eventType).iterator();

            while (listenerSet.hasNext()) {
                ApplicationEventListener<?> listener = listenerSet.next();
                @SuppressWarnings("unchecked")
                ApplicationEventListener<ApplicationEvent> l = (ApplicationEventListener<ApplicationEvent>) listener;
                l.handle(event);
            }
        }
    }
}
