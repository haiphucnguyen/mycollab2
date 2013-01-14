package com.esofthead.mycollab.core.persistence.service;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import java.io.Serializable;

public interface IDefaultService<K extends Serializable, T, S extends SearchCriteria>
        extends ICrudService<K, T>, ISearchableService<S> {
}
