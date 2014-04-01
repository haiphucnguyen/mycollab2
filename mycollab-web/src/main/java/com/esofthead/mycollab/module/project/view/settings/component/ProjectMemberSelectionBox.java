package com.esofthead.mycollab.module.project.view.settings.component;

import java.util.Collection;
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
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class ProjectMemberSelectionBox extends ComboBox {
	private static final long serialVersionUID = 1L;

	public ProjectMemberSelectionBox() {
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		this.setNullSelectionAllowed(false);

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
			this.addItem(member);
			this.setItemCaption(member, member.getDisplayName());
			this.setItemIcon(
					member,
					UserAvatarControlFactory.createAvatarResource(
							member.getMemberAvatarId(), 16));
		}
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof String) {
			Collection<?> containerPropertyIds = this.getItemIds();
			for (Object id : containerPropertyIds) {
				if (id instanceof SimpleProjectMember) {
					if (value.equals(((SimpleProjectMember) id).getUsername())) {
						super.setValue(id);
					}
				}
			}
		} else {
			super.setValue(value);
		}
	}
}
