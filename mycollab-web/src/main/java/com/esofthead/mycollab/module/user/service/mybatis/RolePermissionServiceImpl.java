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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.esofthead.mycollab.module.user.dao.RolePermissionMapper;
import com.esofthead.mycollab.module.user.dao.UserMapperExt;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.RolePermission;
import com.esofthead.mycollab.module.user.domain.RolePermissionExample;
import com.esofthead.mycollab.module.user.service.RolePermissionService;

public class RolePermissionServiceImpl implements RolePermissionService {
	private RolePermissionMapper daoObj;

	private UserMapperExt userExtDAO;

	public void setUserExtDAO(UserMapperExt userExtDAO) {
		this.userExtDAO = userExtDAO;
	}

	public void setDaoObj(RolePermissionMapper daoObj) {
		this.daoObj = daoObj;
	}

	public List<RolePermission> getPermissionByRoleId(int roleId) {
		RolePermissionExample ex = new RolePermissionExample();
		ex.createCriteria().andRoleidEqualTo(roleId);
		return daoObj.selectByExample(ex);
	}

	public void saveRolePermission(List<RolePermission> rolePermissions) {
		for (RolePermission rolePer : rolePermissions) {
			save(rolePer);
		}
	}

	private void save(RolePermission rolePermission) {
		RolePermissionExample ex = new RolePermissionExample();
		ex.createCriteria().andRoleidEqualTo(rolePermission.getRoleid())
				.andPathidEqualTo(rolePermission.getPathid());

		ex.createCriteria().andPathidEqualTo(rolePermission.getPathid());
		List<RolePermission> findResult = daoObj.selectByExample(ex);

		if (findResult == null || findResult.size() == 0) {
			daoObj.insert(rolePermission);
		} else {
			rolePermission.setId(findResult.get(0).getId());
			daoObj.updateByPrimaryKeySelective(rolePermission);
		}
	}

	public List<RolePermission> getPermissionByUser(String username) {
		Map<String, RolePermission> map = new Hashtable<String, RolePermission>();
		List<Role> roles = userExtDAO.findRolesByUser(username);

		for (Role role : roles) {
			List<RolePermission> subPermissions = getPermissionByRoleId(role
					.getId());
			RolePermission temPer;

			for (RolePermission rolePer : subPermissions) {
				temPer = map.get(rolePer.getPathid());
				if (temPer == null) {
					map.put(rolePer.getPathid(), rolePer);
				} else {
					// 0: prohibit, 1:allow. This algorithm denote that
					// if the new permission has higher right, it will overwrite
					// the old one
					if (temPer.getIsauthorz()) {
						map.put(rolePer.getPathid(), rolePer);
					}
				}
			}
		}
		List<RolePermission> result = new ArrayList<RolePermission>(
				map.values());
		return result;
	}
}
