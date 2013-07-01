package com.esofthead.mycollab.vaadin.ui.table;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

public interface IPagedBeanTable<S extends SearchCriteria, T>
        extends HasSelectableItemHandlers<T>, HasPagableHandlers, Component, IBeanTable {

    void setSearchCriteria(S searchCriteria);

    List<T> getCurrentDataList();

    void addTableListener(ApplicationEventListener<? extends ApplicationEvent> listener);

    void addGeneratedColumn(Object id, Table.ColumnGenerator generatedColumn);

    void setColumnExpandRatio(Object propertyId, float expandRation);

    void setColumnWidth(Object propertyId, int width);
    
    T getBeanByIndex(Object itemId);
    
    void refresh();
}
