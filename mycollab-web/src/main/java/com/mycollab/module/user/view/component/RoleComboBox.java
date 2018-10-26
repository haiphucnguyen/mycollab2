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
package com.mycollab.module.user.view.component;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.module.user.accountsettings.localization.RoleI18nEnum;
import com.mycollab.module.user.domain.SimpleRole;
import com.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.mycollab.module.user.service.RoleService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.ComboBox;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
// TODO
public class RoleComboBox extends ComboBox {
    private static final long serialVersionUID = 1L;

    public RoleComboBox() {
//        this.setEmptySelectionAllowed(false);
//        this.setItemCaptionMode(ItemCaptionMode.PROPERTY);

        RoleSearchCriteria criteria = new RoleSearchCriteria();

        RoleService roleService = AppContextUtil.getSpringBean(RoleService.class);
        List<SimpleRole> roles = (List<SimpleRole>) roleService.findPageableListByCriteria(new BasicSearchRequest<>(criteria));

//        BeanContainer<String, SimpleRole> beanItem = new BeanContainer<>(SimpleRole.class);
//        beanItem.setBeanIdProperty("id");
//        this.setContainerDataSource(beanItem);
//        this.setItemCaptionPropertyId("rolename");

        SimpleRole ownerRole = new SimpleRole();
        ownerRole.setId(-1);
        ownerRole.setRolename(UserUIContext.getMessage(RoleI18nEnum.OPT_ACCOUNT_OWNER));
//        beanItem.addBean(ownerRole);

        roles.forEach(role -> {
//            beanItem.addBean(role);
            if (Boolean.TRUE.equals(role.getIsdefault())) {
                this.setValue(role.getId());
            }
        });
    }
}
