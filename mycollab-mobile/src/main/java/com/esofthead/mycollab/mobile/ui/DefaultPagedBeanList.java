/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.ui;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 * @param <SearchService>
 * @param <S>
 * @param <B>
 */
public class DefaultPagedBeanList<SearchService extends ISearchableService<S>, S extends SearchCriteria, B>
		extends AbstractPagedBeanList<S, B> {
	private static final long serialVersionUID = 1L;

	private final SearchService searchService;

	public DefaultPagedBeanList(final SearchService searchService,
			final Class<B> type, String displayColumnId) {
		super(type, displayColumnId);
		this.searchService = searchService;
	}

	@Override
	protected int queryTotalCount() {
		return searchService.getTotalCount(searchRequest.getSearchCriteria());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<B> queryCurrentData() {
		return searchService.findPagableListByCriteria(searchRequest);
	}
}
