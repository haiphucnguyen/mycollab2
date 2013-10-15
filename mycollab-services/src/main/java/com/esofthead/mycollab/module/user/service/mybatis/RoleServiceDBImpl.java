/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.user.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.user.dao.RoleMapper;
import com.esofthead.mycollab.module.user.dao.RoleMapperExt;
import com.esofthead.mycollab.module.user.dao.RolePermissionMapper;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.RoleExample;
import com.esofthead.mycollab.module.user.domain.RolePermission;
import com.esofthead.mycollab.module.user.domain.RolePermissionExample;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@Service
public class RoleServiceDBImpl extends
		DefaultService<Integer, Role, RoleSearchCriteria> implements
		RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMapperExt roleMapperExt;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public ICrudGenericDAO<Integer, Role> getCrudMapper() {
		return roleMapper;
	}

	@Override
	public ISearchableDAO<RoleSearchCriteria> getSearchMapper() {
		return roleMapperExt;
	}

	@Override
	public void savePermission(int roleId, PermissionMap permissionMap,
			int accountid) {
		XStream xstream = new XStream(new StaxDriver());
		String perVal = xstream.toXML(permissionMap);

		RolePermissionExample ex = new RolePermissionExample();
		ex.createCriteria().andRoleidEqualTo(roleId);

		RolePermission rolePer = new RolePermission();
		rolePer.setRoleid(roleId);
		rolePer.setRoleval(perVal);

		int data = rolePermissionMapper.countByExample(ex);
		if (data > 0) {
			rolePermissionMapper.updateByExampleSelective(rolePer, ex);
		} else {
			rolePermissionMapper.insert(rolePer);
		}
	}

	@Override
	public SimpleRole findById(int roleId, int sAccountId) {
		return roleMapperExt.findById(roleId);
	}

	@Override
	public Integer getSystemRoleId(String systemRoleName,
			@CacheKey Integer sAccountId) {
		RoleExample ex = new RoleExample();
		ex.createCriteria().andRolenameEqualTo(systemRoleName)
				.andIssystemroleEqualTo(Boolean.TRUE);
		List<Role> roles = roleMapper.selectByExample(ex);
		if (roles != null && roles.size() > 0) {
			return roles.get(0).getId();
		} else {
			return null;
		}
	}
}
