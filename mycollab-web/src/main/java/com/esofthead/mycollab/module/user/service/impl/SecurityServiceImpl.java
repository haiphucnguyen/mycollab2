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
package com.esofthead.mycollab.module.user.service.impl;

import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.MessageDispatcher;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.user.RoleConstants;
import com.esofthead.mycollab.module.user.domain.Account;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.module.user.service.SecurityService;
import com.esofthead.mycollab.module.user.service.UserService;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageDispatcher messageDispatcher;

	@Autowired
	private BillingAccountService billingAccountService;

	@Override
	public int getTotalCount(UserSearchCriteria criteria) {
		return userService.getTotalCount(criteria);
	}

	@Override
	public List findPagableListByCriteria(
			SearchRequest<UserSearchCriteria> searchRequest) {
		return userService.findPagableListByCriteria(searchRequest);
	}

	public void registerUser(User user, Integer billingAccountId) {
		if (billingAccountId == null) {
			billingAccountId = 1; // Trial id plan
		}

		Account account = new Account();
		account.setBillingplanid(billingAccountId);
		account.setCreatedtime(new GregorianCalendar().getTime());
		int accountid = billingAccountService.saveWithSession(account, "");

		user.setAccountid(accountid);
		userService.save(user);

		int adminRoleId = saveRole(accountid, RoleConstants.ADMINSITRATOR_ROLE);
		assignUserToRoleId(user.getUsername(), adminRoleId);

		int registeredRoleId = saveRole(accountid,
				RoleConstants.REGISTERED_USER);
		assignUserToRoleId(user.getUsername(), registeredRoleId);
		notifyRegisterAccount(user);
	}

	private int saveRole(int accountid, String rolename) {
		Role role = new Role();
		role.setSaccountid(accountid);
		role.setRolename(rolename);
		return roleService.insertAndReturnKey(role);
	}

	private void notifyRegisterAccount(User user) {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put("user", user);
	}

	public void assignUserToRoleId(String username, int roleId) {
		// Assign role to user in database
		if (roleService.findByPrimaryKey(roleId) == null) {
			throw new MyCollabException("There is no role id " + roleId);
		}
		userService.assignUserToRoleId(username, roleId);
	}

	public void unAssignUserToRoleId(String username, int roleId) {
		userService.unAssignUserToRoleId(username, roleId);
	}

	public SimpleUser authentication(String username, String password) {
		User user = findUserByUsername(username);

		if (user != null && password != null) {
			if (!password.equals(user.getPassword())) {
				throw new MyCollabException("Password is error");
			}
			List<Role> roles = roleService.findRolesOfUser(username);

			SimpleUser result = new SimpleUser(user);
			result.setRoles(roles);

			return result;
		} else {
			throw new MyCollabException("No user " + username + " is existed");
		}

	}

	public void deleteRole(int roleid) {
		roleService.remove(roleid);
	}

	public void deleteUser(String username) {
		User user = userService.findByPrimaryKey(username);
		if (user != null) {
			userService.remove(username);
		}
	}

	public List<Role> findRolesByUser(String username) {
		return userService.findRolesByUser(username);
	}

	public User findUserByUsername(String username) {
		return userService.findUserByUsername(username);
	}

	public List<Role> getAccountRoles(int accountid) {
		return roleService.getAccountRoles(accountid);
	}

	public void saveRole(Role role) {
		roleService.save(role);
	}

	public void saveUser(User user) {
		userService.save(user);

		Role registeredRole = roleService.findByRoleName(user.getAccountid(),
				RoleConstants.REGISTERED_USER);
		if (registeredRole != null) {
			assignUserToRoleId(user.getUsername(), registeredRole.getId());
		}

		notifySaveUser(user);
	}

	private void notifySaveUser(User user) {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put("user", user);
	}

	public void updateRole(Role role) {
		roleService.update(role);
	}

	public void updateUser(User user) {
		userService.update(user);
	}

	public void setUserServiceDB(UserService userServiceDB) {
		this.userService = userServiceDB;
	}

	public void updateUserStatus(String username, boolean visible) {
		userService.updateUserStatus(username, visible);

	}

	public Role findByRoleName(int accountid, String rolename) {
		return roleService.findByRoleName(accountid, rolename);
	}

	@Override
	public void removeByCriteria(UserSearchCriteria criteria) {
		// TODO Auto-generated method stub

	}

}
