package com.esofthead.mycollab.module.project.view.people.component;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.bug.MultiSelectComp;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class ProjectMemberMultiSelectField extends MultiSelectComp {

	public ProjectMemberMultiSelectField() {
		super("memberFullName");
	}

	public ProjectMemberMultiSelectField(String width) {
		super("memberFullName", width);
	}

	@Override
	protected void initData() {
		ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));

		ProjectMemberService userService = AppContext
				.getSpringBean(ProjectMemberService.class);
        dataList = userService
		.findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
				criteria, 0, Integer.MAX_VALUE));
	}

}
