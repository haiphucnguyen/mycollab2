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

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.RoleUserMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.dao.UserMapperExt;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.RoleUser;
import com.esofthead.mycollab.module.user.domain.RoleUserExample;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserExample;
import com.esofthead.mycollab.module.user.domain.UserInfo;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;

public class UserServiceDBImpl extends DefaultCrudService<User, String>
		implements UserService {

	private UserMapperExt userExtDAO;

	private RoleUserMapper roleuserDAO;

	public void save(User record) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUsernameEqualTo(record.getUsername());
		ex.createCriteria().andEmailEqualTo(record.getEmail());

		List<User> users = ((UserMapper) daoObj).selectByExample(ex);
		if (users != null && users.size() > 0) {
			throw new EngroupException(
					"There is exist user has the same username or email already.");
		}

		((UserMapper) this.daoObj).insert(record);
	}

	public int update(User record) {
		return ((UserMapper) daoObj).updateByPrimaryKeySelective(record);
	}

	public void setRoleuserDAO(RoleUserMapper roleuserDAO) {
		this.roleuserDAO = roleuserDAO;
	}

	public void setUserExtDAO(UserMapperExt userExtDAO) {
		this.userExtDAO = userExtDAO;
	}

	public List<Role> findRolesByUser(String username) {
		return userExtDAO.findRolesByUser(username);
	}

	public User findUserByUsername(String name) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUsernameEqualTo(name);
		List<User> users = ((UserMapper) (this.daoObj)).selectByExample(ex);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	public List<SimpleUser> findPagableListByCriteria(
			UserSearchCriteria criteria, int skipNum, int maxResult) {
		return userExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	public int getTotalCount(UserSearchCriteria criteria) {
		return userExtDAO.getTotalCount(criteria);
	}

	public void assignUserToRoleId(String username, int roleid) {
		RoleUserExample ex = new RoleUserExample();
		ex.createCriteria().andUsernameEqualTo(username)
				.andRoleidEqualTo(roleid);
		List<RoleUser> result = roleuserDAO.selectByExample(ex);
		if (result == null || result.size() == 0) {
			RoleUser roleuser = new RoleUser();
			roleuser.setRoleid(roleid);
			roleuser.setUsername(username);
			roleuserDAO.insert(roleuser);
		}
	}

	public void unAssignUserToRoleId(String username, int roleid) {
		RoleUserExample ex = new RoleUserExample();
		ex.createCriteria().andUsernameEqualTo(username)
				.andRoleidEqualTo(roleid);
		roleuserDAO.deleteByExample(ex);

	}

	@Override
	public void updateUserStatus(String username, boolean visible) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUsernameEqualTo(username);
		User user = new User();
		user.setUsername(username);
		user.setActive(visible);
		user.setLastaccessedtime(new GregorianCalendar().getTime());

		((UserMapper) daoObj).updateByExampleSelective(user, ex);
	}

	@Override
	public List<UserInfo> getFriendsOfUser(int accountId, String username) {
		return userExtDAO.getFriendsOfUser(accountId, username);
	}
}
