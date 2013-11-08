/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.persistence.service;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;

/**
 * Engroup serivice supports pagable/search data.
 * 
 * @param <S>
 */
public interface ISearchableService<S extends SearchCriteria> extends IService {
	@Cacheable
	int getTotalCount(@CacheKey S criteria);

	@Cacheable
	List findPagableListByCriteria(@CacheKey SearchRequest<S> searchRequest);

	@CacheEvict
	void removeByCriteria(S criteria, @CacheKey int sAccountId);

	@Cacheable
	Integer getNextItemKey(@CacheKey S criteria);

	@Cacheable
	Integer getPreviousItemKey(@CacheKey S criteria);
}
