package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Role;
import com.esofthead.mycollab.module.project.domain.criteria.RoleSearchCriteria;

public interface RoleService extends
		IDefaultService<Integer, Role, RoleSearchCriteria> {

}
