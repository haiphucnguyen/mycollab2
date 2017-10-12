/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.user.domain.criteria;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.StringSearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 *
 */
public class RoleSearchCriteria extends SearchCriteria {
	private static final long serialVersionUID = 1L;

	private StringSearchField username;
	private StringSearchField roleName;

	public StringSearchField getUsername() {
		return username;
	}

	public void setUsername(StringSearchField username) {
		this.username = username;
	}

	public void setRoleName(StringSearchField roleName) {
		this.roleName = roleName;
	}

	public StringSearchField getRoleName() {
		return roleName;
	}

}
