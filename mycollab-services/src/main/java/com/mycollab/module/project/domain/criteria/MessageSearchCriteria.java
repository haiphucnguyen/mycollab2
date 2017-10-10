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
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
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
package com.mycollab.module.project.domain.criteria;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class MessageSearchCriteria extends SearchCriteria {
	private static final long serialVersionUID = 1L;

	private NumberSearchField id;

	private SetSearchField<Integer> projectids;

	private StringSearchField category;

	private StringSearchField title;

	private StringSearchField message;

	public NumberSearchField getId() {
		return id;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public SetSearchField<Integer> getProjectids() {
		return projectids;
	}

	public void setProjectids(SetSearchField<Integer> projectids) {
		this.projectids = projectids;
	}

	public StringSearchField getCategory() {
		return category;
	}

	public void setCategory(StringSearchField category) {
		this.category = category;
	}

	public void setTitle(StringSearchField title) {
		this.title = title;
	}

	public StringSearchField getTitle() {
		return title;
	}

	public void setMessage(StringSearchField message) {
		this.message = message;
	}

	public StringSearchField getMessage() {
		return message;
	}
}
