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
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.common.domain.PermissionMap;

public class SimpleUser extends User {

	private static final long serialVersionUID = 1L;
	public static final String ACTIVE_STATUS = "active";
	public static final String INACTION_STATUS = "inactive";
	public static final String PENDING_STATUS = "pending";
	public static final int ADMIN_VAL = 1;

	private Boolean isAdmin;
	private Integer roleid;
	private String roleName;
	private PermissionMap permissionMaps;
	private Boolean isAccountOwner;
	private String subdomain;
	private Integer accountId;

	public String getDisplayName() {
		String result = getFirstname() + " " + getLastname();
		if (result.trim().equals("")) {
			return getUsername();
		}
		return result;
	}

	public PermissionMap getPermissionMaps() {
		return permissionMaps;
	}

	public void setPermissionMaps(PermissionMap permissionMaps) {
		this.permissionMaps = permissionMaps;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Boolean getIsAccountOwner() {
		return isAccountOwner;
	}

	public void setIsAccountOwner(Boolean isAccountOwner) {
		this.isAccountOwner = isAccountOwner;
	}

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
