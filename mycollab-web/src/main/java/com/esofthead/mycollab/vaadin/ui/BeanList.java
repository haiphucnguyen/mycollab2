package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

public class BeanList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private SearchService searchService;

	private RowDisplayHandler<T> rowDisplayHandler;

	public BeanList(SearchService searchService,
			RowDisplayHandler<T> rowDisplayHandler) {
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
		for (T item : currentListData) {
			Component row = rowDisplayHandler.generateRow(item, i);
			this.addComponent(row);
			i++;
		}
	}

	public interface RowDisplayHandler<T> {
		Component generateRow(T obj, int rowIndex);
	}
}
