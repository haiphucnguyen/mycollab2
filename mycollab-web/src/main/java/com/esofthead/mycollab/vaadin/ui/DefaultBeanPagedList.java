/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.ui.Component;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class DefaultBeanPagedList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
        extends AbstractBeanPagedList<S, T> {

    private static final long serialVersionUID = 1L;
    private final SearchService searchService;

    public DefaultBeanPagedList(
            SearchService searchService,
            Class<? extends RowDisplayHandler<T>> rowDisplayHandler, int defaultNumberSearchItems) {
        super(rowDisplayHandler, defaultNumberSearchItems);
        this.searchService = searchService;
    }

    @Override
    public void doSearch() {
        totalCount = searchService.getTotalCount(searchRequest
                .getSearchCriteria());
        totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
        if (searchRequest.getCurrentPage() > totalPage) {
            searchRequest.setCurrentPage(totalPage);
        }

        this.setCurrentPage(currentPage);
        this.setTotalPage(totalPage);

        List<T> currentListData = searchService
                .findPagableListByCriteria(searchRequest);
        listContainer.removeAllComponents();
        int i = 0;
        try {
            for (T item : currentListData) {
                RowDisplayHandler<T> rowHandler = (RowDisplayHandler<T>) rowDisplayHandler
                        .newInstance();
                Component row = rowHandler.generateRow(item, i);
                listContainer.addComponent(row);
                i++;
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        }
    }
}
