package com.esofthead.mycollab.module.user.dao;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.UserInfo;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;

public interface UserMapperExt extends ISearchableDAO<UserSearchCriteria> {

	List<Role> findRolesByUser(String username);

	List<UserInfo> getFriendsOfUser(int accountId, String username);
}
