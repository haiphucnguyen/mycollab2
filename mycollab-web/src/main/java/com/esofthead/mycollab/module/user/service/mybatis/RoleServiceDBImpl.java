/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.user.service.mybatis;

import java.util.List;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.RoleMapper;
import com.esofthead.mycollab.module.user.dao.RoleMapperExt;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.RoleExample;
import com.esofthead.mycollab.module.user.service.RoleService;

public class RoleServiceDBImpl extends DefaultCrudService<Integer, Role>
		implements RoleService {

	private RoleMapperExt roleExtDAO;

	public void setRoleExtDAO(RoleMapperExt roleExtDAO) {
		this.roleExtDAO = roleExtDAO;
	}

	public List<Role> getAccountRoles(int accountid) {
		RoleExample ex = new RoleExample();
		ex.createCriteria().andSaccountidEqualTo(accountid);

		return ((RoleMapper) this.daoObj).selectByExample(ex);
	}

	public Role findByRoleName(int accountid, String rolename) {
		RoleExample ex = new RoleExample();
		ex.createCriteria().andRolenameEqualTo(rolename)
				.andSaccountidEqualTo(accountid);
		List<Role> roles = ((RoleMapper) this.daoObj).selectByExample(ex);

		if (roles != null && roles.size() > 0) {
			return roles.get(0);
		}
		return null;
	}

	@Override
	public int insertAndReturnKey(Role role) {
		roleExtDAO.insertAndReturnKey(role);
		return role.getId();
	}

	@Override
	public List<Role> findRolesOfUser(String username) {
		return roleExtDAO.findRolesOfUser(username);
	}
	
	@Override
	public void save(Role role) {
		((RoleMapper)daoObj).insert(role);
	}

	@Override
	public int update(Role record) {
		return ((RoleMapper) daoObj).updateByPrimaryKeySelective(record);
	}

	@Override
	public int remove(int roleId) {
		return ((RoleMapper) daoObj).deleteByPrimaryKey(roleId);
	}

}
