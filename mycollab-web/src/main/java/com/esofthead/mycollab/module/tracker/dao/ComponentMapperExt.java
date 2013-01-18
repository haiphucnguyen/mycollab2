package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import java.util.List;

public interface ComponentMapperExt extends ISearchableDAO<ComponentSearchCriteria> {

    List<Component> getComponentByRefKey(String refkey);
    
    SimpleComponent findComponentById(int componentId);
}
