package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import java.util.List;

public class BeanTable<SearchService extends ISearchableService<S>, S extends SearchCriteria, T> extends Table {
    
    private static final long serialVersionUID = 1L;
    private Class typeClass;
    private SearchService searchService;
    
    public BeanTable(SearchService searchService, Class typeClass) {
        super();
        this.searchService = searchService;
        this.typeClass = typeClass;
    }
    
    public void setSearchCriteria(S searchCriteria) {
        List items = searchService.findPagableListByCriteria(new SearchRequest<S>(searchCriteria, 0, Integer.MAX_VALUE));
        BeanItemContainer<T> container = new BeanItemContainer<T>(typeClass,
                items);
        this.setContainerDataSource(container);
    }
    
    @SuppressWarnings("unchecked")
    public T getBeanByIndex(Object itemId) {
        Container container = this.getContainerDataSource();
        BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
        return (item == null) ? null : item.getBean();
    }
}
