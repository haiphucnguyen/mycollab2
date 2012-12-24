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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.user.dao.RoleUserMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.dao.UserMapperExt;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.RoleUser;
import com.esofthead.mycollab.module.user.domain.RoleUserExample;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserExample;
import com.esofthead.mycollab.module.user.domain.UserInfo;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;

@Service
public class UserServiceDBImpl extends DefaultService<String, User, UserSearchCriteria>
		implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMapperExt userMapperExt;

	@Autowired
	private RoleUserMapper roleUserMapper;

	@Override
	public ICrudGenericDAO<String, User> getCrudMapper() {
		return userMapper;
	}

	@Override
	public ISearchableDAO<UserSearchCriteria> getSearchMapper() {
		return userMapperExt;
	}

	public void save(User record) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUsernameEqualTo(record.getUsername());
		ex.createCriteria().andEmailEqualTo(record.getEmail());

		List<User> users = userMapper.selectByExample(ex);
		if (users != null && users.size() > 0) {
			throw new EngroupException(
					"There is exist user has the same username or email already.");
		}

		userMapper.insert(record);
	}

	public int update(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	public void setRoleuserDAO(RoleUserMapper roleuserDAO) {
		this.roleUserMapper = roleuserDAO;
	}

	public void setUserExtDAO(UserMapperExt userExtDAO) {
		this.userMapperExt = userExtDAO;
	}

	public List<Role> findRolesByUser(String username) {
		return userMapperExt.findRolesByUser(username);
	}

	public User findUserByUsername(String name) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUsernameEqualTo(name);
		List<User> users = userMapper.selectByExample(ex);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	public void assignUserToRoleId(String username, int roleid) {
		RoleUserExample ex = new RoleUserExample();
		ex.createCriteria().andUsernameEqualTo(username)
				.andRoleidEqualTo(roleid);
		List<RoleUser> result = roleUserMapper.selectByExample(ex);
		if (result == null || result.size() == 0) {
			RoleUser roleuser = new RoleUser();
			roleuser.setRoleid(roleid);
			roleuser.setUsername(username);
			roleUserMapper.insert(roleuser);
		}
	}

	public void unAssignUserToRoleId(String username, int roleid) {
		RoleUserExample ex = new RoleUserExample();
		ex.createCriteria().andUsernameEqualTo(username)
				.andRoleidEqualTo(roleid);
		roleUserMapper.deleteByExample(ex);

	}

	@Override
	public void updateUserStatus(String username, boolean visible) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUsernameEqualTo(username);
		User user = new User();
		user.setUsername(username);
		user.setActive(visible);
		user.setLastaccessedtime(new GregorianCalendar().getTime());

		userMapper.updateByExampleSelective(user, ex);
	}

	@Override
	public List<UserInfo> getFriendsOfUser(int accountId, String username) {
		return userMapperExt.getFriendsOfUser(accountId, username);
	}
}
