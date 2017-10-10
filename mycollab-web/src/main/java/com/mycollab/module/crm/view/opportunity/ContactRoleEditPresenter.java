/**
 * mycollab-web - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.opportunity;

import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.view.CrmGenericPresenter;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class ContactRoleEditPresenter extends CrmGenericPresenter<ContactRoleEditView> {
    private static final long serialVersionUID = 1L;

    public ContactRoleEditPresenter() {
        super(ContactRoleEditView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY)) {
            SimpleOpportunity opportunity = (SimpleOpportunity) data.getParams();
            super.onGo(container, data);
            view.display(opportunity);

            AppUI.addFragment("crm/opportunity/addcontactroles", "Add Contact Roles");
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }

    }

}
