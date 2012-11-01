package com.esofthead.mycollab.core.persistence;

import java.io.Serializable;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface IDefaultService<K extends Serializable, T, S extends SearchCriteria>
		extends ICrudService<K, T>, IPagableService<S> {

}
