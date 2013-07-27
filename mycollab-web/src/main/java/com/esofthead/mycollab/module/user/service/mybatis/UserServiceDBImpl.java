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
package com.esofthead.mycollab.module.user.service.mybatis;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.file.service.UserAvatarService;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.dao.RolePermissionMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.dao.UserMapperExt;
import com.esofthead.mycollab.module.user.domain.RolePermission;
import com.esofthead.mycollab.module.user.domain.RolePermissionExample;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.domain.UserAccountExample;
import com.esofthead.mycollab.module.user.domain.UserAccountInvitation;
import com.esofthead.mycollab.module.user.domain.UserExample;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.module.user.service.esb.UserDeleteListener;
import com.esofthead.mycollab.module.user.service.esb.UserEndpoints;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@Service
@Transactional
public class UserServiceDBImpl extends
		DefaultService<String, User, UserSearchCriteria> implements UserService {

	private static Logger log = LoggerFactory
			.getLogger(UserServiceDBImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMapperExt userMapperExt;

	@Autowired
	private UserAccountMapper userAccountMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private UserAvatarService userAvatarService;

	@Autowired
	private UserAccountInvitationMapper userAccountInvitationMapper;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return userMapper;
	}

	@Override
	public ISearchableDAO<UserSearchCriteria> getSearchMapper() {
		return userMapperExt;
	}

	@Override
	public void saveUserAccount(SimpleUser record) {
		// check if user email has already in this account yet
		UserAccountExample userAccountEx = new UserAccountExample();
		userAccountEx.createCriteria().andUsernameEqualTo(record.getEmail());
		if (userAccountMapper.countByExample(userAccountEx) > 0) {
			throw new UserInvalidInputException(
					"There is already user has email " + record.getEmail()
							+ " in your account");
		}

		if (record.getPassword() != null) {
			record.setPassword(PasswordEncryptHelper.encryptSaltPassword(record
					.getPassword()));
		}

		if (record.getUsername() == null) {
			record.setUsername(record.getEmail());
		}

		// Check if user has already account in system, if not we will create
		// new user

		UserExample userEx = new UserExample();
		userEx.createCriteria().andUsernameEqualTo(record.getUsername());
		if (userMapper.countByExample(userEx) == 0) {
			record.setRegisterstatus(RegisterStatusConstants.VERIFICATING);
			userMapper.insert(record);

			// Save default user avatar
			InputStream imageResourceStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"assets/images/default_user_avatar_100.png");
			BufferedImage imageBuff;
			try {
				imageBuff = ImageIO.read(imageResourceStream);
				userAvatarService.uploadAvatar(imageBuff, record.getUsername(),
						null);
			} catch (IOException e) {
				log.error("Error while set default avatar to user", e);
			}
		}

		// save record in s_user_account table
		UserAccount userAccount = new UserAccount();
		userAccount.setAccountid(record.getAccountId());
		userAccount
				.setIsaccountowner((record.getIsAccountOwner() == null) ? Boolean.FALSE
						: record.getIsAccountOwner());
		userAccount.setIsadmin((record.getIsAdmin() == null) ? Boolean.FALSE
				: record.getIsAdmin());
		userAccount.setRoleid(record.getRoleid());
		userAccount.setUsername(record.getUsername());
		userAccount.setRegisteredtime(new GregorianCalendar().getTime());
		userAccount.setLastaccessedtime(new GregorianCalendar().getTime());
		userAccount
				.setRegisterstatus((record.getRegisterstatus() == null) ? RegisterStatusConstants.VERIFICATING
						: record.getRegisterstatus());
		userAccountMapper.insert(userAccount);

		if (!RegisterStatusConstants.ACTIVE.equals(record.getRegisterstatus())) {
			// save to invitation user
			UserAccountInvitation invitation = new UserAccountInvitation();
			invitation.setAccountid(record.getAccountId());
			invitation.setCreatedtime(new GregorianCalendar().getTime());
			invitation.setUsername(record.getUsername());
			invitation
					.setInvitationstatus((record.getRegisterstatus() == null) ? RegisterStatusConstants.VERIFICATING
							: record.getRegisterstatus());
			userAccountInvitationMapper.insert(invitation);
		}
	}

	@Override
	public void updateUserAccount(SimpleUser record) {

		userMapper.updateByPrimaryKeySelective(record);

		UserAccountExample userAccountEx = new UserAccountExample();
		userAccountEx.createCriteria().andUsernameEqualTo(record.getUsername());

		UserAccount userAccount = new UserAccount();
		userAccount.setIsadmin(record.getIsAdmin());
		userAccount.setRoleid(record.getRoleid());
		userAccount.setRegisterstatus(record.getRegisterstatus());
		userAccount.setLastaccessedtime(new GregorianCalendar().getTime());
		userAccountMapper.updateByExampleSelective(userAccount, userAccountEx);
	}

	@Override
	public void removeWithSession(List<String> primaryKeys, String username) {
		userMapperExt.removeKeysWithSession(primaryKeys);
	}

	@Override
	public SimpleUser authentication(String username, String password,
			String subdomain, boolean isPasswordEncrypt) {
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setUsername(new StringSearchField(username));

		boolean isSupportSubDomain = ApplicationProperties
				.getBoolean(ApplicationProperties.SUPPORT_ACCOUNT_SUBDOMAIN);
		if (isSupportSubDomain) {
			criteria.setSubdomain(new StringSearchField(subdomain));
		}

		List<SimpleUser> users = findPagableListByCriteria(new SearchRequest<UserSearchCriteria>(
				criteria, 0, Integer.MAX_VALUE));
		if (users == null || users.isEmpty()) {
			throw new UserInvalidInputException("Invalid username or password");
		} else {
			SimpleUser user = users.get(0);
			if (user.getPassword() == null
					|| !PasswordEncryptHelper.checkPassword(password,
							user.getPassword(), isPasswordEncrypt)) {
				log.debug("PASS: " + password + "   " + user.getPassword());
				throw new UserInvalidInputException(
						"Invalid username or password");
			}

			log.debug("User " + username + " login to system successfully!");

			if (user.getIsAdmin() == null
					|| (user.getIsAdmin() != null && !user.getIsAdmin())) {
				if (user.getRoleid() != null) {
					log.debug("User " + username
							+ " is not admin. Getting his role");
					RolePermissionExample ex = new RolePermissionExample();
					ex.createCriteria().andRoleidEqualTo(user.getRoleid());
					List roles = rolePermissionMapper
							.selectByExampleWithBLOBs(ex);
					if (roles != null && roles.size() > 0) {
						RolePermission rolePer = (RolePermission) roles.get(0);
						XStream xstream = new XStream(new StaxDriver());
						PermissionMap permissionMap = (PermissionMap) xstream
								.fromXML(rolePer.getRoleval());
						user.setPermissionMaps(permissionMap);
						log.debug("Find role match to user " + username);
					} else {
						log.debug("We can not find any role associate to user "
								+ username);
					}
				} else {
					log.debug("User " + username + " has no any role");
				}
			}
			user.setPassword(null);
			return user;
		}
	}

	@Override
	public SimpleUser findUserByUserNameInAccount(String username, int accountId) {
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setUsername(new StringSearchField(username));
		criteria.setSaccountid(new NumberSearchField(accountId));
		List<SimpleUser> users = userMapperExt.findPagableListByCriteria(
				criteria, new RowBounds(0, Integer.MAX_VALUE));
		if (users == null || users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public void verifyUser(String username) {
		// TODO: fix issue of account
		// SimpleUser user = findUserByUserName(username);
		// if (user != null) {
		// if (RegisterStatusConstants.VERIFICATING.equals(user
		// .getRegisterstatus())) {
		// user.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		// updateWithSession(user, username);
		//
		// } else if (RegisterStatusConstants.ACTIVE.equals(user
		// .getRegisterstatus())) {
		// // do nothing
		// } else if (RegisterStatusConstants.PENDING.equals(user
		// .getRegisterstatus())) {
		// throw new UserInvalidInputException("User " + username
		// + " is pending");
		// }
		// } else {
		// throw new UserIsNotExistedException("There is no user name "
		// + username + " in database");
		// }
	}

	@Override
	public void removeUserAccount(String username, int accountId) {
		UserAccountExample userAccountEx = new UserAccountExample();
		userAccountEx.createCriteria().andUsernameEqualTo(username)
				.andAccountidEqualTo(accountId);
		userAccountMapper.deleteByExample(userAccountEx);

		// if user does not belong to any account then remove this user
		userAccountEx = new UserAccountExample();
		userAccountEx.createCriteria().andUsernameEqualTo(username);
		int userPresentNum = userAccountMapper.countByExample(userAccountEx);
		if (userPresentNum == 0) {
			UserExample userEx = new UserExample();
			userEx.createCriteria().andUsernameEqualTo(username);
			userMapper.deleteByExample(userEx);
		} else {
			// notify listener user is removed, then silently remove user in
			// associate records
			CamelContext camelContext = ApplicationContextUtil
					.getBean(CamelContext.class);
			try {
				UserDeleteListener userDeleteListener = new ProxyBuilder(
						camelContext).endpoint(
						UserEndpoints.USER_REMOVE_ENDPOINT).build(
						UserDeleteListener.class);
				userDeleteListener.userRemoved(username, accountId);
			} catch (Exception e) {
				log.error("Error while notify user delete", e);
			}
		}
	}

	@Override
	public void removeUserAccounts(List<String> usernames, int accountId) {
		for (String username : usernames) {
			removeUserAccount(username, accountId);
		}
	}

	@Override
	public User findUserByUserName(String username) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUsernameEqualTo(username);
		List<User> users = userMapper.selectByExample(ex);
		if (users == null || users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

}
