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
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.CompoundCustomField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ComponentMultiSelectField extends CompoundCustomField {
	private static final long serialVersionUID = 1L;

	private MultiSelectComp<Component> componentSelection;

	@Override
	protected com.vaadin.ui.Component initContent() {
		ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();
		searchCriteria.setStatus(new StringSearchField("Open"));

		searchCriteria.setProjectid(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProjectId()));

		ComponentService componentService = ApplicationContextUtil
				.getSpringBean(ComponentService.class);

		List<Component> components = (List<Component>) componentService
				.findPagableListByCriteria(new SearchRequest<ComponentSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

		componentSelection = new MultiSelectComp<Component>("componentname",
				components);
		return componentSelection;
	}

	public List<Component> getSelectedItems() {
		return componentSelection.getSelectedItems();
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}
}
