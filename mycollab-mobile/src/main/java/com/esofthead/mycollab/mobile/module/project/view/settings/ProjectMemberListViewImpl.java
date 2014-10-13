package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.mobile.module.project.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
@ViewComponent
public class ProjectMemberListViewImpl extends
		AbstractListViewComp<ProjectMemberSearchCriteria, SimpleProjectMember>
		implements ProjectMemberListView {

	private static final long serialVersionUID = 3008732621100597514L;

	public ProjectMemberListViewImpl() {
		this.addStyleName("member-list-view");
		this.setCaption(AppContext
				.getMessage(ProjectMemberI18nEnum.VIEW_LIST_TITLE));
	}

	@Override
	protected AbstractPagedBeanList<ProjectMemberSearchCriteria, SimpleProjectMember> createBeanTable() {
		return new ProjectMemberListDisplay();
	}

	@Override
	protected Component createRightComponent() {
		Button inviteMemberBtn = new Button();
		inviteMemberBtn.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 3817727548038589396L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				EventBusFactory.getInstance().post(
						new ProjectMemberEvent.GotoInviteMembers(this, null));
			}
		});
		inviteMemberBtn.setStyleName("add-btn");
		return inviteMemberBtn;
	}

}
