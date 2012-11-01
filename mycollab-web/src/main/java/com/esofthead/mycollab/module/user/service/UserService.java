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
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserInfo;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;

/**
 * @author hai
 */
public interface UserService extends ICrudService<String, User>,
		IPagableService<UserSearchCriteria> {
	/**
	 * @param name
	 * @return
	 */
	User findUserByUsername(String name);

	/**
	 * @param name
	 * @return the list of roles correspond with user has name is
	 *         <code>username</code>
	 */
	List<Role> findRolesByUser(String username);

	void assignUserToRoleId(String username, int rileId);

	void unAssignUserToRoleId(String username, int roleId);

	void updateUserStatus(String username, boolean visible);

	List<UserInfo> getFriendsOfUser(int accountId, String username);
	
	void save(User record);
	
	int update(User record);
	
	int remove(String username);

}
