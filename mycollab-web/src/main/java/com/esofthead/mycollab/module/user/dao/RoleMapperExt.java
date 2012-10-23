package com.esofthead.mycollab.module.user.dao;

import java.util.List;

import com.esofthead.mycollab.module.user.domain.Role;

public interface RoleMapperExt {
	
	Role findByRoleName(int accountid, String roleName);

	List<Role> findRolesOfUser(String username);

	void insertAndReturnKey(Role role);
}
