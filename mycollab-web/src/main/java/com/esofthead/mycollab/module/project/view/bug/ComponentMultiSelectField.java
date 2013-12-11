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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.web.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 */
@SuppressWarnings("serial")
public class ComponentMultiSelectField extends MultiSelectComp {

	public ComponentMultiSelectField() {
		super("componentname");
	}

	public ComponentMultiSelectField(String width) {
		super("componentname", width);
	}

	@Override
	protected void initData() {
		ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();
		searchCriteria.setStatus(new StringSearchField("Open"));

		searchCriteria.setProjectid(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProjectId()));

		ComponentService componentService = ApplicationContextUtil
				.getSpringBean(ComponentService.class);
		dataList = componentService
				.findPagableListByCriteria(new SearchRequest<ComponentSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

	}
}
