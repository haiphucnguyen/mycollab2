/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import com.vaadin.ui.ComboBox;

public class ProjectMemberComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public ProjectMemberComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);

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
		this.setNullSelectionAllowed(false);
	}

	public ProjectMemberComboBox(List<SimpleProjectMember> userList) {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);
		loadUserList(userList);
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
	}

}
