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
		public Component generateRow(SimpleProjectMember obj, int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");
			header.addComponent(new ProjectUserLink(obj.getUsername(), obj
					.getMemberFullName(), true));
			layout.addComponent(header);

			CssLayout body = new CssLayout();
			body.setStyleName("activity-date");
			Label dateLbl = new Label("Joined from "
					+ DateTimeUtils.getStringDateFromNow(obj.getJoindate()));
			body.addComponent(dateLbl);

			layout.addComponent(body);
			return layout;
		}

	}
}
