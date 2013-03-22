/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberListViewImpl extends AbstractView implements
		ProjectMemberListView {
	private static final long serialVersionUID = 1L;

	@Override
	public void setSearchCriteria(ProjectMemberSearchCriteria searchCriteria) {
		ProjectMemberService prjMemberService = AppContext
				.getSpringBean(ProjectMemberService.class);
		List<SimpleProjectMember> memberLists = prjMemberService
				.findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

		this.removeAllComponents();
		CssLayout contentLayout = new CssLayout();
		contentLayout.setWidth("100%");
		for (SimpleProjectMember member : memberLists) {
			contentLayout.addComponent(generateMemberBlock(member));
		}
		this.addComponent(contentLayout);
	}

	private Component generateMemberBlock(final SimpleProjectMember member) {
		CssLayout memberBlock = new CssLayout();
		memberBlock.addStyleName("member-block");
		memberBlock.setWidth(SIZE_UNDEFINED, 0);
		VerticalLayout blockContent = new VerticalLayout();
		HorizontalLayout blockTop = new HorizontalLayout();
		blockTop.setSpacing(true);
		Embedded memberAvatar = UserAvatarControlFactory
				.createUserAvatarEmbeddedControl(AppContext.getUsername(), 100);
		blockTop.addComponent(memberAvatar);

		VerticalLayout memberInfo = new VerticalLayout();
		ButtonLink memberLink = new ButtonLink(member.getMemberFullName());
		memberLink.addListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance()
						.fireEvent(
								new ProjectMemberEvent.GotoRead(
										ProjectMemberListViewImpl.this, member
												.getId()));
			}
		});
		memberInfo.addComponent(memberLink);

		Label memberEmailLabel = new Label(member.getUsername());
		memberEmailLabel.addStyleName("member-email");
		memberInfo.addComponent(memberEmailLabel);

		String bugStatus = member.getNumOpenBugs() + " open bug";
		if (member.getNumOpenBugs() > 2)
			bugStatus += "s";

		String taskStatus = member.getNumOpenTasks() + " open task";
		if (member.getNumOpenTasks() > 2)
			taskStatus += "s";

		Label memberWorkStatus = new Label(bugStatus + " – " + taskStatus);
		memberInfo.addComponent(memberWorkStatus);

		blockTop.addComponent(memberInfo);
		blockTop.setExpandRatio(memberInfo, 1.0f);
		blockContent.addComponent(blockTop);

		Label memberRole = new Label();
		if (member.getIsadmin()) {
			memberRole.setValue("Project Admin");
		} else {
			memberRole.setValue(member.getRoleName());
		}
		memberRole.addStyleName("member-role");
		memberRole.setSizeUndefined();
		blockContent.addComponent(memberRole);
		blockContent.setComponentAlignment(memberRole, Alignment.MIDDLE_RIGHT);
		blockContent.setSizeUndefined();

		memberBlock.addComponent(blockContent);
		return memberBlock;
	}
}