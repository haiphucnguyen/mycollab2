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

import java.io.Serializable;
import java.util.List;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;

public interface ICrudService<K extends Serializable, T> extends IService {

	@CacheEvict
	int saveWithSession(@CacheKey T record, String username);

	@CacheEvict
	int updateWithSession(@CacheKey T record, String username);

	@CacheEvict
	void massUpdateWithSession(T record, List<K> primaryKeys,
			@CacheKey int accountId);

	@Cacheable
	T findByPrimaryKey(K primaryKey, @CacheKey int sAccountId);

	@CacheEvict
	int removeWithSession(K primaryKey, String username,
			@CacheKey int sAccountId);

	@CacheEvict
	void massRemoveWithSession(List<K> primaryKeys, String username,
			@CacheKey int sAccountId);
}
