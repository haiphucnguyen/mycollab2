package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import java.util.Collection;

public interface IPagedBeanTable<S extends SearchCriteria, T>
        extends HasSelectableItemHandlers<T>, HasPagableHandlers, Component {

    void setSearchCriteria(S searchCriteria);

    Collection<T> getCurrentDataList();
    
    void addTableListener(ApplicationEventListener<? extends ApplicationEvent> listener);
    
    void addGeneratedColumn(Object id, Table.ColumnGenerator generatedColumn);
    
    void setColumnExpandRatio(Object propertyId, float expandRation);
    
    void setColumnWidth(Object propertyId, int width);

    public static class TableClickEvent extends ApplicationEvent {

        private String fieldName;

        public TableClickEvent(IPagedBeanTable source, Object data, String fieldName) {
            super(source, data);
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }
}
