package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;

public interface ComponentMapperExt extends ISearchableDAO<ComponentSearchCriteria> {

    SimpleComponent findComponentById(int componentId);
}
