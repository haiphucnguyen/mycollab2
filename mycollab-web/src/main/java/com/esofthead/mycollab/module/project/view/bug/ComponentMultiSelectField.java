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
 * @author haiphucnguyen
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
