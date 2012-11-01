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

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ICrudService;

/**
 * The generic class that serves the basic operations in data access layer:
 * Create, Retrieve, Update and Delete.
 * 
 * @param <T>
 * @param <K>
 */
public class DefaultCrudService<K extends Serializable, T> implements
		ICrudService<K, T> {

	protected CrudGenericDAO<K, T> daoObj;

	public int remove(K primaryKey) {
		return daoObj.deleteByPrimaryKey(primaryKey);
	}

	public T findByPrimaryKey(K primaryKey) {
		return (T) daoObj.selectByPrimaryKey(primaryKey);
	}

	@Override
	public void saveWithSession(T record, String username) {
		if (username == null) {
			daoObj.insert(record);
		} else {
			internalSaveWithSession(record, username);
		}
	}

	protected void internalSaveWithSession(T record, String username) {
		daoObj.insert(record);
	}

	public CrudGenericDAO<K, T> getDaoObj() {
		return daoObj;
	}

	@Autowired
	public void setDaoObj(CrudGenericDAO<K, T> daoObj) {
		this.daoObj = daoObj;
	}

	@Override
	public int updateWithSession(T record, String username) {
		if (username == null) {
			return daoObj.updateByPrimaryKeySelective(record);
		} else {
			return internalUpdateWithSession(record, username);
		}
	}

	protected int internalUpdateWithSession(T record, String username) {
		return daoObj.updateByPrimaryKeySelective(record);
	}

	@Override
	public int removeWithSession(K primaryKey, String username) {
		if (username == null) {
			return daoObj.deleteByPrimaryKey(primaryKey);
		} else {
			return internalRemoveWithSession(primaryKey, username);
		}
	}

	protected int internalRemoveWithSession(K primaryKey, String username) {
		return daoObj.deleteByPrimaryKey(primaryKey);
	}
}
