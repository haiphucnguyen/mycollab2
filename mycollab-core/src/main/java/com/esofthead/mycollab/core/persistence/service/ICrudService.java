/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.core.persistence.service;

import java.io.Serializable;
import java.util.List;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.Cacheable;

public interface ICrudService<K extends Serializable, T> extends IService {

	@CacheEvict
	int saveWithSession(T record, String username);

	@CacheEvict
	int updateWithSession(T record, String username);

	@CacheEvict
	void massUpdateWithSession(T record, List<K> primaryKeys, int accountId);

	@Cacheable
	T findByPrimaryKey(K primaryKey);

	@CacheEvict
	int removeWithSession(K primaryKey, String username, int accountId);

	@CacheEvict
	void removeWithSession(List<K> primaryKeys, String username, int accountId);
}
