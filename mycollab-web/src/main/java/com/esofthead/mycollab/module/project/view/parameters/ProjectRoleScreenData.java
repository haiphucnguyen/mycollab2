package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class ProjectRoleScreenData {
	public static class Search extends ScreenData<ProjectRoleSearchCriteria> {

		public Search(ProjectRoleSearchCriteria params) {
			super(params);
		}
	}
}
