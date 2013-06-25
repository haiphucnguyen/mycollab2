package com.esofthead.mycollab.vaadin.ui.table;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;

public class PagedBeanTable2<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends AbstractPagedBeanTable<S, T> {

	private static final long serialVersionUID = 1L;

	private final SearchService searchService;

	public PagedBeanTable2(final SearchService searchService,
			final Class<T> type, final String[] visibleColumns,
			final String[] columnHeaders) {
		super(type, visibleColumns, columnHeaders);
		this.searchService = searchService;
	}

	@Override
	protected int queryTotalCount() {
		return searchService.getTotalCount(searchRequest.getSearchCriteria());
	}

	@Override
	protected List<T> queryCurrentData() {
		return searchService.findPagableListByCriteria(searchRequest);
	}

}
