/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.user.domain;

import java.util.Collection;

/**
 * 
 * @author hai
 * 
 */
public class RoleCollection {

	public static final String REGISTERED_USER = "REGISTERED_USER";
	private Collection<Role> roles;

	public RoleCollection(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean impliesRole(String roleName) {

		for (Role role : roles) {
			if (role.getRolename().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
