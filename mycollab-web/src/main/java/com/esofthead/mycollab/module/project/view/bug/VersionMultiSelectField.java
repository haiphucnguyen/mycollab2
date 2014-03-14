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

package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.data.Property;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class VersionMultiSelectField extends MultiSelectComp {
	private static final long serialVersionUID = 1L;

	public VersionMultiSelectField() {
		super("versionname");
	}

	@Override
	protected List<Version> createData() {
		VersionSearchCriteria searchCriteria = new VersionSearchCriteria();
		searchCriteria.setStatus(new StringSearchField("Open"));

		searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProjectId()));

		VersionService versionService = ApplicationContextUtil
				.getSpringBean(VersionService.class);
		List<Version> versions = versionService
				.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));
		return versions;
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		List<Version> versions = (List<Version>) newDataSource.getValue();
		if (versions != null) {
			this.setSelectedItems(versions);
		}
		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}
}
