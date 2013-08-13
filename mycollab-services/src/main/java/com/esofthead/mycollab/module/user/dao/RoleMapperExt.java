package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;

public interface RoleMapperExt extends ISearchableDAO<RoleSearchCriteria> {
    SimpleRole findById(int roleId);
}
