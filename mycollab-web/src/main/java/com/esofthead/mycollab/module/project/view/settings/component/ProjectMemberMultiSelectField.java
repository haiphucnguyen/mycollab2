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

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProjectMemberMultiSelectField extends MultiSelectComp {
	private static final long serialVersionUID = 1L;

	private static String displayName = "memberFullName";

	public ProjectMemberMultiSelectField() {
		super(displayName);
	}

	public ProjectMemberMultiSelectField(String width) {
		super(displayName, width);
	}

	@Override
	protected void initData() {
		ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setStatus(new StringSearchField(
				ProjectMemberStatusConstants.ACTIVE));

		ProjectMemberService userService = ApplicationContextUtil
				.getSpringBean(ProjectMemberService.class);
		dataList = userService
				.findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
	}

	@Override
	protected void createItemPopup() {
		for (int i = 0; i < dataList.size(); i++) {

			Object itemComp = dataList.get(i);
			String itemName = "";
			String username = "";
			String userAvatarId = "";

			try {
				itemName = (String) PropertyUtils.getProperty(itemComp,
						displayName);
				username = (String) PropertyUtils.getProperty(itemComp,
						"username");
				userAvatarId = (String) PropertyUtils.getProperty(itemComp,
						"memberAvatarId");
			} catch (Exception e) {
				e.printStackTrace();
			}

			final CheckBox chkItem = new CheckBox(itemName);
			chkItem.setImmediate(true);
			chkItem.setIcon(UserAvatarControlFactory.createAvatarResource(
					userAvatarId, 16));
			chkItem.addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					Boolean value = (Boolean) chkItem.getValue();
					String objDisplayName = "";
					if (displayName != "") {
						Object itemObj = getElementInDataListByName(chkItem
								.getCaption());
						try {
							objDisplayName = (String) PropertyUtils
									.getProperty(itemObj, displayName);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (itemObj != null) {
							if (isClicked) {
								removeElementSelectedListByName(objDisplayName);
								if (value) {
									if (!selectedItemsList.contains(itemObj)) {
										selectedItemsList.add(itemObj);
									}
								}
								setSelectedItems(selectedItemsList);
							}
						}
					} else {
						if (isClicked) {
							if (value) {
								if (!selectedItemsList.contains(chkItem
										.getCaption())) {
									selectedItemsList.add(chkItem.getCaption());
								}
							} else {
								selectedItemsList.remove(chkItem.getCaption());
							}
							setSelectedItems(selectedItemsList);
						}
					}
				}
			});
			if (!componentList.containsKey(chkItem.getCaption())) {
				componentList.put(chkItem.getCaption(), chkItem);
			}
		}

		VerticalLayout popupContent = new VerticalLayout();
		for (final CheckBox chk : this.componentList.values()) {
			popupContent.addComponent(chk);
		}
		componentPopupSelection.setContent(popupContent);
	}

}
