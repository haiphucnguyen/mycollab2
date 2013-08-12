package com.esofthead.mycollab.core.persistence.service;

import java.io.Serializable;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.cache.CacheEvict;

public interface IDefaultService<K extends Serializable, T, S extends SearchCriteria>
		extends ICrudService<K, T>, ISearchableService<S> {

	@CacheEvict
	void updateBySearchCriteria(T record, S searchCriteria);
}
