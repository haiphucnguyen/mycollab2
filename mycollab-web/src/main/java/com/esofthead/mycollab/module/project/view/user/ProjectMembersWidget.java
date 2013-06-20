/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectMembersWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private BeanList<ProjectMemberService, ProjectMemberSearchCriteria, SimpleProjectMember> memberList;

	public ProjectMembersWidget() {
		super("Members", new VerticalLayout());

		memberList = new BeanList<ProjectMemberService, ProjectMemberSearchCriteria, SimpleProjectMember>(
				AppContext.getSpringBean(ProjectMemberService.class),
				MemberRowDisplayHandler.class);
		this.bodyContent.addComponent(new LazyLoadWrapper(memberList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showInformation() {
		ProjectMemberSearchCriteria searchCriteria = new ProjectMemberSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		memberList.setSearchCriteria(searchCriteria);
	}

	public static class MemberRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleProjectMember> {

		@Override
		public Component generateRow(SimpleProjectMember member, int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");
			header.addComponent(new ProjectUserLink(member.getUsername(),
					member.getMemberAvatarId(), member.getMemberFullName(),
					false, true));
			layout.addComponent(header);

			CssLayout body = new CssLayout();
			body.setStyleName("activity-date");

			Label memberRole = new Label();
			memberRole.setContentMode(Label.CONTENT_XHTML);
			String textRole = "";
			if (member.getIsadmin() != null
					&& member.getIsadmin() == Boolean.TRUE) {
				textRole = "<a style=\"color: #b00000;\"> Project Admin </a>";
			} else {
				textRole = member.getRoleName();
			}
			textRole += " - Joined from "
					+ DateTimeUtils.getStringDateFromNow(member.getJoindate());
			memberRole.setValue(textRole);

			body.addComponent(memberRole);
			layout.addComponent(body);
			return layout;
		}

	}
}
