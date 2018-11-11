/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.user.ui.components;

import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.billing.RegisterStatusConstants;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.mycollab.module.user.service.UserService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.IconGenerator;
import com.vaadin.ui.ItemCaptionGenerator;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ActiveUserComboBox extends ComboBox implements Converter<String, SimpleUser> {
    private static final long serialVersionUID = 1L;

    private List<SimpleUser> users;

    public ActiveUserComboBox() {
        UserSearchCriteria criteria = new UserSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
        criteria.setRegisterStatuses(new SetSearchField<>(RegisterStatusConstants.ACTIVE));

        UserService userService = AppContextUtil.getSpringBean(UserService.class);
        users = (List<SimpleUser>) userService.findPageableListByCriteria(new BasicSearchRequest<>(criteria));
        setItems(users);
        setItemCaptionGenerator((ItemCaptionGenerator<SimpleUser>) user -> StringUtils.trim(user.getDisplayName(), 30, true));
        setItemIconGenerator((IconGenerator<SimpleUser>) user -> UserAvatarControlFactory.createAvatarResource(user.getAvatarid(), 16));
    }

    @Override
    public Result<SimpleUser> convertToModel(String value, ValueContext context) {
        Optional<SimpleUser> userResult = users.stream().filter(user -> user.getUsername().equals(value)).findFirst();
        return userResult.isPresent() ? Result.ok(userResult.get()) : Result.error("Can not find user " + value);
    }

    @Override
    public String convertToPresentation(SimpleUser value, ValueContext context) {
        return value.getUsername();
    }
}
