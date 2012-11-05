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

import com.esofthead.mycollab.core.AvailableDestinationNames;
import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.core.MessageDispatcher;
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

public class SecurityServiceImpl implements SecurityService {

	private UserService userServiceDB;

	private RoleService roleServiceDB;

	private MessageDispatcher messageDispatcher;

	private BillingAccountService accountService;

	public void registerUser(User user, Integer billingAccountId) {
		if (billingAccountId == null) {
			billingAccountId = 1; // Trial id plan
		}

		Account account = new Account();
		account.setBillingplanid(billingAccountId);
		account.setCreatedtime(new GregorianCalendar().getTime());
		int accountid = accountService.insertAndReturnKey(account);

		user.setAccountid(accountid);
		userServiceDB.save(user);

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
		return roleServiceDB.insertAndReturnKey(role);
	}

	private void notifyRegisterAccount(User user) {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put("user", user);

		// post message to save user topic
		messageDispatcher.dispatchObject(
				AvailableDestinationNames.REGISTER_ACCOUNT, props);
	}

	public void assignUserToRoleId(String username, int roleId) {
		// Assign role to user in database
		if (roleServiceDB.findByPrimaryKey(roleId) == null) {
			throw new EngroupException("There is no role id " + roleId);
		}
		userServiceDB.assignUserToRoleId(username, roleId);
	}

	public void unAssignUserToRoleId(String username, int roleId) {
		userServiceDB.unAssignUserToRoleId(username, roleId);
	}

	public SimpleUser authentication(String username, String password) {
		User user = findUserByUsername(username);

		if (user != null && password != null) {
			if (!password.equals(user.getPassword())) {
				throw new EngroupException("Password is error");
			}
			List<Role> roles = roleServiceDB.findRolesOfUser(username);

			SimpleUser result = new SimpleUser(user);
			result.setRoles(roles);

			return result;
		} else {
			throw new EngroupException("No user " + username + " is existed");
		}

	}

	public void deleteRole(int roleid) {
		roleServiceDB.remove(roleid);
	}

	public void deleteUser(String username) {
		User user = userServiceDB.findByPrimaryKey(username);
		if (user != null) {
			userServiceDB.remove(username);

			Dictionary<String, Object> props = new Hashtable<String, Object>();
			props.put("user", user);

			// post message to topic delete user
			messageDispatcher.dispatchObject(
					AvailableDestinationNames.USER_REMOVE, props);
		}
	}

	public List<Role> findRolesByUser(String username) {
		return userServiceDB.findRolesByUser(username);
	}

	public User findUserByUsername(String username) {
		return userServiceDB.findUserByUsername(username);
	}

	public List<Role> getAccountRoles(int accountid) {
		return roleServiceDB.getAccountRoles(accountid);
	}

	public void saveRole(Role role) {
		roleServiceDB.save(role);
	}

	public void saveUser(User user) {
		userServiceDB.save(user);

		Role registeredRole = roleServiceDB.findByRoleName(user.getAccountid(),
				RoleConstants.REGISTERED_USER);
		if (registeredRole != null) {
			assignUserToRoleId(user.getUsername(), registeredRole.getId());
		}

		notifySaveUser(user);
	}

	private void notifySaveUser(User user) {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put("user", user);

		// post message to save user topic
		messageDispatcher.dispatchObject(AvailableDestinationNames.USER_ADD,
				props);
	}

	public void updateRole(Role role) {
		roleServiceDB.update(role);
	}

	public void updateUser(User user) {
		userServiceDB.update(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findPagableListByCriteria(UserSearchCriteria criteria,
			int skipNum, int maxResult) {
		return userServiceDB.findPagableListByCriteria(criteria, skipNum,
				maxResult);
	}

	public int getTotalCount(UserSearchCriteria criteria) {
		return userServiceDB.getTotalCount(criteria);
	}

	public void setUserServiceDB(UserService userServiceDB) {
		this.userServiceDB = userServiceDB;
	}

	public void setRoleServiceDB(RoleService roleServiceDB) {
		this.roleServiceDB = roleServiceDB;
	}

	public void setAccountService(BillingAccountService accountService) {
		this.accountService = accountService;
	}

	public void updateUserStatus(String username, boolean visible) {
		userServiceDB.updateUserStatus(username, visible);

	}

	public Role findByRoleName(int accountid, String rolename) {
		return roleServiceDB.findByRoleName(accountid, rolename);
	}

	public void setMessageDispatcher(MessageDispatcher messageDispatcher) {
		this.messageDispatcher = messageDispatcher;
	}

}
