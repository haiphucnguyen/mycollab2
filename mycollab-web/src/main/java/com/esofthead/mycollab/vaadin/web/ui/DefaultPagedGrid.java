package com.esofthead.mycollab.vaadin.web.ui;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class DefaultPagedGrid<SearchService extends ISearchableService<S>, S extends SearchCriteria, B> extends
        VerticalLayout {
    private int displayNumItems = SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS;
    protected Collection<B> currentListData;
    protected SearchRequest<S> searchRequest;
    private SearchService searchService;
    private Class<B> type;

    private Grid grid;

    public DefaultPagedGrid(SearchService searchService, Class<B> type, List<TableViewField> displayColumns) {
        this.searchService = searchService;

    }

    protected int queryTotalCount() {
        return searchService.getTotalCount(searchRequest.getSearchCriteria());
    }

    protected List<B> queryCurrentData() {
        return searchService.findPagableListByCriteria(searchRequest);
    }
}
