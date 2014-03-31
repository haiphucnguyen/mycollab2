package com.esofthead.mycollab.module.project.view.settings.component;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.ListSelect;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class ProjectMemberListSelect extends ListSelect {
	private static final long serialVersionUID = 1L;

	public ProjectMemberListSelect() {
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		this.setNullSelectionAllowed(false);
		this.setMultiSelect(true);

		ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setStatus(new StringSearchField(
				ProjectMemberStatusConstants.ACTIVE));

		ProjectMemberService userService = ApplicationContextUtil
				.getSpringBean(ProjectMemberService.class);
		List<SimpleProjectMember> memberList = userService
				.findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		loadUserList(memberList);
	}

	private void loadUserList(List<SimpleProjectMember> memberList) {

		for (SimpleProjectMember member : memberList) {
			this.addItem(member.getUsername());
			this.setItemCaption(member.getUsername(), member.getDisplayName());
			this.setItemIcon(
					member.getUsername(),
					UserAvatarControlFactory.createAvatarResource(
							member.getMemberAvatarId(), 16));
		}
		this.setRows(4);
	}
}
