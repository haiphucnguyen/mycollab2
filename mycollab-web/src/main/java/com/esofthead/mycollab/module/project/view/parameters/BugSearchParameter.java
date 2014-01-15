/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 *
 */
public class BugSearchParameter {
	private BugSearchCriteria searchCriteria;

	private String screenTitle;
	
	public BugSearchParameter(String screenTitle, BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		this.screenTitle = screenTitle;
	}

	public BugSearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public String getScreenTitle() {
		return screenTitle;
	}

	public void setScreenTitle(String screenTitle) {
		this.screenTitle = screenTitle;
	}
}
