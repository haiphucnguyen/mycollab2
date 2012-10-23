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
package com.esofthead.mycollab.core.persistence.mybatis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.PlatformManager;
import com.esofthead.mycollab.core.SessionExpireException;
import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ICrudService;

/**
 * The generic class that serves the basic operations in data access layer:
 * Create, Retrieve, Update and Delete.
 * 
 * @param <T>
 * @param <K>
 */
public class DefaultCrudService<T, K extends Serializable> implements
		ICrudService<T, K> {

	protected CrudGenericDAO<T, K> daoObj;

	public int remove(K primaryKey) {
		return daoObj.deleteByPrimaryKey(primaryKey);
	}

	@SuppressWarnings("unchecked")
	public T findByPrimaryKey(K primaryKey) {
		return (T) daoObj.selectByPrimaryKey(primaryKey);
	}

	@Override
	public void saveWithSession(T record, String userSessionId) {
		if (userSessionId == null) {
			daoObj.insert(record);
		} else {
			String username = getUserNameFromSession(userSessionId);
			internalSaveWithSession(record, username);
		}
	}

	protected void internalSaveWithSession(T record, String username) {
		daoObj.insert(record);
	}

	public CrudGenericDAO<T, K> getDaoObj() {
		return daoObj;
	}

	@Autowired
	public void setDaoObj(CrudGenericDAO<T, K> daoObj) {
		this.daoObj = daoObj;
	}
	
	@Override
	public int updateWithSession(T record, String userSessionId) {
		if (userSessionId == null) {
			return daoObj.updateByPrimaryKeySelective(record);
		} else {
			String username = getUserNameFromSession(userSessionId);
			return internalUpdateWithSession(record, username);
		}
	}

	protected int internalUpdateWithSession(T record, String username) {
		return daoObj.updateByPrimaryKeySelective(record);
	}

	@Override
	public int removeWithSession(K primaryKey, String userSessionId) {
		if (userSessionId == null) {
			return daoObj.deleteByPrimaryKey(primaryKey);
		} else {
			String username = getUserNameFromSession(userSessionId);
			return internalRemoveWithSession(primaryKey, username);
		}
	}

	protected String getUserNameFromSession(String userSessionId) {
		String username = PlatformManager.getInstance().getUsername(
				userSessionId);
		if (username == null) {
			throw new SessionExpireException("User has session "
					+ userSessionId
					+ " has expired session. Please relogin again.");
		}
		return username;
	}

	protected int internalRemoveWithSession(K primaryKey, String username) {
		return daoObj.deleteByPrimaryKey(primaryKey);
	}
}
