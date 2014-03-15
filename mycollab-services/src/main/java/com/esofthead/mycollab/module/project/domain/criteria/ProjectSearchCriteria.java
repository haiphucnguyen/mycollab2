/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProjectSearchCriteria extends SearchCriteria {
	private static final long serialVersionUID = 1L;

	private StringSearchField username;
	private SetSearchField<String> projectStatuses;
	private StringSearchField involvedMember;

	public StringSearchField getUsername() {
		return username;
	}

	public void setUsername(StringSearchField username) {
		this.username = username;
	}

	public SetSearchField<String> getProjectStatuses() {
		return projectStatuses;
	}

	public void setProjectStatuses(SetSearchField<String> projectStatuses) {
		this.projectStatuses = projectStatuses;
	}

	public StringSearchField getInvolvedMember() {
		return involvedMember;
	}

	public void setInvolvedMember(StringSearchField involvedMember) {
		this.involvedMember = involvedMember;
	}
}
