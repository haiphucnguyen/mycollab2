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
import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class VersionMultiSelectField extends MultiSelectComp {

	public VersionMultiSelectField() {
		super("versionname");
	}

	public VersionMultiSelectField(String width) {
		super("versionname", width);
	}

	@Override
	protected void initData() {
		VersionSearchCriteria searchCriteria = new VersionSearchCriteria();
		searchCriteria.setStatus(new StringSearchField("Open"));

		searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProjectId()));

		VersionService versionService = ApplicationContextUtil
				.getSpringBean(VersionService.class);
		dataList = versionService
				.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

	}
}
