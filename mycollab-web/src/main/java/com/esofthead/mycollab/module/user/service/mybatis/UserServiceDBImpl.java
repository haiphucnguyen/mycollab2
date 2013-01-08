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

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.user.AuthenticationException;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.dao.UserMapperExt;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserExample;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDBImpl extends DefaultService<String, User, UserSearchCriteria>
        implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMapperExt userMapperExt;

    @Override
    public ICrudGenericDAO getCrudMapper() {
        return userMapper;
    }

    @Override
    public ISearchableDAO<UserSearchCriteria> getSearchMapper() {
        return userMapperExt;
    }

    @Override
    public SimpleUser authentication(String username, String password) {
        UserSearchCriteria criteria = new UserSearchCriteria();
        criteria.setUsername(new StringSearchField(username));
        List<SimpleUser> users = findPagableListByCriteria(new SearchRequest<UserSearchCriteria>(criteria, 0, Integer.MAX_VALUE));
        if (users == null || users.isEmpty()) {
            throw new AuthenticationException("Invalid username or password");
        } else {
            SimpleUser user = users.get(0);
            if (!password.equals(
                    user.getPassword())) {
                throw new AuthenticationException("Invalid username or password");
            }

            return user;
        }
    }
}
