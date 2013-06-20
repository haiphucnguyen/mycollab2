package com.esofthead.mycollab.module.project.view.people.component;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

@SuppressWarnings("serial")
public class ProjectMemberMultiSelectField extends MultiSelectComp {

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

		ProjectMemberService userService = AppContext
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
			chkItem.addListener(new ValueChangeListener() {
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
			if (!componentPoupMap.containsKey(chkItem.getCaption())) {
				componentPoupMap.put(chkItem.getCaption(), chkItem);
				addItemToComponent(chkItem);
			}
		}
	}

}
