/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.user.service;

import java.util.List;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface UserService extends
		IDefaultService<String, User, UserSearchCriteria> {
	public static int FREE_BILLING = 0;

	SimpleUser authentication(String username, String password,
			String subdomain, boolean isPasswordEncrypt);

	@CacheEvict
	void saveUserAccount(SimpleUser user, @CacheKey Integer sAccountId,
			String inviteUser);

	@CacheEvict
	void updateUserAccount(SimpleUser user, @CacheKey Integer sAccountId);

	@CacheEvict
	void updateUserAccountStatus(String username, @CacheKey Integer sAccountId,
			String registerStatus);

	@CacheEvict
	void updateUserAccountsStatus(List<String> usernames,
			@CacheKey Integer sAccountId, String registerStatus);

	@CacheEvict
	void pendingUserAccount(String username, @CacheKey Integer accountId);

	@CacheEvict
	void pendingUserAccounts(List<String> usernames, @CacheKey Integer accountId);

	@Cacheable
	SimpleUser findUserByUserNameInAccount(String username,
			@CacheKey Integer accountId);

	@Cacheable
	int getTotalActiveUsersInAccount(@CacheKey Integer accountId);

	User findUserByUserName(String username);
}
