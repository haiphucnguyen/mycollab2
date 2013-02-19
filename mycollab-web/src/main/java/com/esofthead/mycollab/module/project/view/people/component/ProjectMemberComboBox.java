package com.esofthead.mycollab.module.project.view.people.component;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

public class ProjectMemberComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public ProjectMemberComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);

		ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));

		ProjectMemberService userService = AppContext
				.getSpringBean(ProjectMemberService.class);
		List<SimpleProjectMember> memberList = userService
				.findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		loadUserList(memberList);

	}

	public ProjectMemberComboBox(List<SimpleProjectMember> userList) {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
		loadUserList(userList);
	}

	private void loadUserList(List<SimpleProjectMember> memberList) {
		BeanContainer<String, SimpleProjectMember> beanItem = new BeanContainer<String, SimpleProjectMember>(
				SimpleProjectMember.class);
		beanItem.setBeanIdProperty("id");

		for (SimpleProjectMember member : memberList) {
			beanItem.addBean(member);
		}

		this.setContainerDataSource(beanItem);
		this.setItemCaptionPropertyId("memberFullName");
	}

}
