package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.RoleMapper;
import com.esofthead.mycollab.module.project.dao.RoleMapperExt;
import com.esofthead.mycollab.module.project.domain.Role;
import com.esofthead.mycollab.module.project.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.RoleService;

@Service
public class RoleServiceImpl extends DefaultService<Integer, Role, RoleSearchCriteria>
		implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleMapperExt roleMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Role> getCrudMapper() {
		return roleMapper;
	}

	@Override
	public ISearchableDAO<RoleSearchCriteria> getSearchMapper() {
		return roleMapperExt;
	}

}
