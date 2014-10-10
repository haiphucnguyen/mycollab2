package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.mobile.module.project.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class ProjectMemberListViewImpl extends
		AbstractListViewComp<ProjectMemberSearchCriteria, SimpleProjectMember>
		implements ProjectMemberListView {

	private static final long serialVersionUID = 3008732621100597514L;

	@Override
	protected AbstractPagedBeanList<ProjectMemberSearchCriteria, SimpleProjectMember> createBeanTable() {
		return new ProjectMemberListDisplay();
	}

	@Override
	protected Component createRightComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}
