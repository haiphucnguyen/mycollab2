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

import java.util.List;

public class SimpleUser extends User {
	private static final long serialVersionUID = 1L;

	private List<Role> roles;

	private String userId;

	private String country;

	public SimpleUser() {
	}

	public SimpleUser(User user) {
		this.setUsername(user.getUsername());
		this.setUserId(user.getUsername());
		this.setDisplayname(user.getDisplayname());
		this.setFirstname(user.getFirstname());
		this.setMiddlename(user.getMiddlename());
		this.setLastname(user.getLastname());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setAccountid(user.getAccountid());
		this.setCompany(user.getCompany());
		this.setTimezoneid(user.getTimezoneid());
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String getDisplayname() {
		String displayName = super.getDisplayname();
		if (displayName != null) {
			return displayName;
		} else {
			String firstname = getFirstname();
			String lastname = getLastname();

			if (firstname != null && lastname != null) {
				return firstname + " " + lastname;
			} else {
				return getUsername();
			}
		}
	}
}
