/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
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
package com.mycollab.module.project.domain;

import com.mycollab.module.crm.domain.Account;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class SimpleInvoice extends Invoice {
    private String createUserFullName;
    private String createUserAvatarId;
    private String assignUserFullName;
    private String assignUserAvatarId;
    private Account client;

    public Account getClient() {
        return client;
    }

    public void setClient(Account client) {
        this.client = client;
    }

    public String getCreateUserFullName() {
        return createUserFullName;
    }

    public void setCreateUserFullName(String createUserFullName) {
        this.createUserFullName = createUserFullName;
    }

    public String getCreateUserAvatarId() {
        return createUserAvatarId;
    }

    public void setCreateUserAvatarId(String createUserAvatarId) {
        this.createUserAvatarId = createUserAvatarId;
    }

    public String getAssignUserFullName() {
        return assignUserFullName;
    }

    public void setAssignUserFullName(String assignUserFullName) {
        this.assignUserFullName = assignUserFullName;
    }

    public String getAssignUserAvatarId() {
        return assignUserAvatarId;
    }

    public void setAssignUserAvatarId(String assignUserAvatarId) {
        this.assignUserAvatarId = assignUserAvatarId;
    }
}
