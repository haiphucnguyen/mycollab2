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
package com.esofthead.mycollab.module.user.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.user.domain.Role;

/**
 * @author hpnguyen
 */
public interface RoleService extends ICrudService<Integer, Role> {

	Role findByRoleName(int accountid, String rolename);

	List<Role> getAccountRoles(int accountid);

	int insertAndReturnKey(Role role);
	
	void save(Role record);
	
	int update(Role record);
	
	int remove(int roleId);

	List<Role> findRolesOfUser(String username);
}
