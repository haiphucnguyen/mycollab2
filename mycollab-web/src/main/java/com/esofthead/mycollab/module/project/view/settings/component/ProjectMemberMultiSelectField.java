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

import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProjectMemberMultiSelectField extends MultiSelectComp {
	public ProjectMemberMultiSelectField() {
		super(displayName, null);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	private static String displayName = "memberFullName";

//	@Override
//	protected void initData() {
//		ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
//		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
//				.getProjectId()));
//		criteria.setStatus(new StringSearchField(
//				ProjectMemberStatusConstants.ACTIVE));
//
//		ProjectMemberService userService = ApplicationContextUtil
//				.getSpringBean(ProjectMemberService.class);
//		items = userService
//				.findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
//						criteria, 0, Integer.MAX_VALUE));
//	}
//
//	@Override
//	protected void createItemPopup() {
//		for (int i = 0; i < items.size(); i++) {
//
//			Object itemComp = items.get(i);
//			String itemName = "";
//			String username = "";
//			String userAvatarId = "";
//
//			try {
//				itemName = (String) PropertyUtils.getProperty(itemComp,
//						displayName);
//				username = (String) PropertyUtils.getProperty(itemComp,
//						"username");
//				userAvatarId = (String) PropertyUtils.getProperty(itemComp,
//						"memberAvatarId");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			final CheckBox chkItem = new CheckBox(itemName);
//			chkItem.setImmediate(true);
//			chkItem.setIcon(UserAvatarControlFactory.createAvatarResource(
//					userAvatarId, 16));
//			
//		}
//	}

}
