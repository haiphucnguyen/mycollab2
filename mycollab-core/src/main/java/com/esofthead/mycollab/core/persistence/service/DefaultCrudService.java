/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.core.persistence.service;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;

/**
 * The generic class that serves the basic operations in data access layer:
 * Create, Retrieve, Update and Delete.
 * 
 * @param <T>
 * @param <K>
 */
public abstract class DefaultCrudService<K extends Serializable, T> implements
		ICrudService<K, T> {

	public abstract ICrudGenericDAO<K, T> getCrudMapper();

	public int remove(K primaryKey) {
		return getCrudMapper().deleteByPrimaryKey(primaryKey);
	}

	public T findByPrimaryKey(K primaryKey, int accountId) {
		return (T) getCrudMapper().selectByPrimaryKey(primaryKey);
	}

	@Override
	public int saveWithSession(T record, String username) {
		try {
			PropertyUtils.setProperty(record, "createdtime",
					new GregorianCalendar().getTime());
			PropertyUtils.setProperty(record, "lastupdatedtime",
					new GregorianCalendar().getTime());
		} catch (Exception e) {
		}

		int result = getCrudMapper().insertAndReturnKey(record);
		try {
			result = (Integer) PropertyUtils.getProperty(record, "id");
		} catch (Exception e) {
			result = 1;
		}

		return result;
	}

	@Override
	public int updateWithSession(T record, String username) {
		if (username == null) {
			try {
				PropertyUtils.setProperty(record, "lastupdatedtime",
						new GregorianCalendar().getTime());
			} catch (Exception e) {
			}
			return getCrudMapper().updateByPrimaryKeySelective(record);
		} else {
			return internalUpdateWithSession(record, username);
		}
	}

	protected int internalUpdateWithSession(T record, String username) {
		try {
			PropertyUtils.setProperty(record, "lastupdatedtime",
					new GregorianCalendar().getTime());
		} catch (Exception e) {
		}
		return getCrudMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int removeWithSession(K primaryKey, String username, int accountId) {
		if (username == null) {
			return getCrudMapper().deleteByPrimaryKey(primaryKey);
		} else {
			return internalRemoveWithSession(primaryKey, username);
		}
	}

	protected int internalRemoveWithSession(K primaryKey, String username) {
		return getCrudMapper().deleteByPrimaryKey(primaryKey);
	}

	@Override
	public void massRemoveWithSession(List<K> primaryKeys, String username,
			int accountId) {
		throw new MyCollabException("Sub classes must override before call");
	}

	@Override
	public void massUpdateWithSession(T record, List<K> primaryKeys,
			int accountId) {
		getCrudMapper().massUpdateWithSession(record, primaryKeys);
	}
}
