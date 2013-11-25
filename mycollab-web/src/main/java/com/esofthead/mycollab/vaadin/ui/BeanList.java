/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Generic list item
 * 
 * @param <SearchService>
 *            search service generic interface
 * @param <S>
 *            search criteria
 * @param <T>
 *            bean item
 */
public class BeanList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends CustomComponent {

	private static Logger log = LoggerFactory.getLogger(BeanList.class);
	private static final long serialVersionUID = 1L;

	protected SearchService searchService;

	private Object parentComponent;
	private Class<? extends RowDisplayHandler<T>> rowDisplayHandler;
	private LazyLoadWrapper contentWrapper;
	private VerticalLayout contentLayout;
	private boolean isLazyLoadComponent = false;
	private boolean isDisplayEmptyListText = true;

	public BeanList(Object parentComponent, SearchService searchService,
			Class<? extends RowDisplayHandler<T>> rowDisplayHandler) {
		this(parentComponent, searchService, rowDisplayHandler,
				new VerticalLayout(), false);
	}

	public BeanList(Object parentComponent, SearchService searchService,
			Class<? extends RowDisplayHandler<T>> rowDisplayHandler,
			boolean isLazyLoadComponent) {
		this(parentComponent, searchService, rowDisplayHandler,
				new VerticalLayout(), isLazyLoadComponent);

	}

	public BeanList(Object parentComponent, SearchService searchService,
			Class<? extends RowDisplayHandler<T>> rowDisplayHandler,
			VerticalLayout contentLayout, boolean isLazyLoadComponent) {
		this.parentComponent = parentComponent;
		this.searchService = searchService;
		this.rowDisplayHandler = rowDisplayHandler;
		this.isLazyLoadComponent = isLazyLoadComponent;

		this.contentWrapper = new LazyLoadWrapper(contentLayout);
		this.contentLayout = contentLayout;
		this.setCompositionRoot(contentWrapper);
	}

	public BeanList(SearchService searchService,
			Class<? extends RowDisplayHandler<T>> rowDisplayHandler) {
		this(null, searchService, rowDisplayHandler);
	}

	public BeanList(SearchService searchService,
			Class<? extends RowDisplayHandler<T>> rowDisplayHandler,
			boolean isLazyLoadComponent) {
		this(null, searchService, rowDisplayHandler, isLazyLoadComponent);
	}

	public void setDisplayEmptyListText(boolean isDisplayEmptyListText) {
		this.isDisplayEmptyListText = isDisplayEmptyListText;
	}

	public void insertItemOnTop(T item) {
		RowDisplayHandler<T> rowHandler = constructRowndisplayHandler();
		Component row = rowHandler.generateRow(item, 0);
		if (row != null && contentLayout != null) {
			if (isLazyLoadComponent) {
				contentLayout.addComponent(new LazyLoadWrapper(row), 0);
			} else {
				contentLayout.addComponent(row, 0);
			}
		}
	}

	public void insetItemOnBottom(T item) {
		RowDisplayHandler<T> rowHandler = constructRowndisplayHandler();
		Component row = rowHandler.generateRow(item,
				contentLayout.getComponentCount());
		if (row != null && contentLayout != null) {
			if (isLazyLoadComponent) {
				contentLayout.addComponent(new LazyLoadWrapper(row));
			} else {
				contentLayout.addComponent(row);
			}
		}
	}

	private RowDisplayHandler<T> constructRowndisplayHandler() {
		RowDisplayHandler<T> rowHandler = null;
		try {

			if (rowDisplayHandler.getEnclosingClass() != null
					&& !Modifier.isStatic(rowDisplayHandler.getModifiers())) {

				Constructor constructor = rowDisplayHandler
						.getDeclaredConstructor(rowDisplayHandler
								.getEnclosingClass());
				rowHandler = (RowDisplayHandler<T>) constructor
						.newInstance(parentComponent);
			} else {
				rowHandler = rowDisplayHandler.newInstance();
			}
			return rowHandler;
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	public int setSearchCriteria(S searchCriteria) {
		SearchRequest<S> searchRequest = new SearchRequest<S>(searchCriteria,
				0, Integer.MAX_VALUE);
		return setSearchRequest(searchRequest);
	}

	public int setSearchRequest(SearchRequest<S> searchRequest) {
		List<T> currentListData = searchService
				.findPagableListByCriteria(searchRequest);
		loadItems(currentListData);
		return currentListData.size();
	}

	public void loadItems(List<T> currentListData) {
		contentLayout.removeAllComponents();

		try {
			if ((currentListData == null || currentListData.size() == 0)
					&& isDisplayEmptyListText) {
				Label noItemLbl = new Label("<<No item>>");
				final VerticalLayout widgetFooter = new VerticalLayout();
				widgetFooter.addStyleName("widget-footer");
				widgetFooter.setWidth("100%");
				widgetFooter.addComponent(noItemLbl);
				widgetFooter.setComponentAlignment(noItemLbl,
						Alignment.MIDDLE_CENTER);
				contentLayout.addComponent(widgetFooter);
			} else {
				int i = 0;
				for (T item : currentListData) {
					RowDisplayHandler<T> rowHandler = constructRowndisplayHandler();

					Component row = rowHandler.generateRow(item, i);
					if (row != null) {
						if (isLazyLoadComponent) {
							contentLayout
									.addComponent(new LazyLoadWrapper(row));
						} else {
							contentLayout.addComponent(row);
						}
					}

					i++;
				}
			}

		} catch (Exception e) {
			log.error("Error while generate column display", e);
		}
	}

	public boolean isEmpty() {
		return (contentLayout != null)
				&& (contentLayout.getComponentCount() > 0);
	}

	public static interface RowDisplayHandler<T> {

		Component generateRow(T obj, int rowIndex);
	}
}
