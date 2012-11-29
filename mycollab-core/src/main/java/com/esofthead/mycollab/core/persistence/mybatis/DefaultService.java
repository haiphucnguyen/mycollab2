package com.esofthead.mycollab.core.persistence.mybatis;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;

public abstract class DefaultService<K extends Serializable, T, S extends SearchCriteria>
		implements IDefaultService<K, T, S> {

	private static Logger log = LoggerFactory.getLogger(DefaultService.class);

	public abstract ICrudGenericDAO<K, T> getCrudMapper();

	public abstract ISearchableDAO<S> getSearchMapper();

	public int remove(K primaryKey) {
		return getCrudMapper().deleteByPrimaryKey(primaryKey);
	}

	public T findByPrimaryKey(K primaryKey) {
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
			e.printStackTrace();
		}
		if (username == null) {
			getCrudMapper().insert(record);
			return -1;
		} else {
			return internalSaveWithSession(record, username);
		}
	}

	protected int internalSaveWithSession(T record, String username) {
		getCrudMapper().insert(record);
		return -1;
	}

	@Override
	public int updateWithSession(T record, String username) {
		try {
			PropertyUtils.setProperty(record, "lastupdatedtime",
					new GregorianCalendar().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (username == null) {
			return getCrudMapper().updateByPrimaryKey(record);
		} else {
			return internalUpdateWithSession(record, username);
		}
	}

	protected int internalUpdateWithSession(T record, String username) {
		return getCrudMapper().updateByPrimaryKey(record);
	}

	@Override
	public int removeWithSession(K primaryKey, String username) {
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
	public int getTotalCount(S criteria) {
		return getSearchMapper().getTotalCount(criteria);
	}

	@Override
	public List findPagableListByCriteria(SearchRequest<S> searchRequest) {
		return getSearchMapper().findPagableListByCriteria(
				searchRequest.getSearchCriteria(),
				new RowBounds((searchRequest.getCurrentPage() - 1)
						* searchRequest.getNumberOfItems(), searchRequest
						.getNumberOfItems()));
	}

	@Override
	public void removeByCriteria(S criteria) {
		boolean isValid = false;
		try {
			PropertyDescriptor[] propertyDescriptors = PropertyUtils
					.getPropertyDescriptors(criteria);
			
			for (PropertyDescriptor descriptor : propertyDescriptors) {
				String propName = descriptor.getName();
				if ((descriptor.getPropertyType().getGenericSuperclass() == SearchField.class)
						&& (PropertyUtils.getProperty(criteria, propName) != null)) {
					isValid = true;
					break;
				}

			}
		} catch (Exception e) {
			log.debug("Error while validating criteria", e);
		}
		if (isValid) {
			getSearchMapper().removeByCriteria(criteria);
		}
		
	}

	@Override
	public void removeWithSession(List<K> primaryKeys, String username) {
		throw new RuntimeException("Sub classes must override before call");
	}
}
