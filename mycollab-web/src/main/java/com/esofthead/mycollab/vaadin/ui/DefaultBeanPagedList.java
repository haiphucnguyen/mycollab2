/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;

/**
 * 
 * @author haiphucnguyen
 */
public class DefaultBeanPagedList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends AbstractBeanPagedList<S, T> {

	private static final long serialVersionUID = 1L;
	private final SearchService searchService;

	public DefaultBeanPagedList(SearchService searchService,
			RowDisplayHandler<T> rowDisplayHandler) {
		this(searchService, rowDisplayHandler, 20);
	}

	public DefaultBeanPagedList(SearchService searchService,
			RowDisplayHandler<T> rowDisplayHandler, int defaultNumberSearchItems) {
		super(rowDisplayHandler, defaultNumberSearchItems);
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
