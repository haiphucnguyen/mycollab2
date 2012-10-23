package com.esofthead.mycollab.module.user.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;

public interface SecurityService  extends IPagableService<UserSearchCriteria> {

	public static int FREE_BILLING = 1;
	
	/**
	 * 
	 * @param user
	 */
	void registerUser(User user, Integer billingAccountId);

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	SimpleUser authentication(String username, String password);

	/**
	 * 
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);

	/**
	 * 
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 
	 * @param user
	 */
	void updateUserStatus(String username, boolean visible);

	/**
	 * 
	 * @param user
	 */
	void deleteUser(String username);

	/**
	 * 
	 * @param role
	 */
	void updateRole(Role role);

	/**
	 * 
	 * @param rolename
	 */
	void deleteRole(int roleId);

	/**
	 * 
	 * @return
	 */
	List<Role> getAccountRoles(int accountid);

	/**
	 * 
	 * @param username
	 * @param rolename
	 */
	void assignUserToRoleId(String username, int roleId);

	/**
	 * 
	 * @param username
	 * @param rolename
	 */
	void unAssignUserToRoleId(String username, int roleId);

	/**
	 * 
	 * @param username
	 * @return
	 */
	List<Role> findRolesByUser(String username);

	/**
	 * 
	 * @param rolename
	 * @return
	 */
	Role findByRoleName(int accountid, String rolename);
}

