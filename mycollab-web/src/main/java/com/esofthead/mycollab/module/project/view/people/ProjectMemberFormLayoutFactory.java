/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;


import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class ProjectMemberFormLayoutFactory implements
		IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private String title;
	private ProjectMemberInformationLayout userInformationLayout;

	public ProjectMemberFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout userAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/user/user.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			userAddLayout.addTopControls(topPanel);
		}

		userInformationLayout = new ProjectMemberInformationLayout();
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

	public static class ProjectMemberInformationLayout implements
			IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			VerticalLayout layout = new VerticalLayout();
			Label organizationHeader = new Label("Member Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 2);

			layout.addComponent(informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("username")) {
				informationLayout.addComponent(field, "User", 0, 0);
			} else if (propertyId.equals("isadmin")) {
				informationLayout.addComponent(field, "Is Admin", 0, 1);
			}
		}
	}
}
