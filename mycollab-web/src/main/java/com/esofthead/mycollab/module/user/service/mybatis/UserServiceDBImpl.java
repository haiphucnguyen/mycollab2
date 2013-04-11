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
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.file.service.UserAvatarService;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.UserIsNotExistedException;
import com.esofthead.mycollab.module.user.dao.RolePermissionMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.dao.UserMapperExt;
import com.esofthead.mycollab.module.user.domain.RolePermission;
import com.esofthead.mycollab.module.user.domain.RolePermissionExample;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@Service
public class UserServiceDBImpl extends
		DefaultService<String, User, UserSearchCriteria> implements UserService {

	private static Logger log = LoggerFactory
			.getLogger(UserServiceDBImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMapperExt userMapperExt;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private UserAvatarService userAvatarService;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return userMapper;
	}

	@Override
	public ISearchableDAO<UserSearchCriteria> getSearchMapper() {
		return userMapperExt;
	}

	@Override
	public int saveWithSession(User record, String username) {
		if (record.getPassword() != null) {
			record.setPassword(PasswordEncryptHelper.encryptSaltPassword(record
					.getPassword()));
		}

		if (record.getRegisterstatus() == null) {
			record.setRegisterstatus(RegisterStatusConstants.VERIFICATING);
		}

		if (record.getUsername() == null) {
			record.setUsername(record.getEmail());
		}

		userMapper.insert(record);

		// set default user avatar
		try {
			log.debug("Set default user avatar");
			InputStream imageResourceStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"assets/images/default_user_avatar_100.png");
			BufferedImage imageBuff = ImageIO.read(imageResourceStream);
			userAvatarService.uploadAvatar(imageBuff, username,
					record.getAccountid());
		} catch (Exception e) {
			log.error("Error while create default user avatar");
		}

		return 1;
	}

	@Override
	public int updateWithSession(User record, String username) {
		if (record.getPassword() != null) {
			record.setPassword(PasswordEncryptHelper.encryptSaltPassword(record
					.getPassword()));
		}
		return super.updateWithSession(record, username);
	}

	@Override
	public void removeWithSession(List<String> primaryKeys, String username) {
		userMapperExt.removeKeysWithSession(primaryKeys);
	}

	@Override
	public SimpleUser authentication(String username, String password,
			boolean isPasswordEncrypt) {
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setUsername(new StringSearchField(username));
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
			if (user.getIsadmin() == null
					|| (user.getIsadmin() != null && !user.getIsadmin())) {
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
	public SimpleUser findUserByUserName(String username) {
		return userMapperExt.findUserByUserName(username);
	}

	@Override
	public void verifyUser(String username) {
		SimpleUser user = findUserByUserName(username);
		if (user != null) {
			if (RegisterStatusConstants.VERIFICATING.equals(user
					.getRegisterstatus())) {
				user.setRegisterstatus(RegisterStatusConstants.ACTIVE);
				updateWithSession(user, username);

			} else if (RegisterStatusConstants.ACTIVE.equals(user
					.getRegisterstatus())) {
				// do nothing
			} else if (RegisterStatusConstants.PENDING.equals(user
					.getRegisterstatus())) {
				throw new UserInvalidInputException("User " + username
						+ " is pending");
			}
		} else {
			throw new UserIsNotExistedException("There is no user name "
					+ username + " in database");
		}
	}
}
