package com.esofthead.mycollab.module.user.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserInfo;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;

public interface UserMapperExt {
	int getTotalCount(UserSearchCriteria criteria);

	List<SimpleUser> findPagableList(UserSearchCriteria criteria,
			RowBounds rowBounds);

	List<Role> findRolesByUser(String username);

	List<UserInfo> getFriendsOfUser(int accountId, String username);
}
