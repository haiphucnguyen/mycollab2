/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.project.ui.components.ProjectUiUtils;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class RoleFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private final String title;
	private RoleInformationLayout userInformationLayout;

	public RoleFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout userAddLayout = new AddViewLayout(title,
				MyCollabResource.newResource("icons/48/user/group.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			userAddLayout.addTopControls(topPanel);
		}

		userInformationLayout = new RoleInformationLayout();
		userInformationLayout.getLayout().setWidth("100%");
		userAddLayout.addBody(userInformationLayout.getLayout());

		Layout bottomPanel = createBottomPanel();
		if (bottomPanel != null) {
			userAddLayout.addBottomControls(bottomPanel);
		}

		return userAddLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(Object propertyId, Field field) {
		userInformationLayout.attachField(propertyId, field);
	}

	public static class RoleInformationLayout implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			VerticalLayout layout = new VerticalLayout();
			Label organizationHeader = new Label("Role Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = ProjectUiUtils.getGridFormLayoutHelper(6, 2);
			informationLayout.getLayout().setWidth("100%");

			layout.addComponent(informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("rolename")) {
				informationLayout.addComponent(field, "Role Name", 0, 0);
			} else if (propertyId.equals("description")) {
				informationLayout.addComponent(field, "Description", 0, 1);
			}
		}
	}
}
