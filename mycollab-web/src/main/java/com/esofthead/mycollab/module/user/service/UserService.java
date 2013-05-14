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

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;

/**
 * @author hai
 */
public interface UserService extends
		IDefaultService<String, User, UserSearchCriteria> {
	public static int FREE_BILLING = 0;

	SimpleUser authentication(String username, String password,
			String subdomain, boolean isPasswordEncrypt);

	void saveUserAccount(SimpleUser user);

	void updateUserAccount(SimpleUser user);

	void removeUserAccount(String username, int accountId);

	void removeUserAccounts(List<String> usernames, int accountId);

	SimpleUser findUserByUserNameInAccount(String username, int accountId);
	
	User findUserByUserName(String username);

	void verifyUser(String username);
}
