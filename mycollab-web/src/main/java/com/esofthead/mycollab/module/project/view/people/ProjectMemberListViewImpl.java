/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
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
		Button createBtn = new Button("Invite", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ProjectMemberEvent.GotoAdd(this, null));
			}
		});
		createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.USERS));
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

		this.addComponent(createBtn);
		this.setComponentAlignment(createBtn, Alignment.MIDDLE_RIGHT);
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

		VerticalLayout blockContent = new VerticalLayout();
		HorizontalLayout blockTop = new HorizontalLayout();
		blockTop.setSpacing(true);
		Embedded memberAvatar = UserAvatarControlFactory
				.createUserAvatarEmbeddedControl(member.getUsername(), 100);
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
		memberLink.setWidth("100%");
		memberLink.setHeight(SIZE_UNDEFINED, 0);
		
		memberInfo.addComponent(memberLink);

		Label memberEmailLabel = new Label("<a href='mailto:"
				+ member.getUsername() + "'>" + member.getUsername() + "</a>",
				Label.CONTENT_XHTML);
		memberEmailLabel.addStyleName("member-email");
		memberEmailLabel.setWidth("100%");
		memberInfo.addComponent(memberEmailLabel);

		String bugStatus = member.getNumOpenBugs() + " open bug";
		if (member.getNumOpenBugs() > 2)
			bugStatus += "s";

		String taskStatus = member.getNumOpenTasks() + " open task";
		if (member.getNumOpenTasks() > 2)
			taskStatus += "s";

		Label memberWorkStatus = new Label(bugStatus + " � " + taskStatus);
		memberInfo.addComponent(memberWorkStatus);
		memberInfo.setWidth("100%");

		blockTop.addComponent(memberInfo);
		blockTop.setExpandRatio(memberInfo, 1.0f);
		blockTop.setWidth("100%");
		blockContent.addComponent(blockTop);

		Label memberRole = new Label();
		if (member.getIsadmin() != null && member.getIsadmin() == Boolean.TRUE) {
			memberRole.setValue("Project Admin");
			memberRole.addStyleName("is-admin");
		} else {
			memberRole.setValue(member.getRoleName());
		}
		memberRole.addStyleName("member-role");
		memberRole.setSizeUndefined();
		blockContent.addComponent(memberRole);
		blockContent.setComponentAlignment(memberRole, Alignment.MIDDLE_RIGHT);
		blockContent.setWidth("100%");

		memberBlock.addComponent(blockContent);
		return memberBlock;
	}
}