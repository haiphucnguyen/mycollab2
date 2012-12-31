/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class BeanPagedList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
        extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private SearchService searchService;
    private Class<? extends BeanPagedList.RowDisplayHandler<T>> rowDisplayHandler;

    public BeanPagedList(SearchService searchService,
            Class<? extends BeanPagedList.RowDisplayHandler<T>> rowDisplayHandler) {
        this.searchService = searchService;
        this.rowDisplayHandler = rowDisplayHandler;
    }

    @SuppressWarnings("unchecked")
    public void setSearchCriteria(S searchCriteria) {
        this.removeAllComponents();

        SearchRequest<S> searchRequest = new SearchRequest<S>(searchCriteria,
                0, Integer.MAX_VALUE);
        List<T> currentListData = searchService
                .findPagableListByCriteria(searchRequest);
        int i = 0;
        try {
            for (T item : currentListData) {
                BeanPagedList.RowDisplayHandler<T> rowHandler = rowDisplayHandler.newInstance();
                Component row = rowHandler.generateRow(item, i);
                this.addComponent(row);
                i++;
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        }
    }

    public interface RowDisplayHandler<T> {

        Component generateRow(T obj, int rowIndex);
    }
}
