package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.RoleMapperExt;
import com.esofthead.mycollab.module.project.domain.Role;
import com.esofthead.mycollab.module.project.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.RoleService;

public class RoleServiceImpl extends DefaultCrudService<Integer, Role>
		implements RoleService {

	private RoleMapperExt roleExtDAO;

	@Override
	public List findPagableListByCriteria(RoleSearchCriteria criteria,
			int skipNum, int maxResult) {
		return roleExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(RoleSearchCriteria criteria) {
		return roleExtDAO.getTotalCount(criteria);
	}

	public void setRoleExtDAO(RoleMapperExt roleExtDAO) {
		this.roleExtDAO = roleExtDAO;
	}

}
