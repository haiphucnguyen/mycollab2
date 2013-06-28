/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
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
	private final String title;
	private ProjectMemberInformationLayout userInformationLayout;
	private final Resource avatarRes;

	public ProjectMemberFormLayoutFactory(final String title,
			final Resource avatarRes) {
		this.title = title;
		this.avatarRes = avatarRes;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout userAddLayout = new AddViewLayout(this.title,
				this.avatarRes);

		final Layout topPanel = this.createTopPanel();
		if (topPanel != null) {
			userAddLayout.addTopControls(topPanel);
		}

		this.userInformationLayout = new ProjectMemberInformationLayout();
		this.userInformationLayout.getLayout().setWidth("100%");
		userAddLayout.addBody(this.userInformationLayout.getLayout());

		final Layout bottomPanel = this.createBottomPanel();
		if (bottomPanel != null) {
			userAddLayout.addBottomControls(bottomPanel);
		}

		return userAddLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(final Object propertyId, final Field field) {
		this.userInformationLayout.attachField(propertyId, field);
	}

	public static class ProjectMemberInformationLayout implements
			IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();
			final Label organizationHeader = new Label("Member Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			this.informationLayout = new GridFormLayoutHelper(2, 2, "100%",
					"167px", Alignment.MIDDLE_LEFT);
			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");

			layout.addComponent(this.informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("username")) {
				this.informationLayout.addComponent(field, "User", 0, 0);
			} else if (propertyId.equals("isadmin")) {
				this.informationLayout.addComponent(field, "Is Admin", 0, 1);
			}
		}
	}
}
