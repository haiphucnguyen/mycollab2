package com.esofthead.mycollab.core.persistence.service;

import java.io.Serializable;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface IDefaultService<K extends Serializable, T, S extends SearchCriteria>
		extends ICrudService<K, T>, ISearchableService<S> {
	void updateBySearchCriteria(T record, S searchCriteria);
}
