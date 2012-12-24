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

public interface ICrudService<K extends Serializable, T> extends IService {

	int saveWithSession(T record, String userSessionId);

	/**
	 * 
	 * @param record
	 * @param userSessionId
	 * @return
	 */
	int updateWithSession(T record, String userSessionId);

	/**
	 * @param id
	 * @return
	 */
	T findByPrimaryKey(K primaryKey);

	/**
	 * 
	 * @param primaryKey
	 * @param userSessionId
	 * @return
	 */
	int removeWithSession(K primaryKey, String userSessionId);
	
	/**
	 * 
	 * @param primaryKeys
	 * @param username
	 * @return
	 */
	void removeWithSession(List<K> primaryKeys, String username);
}
