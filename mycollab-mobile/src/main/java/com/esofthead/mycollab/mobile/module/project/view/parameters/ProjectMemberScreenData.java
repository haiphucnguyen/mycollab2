package com.esofthead.mycollab.mobile.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class ProjectMemberScreenData {
	public static class Search extends ScreenData<ProjectMemberSearchCriteria> {

		public Search(ProjectMemberSearchCriteria params) {
			super(params);
		}
	}

	public static class Add extends ScreenData<ProjectMember> {

		public Add(ProjectMember params) {
			super(params);
		}
	}

	public static class InviteProjectMembers extends ScreenData<Object> {

		public InviteProjectMembers() {
			super(null);
		}
	}

	public static class Read extends ScreenData<Object> {

		public Read(Object params) {
			super(params);
		}
	}
}
