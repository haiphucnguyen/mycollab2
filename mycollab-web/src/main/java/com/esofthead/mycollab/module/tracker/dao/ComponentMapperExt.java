package com.esofthead.mycollab.module.tracker.dao;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;

public interface ComponentMapperExt extends ISearchableDAO<ComponentSearchCriteria>{

	List<Component> getComponentByRefKey(String refkey);
}
