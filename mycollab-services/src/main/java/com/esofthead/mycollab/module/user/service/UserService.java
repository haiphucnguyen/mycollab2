/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.user.service;

import java.util.List;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;

/**
 * @author hai
 */
@RemotingDestination(channels = { "mycollab-amf", "mycollab-secure-amf" })
public interface UserService extends
		IDefaultService<String, User, UserSearchCriteria> {
	public static int FREE_BILLING = 0;

	SimpleUser authentication(String username, String password,
			String subdomain, boolean isPasswordEncrypt);

	@CacheEvict
	void saveUserAccount(SimpleUser user, @CacheKey Integer sAccountId);

	@CacheEvict
	void updateUserAccount(SimpleUser user, @CacheKey Integer sAccountId);

	void removeUserAccount(String username, Integer accountId);

	@CacheEvict
	void removeUserAccounts(List<String> usernames, @CacheKey Integer accountId);

	@Cacheable
	SimpleUser findUserByUserNameInAccount(String username,
			@CacheKey Integer accountId);

	User findUserByUserName(String username);

	void verifyUser(String username);
}
